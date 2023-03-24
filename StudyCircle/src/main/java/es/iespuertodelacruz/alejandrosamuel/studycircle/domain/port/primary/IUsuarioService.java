package es.iespuertodelacruz.alejandrosamuel.studycircle.domain.port.primary;

import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.model.Usuario;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.primary.UserDTO;

public interface IUsuarioService {

	public boolean checkCredentials(String username, String pass);

	public Usuario create(UserDTO user);

	public Usuario findByUsername(String username);

}
