package es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.mapper;

import java.util.Collection;
import java.util.Objects;
import java.util.Optional;

import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.model.Usuario;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.entity.UsuarioEntity;

public class UsuarioEntityMapper {
	
	public Usuario toDomain(UsuarioEntity in) {
		if(Objects.isNull(in)) return null;

		Usuario usuario = new Usuario();
		usuario.setId(in.getId());
		usuario.setNombreCompleto(in.getNombreCompleto());
		usuario.setEmail(in.getEmail());
		usuario.setUsername(in.getUsername());
		usuario.setEstado(in.getEstado());
		usuario.setFechaCreacion(in.getFechaCreacion());
		usuario.setHashpswd(in.getHashpswd());
		RolEntityMapper rolMapper = new RolEntityMapper();
		usuario.setRoles(Optional.ofNullable(in.getRoles()).stream().flatMap(Collection::stream)
				.map(rolMapper::toDomain)
				.toList());
		return usuario;
	}
	
	public UsuarioEntity toEntity(Usuario in) {
		if(Objects.isNull(in)) return null;

		UsuarioEntity usuario = new UsuarioEntity();
		usuario.setId(in.getId());
		usuario.setNombreCompleto(in.getNombreCompleto());
		usuario.setEmail(in.getEmail());
		usuario.setUsername(in.getUsername());

		return usuario;
	}
}
