package es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.mapper;

import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.model.Amistad;
import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.model.Usuario;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.entity.AmistadEntity;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.entity.UsuarioEntity;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.enums.EstadosAmistad;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigInteger;
import java.util.Date;
import java.util.Objects;

public class AmistadEntityMapper {

    @Autowired
    private EntityJustIdMapper entityJustIdMapper;

    public Amistad toDomain(AmistadEntity in) {
        if(Objects.isNull(in)) return null;

        Amistad amistad = new Amistad();
        amistad.setId(in.getId());
        amistad.setUsuario1(toDomain(in.getUsuario1()));
        amistad.setUsuario2(toDomain(in.getUsuario2()));
        amistad.setEstado(in.getEstado());
        return amistad;
    }

    public Usuario toDomain(UsuarioEntity in) {
        if(Objects.isNull(in)) return null;

        Usuario usuario = new Usuario();
        usuario.setId(in.getId());
        usuario.setUsername(in.getUsername());
        usuario.setNombreCompleto(in.getNombreCompleto());
        return usuario;
    }

    public AmistadEntity toEntityPost(Amistad in) {
        if(Objects.isNull(in)) return null;

        AmistadEntity amistad = new AmistadEntity();
        amistad.setUsuario1(entityJustIdMapper.toEntity(in.getUsuario1()));
        amistad.setUsuario2(entityJustIdMapper.toEntity(in.getUsuario2()));
        amistad.setEstado(EstadosAmistad.FRIENDSHIP_REQUESTED.name());
        amistad.setFechaCreacion(new BigInteger(String.valueOf(new Date().getTime())));
        return amistad;
    }
}
