package es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.primary.controller;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.model.Usuario;
import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.model.enums.ErroresRegistro;
import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.model.enums.EstadosVerificacionCorreo;
import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.port.primary.IHTMLBuilder;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.primary.mapper.UsuarioDTOMapper;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.entity.TokenConfirmacionEntity;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.mapper.UsuarioEntityMapper;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.config.EmailSender;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.config.SwaggerConfig;
import io.swagger.annotations.Api;
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
	private EmailSender emailSender;

	@Autowired
	private IHTMLBuilder htmlBuilder;

	@Autowired
	private JwtService jwtService;

	@Autowired
	private HttpServletRequest request;

	@PostMapping
	public ResponseEntity<?> register(@RequestBody UsuarioRegister request) {
		if(!validateNombreCompleto(request.getNombre()))
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ErroresRegistro.INVALID_NAME);
		if(!validateUsername(request.getUsername()))
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ErroresRegistro.NOT_AVAILABLE_USERNAME);
		if(!validateEmail(request.getEmail()))
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ErroresRegistro.INVALID_EMAIL);
		if(!validateClave(request.getClave()))
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(ErroresRegistro.NOT_MINIMUN_REQUIREMENTS_PASSWORD);

		TokenConfirmacionEntity token = usuarioService.create(request.getNombre(), request.getUsername(), request.getEmail(), request.getClave());

		UsuarioEntityMapper entityMapper = new UsuarioEntityMapper();

		Usuario usuario = entityMapper.toDomain(token.getUsuario());

		String link = getBaseUrl() + "/api/register/confirm?token=" + token.getToken();
		emailSender.enviar(
				request.getEmail(),
				htmlBuilder.buildEmail(usuario.getNombreCompleto(), link));

		UsuarioDTOMapper dtoMapper = new UsuarioDTOMapper();

		return ResponseEntity.status(HttpStatus.OK).body(dtoMapper.toDTO(usuario));
	}

	@GetMapping("confirm")
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
		if(clave == null) return false;

		String regex = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{8,20}$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(clave);
		return matcher.matches();
	}

	private boolean validateNombreCompleto(String nombreCompleto) {
		if(nombreCompleto == null) return false;

		return !nombreCompleto.trim().equals("");
	}

	private boolean validateUsername(String username) {
		return usuarioService.findByUsername(username) == null;
	}

	private boolean validateEmail(String email) {
		if(email == null) return false;

		if(usuarioService.findByEmail(email) != null)
			return false;
		return EmailValidator.getInstance().isValid(email);
	}

	public String getBaseUrl() {
		return String.format("%s://%s:%d", request.getScheme(), request.getServerName(), request.getServerPort());
	}
}
