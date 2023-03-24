package es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.service;

import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.entities.UsuarioEntity;
import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.model.Usuario;


public class EntityUsuarioMapper {

	public Usuario toDomain(UsuarioEntity in) {
		Usuario u = new Usuario();
		// Asignar atributos
		return u;
	}
	
	public UsuarioEntity toEntity(Usuario in) {
		UsuarioEntity u = new UsuarioEntity();
		// Asignar atributos
		return u;
	}	
}
