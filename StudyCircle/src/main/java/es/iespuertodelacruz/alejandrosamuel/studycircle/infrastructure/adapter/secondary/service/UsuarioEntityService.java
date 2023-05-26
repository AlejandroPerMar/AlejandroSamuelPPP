package es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.service;

import java.math.BigInteger;
import java.util.*;

import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.entity.TokenConfirmacionEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.model.Usuario;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.enums.EstadosUsuario;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.enums.Roles;
import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.port.secondary.IUsuarioRepository;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.entity.RolEntity;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.entity.UsuarioEntity;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.repository.RolEntityJPARepository;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.repository.UsuarioEntityJPARepository;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.mapper.UsuarioEntityMapper;
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

	@Autowired
	private UsuarioEntityMapper mapper;

	@Override
	public List<Usuario> findAll() {
		List<UsuarioEntity> usuarios = usuarioRepository.findAll();
		return usuarios.stream().map(u -> mapper.toDomain(u)).toList();
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

		return createConfirmationToken(mapper.toDomain(savedEntity));
	}

	@Override
	@Transactional
	public TokenConfirmacionEntity resendConfirmationToken(Usuario usuario) {
		usuarioRepository.invalidarTokensUsuario(usuario.getId());
		return createConfirmationToken(usuario);
	}

	public TokenConfirmacionEntity createConfirmationToken(Usuario usuario) {
		TokenConfirmacionEntity tokenConfirmacion = new TokenConfirmacionEntity();
		String token = UUID.randomUUID().toString();
		tokenConfirmacion.setToken(token);
		tokenConfirmacion.setFechaCreacion(new BigInteger(new Date().getTime() + ""));
		BigInteger fechaExpiracion = new BigInteger((tokenConfirmacion.getFechaCreacion().longValue() + 900000) + "");
		tokenConfirmacion.setFechaExpiracion(fechaExpiracion);
		tokenConfirmacion.setUsuario(mapper.toEntity(usuario));
		tokenConfirmacion.setValido(true);
		return tokenService.saveTokenConfirmacion(tokenConfirmacion);
	}

	@Override
	public Usuario findById(Integer id) {
		Optional<UsuarioEntity> optUsuario = usuarioRepository.findById(id);
		return optUsuario.map(u -> mapper.toDomain(u)).orElse(null);
	}

	@Override
	public boolean checkPassword(String username, String pswd) {
		boolean ok = false;
		Usuario usuario = findByUsername(username);
		if (Objects.nonNull(usuario))
			ok = passwordEncoder.matches(pswd, usuario.getHashpswd());
		return ok;

	}

	@Override
	public Usuario findByUsername(String username) {
		Optional<UsuarioEntity> optUsuario = usuarioRepository.findByUsername(username);
		return optUsuario.map(u -> mapper.toDomain(u)).orElse(null);
	}

	@Override
	public Usuario findByEmail(String email) {
		Optional<UsuarioEntity> optUsuario = usuarioRepository.findByEmail(email);
		return optUsuario.map(u -> mapper.toDomain(u)).orElse(null);
	}

	public Integer confirmarEmailUsuario(String email) {
		return usuarioRepository.confirmarUsuario(email);
	}

}
