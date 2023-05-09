package es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.service;

import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.model.Amistad;
import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.port.secondary.IAmistadRepository;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.mapper.AmistadEntityMapper;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.repository.AmistadEntityJPARepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AmistadEntityService implements IAmistadRepository {

    @Autowired
    private AmistadEntityJPARepository repository;

    @Autowired
    private AmistadEntityMapper mapper;

    @Override
    public Amistad create(Amistad amistad) {
        return null;
    }

    @Override
    public Amistad accept(Amistad amistad) {
        return null;
    }

    @Override
    public Amistad remove(Amistad amistad) {
        return null;
    }

    @Override
    public Amistad findByUsuarioId(Integer idUsuario) {
        return null;
    }
}
