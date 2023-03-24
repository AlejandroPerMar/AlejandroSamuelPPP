package es.iespuertodelacruz.alejandrosamuel.studycircle.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.model.Usuario;
import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.port.primary.IUsuarioService;
import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.port.secondary.IUsuarioRepository;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.primary.UserDTO;

@Service
public class UsuarioService implements IUsuarioService{

	@Autowired
	private IUsuarioRepository usuarioRepository;
	
	@Override
	public boolean checkCredentials(String username, String pass) {
		return usuarioRepository.checkPassword(username, pass);
		
	}

	@Override
	public Usuario create(UserDTO user) {
		return usuarioRepository.create(user);
	}

	@Override
	public Usuario findByUsername(String username) {
		return usuarioRepository.findByUsername(username);
	}

}
