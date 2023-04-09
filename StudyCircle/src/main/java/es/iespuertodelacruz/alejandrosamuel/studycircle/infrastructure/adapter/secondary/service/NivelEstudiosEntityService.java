package es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.service;

import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.model.NivelEstudios;
import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.port.secondary.INivelEstudiosRepository;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.entity.NivelEstudiosEntity;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.mapper.NivelEstudiosEntityMapper;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.repository.NivelEstudiosEntityJPARepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class NivelEstudiosEntityService implements INivelEstudiosRepository {

    @Autowired
    private NivelEstudiosEntityJPARepository repository;

    @Autowired
    private NivelEstudiosEntityMapper mapper;

    @Override
    public NivelEstudios findById(Integer id) {
        Optional<NivelEstudiosEntity> optNivelEstudios = repository.findById(id);
        return optNivelEstudios.map(n -> mapper.toDomain(n)).orElse(null);
    }

    @Override
    public NivelEstudios findByNombre(String nombre) {
        Optional<NivelEstudiosEntity> optNivelEstudios = repository.findByNombre(nombre);
        return optNivelEstudios.map(n -> mapper.toDomain(n)).orElse(null);
    }

    @Override
    public NivelEstudios create(NivelEstudios nivelEstudios) {
        NivelEstudiosEntity nivelEstudiosEntity = repository.save(mapper.toEntityPost(nivelEstudios));
        return mapper.toDomain(nivelEstudiosEntity);
    }

    @Override
    public NivelEstudios update(NivelEstudios nivelEstudios) {
        Optional<NivelEstudiosEntity> optNivelEstudios = repository.findById(nivelEstudios.getId());

        if(optNivelEstudios.isEmpty())
            return null;

        NivelEstudiosEntity nivelEstudiosEntity = optNivelEstudios.get();
        NivelEstudiosEntity nivelEstudiosActualizado = mapper.toEntityPut(nivelEstudios);

        nivelEstudiosEntity.setNombre(nivelEstudiosActualizado.getNombre());
        nivelEstudiosEntity.setMaterias(nivelEstudiosActualizado.getMaterias());

        return mapper.toDomain(repository.save(nivelEstudiosEntity));
    }

    @Override
    public List<NivelEstudios> findAll() {
        return repository.findAll().stream().map(n -> mapper.toDomain(n)).collect(Collectors.toList());
    }
}
