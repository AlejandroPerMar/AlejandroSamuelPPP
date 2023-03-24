package es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.primary.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.model.Usuario;
import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.port.primary.IUsuarioService;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.security.JwtService;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.security.UserDetailsLogin;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@CrossOrigin
@RequestMapping("/api/login")
public class LoginController {
	
	  @Autowired
	  private IUsuarioService usuarioService;

	  @Autowired
	  private JwtService jwtService;  
	  
	  @PostMapping
	  public ResponseEntity<String> login(@RequestBody UserDetailsLogin request) {
		  String username = request.getUsername();
		  String plainPassword = request.getPassword();
		  String token = null;
		  if( usuarioService.checkCredentials(username, plainPassword) ) {
			  Usuario usuario = usuarioService.findByUsername(username);
			  List<String> roles = usuario.getRoles()
					  .stream()
					  .map(r -> r.getRol())
					  .collect(Collectors.toList());
			  token = jwtService.generateToken(usuario.getUsername(), roles);
		  }

		  if(token == null)
			  return ResponseEntity.status(HttpStatus.FORBIDDEN).body("User/pass erróneo");
		  else
			  return ResponseEntity.ok(token);
	  }
}
