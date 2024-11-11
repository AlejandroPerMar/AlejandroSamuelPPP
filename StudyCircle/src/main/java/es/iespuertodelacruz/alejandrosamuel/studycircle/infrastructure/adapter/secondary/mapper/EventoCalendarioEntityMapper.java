package es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.mapper;

import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.model.EventoCalendario;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.entity.EventoCalendarioEntity;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigInteger;
import java.util.Date;
import java.util.Objects;

public class EventoCalendarioEntityMapper {

    @Autowired
    private EntityJustIdMapper entityJustIdMapper;

    @Autowired
    private ActividadEntityMapper actividadMapper;

    @Autowired
    private UsuarioEntityMapper usuarioMapper;

    public EventoCalendarioEntity toEntity(EventoCalendario in) {
        if(Objects.isNull(in)) return null;

        EventoCalendarioEntity eventoCalendario = new EventoCalendarioEntity();
        eventoCalendario.setNombre(in.getNombre());
        eventoCalendario.setDescripcion(in.getDescripcion());
        eventoCalendario.setFechaEvento(in.getFechaEvento());
        eventoCalendario.setPerfilUsuario(in.getPerfilUsuario());
        eventoCalendario.setFechaCreacion(new BigInteger(String.valueOf(new Date().getTime())));
        eventoCalendario.setUsuario(entityJustIdMapper.toEntity(in.getUsuario()));
        return eventoCalendario;
    }

    public EventoCalendario toDomain(EventoCalendarioEntity in) {
        if(Objects.isNull(in)) return null;

        EventoCalendario eventoCalendario = new EventoCalendario();
        eventoCalendario.setId(in.getId());
        eventoCalendario.setNombre(in.getNombre());
        eventoCalendario.setDescripcion(in.getDescripcion());
        eventoCalendario.setFechaEvento(in.getFechaEvento());
        eventoCalendario.setPerfilUsuario(in.getPerfilUsuario());
        eventoCalendario.setFechaCreacion(new BigInteger(String.valueOf(new Date().getTime())));
        if(Objects.nonNull(in.getActividad()))
            eventoCalendario.setActividad(actividadMapper.toDomain(in.getActividad()));
        eventoCalendario.setUsuario(usuarioMapper.toDomain(in.getUsuario()));
        return eventoCalendario;
    }
}
