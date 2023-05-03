package es.iespuertodelacruz.alejandrosamuel.studycircle.domain.port.primary;

import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.model.Usuario;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.entity.TokenConfirmacionEntity;

public interface IUsuarioService {
	
	boolean checkCredentials(String username, String pass);

	TokenConfirmacionEntity create(String nombreCompleto, String username, String email, String clave);

	String confirmarToken(String token);

	TokenConfirmacionEntity resendConfirmationToken(Usuario usuario);

	Usuario findByUsername(String username);

	Usuario findByEmail(String email);

    Usuario findById(Integer id);
}
