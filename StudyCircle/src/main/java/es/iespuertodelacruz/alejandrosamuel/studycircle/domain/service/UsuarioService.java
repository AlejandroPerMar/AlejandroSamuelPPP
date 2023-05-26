package es.iespuertodelacruz.alejandrosamuel.studycircle.domain.service;

import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.enums.EstadosVerificacionCorreo;
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
import java.util.List;
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
			return EstadosVerificacionCorreo.STATUS_NOT_FOUND.name();

		TokenConfirmacionEntity tokenConfirmacion = optToken.get();

		if (tokenConfirmacion.getFechaConfirmacion() != null) {
			return EstadosVerificacionCorreo.STATUS_ALREADY_CONFIRMED.name();
		}

		BigInteger fechaExpiracion = tokenConfirmacion.getFechaExpiracion();

		if (fechaExpiracion.longValue() < new Date().getTime()) {
			return EstadosVerificacionCorreo.STATUS_EXPIRED.name();
		}

		if(!tokenConfirmacion.isValido())
			return EstadosVerificacionCorreo.STATUS_INVALID.name();

		tokenService.setConfirmado(token);
		Integer confirmado = usuarioEntityService.confirmarEmailUsuario(
				tokenConfirmacion.getUsuario().getEmail());

		if(confirmado != 0)
			return EstadosVerificacionCorreo.STATUS_CONFIRMED.name();

		return EstadosVerificacionCorreo.STATUS_NOT_CONFIRMED.name();
	}

	@Override
	public TokenConfirmacionEntity resendConfirmationToken(Usuario usuario) {
		return usuarioRepository.resendConfirmationToken(usuario);
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

	@Override
	public List<Usuario> findUsuarios(Integer idUsuario) {
		return usuarioRepository.findUsuarios(idUsuario);
	}

}
