package es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.mapper;

import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.model.Rol;
import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.model.Usuario;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.entity.RolEntity;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.entity.UsuarioEntity;

public class UsuarioLoginEntityMapper {

	public Usuario toDomain(UsuarioEntity in) {
		Usuario usuario = new Usuario();
		usuario.setUsername(in.getUsername());
		usuario.setHashpswd(in.getHashpswd());
		usuario.setEmail(in.getEmail());
		usuario.setEstado(in.getEstado());
		usuario.setRoles(in.getRoles().stream().map(r -> toDomain(r)).toList());
		return usuario;
	}
	
	public UsuarioEntity toEntity(Usuario in) {
		return null;
	}
	
	public Rol toDomain(RolEntity in) {
		Rol rol = new Rol();
		rol.setRol(in.getRol());
		return rol;
	}
}
