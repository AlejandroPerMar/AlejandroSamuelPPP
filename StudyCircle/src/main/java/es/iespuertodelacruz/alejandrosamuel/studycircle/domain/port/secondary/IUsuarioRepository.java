package es.iespuertodelacruz.alejandrosamuel.studycircle.domain.port.secondary;

import java.util.List;

import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.model.Usuario;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.primary.dto.UsuarioRegisterDTO;

public interface IUsuarioRepository {
	
	boolean checkPassword(String username, String pswd);
	
	Usuario findByUsername(String username);

	Usuario findByEmail(String email);

	List<Usuario> findAll();
	
	String create(String nombreCompleto, String username, String email, String clave);
}
