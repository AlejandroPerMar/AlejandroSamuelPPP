package es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.service;

import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.model.NivelEstudios;
import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.port.secondary.INivelEstudiosRepository;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.primary.dto.NivelEstudiosDTO;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.entity.NivelEstudiosEntity;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.mapper.NivelEstudiosPostEntityMapper;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.repository.NivelEstudiosEntityJPARepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NivelEstudiosEntityService implements INivelEstudiosRepository {

    @Autowired
    private NivelEstudiosEntityJPARepository repository;

    @Override
    public NivelEstudios findById(Integer id) {
        return null;
    }

    @Override
    public NivelEstudios findByNombre(String nombre) {
        return null;
    }

    @Override
    public NivelEstudios create(NivelEstudios nivelEstudios) {
        NivelEstudiosPostEntityMapper mapper = new NivelEstudiosPostEntityMapper();

        NivelEstudiosEntity nivelEstudiosEntity = repository.save(mapper.toEntity(nivelEstudios));

        return mapper.toDomain(nivelEstudiosEntity);
    }

    @Override
    public NivelEstudios update(NivelEstudios nivelEstudios) {
        return null;
    }

    @Override
    public List<NivelEstudios> findAll() {
        return null;
    }
}
