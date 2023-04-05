package es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.primary.controller;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.port.primary.IUsuarioService;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.primary.dto.UsuarioRegisterDTO;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.security.JwtService;

@RestController
@CrossOrigin
@RequestMapping("api/register")
public class RegisterController {

	@Autowired
	private IUsuarioService usuarioService;
	
	@Autowired
	private JwtService jwtService;  
	
	@PostMapping
	public ResponseEntity<?> register(@RequestBody UsuarioRegisterDTO request) {
		if(!validateNombreCompleto(request.getNombre()))
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Nombre no válido");
		if(!validateUsername(request.getUsername()))
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Nombre de usuario en uso");
		if(!validateEmail(request.getEmail()))
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Correo electrónico no válido o en uso");
		if(!validateClave(request.getClave()))
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("La clave no cumple con los requisitos mínimos");

		String token = usuarioService.create(request.getNombre(), request.getUsername(), request.getEmail(), request.getClave());

		return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Usuario no creado");
		//return ResponseEntity.ok(token);
	}

	@PostMapping("confirm")
	public ResponseEntity<?> confirmarUsuario(@RequestParam("token") String token) {
		String estadoToken = usuarioService.confirmarToken(token);
		return ResponseEntity.status(HttpStatus.ACCEPTED).body(estadoToken);
	}

	/*
	 *  Must have at least one numeric character
	 *	Must have at least one lowercase character
	 *	Must have at least one uppercase character
	 *	Must have at least one special symbol among @#$%
	 *	Password length should be between 8 and 20
	 *  Ref: https://java2blog.com/validate-password-java/
	 */
	private boolean validateClave(String clave) {
		String regex = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{8,20}$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(clave);
		return matcher.matches();
	}

	private boolean validateNombreCompleto(String nombreCompleto) {
		return !nombreCompleto.trim().equals("");
	}

	private boolean validateUsername(String username) {
		return usuarioService.findByUsername(username) == null;
	}

	private boolean validateEmail(String email) {
		if(usuarioService.findByEmail(email) != null)
			return false;
		return EmailValidator.getInstance().isValid(email);

	}
}
