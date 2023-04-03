package es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.service;

import java.util.List;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.model.Usuario;
import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.port.secondary.IUsuarioRepository;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.primary.dto.UsuarioRegisterDTO;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.entity.RolEntity;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.entity.UsuarioEntity;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.repository.UsuarioEntityJPARepository;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.mapper.UsuarioLoginEntityMapper;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.mapper.UsuarioRegisterEntityMapper;

@Service
public class UsuarioEntityService implements IUsuarioRepository {

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private UsuarioEntityJPARepository repository;

	@Override
	public List<Usuario> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Usuario create(UsuarioRegisterDTO usuario) {
		UsuarioRegisterEntityMapper mapper = new UsuarioRegisterEntityMapper();
		UsuarioEntity usuarioEntity = new UsuarioEntity();
		usuarioEntity.setNombreCompleto(usuario.getNombre());
		usuarioEntity.setHashpswd(passwordEncoder.encode(usuario.getClave()));
		usuarioEntity.setFechaCreacion(null);
		List<RolEntity> roles = new ArrayList<>();
		usuarioEntity.setRoles(roles);
		UsuarioEntity savedEntity = repository.save(usuarioEntity);
		return mapper.toDomain(savedEntity);
	}

	@Override
	public boolean checkPassword(String username, String pswd) {
		boolean ok = false;
		Usuario usuario = findByUsername(username);
		System.out.println("adios");
		if (usuario != null) {
			System.out.println("hola");
			ok = passwordEncoder.matches(pswd, usuario.getHashpswd());
		}
		return ok;

	}

	@Override
	public Usuario findByUsername(String username) {
		UsuarioEntity ue = repository.findByUsername(username);
		if (ue != null) {
			System.out.println("adios23");
			UsuarioLoginEntityMapper mapper = new UsuarioLoginEntityMapper();
			return mapper.toDomain(ue);
		} else {
			return null;
		}
	}

}
