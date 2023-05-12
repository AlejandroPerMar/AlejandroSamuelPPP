package es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.service;

import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.model.MateriaTutor;
import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.port.secondary.IMateriaTutorRepository;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.entity.MateriaTutorEntity;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.mapper.MateriaTutorEntityMapper;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.repository.MateriaTutorEntityJPARepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MateriaTutorEntityService implements IMateriaTutorRepository {

    @Autowired
    private MateriaTutorEntityJPARepository repository;

    @Autowired
    private MateriaTutorEntityMapper mapper;

    @Override
    public MateriaTutor findByMateriaTutor(Integer idMateria, Integer idTutor) {
        MateriaTutorEntity materiaTutorEntity = repository.findByMateriaTutor(idMateria, idTutor).orElse(null);
        return mapper.toDomain(materiaTutorEntity);
    }
}
