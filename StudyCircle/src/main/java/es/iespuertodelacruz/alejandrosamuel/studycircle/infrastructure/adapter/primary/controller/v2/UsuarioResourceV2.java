package es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.primary.controller.v2;

import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.model.Rol;
import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.model.Usuario;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.config.SwaggerConfig;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.enums.EstadosUsuario;
import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.port.primary.IHTMLBuilder;
import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.port.primary.IUsuarioService;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.entity.TokenConfirmacionEntity;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.config.EmailSender;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.security.JwtService;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.security.UserDetailsLogin;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

@Api(tags = {SwaggerConfig.USUARIO_V2_TAG})
@RestController
@CrossOrigin
@RequestMapping("/api/v2/usuarios")
public class UsuarioResourceV2 {

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

    @GetMapping("resendconfirmationemail")
    @ApiOperation(
            value= "Reenviar el correo de confirmación de email",
            notes= """
                    Posibles respuestas:\s
                    • "Token de verificación. Devuelve el token de verificación necesario para confirmar el correo electrónico
                    • "Estado de la cuenta de usuario. Devuelve el estado de la cuenta del usuario en caso de no estar pendiente de verificación
                    """
    )
    public ResponseEntity<?> reenviarConfirmacionToken() {
        Usuario usuario = usuarioService.findByUsername(getUsernameUsuario());

        if(usuario.getEstado().equals(EstadosUsuario.STATUS_PENDING_VERIFICATION.name())) {
            TokenConfirmacionEntity token = usuarioService.resendConfirmationToken(usuario);
            String link = getBaseUrl() + "/api/register/confirm?token=" + token.getToken();
            emailSender.enviar(
                    usuario.getEmail(),
                    htmlBuilder.buildEmail(usuario.getNombreCompleto(), link));
            return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.TEXT_PLAIN).body(token.getToken());
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).contentType(MediaType.TEXT_PLAIN).body(usuario.getEstado());
    }

    @GetMapping("activeUser")
    @ApiOperation(
            value= "Consultar si el usuario se encuentra activo",
            notes= """
                    Posibles respuestas:\s
                    • "Estado de la cuenta del usuario.
                    """
    )
    public ResponseEntity<?> getEstadoUsuario() {
        Usuario usuario = usuarioService.findByUsername(getUsernameUsuario());
        return ResponseEntity.ok(usuario.getEstado());
    }

    @GetMapping("renewToken")
    @ApiOperation(
            value = "Renovar toke Bearer de Autorización a partir de un token válido",
            notes = """
                    Posibles respuestas:\s
                    • "Token de Autorización. Devuelve un nuevo token de Autorización para el usuario vinculado al token dado
                    """
    )
    public ResponseEntity<?> renewTokenBearer() {
        Usuario usuario = usuarioService.findByUsername(getUsernameUsuario());
        List<String> roles = usuario.getRoles()
                .stream()
                .map(Rol::getRol)
                .collect(Collectors.toList());
        String token = jwtService.generateToken(usuario.getUsername(), roles);
        return ResponseEntity.ok(token);
    }

    private String getUsernameUsuario() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return ((UserDetailsLogin) principal).getUsername();
    }

    public String getBaseUrl() {
        return String.format("%s://%s:%d", request.getScheme(), request.getServerName(), request.getServerPort());
    }
}
