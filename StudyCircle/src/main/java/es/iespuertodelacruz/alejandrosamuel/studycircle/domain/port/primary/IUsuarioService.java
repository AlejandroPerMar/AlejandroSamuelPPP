package es.iespuertodelacruz.alejandrosamuel.studycircle.domain.port.primary;

import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.model.Usuario;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.primary.dto.UsuarioDTO;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.primary.dto.UsuarioRegisterDTO;

public interface IUsuarioService {
	
	public boolean checkCredentials(String username, String pass);

	public String create(String nombreCompleto, String username, String email, String clave);

	public String confirmarToken(String token);

	public Usuario findByUsername(String username);

	public Usuario findByEmail(String email);

}
