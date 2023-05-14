package es.iespuertodelacruz.alejandrosamuel.studycircle.domain.port.primary;

import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.model.EventoCalendario;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.enums.PerfilUsuario;

import java.util.List;

public interface IEventoCalendarioService {

    EventoCalendario create(EventoCalendario eventoCalendario);

    List<EventoCalendario> findByPerfilUsuario(Integer idUsuario, PerfilUsuario perfilUsuario);

    EventoCalendario findById(Integer id);
}
