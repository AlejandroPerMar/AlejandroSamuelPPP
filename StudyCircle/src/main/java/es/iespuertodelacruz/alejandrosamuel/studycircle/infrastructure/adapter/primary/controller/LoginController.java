package es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.primary.controller;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.model.Rol;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.config.SwaggerConfig;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.enums.ErroresInicioSesion;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.model.Usuario;
import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.port.primary.IUsuarioService;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.primary.dto.UsuarioLogin;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.security.JwtService;

@Api(tags = {SwaggerConfig.LOGIN_TAG})
@RestController
@CrossOrigin
@RequestMapping("/api/login")
public class LoginController {

	@Autowired
	private IUsuarioService usuarioService;

	@Autowired
	private JwtService jwtService;

	@PostMapping
	@ApiOperation(
			value= "Iniciar sesión",
			notes= """
                    Parámetros solicitados:\s
                    • "UsuarioLogin usuarioLogin. Datos de autenticacióon del usuario (Username y contraseña)
                    
                    Posibles respuestas:\s
                    • "INVALID_USERNAME_OR_PASSWORD" (String). Indica que las credenciales no corresponden con las de ningún usuario
                    • "Token Bearer de autorización. Devuelve el token de autorización que se debe incluir en la cabecera de las peticiones que lo requieren
                    """
	)
	public ResponseEntity<String> login(@RequestBody UsuarioLogin usuarioLogin) {
		String username = usuarioLogin.getUsername();
		String plainPassword = usuarioLogin.getClave();
		String token = null;
		if(usuarioService.checkCredentials(username, plainPassword) ) {
			Usuario usuario = usuarioService.findByUsername(username);
			List<String> roles = usuario.getRoles()
					.stream()
					.map(Rol::getRol)
					.collect(Collectors.toList());
			token = jwtService.generateToken(usuario.getUsername(), roles);
		}

		if(Objects.isNull(token))
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(ErroresInicioSesion.INVALID_USERNAME_OR_PASSWORD.name());

		return ResponseEntity.ok(token);
	}
}
