package es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.primary.controller;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.model.Usuario;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.primary.mapper.UsuarioDTOMapper;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.entity.TokenConfirmacionEntity;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.mapper.UsuarioEntityMapper;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.config.EmailSender;
import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.port.primary.IUsuarioService;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.primary.dto.UsuarioRegister;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.security.JwtService;

@RestController
@CrossOrigin
@RequestMapping("api/register")
public class RegisterController {

	@Autowired
	private IUsuarioService usuarioService;

	@Autowired
	private EmailSender emailSender;

	@Autowired
	private JwtService jwtService;

	@PostMapping
	public ResponseEntity<?> register(@RequestBody UsuarioRegister request) {
		if(!validateNombreCompleto(request.getNombre()))
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Nombre no válido");
		if(!validateUsername(request.getUsername()))
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Nombre de usuario en uso");
		if(!validateEmail(request.getEmail()))
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Correo electrónico no válido o en uso");
		if(!validateClave(request.getClave()))
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("La clave no cumple con los requisitos mínimos");

		TokenConfirmacionEntity token = usuarioService.create(request.getNombre(), request.getUsername(), request.getEmail(), request.getClave());

		UsuarioEntityMapper entityMapper = new UsuarioEntityMapper();

		Usuario usuario = entityMapper.toDomain(token.getUsuario());

		String link = "http://localhost:8080/api/register/confirm?token=" + token.getToken();
		emailSender.enviar(
				request.getEmail(),
				buildEmail(usuario.getNombreCompleto(), link));

		UsuarioDTOMapper dtoMapper = new UsuarioDTOMapper();

		return ResponseEntity.status(HttpStatus.OK).body(dtoMapper.toDTO(usuario));
	}

	@GetMapping("confirm")
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

