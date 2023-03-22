package es.iespuertodelacruz.alejandrosamuel.studycircle.domain.port.primary;

import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.model.Usuario;

public interface IUsuarioService {

	public boolean checkCredentials(String username, String pass);

	public Usuario create(String username, String pass, String role);

	public Usuario findByUsername(String username);

}
