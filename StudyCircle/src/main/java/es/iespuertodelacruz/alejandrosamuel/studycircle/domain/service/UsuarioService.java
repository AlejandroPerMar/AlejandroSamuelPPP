package es.iespuertodelacruz.alejandrosamuel.studycircle.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.model.Usuario;
import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.port.primary.IUsuarioService;
import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.port.secondary.IUsuarioRepository;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.primary.dto.UsuarioDTO;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.primary.dto.UsuarioRegisterDTO;

@Service
public class UsuarioService implements IUsuarioService {
	
	@Autowired
	private IUsuarioRepository usuarioRepository;

	@Override
	public boolean checkCredentials(String name, String pass) {
		return usuarioRepository.checkPassword(name, pass);
	}

	@Override
	public Usuario create(String nombreCompleto, String username, String email, String clave) {
		return usuarioRepository.create(nombreCompleto, username, email, clave);
	}

	@Override
	public Usuario findByUsername(String username) {
		return usuarioRepository.findByUsername(username);
	}

}
