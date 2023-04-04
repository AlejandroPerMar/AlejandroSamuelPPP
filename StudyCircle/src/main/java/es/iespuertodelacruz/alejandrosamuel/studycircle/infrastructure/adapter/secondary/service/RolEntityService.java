package es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.model.Rol;
import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.port.secondary.IRolRepository;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.entity.RolEntity;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.mapper.RolEntityMapper;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.repository.RolEntityJPARepository;

@Service
public class RolEntityService implements IRolRepository {
	@Autowired
	private RolEntityJPARepository rolRepository;

	@Override
	public Rol findByRol(String rol) {
		RolEntity rolEntity = rolRepository.findByRol(rol);
		RolEntityMapper mapper = new RolEntityMapper();
		return mapper.toDomain(rolEntity);
	}
}
