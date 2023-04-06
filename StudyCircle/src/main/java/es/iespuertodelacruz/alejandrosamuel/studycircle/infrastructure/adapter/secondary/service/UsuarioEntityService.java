package es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.service;

import java.math.BigInteger;
import java.time.Clock;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.sql.Timestamp;
import java.time.Instant;

import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.entity.TokenConfirmacionEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.model.Usuario;
import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.model.enums.EstadosUsuario;
import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.model.enums.Roles;
import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.port.secondary.IUsuarioRepository;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.entity.RolEntity;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.entity.UsuarioEntity;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.repository.RolEntityJPARepository;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.repository.UsuarioEntityJPARepository;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.mapper.UsuarioEntityMapper;
import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UsuarioEntityService implements IUsuarioRepository {

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private UsuarioEntityJPARepository usuarioRepository;
	
	@Autowired
	private RolEntityJPARepository rolRepository;

	@Autowired
	private TokenConfirmacionEntityService tokenService;

	@Override
	public List<Usuario> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	@Transactional
	public TokenConfirmacionEntity create(String nombreCompleto, String username, String email, String clave) {
		UsuarioEntity usuarioEntity = new UsuarioEntity();
		usuarioEntity.setNombreCompleto(nombreCompleto);
		usuarioEntity.setUsername(username);
		usuarioEntity.setEmail(email);
		usuarioEntity.setHashpswd(passwordEncoder.encode(clave));
		usuarioEntity.setFechaCreacion(new BigInteger(new Date().getTime() + ""));
		usuarioEntity.setEstado(EstadosUsuario.STATUS_PENDING_VERIFICATION.name());
		List<RolEntity> roles = new ArrayList<>();
		roles.add(rolRepository.findByRol(Roles.ROLE_USER.name()));
		usuarioEntity.setRoles(roles);

		UsuarioEntity savedEntity = usuarioRepository.save(usuarioEntity);

		TokenConfirmacionEntity tokenConfirmacion = new TokenConfirmacionEntity();
		String token = UUID.randomUUID().toString();
		tokenConfirmacion.setToken(token);
		tokenConfirmacion.setFechaCreacion(new BigInteger(new Date().getTime() + ""));
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.MINUTE, 15);
		BigInteger fechaExpiracion = new BigInteger((usuarioEntity.getFechaCreacion().longValue() + 900000) + "");
		tokenConfirmacion.setFechaExpiracion(fechaExpiracion);
		tokenConfirmacion.setUser(savedEntity);

		tokenConfirmacion = tokenService.saveTokenConfirmacion(tokenConfirmacion);

		return tokenConfirmacion;
	}

	@Override
	public boolean checkPassword(String username, String pswd) {
		boolean ok = false;
		Usuario usuario = findByUsername(username);
		if (usuario != null) {
			ok = passwordEncoder.matches(pswd, usuario.getHashpswd());
		}
		return ok;

	}

	@Override
	public Usuario findByUsername(String username) {
		UsuarioEntity ue = usuarioRepository.findByUsername(username);
		if (ue != null) {
			UsuarioEntityMapper mapper = new UsuarioEntityMapper();
			return mapper.toDomain(ue);
		} else {
			return null;
		}
	}

	@Override
	public Usuario findByEmail(String email) {
		UsuarioEntity ue = usuarioRepository.findByEmail(email);
		if (ue != null) {
			UsuarioEntityMapper mapper = new UsuarioEntityMapper();
			return mapper.toDomain(ue);
		} else {
			return null;
		}
	}

	public Integer confirmarEmailUsuario(String email) {
		return usuarioRepository.confirmarUsuario(email);
	}

}
