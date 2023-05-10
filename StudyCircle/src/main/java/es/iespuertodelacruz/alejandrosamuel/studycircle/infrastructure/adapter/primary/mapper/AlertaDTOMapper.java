package es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.primary.mapper;

import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.model.Usuario;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.primary.dto.UsuarioDTO;
import org.springframework.stereotype.Component;

import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.model.Alerta;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.primary.dto.AlertaDTO;

@Component
public class AlertaDTOMapper {
	
    public Alerta toDomain(AlertaDTO in) {
    	Alerta alerta = new Alerta();

        alerta.setEstado(in.getEstado());
        alerta.setTipo(in.getTipo());
        alerta.setMensaje(in.getMensaje());
        alerta.setFechaCreacion(in.getFechaCreacion());
        alerta.setUsuario(toDomain(in.getUsuario()));

        return alerta;
    }

    private Usuario toDomain(UsuarioDTO in) {
        Usuario usuario = new Usuario();
        usuario.setId(in.getId());
        return usuario;
    }

    public AlertaDTO toDTO(Alerta in) {
    	AlertaDTO alerta = new AlertaDTO();

    	alerta.setId(in.getId());
    	alerta.setEstado(in.getEstado());
        alerta.setTipo(in.getTipo());
        alerta.setMensaje(in.getMensaje());
        alerta.setFechaCreacion(in.getFechaCreacion());

        return alerta;
    }
    
}