package es.iespuertodelacruz.alejandrosamuel.studycircle.domain.port.secondary;

import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.model.EventoCalendario;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.enums.PerfilUsuario;

import java.util.List;

public interface IEventoCalendarioRepository {

    List<EventoCalendario> findByPerfilUsuario(Integer idUsuario, PerfilUsuario perfilUsuario);

    EventoCalendario create(EventoCalendario eventoCalendario);

    EventoCalendario findById(Integer id);
}
