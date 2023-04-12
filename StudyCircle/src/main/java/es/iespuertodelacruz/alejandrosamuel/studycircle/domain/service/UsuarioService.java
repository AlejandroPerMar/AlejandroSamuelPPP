package es.iespuertodelacruz.alejandrosamuel.studycircle.domain.service;

import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.entity.TokenConfirmacionEntity;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.service.TokenConfirmacionEntityService;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.service.UsuarioEntityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.model.Usuario;
import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.port.primary.IUsuarioService;
import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.port.secondary.IUsuarioRepository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.util.Date;
import java.util.Optional;

@Service
public class UsuarioService implements IUsuarioService {
	
	@Autowired
	private IUsuarioRepository usuarioRepository;

	@Autowired
	private UsuarioEntityService usuarioEntityService;

	@Autowired
	private TokenConfirmacionEntityService tokenService;

	@Override
	public boolean checkCredentials(String name, String pass) {
		return usuarioRepository.checkPassword(name, pass);
	}

	@Override
	public TokenConfirmacionEntity create(String nombreCompleto, String username, String email, String clave) {
		return usuarioRepository.create(nombreCompleto, username, email, clave);
	}

	@Override
	@Transactional
	public String confirmarToken(String token) {
		Optional<TokenConfirmacionEntity> optToken = tokenService
				.getToken(token);
		if(optToken.isEmpty())
			return "token no encontrado";

		TokenConfirmacionEntity tokenConfirmacion = optToken.get();

		if (tokenConfirmacion.getFechaConfirmacion() != null) {
			return "email ya confirmado";
		}

		BigInteger fechaExpiracion = tokenConfirmacion.getFechaExpiracion();

		if (fechaExpiracion.longValue() < new Date().getTime()) {
			return "token expirado";
		}

		tokenService.setConfirmado(token);
		Integer confirmado = usuarioEntityService.confirmarEmailUsuario(
				tokenConfirmacion.getUsuario().getEmail());

		if(confirmado != 0)
			return "confirmado";

		return "no confirmado";
	}

	@Override
	public Usuario findByUsername(String username) {
		return usuarioRepository.findByUsername(username);
	}

	@Override
	public Usuario findByEmail(String email) {
		return usuarioRepository.findByEmail(email);
	}

	@Override
	public Usuario findById(Integer id) {
		return usuarioRepository.findById(id);
	}

}
