package es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.service;

import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.model.Materia;
import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.model.MateriaTutor;
import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.model.Tutor;
import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.model.Usuario;
import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.model.enums.Roles;
import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.port.secondary.ITutorRepository;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.entity.MateriaEntity;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.entity.MateriaTutorEntity;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.entity.TutorEntity;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.entity.UsuarioEntity;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.mapper.TutorEntityMapper;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.repository.MateriaTutorEntityJPARepository;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.repository.RolEntityJPARepository;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.repository.TutorEntityJPARepository;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.repository.UsuarioEntityJPARepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class TutorEntityService implements ITutorRepository {

    @Autowired
    private TutorEntityJPARepository repository;

    @Autowired
    private MateriaTutorEntityJPARepository materiaRepository;

    @Autowired
    private UsuarioEntityJPARepository usuarioRepository;

    @Autowired
    private RolEntityJPARepository rolRepository;

    @Autowired
    private TutorEntityMapper mapper;

    @Override
    @Transactional
    public Tutor create(Usuario usuario, List<Materia> materias) {
        Tutor tutor = new Tutor();
        tutor.setUsuario(usuario);
        tutor.setFechaCreacion(new BigInteger(String.valueOf(new Date().getTime())));
        TutorEntity savedEntity = repository.save(mapper.toEntityPost(tutor));
        List<MateriaTutor> materiasTutor = new ArrayList<>();
        materias.forEach(m -> {
            MateriaTutorEntity materiaTutor = new MateriaTutorEntity();
            materiaTutor.setTutor(savedEntity);
            materiaTutor.setMateria(mapper.toEntity(m));
            materiaTutor = materiaRepository.save(materiaTutor);
            materiasTutor.add(mapper.toDomain(materiaRepository.save(materiaTutor)));
        });
        savedEntity.setMateriasTutor(materiasTutor.stream().map(mt -> mapper.toEntity(mt)).toList());
        UsuarioEntity usuarioEntity = usuarioRepository.getReferenceById(savedEntity.getUsuario().getId());
        usuarioEntity.getRoles().add(rolRepository.findByRol(Roles.ROLE_TUTOR.name()));
        return mapper.toDomain(savedEntity);
    }

    @Override
    @Transactional
    public Tutor update(Usuario usuario, List<Materia> materias) {
        TutorEntity tutor = repository.findByIdUsuario(usuario.getId()).get();
        List<MateriaTutorEntity> materiasTutor = tutor.getMateriasTutor();
        List<MateriaTutorEntity> materiasTutorNuevas = new ArrayList<>();
        List<MateriaEntity> materiasActuales = materiasTutor.stream().map(MateriaTutorEntity::getMateria).toList();
        materias.stream().map(m -> mapper.toEntity(m)).forEach(m -> {
            if(materiasActuales.contains(m)) {
                // Agregamos a la lista materiasTutorNuevas la materiaTutor existente en materiasTutor que coincida con la materia sobre la que iteramos
                materiasTutorNuevas.add(materiasTutor.stream().filter(mt -> mt.getMateria().equals(m)).findFirst().get());
            }else {
                //Creamos una nueva entidad MateriaTutor y la guardamos en bbdd para agregarla a la  nueva lista del tutor
                MateriaTutorEntity materiaTutor = new MateriaTutorEntity();
                materiaTutor.setTutor(tutor);
                materiaTutor.setMateria(m);
                materiasTutorNuevas.add(materiaRepository.save(materiaTutor));
            }
        });
        List<MateriaTutorEntity> materiasTutorEliminar = materiasTutor.stream()
                .filter(mt -> !materiasTutorNuevas.contains(mt)).toList();
        materiaRepository.deleteAll(materiasTutorEliminar);
        tutor.setMateriasTutor(materiasTutorNuevas);
        repository.save(tutor);
        return mapper.toDomain(tutor);
    }

    @Override
    public Tutor findTutorById(Integer id) {
        Optional<TutorEntity> optTutor = repository.findById(id);
        return optTutor.map(a -> mapper.toDomain(a)).orElse(null);
    }

    @Override
    public Tutor findTutorByIdUsuario(Integer id) {
        Optional<TutorEntity> optTutor = repository.findByIdUsuario(id);
        return optTutor.map(a -> mapper.toDomain(a)).orElse(null);
    }

    @Override
    public Tutor findTutorByUsername(String username) {
        Optional<TutorEntity> optTutor = repository.findByUsername(username);
        return optTutor.map(a -> mapper.toDomain(a)).orElse(null);
    }
}
