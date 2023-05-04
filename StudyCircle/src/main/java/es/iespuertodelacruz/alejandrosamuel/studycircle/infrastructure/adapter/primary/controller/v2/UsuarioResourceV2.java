package es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.primary.controller.v2;

import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.model.Usuario;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.enums.EstadosUsuario;
import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.port.primary.IHTMLBuilder;
import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.port.primary.IUsuarioService;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.entity.TokenConfirmacionEntity;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.config.EmailSender;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.security.UserDetailsLogin;
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
    private HttpServletRequest request;

    @GetMapping("resendconfirmationemail")
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

    private String getUsernameUsuario() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return ((UserDetailsLogin) principal).getUsername();
    }

    public String getBaseUrl() {
        return String.format("%s://%s:%d", request.getScheme(), request.getServerName(), request.getServerPort());
    }
}
