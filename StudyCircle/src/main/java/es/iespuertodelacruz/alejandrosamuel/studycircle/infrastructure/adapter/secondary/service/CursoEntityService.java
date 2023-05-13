package es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.service;

import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.model.Alumno;
import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.model.Curso;
import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.port.secondary.ICursoRepository;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.entity.AlumnoEntity;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.entity.CursoEntity;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.mapper.CursoEntityMapper;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.mapper.EntityJustIdMapper;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.repository.AlumnoEntityJPARepository;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.repository.CursoEntityJPARepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
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
        cursoEntity.setAlumnos(curso.getAlumnos().stream().map(entityJustIdMapper::toEntity).toList());
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
    public void removeAlumnoFromCurso(Curso curso, Alumno alumno) {
        CursoEntity cursoEntity = repository.findById(curso.getId()).orElse(null);
        AlumnoEntity alumnoEntity = Objects.requireNonNull(cursoEntity).getAlumnos().stream()
                .filter(a -> a.getId().equals(alumno.getId())).findFirst().orElse(null);
        if(Objects.nonNull(alumnoEntity)) {
            cursoEntity.getAlumnos().remove(alumnoEntity);
            repository.save(cursoEntity);
        }
    }

    @Override
    public void addAlumnoFromCurso(Curso curso, Alumno alumno) {
        CursoEntity cursoEntity = repository.findById(curso.getId()).orElse(null);
        AlumnoEntity alumnoEntity = alumnoRepository.findById(alumno.getId()).orElse(null);
        if(Objects.nonNull(alumnoEntity)) {
            if(Objects.requireNonNull(cursoEntity).getAlumnos().stream().
                    filter(a -> a.getId().equals(alumnoEntity.getId())).findFirst().isEmpty()) {
                Objects.requireNonNull(cursoEntity).getAlumnos().add(alumnoEntity);
                repository.save(cursoEntity);
            }
        }
    }
}
