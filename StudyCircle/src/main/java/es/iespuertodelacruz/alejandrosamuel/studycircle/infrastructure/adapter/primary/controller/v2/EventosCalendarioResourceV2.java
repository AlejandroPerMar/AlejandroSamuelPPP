package es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.primary.controller.v2;

import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.model.EventoCalendario;
import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.model.Usuario;
import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.port.primary.IEventoCalendarioService;
import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.port.primary.IUsuarioService;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.primary.dto.EventoCalendarioDTO;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.primary.mapper.EventoCalendarioDTOMapper;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.config.SwaggerConfig;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.enums.PerfilUsuario;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.enums.RespuestasCurso;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.enums.RespuestasEventoCalendario;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.security.UserDetailsLogin;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.utils.ObjectUtils;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Api(tags = {SwaggerConfig.EVENTO_CALENDARIO_V2_TAG})
@RestController
@CrossOrigin
@RequestMapping("/api/v2/eventosCalendario")
public class EventosCalendarioResourceV2 {

    @Autowired
    private EventoCalendarioDTOMapper mapper;

    @Autowired
    private IEventoCalendarioService service;

    @Autowired
    private IUsuarioService usuarioService;

    @PersistenceContext
    private EntityManager entityManager;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody EventoCalendarioDTO eventoCalendarioDTO) {
        if(ObjectUtils.notNullNorEmpty(eventoCalendarioDTO))
            if(ObjectUtils.notNullNorEmpty(eventoCalendarioDTO.getNombre(),
                    eventoCalendarioDTO.getFechaEvento(),
                    eventoCalendarioDTO.getDescripcion(),
                    eventoCalendarioDTO.getPerfilUsuario())) {
                try {
                    PerfilUsuario.valueOf(eventoCalendarioDTO.getPerfilUsuario());

                }catch (IllegalArgumentException ex) {
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(RespuestasEventoCalendario.INVALID_USER_PROFILE.name());
                }
                Usuario usuario = usuarioService.findByUsername(getUsernameUsuario());
                EventoCalendario eventoCalendario = mapper.toDomain(eventoCalendarioDTO);
                eventoCalendario.setUsuario(usuario);
                eventoCalendario = service.create(eventoCalendario);
                entityManager.clear();
                eventoCalendario = service.findById(eventoCalendario.getId());
                return ResponseEntity.ok(mapper.toDTO(eventoCalendario));
            }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(RespuestasCurso.COURSE_DTO_NOT_VALID.name());
    }

    @GetMapping("/perfilTutor")
    public ResponseEntity<?> findByPerfilUsuarioTutor() {
        Usuario usuario = usuarioService.findByUsername(getUsernameUsuario());
        List<EventoCalendario> eventosPerfilUsuarioTutor = service.findByPerfilUsuario(usuario.getId(), PerfilUsuario.TUTOR_PROFILE);
        return ResponseEntity.ok(eventosPerfilUsuarioTutor.stream().map(mapper::toDTO).toList());
    }

    @GetMapping("/perfilAlumno")
    public ResponseEntity<?> findByPerfilUsuarioAlumno() {
        Usuario usuario = usuarioService.findByUsername(getUsernameUsuario());
        List<EventoCalendario> eventosPerfilUsuarioAlumno = service.findByPerfilUsuario(usuario.getId(), PerfilUsuario.STUDENT_PROFILE);
        return ResponseEntity.ok(eventosPerfilUsuarioAlumno.stream().map(mapper::toDTO).toList());
    }

    private String getUsernameUsuario() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return ((UserDetailsLogin) principal).getUsername();
    }
}
