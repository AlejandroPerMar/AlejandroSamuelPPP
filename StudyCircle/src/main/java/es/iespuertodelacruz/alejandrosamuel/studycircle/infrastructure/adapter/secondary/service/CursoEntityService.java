package es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.service;

import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.model.Alumno;
import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.model.Curso;
import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.port.secondary.ICursoRepository;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.entity.AlertaActividadEntity;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.entity.AlumnoEntity;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.entity.CursoEntity;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.entity.EventoCalendarioEntity;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.mapper.CursoEntityMapper;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.mapper.EntityJustIdMapper;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.repository.AlertaActividadEntityJPARepository;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.repository.AlumnoEntityJPARepository;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.repository.CursoEntityJPARepository;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.repository.EventoCalendarioEntityJPARepository;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.enums.PerfilUsuario;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
public class CursoEntityService implements ICursoRepository {

    @Autowired
    private CursoEntityJPARepository repository;

    @Autowired
    private AlumnoEntityJPARepository alumnoRepository;

    @Autowired
    private CursoEntityMapper mapper;

    @Autowired
    private EntityJustIdMapper entityJustIdMapper;

    @Autowired
    private EventoCalendarioEntityJPARepository eventoCalendarioRepository;

    @Autowired
    private AlertaActividadEntityJPARepository alertaActividadRepository;

    @Override
    public Curso findById(Integer id) {
        CursoEntity byId = repository.findById(id).orElse(null);
        return mapper.toDomain(byId);
    }

    @Override
    public List<Curso> findByIdTutor(Integer id) {
        return repository.findByIdTutor(id).stream().map(mapper::toDomain).toList();
    }

    @Override
    public List<Curso> findByAlumno(Integer id) {
        return repository.findByAlumno(id).stream().map(mapper::toDomain).toList();
    }

    @Override
    @Transactional
    public Curso create(Curso curso) {
        CursoEntity cursoEntity = new CursoEntity();
        cursoEntity.setTitulo(curso.getTitulo());
        cursoEntity.setFechaCreacion(new BigInteger(String.valueOf(new Date().getTime())));
        cursoEntity.setMateriaTutor(entityJustIdMapper.toEntity(curso.getMateriaTutor()));
        cursoEntity = repository.save(cursoEntity);

        return mapper.toDomain(cursoEntity);
    }

    @Override
    public Curso changeTituloCurso(Integer idCurso, String titulo) {
        CursoEntity cursoEntity = repository.findById(idCurso).orElse(null);
        if(Objects.nonNull(cursoEntity)) {
            cursoEntity.setTitulo(titulo);
            cursoEntity = repository.save(cursoEntity);
            return mapper.toDomain(cursoEntity);
        }
        return null;
    }

    @Override
    public Curso update(Curso curso) {
        return null;
    }

    @Override
    public boolean delete(Integer id) {
        return false;
    }

    @Override
    @Transactional
    public void removeAlumnoFromCurso(Curso curso, Alumno alumno) {
        CursoEntity cursoEntity = repository.findById(curso.getId()).orElse(null);
        AlumnoEntity alumnoEntity = Objects.requireNonNull(cursoEntity).getAlumnos().stream()
                .filter(a -> a.getId().equals(alumno.getId())).findFirst().orElse(null);
        if(Objects.nonNull(alumnoEntity)) {
            cursoEntity.getAlumnos().remove(alumnoEntity);
            repository.save(cursoEntity);
            cursoEntity.getActividades().forEach(a -> {
                List<AlertaActividadEntity> alertasActividadEntity =
                        alertaActividadRepository.findByActividadUsuario(a.getId(), alumnoEntity.getUsuario().getId());
                alertaActividadRepository.deleteAll(alertasActividadEntity);
            });
            List<EventoCalendarioEntity> eventosCalendarioEntity =
                    eventoCalendarioRepository.findByUsuarioAndCurso(alumnoEntity.getUsuario().getId(), cursoEntity.getId());
            eventoCalendarioRepository.deleteAll(eventosCalendarioEntity);
        }
    }

    @Override
    @Transactional
    public void addAlumnoToCurso(Curso curso, Alumno alumno) {
        CursoEntity cursoEntity = repository.findById(curso.getId()).orElse(null);
        AlumnoEntity alumnoEntity = alumnoRepository.findById(alumno.getId()).orElse(null);
        if(ObjectUtils.notNullNorEmpty(cursoEntity, alumnoEntity)) {
            if(cursoEntity.getAlumnos().stream().
                    filter(a -> a.getId().equals(alumnoEntity.getId())).findFirst().isEmpty()) {
                List<AlumnoEntity> alumnos = cursoEntity.getAlumnos();
                alumnos.add(alumnoEntity);
                cursoEntity.setAlumnos(alumnos);
                cursoEntity.getActividades().forEach(a -> {
                        EventoCalendarioEntity eventoCalendarioEntity = new EventoCalendarioEntity();
                        eventoCalendarioEntity.setFechaEvento(a.getFechaActividad());
                        eventoCalendarioEntity.setDescripcion(a.getDescripcion());
                        eventoCalendarioEntity.setNombre(a.getNombre());
                        eventoCalendarioEntity.setPerfilUsuario(PerfilUsuario.STUDENT_PROFILE.getPerfilUsuario());
                        eventoCalendarioEntity.setFechaCreacion(new BigInteger(String.valueOf(new Date().getTime())));
                        eventoCalendarioEntity.setActividad(a);
                        eventoCalendarioEntity.setUsuario(alumnoEntity.getUsuario());
                        eventoCalendarioRepository.save(eventoCalendarioEntity);
                });
            }
        }
    }
}
