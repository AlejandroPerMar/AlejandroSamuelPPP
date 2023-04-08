package es.iespuertodelacruz.alejandrosamuel.studycircle.domain.service;

import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.model.NivelEstudios;
import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.port.primary.INivelEstudiosService;
import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.port.secondary.INivelEstudiosRepository;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.primary.dto.NivelEstudiosDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NivelEstudiosService implements INivelEstudiosService {

    @Autowired
    private INivelEstudiosRepository repository;

    @Override
    public NivelEstudios findById(Integer id) {
        return repository.findById(id);
    }

    @Override
    public NivelEstudios findByNombre(String nombre) {
        return repository.findByNombre(nombre);
    }

    @Override
    public NivelEstudios create(NivelEstudios nivelEstudios) {
        return repository.create(nivelEstudios);
    }

    @Override
    public NivelEstudios update(NivelEstudios nivelEstudios) {
        return repository.update(nivelEstudios);
    }

    @Override
    public List<NivelEstudios> findAll() {
        return repository.findAll();
    }
}
