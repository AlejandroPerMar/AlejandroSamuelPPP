package es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.service;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.model.Usuario;
import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.model.status.EstadosUsuario;
import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.port.secondary.IUsuarioRepository;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.entity.RolEntity;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.entity.UsuarioEntity;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.repository.RolEntityJPARepository;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.repository.UsuarioEntityJPARepository;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.mapper.UsuarioEntityMapper;
import org.apache.commons.validator.routines.EmailValidator;

@Service
public class UsuarioEntityService implements IUsuarioRepository {

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private UsuarioEntityJPARepository usuarioRepository;
	
	@Autowired
	private RolEntityJPARepository rolRepository;

	@Override
	public List<Usuario> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Usuario create(String nombreCompleto, String username, String email, String clave) {
		if(!validateDatos(nombreCompleto, username, email, clave))
			return null;
			
		UsuarioEntity usuarioEntity = new UsuarioEntity();
		usuarioEntity.setNombreCompleto(nombreCompleto);
		usuarioEntity.setUsername(username);
		usuarioEntity.setEmail(email);
		usuarioEntity.setHashpswd(passwordEncoder.encode(clave));
		usuarioEntity.setFechaCreacion(Timestamp.from(Instant.now()));
		usuarioEntity.setEstado(EstadosUsuario.STATUS_PENDING_VERIFICATION.toString());
		List<RolEntity> roles = new ArrayList<>();
		roles.add(rolRepository.findByRol("ROLE_USER"));
		usuarioEntity.setRoles(roles);
		UsuarioEntity savedEntity = usuarioRepository.save(usuarioEntity);
		
		UsuarioEntityMapper mapper = new UsuarioEntityMapper();
		return mapper.toDomain(savedEntity);
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
	
	private boolean validateDatos(String nombreCompleto, String username, String email, String clave) {
		return validateNombreCompleto(nombreCompleto) && 
				validateUsername(username) && 
				validateEmail(email) && 
				validateClave(clave);
	}
	
	/*
	 *  Must have at least one numeric character
	 *	Must have at least one lowercase character
	 *	Must have at least one uppercase character
	 *	Must have at least one special symbol among @#$%
	 *	Password length should be between 8 and 20
	 */
	private boolean validateClave(String clave) {
		String regex = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{8,20}$";
		Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(clave);
        return matcher.matches();
	}
	
	private boolean validateNombreCompleto(String nombreCompleto) {
		return !nombreCompleto.trim().equals("");
	}
	
	private boolean validateUsername(String username) {
		return findByUsername(username) == null;
	}
	
	private boolean validateEmail(String email) {
		return EmailValidator.getInstance().isValid(email);
		
	}

}
