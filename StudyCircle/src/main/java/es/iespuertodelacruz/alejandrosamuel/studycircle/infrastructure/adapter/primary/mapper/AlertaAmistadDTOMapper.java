package es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.primary.mapper;

import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.model.AlertaAmistad;
import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.model.Amistad;
import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.model.Usuario;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.primary.dto.AlertaAmistadDTO;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.primary.dto.AmistadDTO;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.primary.dto.UsuarioDTO;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Objects;

public class AlertaAmistadDTOMapper {

    @Autowired
    private DTOJustIdMapper dtoJustIdMapper;

    public AlertaAmistad toDomain(AlertaAmistadDTO in) {
        if(Objects.isNull(in)) return null;

        AlertaAmistad alerta = new AlertaAmistad();
        alerta.setEstado(in.getEstado());
        alerta.setAmistad(dtoJustIdMapper.toDomain(in.getAmistad()));
        alerta.setFechaCreacion(in.getFechaCreacion());
        alerta.setUsuario(dtoJustIdMapper.toDomain(in.getUsuario()));

        return alerta;
    }

    private UsuarioDTO toDTO(Usuario in) {
        if(Objects.isNull(in)) return null;

        UsuarioDTO usuario = new UsuarioDTO();
        usuario.setId(in.getId());
        usuario.setNombreCompleto(in.getNombreCompleto());
        usuario.setUsername(in.getUsername());
        return usuario;
    }

    AmistadDTO toDTO(Amistad in) {
        if(Objects.isNull(in)) return null;

        AmistadDTO amistad = new AmistadDTO();
        amistad.setId(in.getId());
        amistad.setUsuario1(toDTO(in.getUsuario1()));

        return amistad;
    }

    public AlertaAmistadDTO toDTO(AlertaAmistad in) {
        if(Objects.isNull(in)) return null;

        AlertaAmistadDTO alerta = new AlertaAmistadDTO();
    	alerta.setId(in.getId());
    	alerta.setEstado(in.getEstado());
        alerta.setAmistad(toDTO(in.getAmistad()));
        alerta.setFechaCreacion(in.getFechaCreacion());

        return alerta;
    }
    
}