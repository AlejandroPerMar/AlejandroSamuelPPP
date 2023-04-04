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
import org.springframework.web.bind.annotation.RequestBody;

import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.model.Usuario;
import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.port.primary.IUsuarioService;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.primary.dto.UsuarioRegisterDTO;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.security.JwtService;

@RestController
@CrossOrigin
@RequestMapping("/api/register")
public class RegisterController {

	@Autowired
	private IUsuarioService usuarioService;
	
	@Autowired
	private JwtService jwtService;  
	
	@PostMapping
	public ResponseEntity<?> register(@RequestBody UsuarioRegisterDTO request) {
		Usuario usuario = usuarioService.create(request.getNombre(), request.getUsername(), request.getEmail(), request.getClave());
		String token = null;
		if(usuario != null) {
			List<String> roles = usuario.getRoles()
					  .stream()
					  .map(r -> r.getRol())
					  .collect(Collectors.toList());
			token = jwtService.generateToken(usuario.getUsername(), roles);
		}
		if(token == null) 
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Usuario no creado");
		else
			return ResponseEntity.ok(token);
	}
}
