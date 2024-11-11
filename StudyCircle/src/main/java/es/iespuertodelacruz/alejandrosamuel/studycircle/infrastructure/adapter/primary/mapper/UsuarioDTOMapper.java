package es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.primary.mapper;

import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.model.Usuario;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.primary.dto.UsuarioDTO;

import java.util.Objects;

public class UsuarioDTOMapper {

    public UsuarioDTO toDTO(Usuario in) {
        if(Objects.isNull(in))
            return null;

        UsuarioDTO usuario = new UsuarioDTO();
        usuario.setId(in.getId());
        usuario.setNombreCompleto(in.getNombreCompleto());
        usuario.setUsername(in.getUsername());
        usuario.setEmail(in.getEmail());
        return usuario;
    }

}