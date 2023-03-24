package es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.service;

import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.port.secondary.IUsuarioRepository;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.primary.UserDTO;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.entities.UsuarioEntity;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.repositories.UsuarioEntityJPARepository;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.security.JwtService;
import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.model.Usuario;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioEntityService implements IUsuarioRepository {

	@Autowired
	private UsuarioEntityJPARepository jparepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private JwtService jwtService;

	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Override
	public boolean checkPassword(String name, String plainPassword) {
		boolean ok = false;
		Usuario usuario = findByUsername(name);
		if(usuario != null) {
			ok = passwordEncoder.matches(plainPassword,usuario.getHashpswd());
		}
		return ok;
	}

	@Override
	public Usuario findByUsername(String name) {
		UsuarioEntity ue = jparepository.findByName(name);
		if( ue != null) {
			EntityUsuarioMapper mapper = new EntityUsuarioMapper();
			return mapper.toDomain(ue);			
		}else {
			return null;
		}

	}

	@Override
	public List<Usuario> findAll() {
		EntityUsuarioMapper mapper = new EntityUsuarioMapper();
		return jparepository.findAll()
			.stream()
			.map(ue->mapper.toDomain(ue))
			.collect(Collectors.toList());
	}

	@Override
	public Usuario create(UserDTO user) {
		EntityUsuarioMapper mapper = new EntityUsuarioMapper();
		UsuarioEntity usuarioEntity = new UsuarioEntity();
		usuarioEntity.setNombre(user.getUsername());
		usuarioEntity.setHashpswd(passwordEncoder.encode(user.getPass()));
		//usuarioEntity.setRol();
		UsuarioEntity savedEntity = jparepository.save(usuarioEntity);
		return mapper.toDomain(savedEntity);
	}

	
	


}
