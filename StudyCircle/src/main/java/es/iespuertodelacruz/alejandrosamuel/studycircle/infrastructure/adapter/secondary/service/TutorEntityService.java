package es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.service;

import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.model.Materia;
import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.model.Tutor;
import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.port.secondary.ITutorRepository;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.entity.TutorEntity;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.mapper.TutorEntityMapper;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.repository.MateriaTutorJPARepository;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.repository.TutorEntityJPARepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class TutorEntityService implements ITutorRepository {

    @Autowired
    private TutorEntityJPARepository repository;

    @Autowired
    private MateriaTutorJPARepository materiaRepository;

    @Autowired
    private TutorEntityMapper mapper;

    @Override
    public Tutor create(Tutor tutor, List<Materia> materias) {
        tutor.setFechaCreacion(new BigInteger(new Date().getTime() + ""));
        TutorEntity savedEntity = repository.save(mapper.toEntityPost(tutor));
        return mapper.toDomain(savedEntity);
    }

    @Override
    public Tutor update(Tutor tutor) {
        TutorEntity updatedEntity = repository.save(mapper.toEntityPut(tutor));
        return mapper.toDomain(updatedEntity);
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
