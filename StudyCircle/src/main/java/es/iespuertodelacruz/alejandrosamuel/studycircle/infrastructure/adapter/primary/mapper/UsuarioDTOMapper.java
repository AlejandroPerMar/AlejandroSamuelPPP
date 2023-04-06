package es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.primary.mapper;

import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.model.Usuario;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.primary.dto.UsuarioDTO;

public class UsuarioDTOMapper {
    public Usuario toDomain(UsuarioDTO in) {
        if(in == null)
            return null;

        Usuario usuario = new Usuario();
        usuario.setId(in.getId());
        return usuario;
    }

    public UsuarioDTO toDTO(Usuario in) {
        if(in == null)
            return null;

        UsuarioDTO usuario = new UsuarioDTO();
        usuario.setId(in.getId());
        usuario.setNombreCompleto(in.getNombreCompleto());
        usuario.setUsername(in.getUsername());
        usuario.setEmail(in.getEmail());
        return usuario;
    }
}
