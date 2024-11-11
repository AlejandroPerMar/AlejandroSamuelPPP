package es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.mapper;

import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.model.Rol;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.entity.RolEntity;

public class RolEntityMapper {
	
	public Rol toDomain(RolEntity in) {
		Rol rol = new Rol();
		rol.setId(in.getId());
		rol.setRol(in.getRol());
		return rol;
	}
}
