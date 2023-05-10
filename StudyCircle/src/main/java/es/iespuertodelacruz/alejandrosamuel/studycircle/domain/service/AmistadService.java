package es.iespuertodelacruz.alejandrosamuel.studycircle.domain.service;

import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.model.Amistad;
import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.port.primary.IAmistadService;
import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.port.secondary.IAmistadRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class AmistadService implements IAmistadService {

    @Autowired
    private IAmistadRepository repository;

    @Override
    public Amistad create(Amistad amistad) {
        return repository.create(amistad);
    }

    @Override
    public Amistad accept(Amistad amistad) {
        return repository.accept(amistad);
    }

    @Override
    public Amistad findById(Integer id) {
        return repository.findById(id);
    }

    @Override
    public Amistad remove(Amistad amistad) {
        return repository.remove(amistad);
    }

    @Override
    public Amistad findByUsuarioId(Integer idUsuario) {
        return repository.findByUsuarioId(idUsuario);
    }

    @Override
    public Amistad findAmistadByIds(Integer id1, Integer id2) {
        return repository.findAmistadByIds(id1, id2);
    }

}