	private String buildEmail(String name, String link) {
		return "<!doctype html>\n" +
				"<html ⚡4email data-css-strict>\n" +
				"\n" +
				"<head>\n" +
				"    <meta charset=\"utf-8\">\n" +
				"    <style amp4email-boilerplate>\n" +
				"        body {\n" +
				"            visibility: hidden\n" +
				"        }\n" +
				"    </style>\n" +
				"    <script async src=\"https://cdn.ampproject.org/v0.js\"></script>\n" +
				"    <style amp-custom>\n" +
				"        .es-desk-hidden {\n" +
				"            display: none;\n" +
				"            float: left;\n" +
				"            overflow: hidden;\n" +
				"            width: 0;\n" +
				"            max-height: 0;\n" +
				"            line-height: 0;\n" +
				"        }\n" +
				"\n" +
				"        body {\n" +
				"            width: 100%;\n" +
				"            font-family: Imprima, Arial, sans-serif;\n" +
				"        }\n" +
				"\n" +
				"        table {\n" +
				"            border-collapse: collapse;\n" +
				"            border-spacing: 0px;\n" +
				"        }\n" +
				"\n" +
				"        table td,\n" +
				"        body,\n" +
				"        .es-wrapper {\n" +
				"            padding: 0;\n" +
				"            Margin: 0;\n" +
				"        }\n" +
				"\n" +
				"        .es-content,\n" +
				"        .es-header,\n" +
				"        .es-footer {\n" +
				"            table-layout: fixed;\n" +
				"            width: 100%;\n" +
				"        }\n" +
				"\n" +
				"        p,\n" +
				"        hr {\n" +
				"            Margin: 0;\n" +
				"        }\n" +
				"\n" +
				"        h1,\n" +
				"        h2,\n" +
				"        h3,\n" +
				"        h4,\n" +
				"        h5 {\n" +
				"            Margin: 0;\n" +
				"            line-height: 120%;\n" +
				"            font-family: Imprima, Arial, sans-serif;\n" +
				"        }\n" +
				"\n" +
				"        .es-left {\n" +
				"            float: left;\n" +
				"        }\n" +
				"\n" +
				"        .es-right {\n" +
				"            float: right;\n" +
				"        }\n" +
				"\n" +
				"        .es-p5 {\n" +
				"            padding: 5px;\n" +
				"        }\n" +
				"\n" +
				"        .es-p5t {\n" +
				"            padding-top: 5px;\n" +
				"        }\n" +
				"\n" +
				"        .es-p5b {\n" +
				"            padding-bottom: 5px;\n" +
				"        }\n" +
				"\n" +
				"        .es-p5l {\n" +
				"            padding-left: 5px;\n" +
				"        }\n" +
				"\n" +
				"        .es-p5r {\n" +
				"            padding-right: 5px;\n" +
				"        }\n" +
				"\n" +
				"        .es-p10 {\n" +
				"            padding: 10px;\n" +
				"        }\n" +
				"\n" +
				"        .es-p10t {\n" +
				"            padding-top: 10px;\n" +
				"        }\n" +
				"\n" +
				"        .es-p10b {\n" +
				"            padding-bottom: 10px;\n" +
				"        }\n" +
				"\n" +
				"        .es-p10l {\n" +
				"            padding-left: 10px;\n" +
				"        }\n" +
				"\n" +
				"        .es-p10r {\n" +
				"            padding-right: 10px;\n" +
				"        }\n" +
				"\n" +
				"        .es-p15 {\n" +
				"            padding: 15px;\n" +
				"        }\n" +
				"\n" +
				"        .es-p15t {\n" +
				"            padding-top: 15px;\n" +
				"        }\n" +
				"\n" +
				"        .es-p15b {\n" +
				"            padding-bottom: 15px;\n" +
				"        }\n" +
				"\n" +
				"        .es-p15l {\n" +
				"            padding-left: 15px;\n" +
				"        }\n" +
				"\n" +
				"        .es-p15r {\n" +
				"            padding-right: 15px;\n" +
				"        }\n" +
				"\n" +
				"        .es-p20 {\n" +
				"            padding: 20px;\n" +
				"        }\n" +
				"\n" +
				"        .es-p20t {\n" +
				"            padding-top: 20px;\n" +
				"        }\n" +
				"\n" +
				"        .es-p20b {\n" +
				"            padding-bottom: 20px;\n" +
				"        }\n" +
				"\n" +
				"        .es-p20l {\n" +
				"            padding-left: 20px;\n" +
				"        }\n" +
				"\n" +
				"        .es-p20r {\n" +
				"            padding-right: 20px;\n" +
				"        }\n" +
				"\n" +
				"        .es-p25 {\n" +
				"            padding: 25px;\n" +
				"        }\n" +
				"\n" +
				"        .es-p25t {\n" +
				"            padding-top: 25px;\n" +
				"        }\n" +
				"\n" +
				"        .es-p25b {\n" +
				"            padding-bottom: 25px;\n" +
				"        }\n" +
				"\n" +
				"        .es-p25l {\n" +
				"            padding-left: 25px;\n" +
				"        }\n" +
				"\n" +
				"        .es-p25r {\n" +
				"            padding-right: 25px;\n" +
				"        }\n" +
				"\n" +
				"        .es-p30 {\n" +
				"            padding: 30px;\n" +
				"        }\n" +
				"\n" +
				"        .es-p30t {\n" +
				"            padding-top: 30px;\n" +
				"        }\n" +
				"\n" +
				"        .es-p30b {\n" +
				"            padding-bottom: 30px;\n" +
				"        }\n" +
				"\n" +
				"        .es-p30l {\n" +
				"            padding-left: 30px;\n" +
				"        }\n" +
				"\n" +
				"        .es-p30r {\n" +
				"            padding-right: 30px;\n" +
				"        }\n" +
				"\n" +
				"        .es-p35 {\n" +
				"            padding: 35px;\n" +
				"        }\n" +
				"\n" +
				"        .es-p35t {\n" +
				"            padding-top: 35px;\n" +
				"        }\n" +
				"\n" +
				"        .es-p35b {\n" +
				"            padding-bottom: 35px;\n" +
				"        }\n" +
				"\n" +
				"        .es-p35l {\n" +
				"            padding-left: 35px;\n" +
				"        }\n" +
				"\n" +
				"        .es-p35r {\n" +
				"            padding-right: 35px;\n" +
				"        }\n" +
				"\n" +
				"        .es-p40 {\n" +
				"            padding: 40px;\n" +
				"        }\n" +
				"\n" +
				"        .es-p40t {\n" +
				"            padding-top: 40px;\n" +
				"        }\n" +
				"\n" +
				"        .es-p40b {\n" +
				"            padding-bottom: 40px;\n" +
				"        }\n" +
				"\n" +
				"        .es-p40l {\n" +
				"            padding-left: 40px;\n" +
				"        }\n" +
				"\n" +
				"        .es-p40r {\n" +
				"            padding-right: 40px;\n" +
				"        }\n" +
				"\n" +
				"        .es-menu td {\n" +
				"            border: 0;\n" +
				"        }\n" +
				"\n" +
				"        s {\n" +
				"            text-decoration: line-through;\n" +
				"        }\n" +
				"\n" +
				"        p,\n" +
				"        ul li,\n" +
				"        ol li {\n" +
				"            font-family: Imprima, Arial, sans-serif;\n" +
				"            line-height: 150%;\n" +
				"        }\n" +
				"\n" +
				"        ul li,\n" +
				"        ol li {\n" +
				"            Margin-bottom: 15px;\n" +
				"            margin-left: 0;\n" +
				"        }\n" +
				"\n" +
				"        a {\n" +
				"            text-decoration: underline;\n" +
				"        }\n" +
				"\n" +
				"        .es-menu td a {\n" +
				"            text-decoration: none;\n" +
				"            display: block;\n" +
				"            font-family: Imprima, Arial, sans-serif;\n" +
				"        }\n" +
				"\n" +
				"        .es-menu amp-img,\n" +
				"        .es-button amp-img {\n" +
				"            vertical-align: middle;\n" +
				"        }\n" +
				"\n" +
				"        .es-wrapper {\n" +
				"            width: 100%;\n" +
				"            height: 100%;\n" +
				"        }\n" +
				"\n" +
				"        .es-wrapper-color,\n" +
				"        .es-wrapper {\n" +
				"            background-color: #FFFFFF;\n" +
				"        }\n" +
				"\n" +
				"        .es-header {\n" +
				"            background-color: transparent;\n" +
				"        }\n" +
				"\n" +
				"        .es-header-body {\n" +
				"            background-color: #EFEFEF;\n" +
				"        }\n" +
				"\n" +
				"        .es-header-body p,\n" +
				"        .es-header-body ul li,\n" +
				"        .es-header-body ol li {\n" +
				"            color: #2D3142;\n" +
				"            font-size: 14px;\n" +
				"        }\n" +
				"\n" +
				"        .es-header-body a {\n" +
				"            color: #2D3142;\n" +
				"            font-size: 14px;\n" +
				"        }\n" +
				"\n" +
				"        .es-content-body {\n" +
				"            background-color: #EFEFEF;\n" +
				"        }\n" +
				"\n" +
				"        .es-content-body p,\n" +
				"        .es-content-body ul li,\n" +
				"        .es-content-body ol li {\n" +
				"            color: #2D3142;\n" +
				"            font-size: 18px;\n" +
				"        }\n" +
				"\n" +
				"        .es-content-body a {\n" +
				"            color: #2D3142;\n" +
				"            font-size: 18px;\n" +
				"        }\n" +
				"\n" +
				"        .es-footer {\n" +
				"            background-color: transparent;\n" +
				"        }\n" +
				"\n" +
				"        .es-footer-body {\n" +
				"            background-color: #FFFFFF;\n" +
				"        }\n" +
				"\n" +
				"        .es-footer-body p,\n" +
				"        .es-footer-body ul li,\n" +
				"        .es-footer-body ol li {\n" +
				"            color: #2D3142;\n" +
				"            font-size: 14px;\n" +
				"        }\n" +
				"\n" +
				"        .es-footer-body a {\n" +
				"            color: #2D3142;\n" +
				"            font-size: 14px;\n" +
				"        }\n" +
				"\n" +
				"        .es-infoblock,\n" +
				"        .es-infoblock p,\n" +
				"        .es-infoblock ul li,\n" +
				"        .es-infoblock ol li {\n" +
				"            line-height: 120%;\n" +
				"            font-size: 12px;\n" +
				"            color: #CCCCCC;\n" +
				"        }\n" +
				"\n" +
				"        .es-infoblock a {\n" +
				"            font-size: 12px;\n" +
				"            color: #CCCCCC;\n" +
				"        }\n" +
				"\n" +
				"        h1 {\n" +
				"            font-size: 48px;\n" +
				"            font-style: normal;\n" +
				"            font-weight: bold;\n" +
				"            color: #2D3142;\n" +
				"        }\n" +
				"\n" +
				"        h2 {\n" +
				"            font-size: 36px;\n" +
				"            font-style: normal;\n" +
				"            font-weight: bold;\n" +
				"            color: #2D3142;\n" +
				"        }\n" +
				"\n" +
				"        h3 {\n" +
				"            font-size: 28px;\n" +
				"            font-style: normal;\n" +
				"            font-weight: bold;\n" +
				"            color: #2D3142;\n" +
				"        }\n" +
				"\n" +
				"        .es-header-body h1 a,\n" +
				"        .es-content-body h1 a,\n" +
				"        .es-footer-body h1 a {\n" +
				"            font-size: 48px;\n" +
				"        }\n" +
				"\n" +
				"        .es-header-body h2 a,\n" +
				"        .es-content-body h2 a,\n" +
				"        .es-footer-body h2 a {\n" +
				"            font-size: 36px;\n" +
				"        }\n" +
				"\n" +
				"        .es-header-body h3 a,\n" +
				"        .es-content-body h3 a,\n" +
				"        .es-footer-body h3 a {\n" +
				"            font-size: 28px;\n" +
				"        }\n" +
				"\n" +
				"        a.es-button,\n" +
				"        button.es-button {\n" +
				"            padding: 15px 20px 15px 20px;\n" +
				"            display: inline-block;\n" +
				"            background: #4114F7;\n" +
				"            border-radius: 30px;\n" +
				"            font-size: 22px;\n" +
				"            font-family: Imprima, Arial, sans-serif;\n" +
				"            font-weight: bold;\n" +
				"            font-style: normal;\n" +
				"            line-height: 120%;\n" +
				"            color: #FFFFFF;\n" +
				"            text-decoration: none;\n" +
				"            width: auto;\n" +
				"            text-align: center;\n" +
				"        }\n" +
				"\n" +
				"        .es-button-border {\n" +
				"            border-style: solid solid solid solid;\n" +
				"            border-color: #2CB543 #2CB543 #2CB543 #2CB543;\n" +
				"            background: #4114F7;\n" +
				"            border-width: 0px 0px 0px 0px;\n" +
				"            display: inline-block;\n" +
				"            border-radius: 30px;\n" +
				"            width: auto;\n" +
				"        }\n" +
				"\n" +
				"        body {\n" +
				"            font-family: arial, \"helvetica neue\", helvetica, sans-serif;\n" +
				"        }\n" +
				"\n" +
				"        .es-p-default {\n" +
				"            padding-top: 20px;\n" +
				"            padding-right: 40px;\n" +
				"            padding-bottom: 0px;\n" +
				"            padding-left: 40px;\n" +
				"        }\n" +
				"\n" +
				"        .es-p-all-default {\n" +
				"            padding: 0px;\n" +
				"        }\n" +
				"\n" +
				"        @media only screen and (max-width:600px) {\n" +
				"\n" +
				"            p,\n" +
				"            ul li,\n" +
				"            ol li,\n" +
				"            a {\n" +
				"                line-height: 150%\n" +
				"            }\n" +
				"\n" +
				"            h1,\n" +
				"            h2,\n" +
				"            h3,\n" +
				"            h1 a,\n" +
				"            h2 a,\n" +
				"            h3 a {\n" +
				"                line-height: 120%\n" +
				"            }\n" +
				"\n" +
				"            h1 {\n" +
				"                font-size: 30px;\n" +
				"                text-align: left\n" +
				"            }\n" +
				"\n" +
				"            h2 {\n" +
				"                font-size: 24px;\n" +
				"                text-align: left\n" +
				"            }\n" +
				"\n" +
				"            h3 {\n" +
				"                font-size: 20px;\n" +
				"                text-align: left\n" +
				"            }\n" +
				"\n" +
				"            .es-header-body h1 a,\n" +
				"            .es-content-body h1 a,\n" +
				"            .es-footer-body h1 a {\n" +
				"                font-size: 30px;\n" +
				"                text-align: left\n" +
				"            }\n" +
				"\n" +
				"            .es-header-body h2 a,\n" +
				"            .es-content-body h2 a,\n" +
				"            .es-footer-body h2 a {\n" +
				"                font-size: 24px;\n" +
				"                text-align: left\n" +
				"            }\n" +
				"\n" +
				"            .es-header-body h3 a,\n" +
				"            .es-content-body h3 a,\n" +
				"            .es-footer-body h3 a {\n" +
				"                font-size: 20px;\n" +
				"                text-align: left\n" +
				"            }\n" +
				"\n" +
				"            .es-menu td a {\n" +
				"                font-size: 14px\n" +
				"            }\n" +
				"\n" +
				"            .es-header-body p,\n" +
				"            .es-header-body ul li,\n" +
				"            .es-header-body ol li,\n" +
				"            .es-header-body a {\n" +
				"                font-size: 14px\n" +
				"            }\n" +
				"\n" +
				"            .es-content-body p,\n" +
				"            .es-content-body ul li,\n" +
				"            .es-content-body ol li,\n" +
				"            .es-content-body a {\n" +
				"                font-size: 14px\n" +
				"            }\n" +
				"\n" +
				"            .es-footer-body p,\n" +
				"            .es-footer-body ul li,\n" +
				"            .es-footer-body ol li,\n" +
				"            .es-footer-body a {\n" +
				"                font-size: 14px\n" +
				"            }\n" +
				"\n" +
				"            .es-infoblock p,\n" +
				"            .es-infoblock ul li,\n" +
				"            .es-infoblock ol li,\n" +
				"            .es-infoblock a {\n" +
				"                font-size: 12px\n" +
				"            }\n" +
				"\n" +
				"            *[class=\"gmail-fix\"] {\n" +
				"                display: none\n" +
				"            }\n" +
				"\n" +
				"            .es-m-txt-c,\n" +
				"            .es-m-txt-c h1,\n" +
				"            .es-m-txt-c h2,\n" +
				"            .es-m-txt-c h3 {\n" +
				"                text-align: center\n" +
				"            }\n" +
				"\n" +
				"            .es-m-txt-r,\n" +
				"            .es-m-txt-r h1,\n" +
				"            .es-m-txt-r h2,\n" +
				"            .es-m-txt-r h3 {\n" +
				"                text-align: right\n" +
				"            }\n" +
				"\n" +
				"            .es-m-txt-l,\n" +
				"            .es-m-txt-l h1,\n" +
				"            .es-m-txt-l h2,\n" +
				"            .es-m-txt-l h3 {\n" +
				"                text-align: left\n" +
				"            }\n" +
				"\n" +
				"            .es-m-txt-r amp-img {\n" +
				"                float: right\n" +
				"            }\n" +
				"\n" +
				"            .es-m-txt-c amp-img {\n" +
				"                margin: 0 auto\n" +
				"            }\n" +
				"\n" +
				"            .es-m-txt-l amp-img {\n" +
				"                float: left\n" +
				"            }\n" +
				"\n" +
				"            .es-button-border {\n" +
				"                display: block\n" +
				"            }\n" +
				"\n" +
				"            a.es-button,\n" +
				"            button.es-button {\n" +
				"                font-size: 18px;\n" +
				"                display: block;\n" +
				"                border-right-width: 0px;\n" +
				"                border-left-width: 0px;\n" +
				"                border-top-width: 15px;\n" +
				"                border-bottom-width: 15px\n" +
				"            }\n" +
				"\n" +
				"            .es-adaptive table,\n" +
				"            .es-left,\n" +
				"            .es-right {\n" +
				"                width: 100%\n" +
				"            }\n" +
				"\n" +
				"            .es-content table,\n" +
				"            .es-header table,\n" +
				"            .es-footer table,\n" +
				"            .es-content,\n" +
				"            .es-footer,\n" +
				"            .es-header {\n" +
				"                width: 100%;\n" +
				"                max-width: 600px\n" +
				"            }\n" +
				"\n" +
				"            .es-adapt-td {\n" +
				"                display: block;\n" +
				"                width: 100%\n" +
				"            }\n" +
				"\n" +
				"            .adapt-img {\n" +
				"                width: 100%;\n" +
				"                height: auto\n" +
				"            }\n" +
				"\n" +
				"            td.es-m-p0 {\n" +
				"                padding: 0px\n" +
				"            }\n" +
				"\n" +
				"            td.es-m-p0r {\n" +
				"                padding-right: 0px\n" +
				"            }\n" +
				"\n" +
				"            td.es-m-p0l {\n" +
				"                padding-left: 0px\n" +
				"            }\n" +
				"\n" +
				"            td.es-m-p0t {\n" +
				"                padding-top: 0px\n" +
				"            }\n" +
				"\n" +
				"            td.es-m-p0b {\n" +
				"                padding-bottom: 0\n" +
				"            }\n" +
				"\n" +
				"            td.es-m-p20b {\n" +
				"                padding-bottom: 20px\n" +
				"            }\n" +
				"\n" +
				"            .es-mobile-hidden,\n" +
				"            .es-hidden {\n" +
				"                display: none\n" +
				"            }\n" +
				"\n" +
				"            tr.es-desk-hidden,\n" +
				"            td.es-desk-hidden,\n" +
				"            table.es-desk-hidden {\n" +
				"                width: auto;\n" +
				"                overflow: visible;\n" +
				"                float: none;\n" +
				"                max-height: inherit;\n" +
				"                line-height: inherit\n" +
				"            }\n" +
				"\n" +
				"            tr.es-desk-hidden {\n" +
				"                display: table-row\n" +
				"            }\n" +
				"\n" +
				"            table.es-desk-hidden {\n" +
				"                display: table\n" +
				"            }\n" +
				"\n" +
				"            td.es-desk-menu-hidden {\n" +
				"                display: table-cell\n" +
				"            }\n" +
				"\n" +
				"            .es-menu td {\n" +
				"                width: 1%\n" +
				"            }\n" +
				"\n" +
				"            table.es-table-not-adapt,\n" +
				"            .esd-block-html table {\n" +
				"                width: auto\n" +
				"            }\n" +
				"\n" +
				"            table.es-social {\n" +
				"                display: inline-block\n" +
				"            }\n" +
				"\n" +
				"            table.es-social td {\n" +
				"                display: inline-block\n" +
				"            }\n" +
				"\n" +
				"            .es-desk-hidden {\n" +
				"                display: table-row;\n" +
				"                width: auto;\n" +
				"                overflow: visible;\n" +
				"                max-height: inherit\n" +
				"            }\n" +
				"        }\n" +
				"    </style>\n" +
				"</head>\n" +
				"\n" +
				"<body>\n" +
				"    <div class=\"es-wrapper-color\">\n" +
				"        <!--[if gte mso 9]><v:background xmlns:v=\"urn:schemas-microsoft-com:vml\" fill=\"t\"> <v:fill type=\"tile\" color=\"#ffffff\"></v:fill> </v:background><![endif]-->\n" +
				"        <table class=\"es-wrapper\" width=\"100%\" cellspacing=\"0\" cellpadding=\"0\">\n" +
				"            <tr>\n" +
				"                <td valign=\"top\">\n" +
				"                    <table cellpadding=\"0\" cellspacing=\"0\" class=\"es-footer\" align=\"center\">\n" +
				"                        <tr>\n" +
				"                            <td align=\"center\">\n" +
				"                                <table bgcolor=\"#bcb8b1\" class=\"es-footer-body\" align=\"center\" cellpadding=\"0\"\n" +
				"                                    cellspacing=\"0\" width=\"600\">\n" +
				"                                    <tr>\n" +
				"                                        <td class=\"es-p20t es-p20b es-p40r es-p40l\" align=\"left\">\n" +
				"                                            <table cellpadding=\"0\" cellspacing=\"0\" width=\"100%\">\n" +
				"                                                <tr>\n" +
				"                                                    <td width=\"520\" align=\"center\" valign=\"top\">\n" +
				"                                                        <table cellpadding=\"0\" cellspacing=\"0\" width=\"100%\"\n" +
				"                                                            role=\"presentation\">\n" +
				"                                                            <tr>\n" +
				"                                                                <td align=\"center\" style=\"font-size: 0px\">\n" +
				"                                                                    <span>\n" +
				"                                                                        <amp-img\n" +
				"                                                                                src=\"https://gtnwuz.stripocdn.email/content/guids/CABINET_4505201e31a9675ec0a542be3e157fde4df6c230ab8c671d2329a18263233064/images/looped_square_on_white_background_NOH.png\"\n" +
				"                                                                                alt=\"Logo\" style=\"display: block\"\n" +
				"                                                                                height=\"60\" title=\"Logo\"\n" +
				"                                                                                width=\"60\">\n" +
				"                                                                        </amp-img>\n" +
				"                                                                    </span>\n" +
				"                                                                </td>\n" +
				"                                                            </tr>\n" +
				"                                                        </table>\n" +
				"                                                    </td>\n" +
				"                                                </tr>\n" +
				"                                            </table>\n" +
				"                                        </td>\n" +
				"                                    </tr>\n" +
				"                                </table>\n" +
				"                            </td>\n" +
				"                        </tr>\n" +
				"                    </table>\n" +
				"                    <table cellpadding=\"0\" cellspacing=\"0\" class=\"es-content\" align=\"center\">\n" +
				"                        <tr>\n" +
				"                            <td align=\"center\">\n" +
				"                                <table bgcolor=\"#efefef\" class=\"es-content-body\" align=\"center\" cellpadding=\"0\"\n" +
				"                                    cellspacing=\"0\" width=\"600\" style=\"border-radius: 20px 20px 0 0 \">\n" +
				"                                    <tr>\n" +
				"                                        <td class=\"es-p40t es-p40r es-p40l\" align=\"left\">\n" +
				"                                            <table cellpadding=\"0\" cellspacing=\"0\" width=\"100%\">\n" +
				"                                                <tr>\n" +
				"                                                    <td width=\"520\" align=\"center\" valign=\"top\">\n" +
				"                                                        <table cellpadding=\"0\" cellspacing=\"0\" width=\"100%\"\n" +
				"                                                            role=\"presentation\">\n" +
				"                                                            <tr>\n" +
				"                                                                <td align=\"left\" class=\"es-m-txt-c\"\n" +
				"                                                                    style=\"font-size: 0px\"><span>\n" +
				"                                                                        <amp-img\n" +
				"                                                                            src=\"https://gtnwuz.stripocdn.email/content/guids/CABINET_4505201e31a9675ec0a542be3e157fde4df6c230ab8c671d2329a18263233064/images/11256.png\"\n" +
				"                                                                            alt=\"Confirm email\"\n" +
				"                                                                            style=\"display: block;border-radius: 100px;font-size: 12px\"\n" +
				"                                                                            width=\"100\" title=\"Confirm email\"\n" +
				"                                                                            height=\"100\">\n" +
				"                                                                        </amp-img>\n" +
				"                                                                    </span>\n" +
				"                                                                </td>\n" +
				"                                                            </tr>\n" +
				"                                                        </table>\n" +
				"                                                    </td>\n" +
				"                                                </tr>\n" +
				"                                            </table>\n" +
				"                                        </td>\n" +
				"                                    </tr>\n" +
				"                                    <tr>\n" +
				"                                        <td class=\"es-p20t es-p40r es-p40l\" align=\"left\">\n" +
				"                                            <table cellpadding=\"0\" cellspacing=\"0\" width=\"100%\">\n" +
				"                                                <tr>\n" +
				"                                                    <td width=\"520\" align=\"center\" valign=\"top\">\n" +
				"                                                        <table cellpadding=\"0\" cellspacing=\"0\" width=\"100%\"\n" +
				"                                                            bgcolor=\"#fafafa\"\n" +
				"                                                            style=\"background-color: #fafafa;border-radius: 10px;border-collapse: separate\"\n" +
				"                                                            role=\"presentation\">\n" +
				"                                                            <tr>\n" +
				"                                                                <td align=\"left\" class=\"es-p20\">\n" +
				"                                                                    <h3>Bienvenido,&nbsp; " + name + "</h3>\n" +
				"                                                                    <p><br></p>\n" +
				"                                                                    <p>Nos alegra darle la bienvenida a nuestra comunidad.<br><br>\n" +
				"                                                                        Antes de comenzar su aventura en StudyCircle, debe verificar\n" +
				"                                                                        su dirección de correo electrónico. <br><br>Para ello,\n" +
				"                                                                        haga click en el botón más abajo.\n" +
				"																	 </p>\n" +
				"                                                                </td>\n" +
				"                                                            </tr>\n" +
				"                                                        </table>\n" +
				"                                                    </td>\n" +
				"                                                </tr>\n" +
				"                                            </table>\n" +
				"                                        </td>\n" +
				"                                    </tr>\n" +
				"                                </table>\n" +
				"                            </td>\n" +
				"                        </tr>\n" +
				"                    </table>\n" +
				"                    <table cellpadding=\"0\" cellspacing=\"0\" class=\"es-content\" align=\"center\">\n" +
				"                        <tr>\n" +
				"                            <td align=\"center\">\n" +
				"                                <table bgcolor=\"#efefef\" class=\"es-content-body\" align=\"center\" cellpadding=\"0\"\n" +
				"                                    cellspacing=\"0\" width=\"600\">\n" +
				"                                    <tr>\n" +
				"                                        <td class=\"es-p30t es-p40b es-p40r es-p40l\" align=\"left\">\n" +
				"                                            <table cellpadding=\"0\" cellspacing=\"0\" width=\"100%\">\n" +
				"                                                <tr>\n" +
				"                                                    <td width=\"520\" align=\"center\" valign=\"top\">\n" +
				"                                                        <table cellpadding=\"0\" cellspacing=\"0\" width=\"100%\"\n" +
				"                                                            role=\"presentation\">\n" +
				"                                                            <tr>\n" +
				"                                                                <td align=\"center\">\n" +
				"                                                                    <!--[if mso]><a href=\"\" target=\"_blank\" hidden> <v:roundrect xmlns:v=\"urn:schemas-microsoft-com:vml\" xmlns:w=\"urn:schemas-microsoft-com:office:word\" esdevVmlButton href=\"\" style=\"height:56px; v-text-anchor:middle; width:520px\" arcsize=\"50%\" stroke=\"f\" fillcolor=\"#69cdf1\"> <w:anchorlock></w:anchorlock> <center style='color:#ffffff; font-family:Imprima, Arial, sans-serif; font-size:22px; font-weight:700; line-height:22px; mso-text-raise:1px'>Confirmar correo electrónico</center> </v:roundrect></a><![endif]-->\n" +
				"                                                                    <!--[if !mso]><!-- --><span\n" +
				"                                                                        class=\"msohide es-button-border\"\n" +
				"                                                                        style=\"display: block;background: #69cdf1\"><a\n" +
				"                                                                            href=\"" + link + "\" class=\"es-button msohide\"\n" +
				"                                                                            target=\"_blank\"\n" +
				"                                                                            style=\"padding-left: 5px;padding-right: 5px;display: block;background: #69cdf1;border-color: #69cdf1\">Confirmar\n" +
				"                                                                            correo electrónico</a></span> <!--<![endif]--></td>\n" +
				"                                                            </tr>\n" +
				"                                                        </table>\n" +
				"                                                    </td>\n" +
				"                                                </tr>\n" +
				"                                            </table>\n" +
				"                                        </td>\n" +
				"                                    </tr>\n" +
				"                                    <tr>\n" +
				"                                        <td class=\"es-p40r es-p40l\" align=\"left\">\n" +
				"                                            <table cellpadding=\"0\" cellspacing=\"0\" width=\"100%\">\n" +
				"                                                <tr>\n" +
				"                                                    <td width=\"520\" align=\"center\" valign=\"top\">\n" +
				"                                                        <table cellpadding=\"0\" cellspacing=\"0\" width=\"100%\"\n" +
				"                                                            role=\"presentation\">\n" +
				"                                                            <tr>\n" +
				"                                                                <td align=\"left\">\n" +
				"                                                                    <p>Atentamente,<br><br>El equipo de StudyCircle</p>\n" +
				"                                                                </td>\n" +
				"                                                            </tr>\n" +
				"                                                            <tr>\n" +
				"                                                                <td align=\"center\" class=\"es-p40t es-p20b\"\n" +
				"                                                                    style=\"font-size:0\">\n" +
				"                                                                    <table border=\"0\" width=\"100%\" cellpadding=\"0\"\n" +
				"                                                                        cellspacing=\"0\" role=\"presentation\">\n" +
				"                                                                        <tr>\n" +
				"                                                                            <td\n" +
				"                                                                                style=\"border-bottom: 1px solid #666666;background: unset;height: 1px;width: 100%;margin: 0px\">\n" +
				"                                                                            </td>\n" +
				"                                                                        </tr>\n" +
				"                                                                    </table>\n" +
				"                                                                </td>\n" +
				"                                                            </tr>\n" +
				"                                                        </table>\n" +
				"                                                    </td>\n" +
				"                                                </tr>\n" +
				"                                            </table>\n" +
				"                                        </td>\n" +
				"                                    </tr>\n" +
				"                                </table>\n" +
				"                            </td>\n" +
				"                        </tr>\n" +
				"                    </table>\n" +
				"                    <table cellpadding=\"0\" cellspacing=\"0\" class=\"es-content\" align=\"center\">\n" +
				"                        <tr>\n" +
				"                            <td align=\"center\">\n" +
				"                                <table bgcolor=\"#efefef\" class=\"es-content-body\" align=\"center\" cellpadding=\"0\"\n" +
				"                                    cellspacing=\"0\" width=\"600\" style=\"border-radius: 0 0 20px 20px\">\n" +
				"                                    <tr>\n" +
				"                                        <td class=\"es-p20t es-p20b es-p40r es-p40l esdev-adapt-off\" align=\"left\">\n" +
				"                                            <table width=\"520\" cellpadding=\"0\" cellspacing=\"0\" class=\"esdev-mso-table\">\n" +
				"                                                <tr>\n" +
				"                                                    <td class=\"esdev-mso-td\" valign=\"top\">\n" +
				"                                                        <table cellpadding=\"0\" cellspacing=\"0\" align=\"left\"\n" +
				"                                                            class=\"es-left\">\n" +
				"                                                            <tr>\n" +
				"                                                                <td width=\"47\" align=\"center\" valign=\"top\">\n" +
				"                                                                    <table cellpadding=\"0\" cellspacing=\"0\" width=\"100%\"\n" +
				"                                                                        role=\"presentation\">\n" +
				"                                                                        <tr>\n" +
				"                                                                            <td align=\"center\" class=\"es-m-txt-l\"\n" +
				"                                                                                style=\"font-size: 0px\">\n" +
				"                                                                                <span>\n" +
				"                                                                                    <amp-img\n" +
				"                                                                                        src=\"https://gtnwuz.stripocdn.email/content/guids/CABINET_4505201e31a9675ec0a542be3e157fde4df6c230ab8c671d2329a18263233064/images/warningoutline256.png\"\n" +
				"                                                                                        alt=\"Warning\"\n" +
				"                                                                                        style=\"display: block;font-size: 12px\"\n" +
				"                                                                                        width=\"47\" title=\"Demo\"\n" +
				"                                                                                        height=\"47\">\n" +
				"                                                                                    </amp-img>\n" +
				"                                                                                </span>\n" +
				"                                                                            </td>\n" +
				"                                                                        </tr>\n" +
				"                                                                    </table>\n" +
				"                                                                </td>\n" +
				"                                                            </tr>\n" +
				"                                                        </table>\n" +
				"                                                    </td>\n" +
				"                                                    <td width=\"20\"></td>\n" +
				"                                                    <td class=\"esdev-mso-td\" valign=\"top\">\n" +
				"                                                        <table cellpadding=\"0\" cellspacing=\"0\" class=\"es-right\"\n" +
				"                                                            align=\"right\">\n" +
				"                                                            <tr>\n" +
				"                                                                <td width=\"453\" align=\"center\" valign=\"top\">\n" +
				"                                                                    <table cellpadding=\"0\" cellspacing=\"0\" width=\"100%\"\n" +
				"                                                                        role=\"presentation\">\n" +
				"                                                                        <tr>\n" +
				"                                                                            <td align=\"left\">\n" +
				"                                                                                <p style=\"font-size: 16px\">\n" +
				"                                                                                    Este enlace expira en 15 minutos. Si no ha sido usted quien ha solicitado el \n" +
				"                                                                                    registro en nuestra aplicación con esta dirección de correo electrónico, por favor, no \n" +
				"                                                                                    acceda al enlace de verificación y tome las medidas necesarias para proteger su cuenta\n" +
				"                                                                                </p>\n" +
				"                                                                            </td>\n" +
				"                                                                        </tr>\n" +
				"                                                                    </table>\n" +
				"                                                                </td>\n" +
				"                                                            </tr>\n" +
				"                                                        </table>\n" +
				"                                                    </td>\n" +
				"                                                </tr>\n" +
				"                                            </table>\n" +
				"                                        </td>\n" +
				"                                    </tr>\n" +
				"                                </table>\n" +
				"                            </td>\n" +
				"                        </tr>\n" +
				"                    </table>\n" +
				"                    <table cellpadding=\"0\" cellspacing=\"0\" class=\"es-footer\" align=\"center\">\n" +
				"                        <tr>\n" +
				"                            <td align=\"center\">\n" +
				"                                <table bgcolor=\"#bcb8b1\" class=\"es-footer-body\" align=\"center\" cellpadding=\"0\"\n" +
				"                                    cellspacing=\"0\" width=\"600\">\n" +
				"                                    <tr>\n" +
				"                                        <td class=\"es-p40t es-p30b es-p20r es-p20l\" align=\"left\">\n" +
				"                                            <table cellpadding=\"0\" cellspacing=\"0\" width=\"100%\">\n" +
				"                                                <tr>\n" +
				"                                                    <td width=\"560\" align=\"left\">\n" +
				"                                                        <table cellpadding=\"0\" cellspacing=\"0\" width=\"100%\"\n" +
				"                                                            role=\"presentation\">\n" +
				"                                                            <tr>\n" +
				"                                                                <td align=\"center\" class=\"es-p20b es-m-txt-c\"\n" +
				"                                                                    style=\"font-size: 0px\">\n" +
				"                                                                    <span>\n" +
				"                                                                        <amp-img\n" +
				"                                                                            src=\"https://gtnwuz.stripocdn.email/content/guids/CABINET_4505201e31a9675ec0a542be3e157fde4df6c230ab8c671d2329a18263233064/images/looped_square_on_white_background.png\"\n" +
				"                                                                            alt=\"Logo\"\n" +
				"                                                                            style=\"display: block;font-size: 12px\"\n" +
				"                                                                            title=\"Logo\" height=\"60\"\n" +
				"                                                                            width=\"60\">\n" +
				"                                                                        </amp-img>\n" +
				"                                                                    </span>\n" +
				"                                                                </td>\n" +
				"                                                            </tr>\n" +
				"                                                            <tr>\n" +
				"                                                                <td align=\"center\" class=\"es-m-txt-c es-p10t es-p20b\"\n" +
				"                                                                    style=\"font-size:0\">\n" +
				"                                                                    <table cellpadding=\"0\" cellspacing=\"0\"\n" +
				"                                                                        class=\"es-table-not-adapt es-social\"\n" +
				"                                                                        role=\"presentation\">\n" +
				"                                                                        <tr>\n" +
				"                                                                            <td align=\"center\" valign=\"top\"><a\n" +
				"                                                                                    target=\"_blank\"\n" +
				"                                                                                    href=\"mailto:stdycircleofficial@gmail.com\"><amp-img\n" +
				"                                                                                        src=\"https://gtnwuz.stripocdn.email/content/guids/CABINET_4505201e31a9675ec0a542be3e157fde4df6c230ab8c671d2329a18263233064/images/googlegmail256.png\"\n" +
				"                                                                                        alt=\"gm\" title=\"Gmail\"\n" +
				"                                                                                        height=\"24\"\n" +
				"                                                                                        width=\"24\"></amp-img></a></td>\n" +
				"                                                                        </tr>\n" +
				"                                                                    </table>\n" +
				"                                                                </td>\n" +
				"                                                            </tr>\n" +
				"                                                            <tr>\n" +
				"                                                                <td align=\"center\">\n" +
				"                                                                    <p style=\"font-size: 13px\">\n" +
				"                                                                        <span\n" +
				"                                                                            style=\"text-decoration: none\">Privacy Policy\n" +
				"                                                                        </span>\n" +
				"                                                                    </p>\n" +
				"                                                                </td>\n" +
				"                                                            </tr>\n" +
				"                                                            <tr>\n" +
				"                                                                <td align=\"center\" class=\"es-p20t\">\n" +
				"                                                                    <p>Copyright © 2023&nbsp;StudyCircle</p>\n" +
				"                                                                </td>\n" +
				"                                                            </tr>\n" +
				"                                                        </table>\n" +
				"                                                    </td>\n" +
				"                                                </tr>\n" +
				"                                            </table>\n" +
				"                                        </td>\n" +
				"                                    </tr>\n" +
				"                                </table>\n" +
				"                            </td>\n" +
				"                        </tr>\n" +
				"                    </table>\n" +
				"                    <table cellpadding=\"0\" cellspacing=\"0\" class=\"es-footer\" align=\"center\">\n" +
				"                        <tr>\n" +
				"                            <td align=\"center\">\n" +
				"                                <table bgcolor=\"#ffffff\" class=\"es-footer-body\" align=\"center\" cellpadding=\"0\"\n" +
				"                                    cellspacing=\"0\" width=\"600\">\n" +
				"                                    <tr>\n" +
				"                                        <td class=\"es-p20\" align=\"left\">\n" +
				"                                            <table cellpadding=\"0\" cellspacing=\"0\" width=\"100%\">\n" +
				"                                                <tr>\n" +
				"                                                    <td width=\"560\" align=\"left\">\n" +
				"                                                        <table cellpadding=\"0\" cellspacing=\"0\" width=\"100%\">\n" +
				"                                                            <tr>\n" +
				"                                                                <td align=\"center\" style=\"display: none\"></td>\n" +
				"                                                            </tr>\n" +
				"                                                        </table>\n" +
				"                                                    </td>\n" +
				"                                                </tr>\n" +
				"                                            </table>\n" +
				"                                        </td>\n" +
				"                                    </tr>\n" +
				"                                </table>\n" +
				"                            </td>\n" +
				"                        </tr>\n" +
				"                    </table>\n" +
				"                </td>\n" +
				"            </tr>\n" +
				"        </table>\n" +
				"    </div>\n" +
				"</body>\n" +
				"\n" +
				"</html>";
	}
}
