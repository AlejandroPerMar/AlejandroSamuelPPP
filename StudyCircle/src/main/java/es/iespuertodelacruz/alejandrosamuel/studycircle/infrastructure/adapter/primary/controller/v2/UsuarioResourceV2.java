package es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.primary.controller.v2;

import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.model.Usuario;
import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.model.enums.EstadosUsuario;
import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.port.primary.IHTMLBuilder;
import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.port.primary.IUsuarioService;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.entity.TokenConfirmacionEntity;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.config.EmailSender;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.security.UserDetailsLogin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping("resendconfirmationemail")
    public ResponseEntity<?> reenviarConfirmacionToken() {
        Usuario usuario = usuarioService.findByUsername(getUsernameUsuario());
        TokenConfirmacionEntity token = usuarioService.resendConfirmationToken(usuario);
        String link = "http://localhost:8080/api/register/confirm?token=" + token.getToken();
        emailSender.enviar(
                usuario.getEmail(),
                htmlBuilder.buildEmail(usuario.getNombreCompleto(), link));
        if(usuario.getEstado().equals(EstadosUsuario.STATUS_PENDING_VERIFICATION)) {

        }
        return null;
    }

    private String getUsernameUsuario() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String nombreAutenticado = ((UserDetailsLogin) principal).getUsername();
        return nombreAutenticado;
    }
}
