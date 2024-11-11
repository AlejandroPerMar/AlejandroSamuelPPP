package es.iespuertodelacruz.alejandrosamuel.studycircle.domain.service;

import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.model.EventoCalendario;
import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.port.primary.IEventoCalendarioService;
import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.port.secondary.IEventoCalendarioRepository;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.enums.PerfilUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventoCalendarioService implements IEventoCalendarioService {

    @Autowired
    private IEventoCalendarioRepository repository;

    @Override
    public EventoCalendario create(EventoCalendario eventoCalendario) {
        return repository.create(eventoCalendario);
    }

    @Override
    public List<EventoCalendario> findByPerfilUsuario(Integer idUsuario, PerfilUsuario perfilUsuario) {
        return repository.findByPerfilUsuario(idUsuario, perfilUsuario);
    }

    @Override
    public EventoCalendario findById(Integer id) {
        return repository.findById(id);
    }
}
