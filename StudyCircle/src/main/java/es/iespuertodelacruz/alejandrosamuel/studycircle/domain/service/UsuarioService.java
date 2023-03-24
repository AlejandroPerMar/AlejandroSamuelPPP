package es.iespuertodelacruz.alejandrosamuel.studycircle.domain.service;

import org.springframework.beans.factory.annotation.Autowired;

import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.model.Usuario;
import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.port.primary.IUsuarioService;
import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.port.secondary.IUsuarioRepository;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.primary.dto.UsuarioDTO;

public class UsuarioService implements IUsuarioService {
	
	@Autowired
	private IUsuarioRepository usuarioRepository;

	@Override
	public boolean checkCredentials(String name, String pass) {
		return usuarioRepository.checkPassword(name, pass);
	}

	@Override
	public Usuario create(UsuarioDTO user) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Usuario findByUsername(String username) {
		// TODO Auto-generated method stub
		return null;
	}

}
