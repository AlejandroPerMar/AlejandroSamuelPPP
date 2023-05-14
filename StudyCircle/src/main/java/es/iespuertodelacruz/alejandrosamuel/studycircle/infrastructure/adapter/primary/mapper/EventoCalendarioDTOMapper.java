package es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.primary.mapper;

import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.model.EventoCalendario;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.primary.dto.EventoCalendarioDTO;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Objects;

public class EventoCalendarioDTOMapper {

    @Autowired
    private DTOJustIdMapper dtoJustIdMapper;

    @Autowired
    private UsuarioDTOMapper usuarioMapper;

    @Autowired
    private ActividadDTOMapper actividadMapper;

    public EventoCalendario toDomain(EventoCalendarioDTO in) {
        if(Objects.isNull(in)) return null;

        EventoCalendario eventoCalendario = new EventoCalendario();
        eventoCalendario.setNombre(in.getNombre());
        eventoCalendario.setDescripcion(in.getDescripcion());
        eventoCalendario.setFechaEvento(in.getFechaEvento());
        eventoCalendario.setPerfilUsuario(in.getPerfilUsuario());
        return eventoCalendario;
    }

    public EventoCalendarioDTO toDTO(EventoCalendario in) {
        if(Objects.isNull(in)) return null;

        EventoCalendarioDTO eventoCalendario = new EventoCalendarioDTO();
        eventoCalendario.setId(in.getId());
        eventoCalendario.setNombre(in.getNombre());
        eventoCalendario.setDescripcion(in.getDescripcion());
        eventoCalendario.setFechaEvento(in.getFechaEvento());
        eventoCalendario.setPerfilUsuario(in.getPerfilUsuario());
        if(Objects.nonNull(in.getActividad()))
            eventoCalendario.setActividad(actividadMapper.toDTO(in.getActividad()));
        eventoCalendario.setUsuario(usuarioMapper.toDTO(in.getUsuario()));
        return eventoCalendario;

    }
}
