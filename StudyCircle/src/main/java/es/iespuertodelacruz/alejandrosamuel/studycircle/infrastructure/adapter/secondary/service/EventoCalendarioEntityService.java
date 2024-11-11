package es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.service;

import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.model.EventoCalendario;
import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.port.secondary.IEventoCalendarioRepository;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.entity.EventoCalendarioEntity;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.mapper.EventoCalendarioEntityMapper;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.repository.EventoCalendarioEntityJPARepository;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.enums.PerfilUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventoCalendarioEntityService implements IEventoCalendarioRepository {

    @Autowired
    private EventoCalendarioEntityJPARepository repository;

    @Autowired
    private EventoCalendarioEntityMapper mapper;

    @Override
    public List<EventoCalendario> findByPerfilUsuario(Integer idUsuario, PerfilUsuario perfilUsuario) {
        return repository.findByPerfilUsuario(idUsuario, perfilUsuario.name()).stream().map(mapper::toDomain).toList();
    }

    @Override
    public EventoCalendario create(EventoCalendario eventoCalendario) {
        EventoCalendarioEntity eventoCalendarioEntity = mapper.toEntity(eventoCalendario);
        eventoCalendarioEntity = repository.save(eventoCalendarioEntity);
        return mapper.toDomain(eventoCalendarioEntity);
    }

    @Override
    public EventoCalendario findById(Integer id) {
        return mapper.toDomain(repository.findById(id).orElse(null));
    }
}
