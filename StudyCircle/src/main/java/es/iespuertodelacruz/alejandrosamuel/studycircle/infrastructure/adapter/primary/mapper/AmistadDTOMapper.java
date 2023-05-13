package es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.primary.mapper;

import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.model.Amistad;
import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.model.Usuario;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.primary.dto.AmistadDTO;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.primary.dto.UsuarioDTO;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Objects;

public class AmistadDTOMapper {

    @Autowired
    private DTOJustIdMapper dtoJustIdMapper;

    public Amistad toDomain(AmistadDTO in) {
        if(Objects.isNull(in)) return null;

        Amistad amistad = new Amistad();
        amistad.setUsuario1(dtoJustIdMapper.toDomain(in.getUsuario1()));
        amistad.setUsuario2(dtoJustIdMapper.toDomain(in.getUsuario2()));
        return amistad;
    }

    public AmistadDTO toDTO(Amistad in) {
        if(Objects.isNull(in)) return null;

        AmistadDTO amistad = new AmistadDTO();
        amistad.setId(in.getId());
        amistad.setUsuario1(toDTO(in.getUsuario1()));
        amistad.setUsuario2(toDTO(in.getUsuario2()));
        amistad.setEstado(in.getEstado());
        return amistad;
    }

    public UsuarioDTO toDTO(Usuario in) {
        if(Objects.isNull(in)) return null;

        UsuarioDTO usuario = new UsuarioDTO();
        usuario.setId(in.getId());
        usuario.setUsername(in.getUsername());
        usuario.setNombreCompleto(in.getNombreCompleto());
        return usuario;
    }
}
