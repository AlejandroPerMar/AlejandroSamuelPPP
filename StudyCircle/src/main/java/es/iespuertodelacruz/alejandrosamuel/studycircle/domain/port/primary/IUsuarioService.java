package es.iespuertodelacruz.alejandrosamuel.studycircle.domain.port.primary;

import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.model.Usuario;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.entity.TokenConfirmacionEntity;

public interface IUsuarioService {
	
	public boolean checkCredentials(String username, String pass);

	public TokenConfirmacionEntity create(String nombreCompleto, String username, String email, String clave);

	public String confirmarToken(String token);

	public Usuario findByUsername(String username);

	public Usuario findByEmail(String email);

}
