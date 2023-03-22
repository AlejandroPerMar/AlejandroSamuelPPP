package es.iespuertodelacruz.alejandrosamuel.studycircle.domain.port.secondary;

import java.util.List;

import org.springframework.stereotype.Repository;

import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.model.Usuario;


@Repository
public interface IUsuarioRepository {

	boolean checkPassword(String username, String plainPassword);

	Usuario findByUsername(String username);

	List<Usuario> findAll();

	Usuario create(String username, String plainPassword, String rol);
	
}
