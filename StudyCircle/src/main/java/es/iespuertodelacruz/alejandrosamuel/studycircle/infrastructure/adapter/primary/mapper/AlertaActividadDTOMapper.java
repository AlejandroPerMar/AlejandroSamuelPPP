package es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.primary.mapper;

import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.model.Actividad;
import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.model.AlertaActividad;
import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.model.Usuario;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.primary.dto.ActividadDTO;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.primary.dto.AlertaActividadDTO;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.primary.dto.UsuarioDTO;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Objects;

public class AlertaActividadDTOMapper {

    @Autowired
    private DTOJustIdMapper dtoJustIdMapper;
	
    public AlertaActividad toDomain(AlertaActividadDTO in) {
        if(Objects.isNull(in)) return null;

    	AlertaActividad alerta = new AlertaActividad();
        alerta.setEstado(in.getEstado());
        alerta.setActividad(dtoJustIdMapper.toDomain(in.getActividad()));
        alerta.setFechaCreacion(in.getFechaCreacion());
        alerta.setUsuario(toDomain(in.getUsuario()));

        return alerta;
    }

    private ActividadDTO toDTO(Actividad in) {
        if(Objects.isNull(in)) return null;

        ActividadDTO actividad = new ActividadDTO();
        actividad.setId(in.getId());
        actividad.setNombre(in.getNombre());
        actividad.setFechaActividad(in.getFechaActividad());
        return actividad;
    }

    private Usuario toDomain(UsuarioDTO in) {
        if(Objects.isNull(in)) return null;

        Usuario usuario = new Usuario();
        usuario.setId(in.getId());
        return usuario;
    }

    public AlertaActividadDTO toDTO(AlertaActividad in) {
        if(Objects.isNull(in)) return null;

    	AlertaActividadDTO alerta = new AlertaActividadDTO();
    	alerta.setId(in.getId());
    	alerta.setEstado(in.getEstado());
        alerta.setActividad(toDTO(in.getActividad()));
        alerta.setFechaCreacion(in.getFechaCreacion());

        return alerta;
    }
    
}