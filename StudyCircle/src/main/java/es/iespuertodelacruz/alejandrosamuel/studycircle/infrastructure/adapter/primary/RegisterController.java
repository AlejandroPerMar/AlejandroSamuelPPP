package es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.primary;

import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.model.Usuario;
import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.port.primary.IUsuarioService;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.security.JwtService;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.security.UserDetailsLogin;

@RestController
@CrossOrigin
@RequestMapping("/api/register")
public class RegisterController {
	
  @Autowired
  private IUsuarioService usuarioService;

  @Autowired
  private JwtService jwtService;  

  @PostMapping
  public ResponseEntity<String> register(
      @RequestBody UserDetailsLogin request
  ) {
	  String username = request.getUsername();
	  String plainPassword = request.getPassword();
	  String token = null;
	  
	  UserDTO newUser = new UserDTO();
	  
	  newUser.setUsername(username);
	  newUser.setPass(plainPassword);
	  // Falta añadir más atributos, al menos los necesarios para la gestion en la bd
	  
	  Usuario usuario = usuarioService.create(newUser);
	  if( usuario != null ) {
		  token = jwtService.generateToken(usuario.getUsername(), usuario.getRoles());
		  // Exactamente el mismo problema que con el login. Posee varios roles.
		  if( token != null) {
			  return ResponseEntity.ok(token);  
		  }else {
			  return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Usuario creado, pero no posible obtener token");
		  }
	  }else {
		  return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("No se pudo registrar en la DDBB");
	  }
		  
  }


}
	