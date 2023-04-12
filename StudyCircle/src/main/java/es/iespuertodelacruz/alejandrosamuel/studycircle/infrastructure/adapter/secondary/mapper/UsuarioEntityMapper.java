package es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.mapper;

import java.util.stream.Collectors;

import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.model.Usuario;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.entity.UsuarioEntity;

public class UsuarioEntityMapper {
	
	public Usuario toDomain(UsuarioEntity in) {
		Usuario usuario = new Usuario();
		usuario.setId(in.getId());
		usuario.setNombreCompleto(in.getNombreCompleto());
		usuario.setEmail(in.getEmail());
		usuario.setUsername(in.getUsername());
		usuario.setEstado(in.getEstado());
		usuario.setFechaCreacion(in.getFechaCreacion());
		usuario.setHashpswd(in.getHashpswd());
		RolEntityMapper rolMapper = new RolEntityMapper();
		usuario.setRoles(in.getRoles().stream().map(rolMapper::toDomain).collect(Collectors.toList()));
		return usuario;
	}
	
	public UsuarioEntity toEntity(Usuario in) {
		UsuarioEntity usuario = new UsuarioEntity();
		usuario.setId(in.getId());
		usuario.setNombreCompleto(in.getNombreCompleto());
		usuario.setEmail(in.getEmail());
		usuario.setUsername(in.getUsername());

		return usuario;
	}
}
