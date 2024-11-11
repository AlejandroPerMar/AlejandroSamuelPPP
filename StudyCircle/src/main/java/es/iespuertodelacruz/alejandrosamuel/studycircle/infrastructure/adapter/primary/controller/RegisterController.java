package es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.primary.controller;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.model.Usuario;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.enums.ErroresRegistro;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.enums.EstadosVerificacionCorreo;
import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.port.primary.IHTMLBuilder;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.primary.mapper.UsuarioDTOMapper;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.entity.TokenConfirmacionEntity;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.mapper.UsuarioEntityMapper;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.config.EmailSender;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.config.SwaggerConfig;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.port.primary.IUsuarioService;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.primary.dto.UsuarioRegister;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.security.JwtService;

import javax.servlet.http.HttpServletRequest;

@Api(tags = {SwaggerConfig.REGISTER_TAG})
@RestController
@CrossOrigin
@RequestMapping("api/register")
public class RegisterController {

	@Autowired
	private IUsuarioService usuarioService;

	@Autowired
	private UsuarioDTOMapper mapper;

	@Autowired
	private EmailSender emailSender;

	@Autowired
	private IHTMLBuilder htmlBuilder;

	@Autowired
	private JwtService jwtService;

	@Autowired
	private HttpServletRequest request;

	@PostMapping
	@ApiOperation(
			value= "Registrar nuevo usuario",
			notes= """
                    Parámetros solicitados:\s
                    • "UsuarioRegister usuarioRegister. Datos de registro del usuario (Nombre completo, correo electrónico, username y contraseña)
                    
                    Posibles respuestas:\s
                    • "INVALID_NAME" (String). Indica que el nombre no es válido
                    • "NOT_AVAILABLE_USERNAME" (String). Indica que el username no está disponible
                    • "INVALID_EMAIL" (String). Indica que el correo electrónico es inválido
                    • "NOT_MINIMUN_REQUIREMENTS_PASSWORD" (String). Indica que la contraseña no cumple con los requisitos mínimos
                    • "UsuarioDTO. Devuelve el Usuario que se acaba de crear
                    """
	)
	public ResponseEntity<?> register(@RequestBody UsuarioRegister usuarioRegister) {
		if(!validateNombreCompleto(usuarioRegister.getNombre()))
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ErroresRegistro.INVALID_NAME.name());

		if(!validateUsername(usuarioRegister.getUsername()))
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ErroresRegistro.NOT_AVAILABLE_USERNAME.name());

		if(!validateEmail(usuarioRegister.getEmail()))
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ErroresRegistro.INVALID_EMAIL.name());
		if(!validateDisponibilidadEmail(usuarioRegister.getEmail()))
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ErroresRegistro.NOT_AVAILABLE_EMAIL.name());
		if(!validateClave(usuarioRegister.getClave()))
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ErroresRegistro.NOT_MINIMUN_REQUIREMENTS_PASSWORD.name());


		TokenConfirmacionEntity token = usuarioService.create(usuarioRegister.getNombre(),
				usuarioRegister.getUsername(),
				usuarioRegister.getEmail(),
				usuarioRegister.getClave());

		UsuarioEntityMapper entityMapper = new UsuarioEntityMapper();

		Usuario usuario = entityMapper.toDomain(token.getUsuario());

		String link = getBaseUrl() + "/api/register/confirm?token=" + token.getToken();
		emailSender.enviar(
				usuarioRegister.getEmail(),
				htmlBuilder.buildEmail(usuario.getNombreCompleto(), link));

		return ResponseEntity.ok().body(mapper.toDTO(usuario));
	}

	@GetMapping("confirm")
	@ApiOperation(
			value= "Confirmar usuario (por correo electrónico)",
			notes= """
                    Parámetros solicitados:\s
                    • "String token. Token de verificación de usuario
                    
                    Posibles respuestas:\s
                    • "Página HTML indicando la correcta verificación
                    • "Mensaje de error en caso de no haberse confirmado o ya encontrarse verificado previamente
                    """
	)
	public ResponseEntity<?> confirmarUsuario(@RequestParam("token") String token) {
		String estadoToken = usuarioService.confirmarToken(token);
		if(estadoToken.equals(EstadosVerificacionCorreo.STATUS_CONFIRMED.name()))
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(htmlBuilder.buildConfirmationPage());

		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("error");
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
		if(Objects.isNull(clave)) return false;

		String regex = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{8,20}$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(clave);
		return matcher.matches();
	}

	private boolean validateNombreCompleto(String nombreCompleto) {
		if(Objects.isNull(nombreCompleto)) return false;

		return !nombreCompleto.trim().equals("");
	}

	private boolean validateUsername(String username) {
		return Objects.isNull(usuarioService.findByUsername(username));
	}

	private boolean validateEmail(String email) {
		if(Objects.isNull(email)) return false;
		return EmailValidator.getInstance().isValid(email);
	}

	private boolean validateDisponibilidadEmail(String email) {
		return Objects.isNull(usuarioService.findByEmail(email));
	}

	public String getBaseUrl() {
		return String.format("%s://%s:%d", request.getScheme(), request.getServerName(), request.getServerPort());
	}
}
