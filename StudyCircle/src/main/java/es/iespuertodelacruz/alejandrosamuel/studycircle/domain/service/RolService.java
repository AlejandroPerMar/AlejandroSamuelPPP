package es.iespuertodelacruz.alejandrosamuel.studycircle.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.model.Rol;
import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.port.primary.IRolService;
import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.port.secondary.IRolRepository;

@Service
public class RolService implements IRolService {
	
	@Autowired
	private IRolRepository rolRepository;
	
	@Override
	public Rol findByRol(String rol) {
		return rolRepository.findByRol(rol);
	}
}
