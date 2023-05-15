package es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.primary.controller.v2;

import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.model.EventoCalendario;
import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.model.Usuario;
import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.port.primary.IEventoCalendarioService;
import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.port.primary.IUsuarioService;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.primary.dto.EventoCalendarioDTO;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.primary.mapper.EventoCalendarioDTOMapper;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.config.SwaggerConfig;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.enums.PerfilUsuario;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.enums.RespuestasEventoCalendario;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.security.UserDetailsLogin;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.utils.ObjectUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
    @ApiOperation(
            value= "Crear Evento de Calendario personalizado",
            notes= """
                    Parámetros solicitados:\s
                    • "EventoCalendarioDTO eventoCalendarioDTO. Evento de calendario a crear
                    
                    Posibles respuestas:\s
                    • "INVALID_USER_PROFILE" (String). Indica que el perfil de usuario indicado no es válido (TUTOR_PROFILE o STUDENT_PROFILE)
                    • "CALENDAR_EVENT_DTO_NOT_VALID" (String). Indica que el Evento de Calendario indicado no es válido
                    • "EventoCalendarioDTO. Devuelve el Evento de Calendario creado
                    """
    )
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
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(RespuestasEventoCalendario.CALENDAR_EVENT_DTO_NOT_VALID.name());
    }

    @GetMapping("/perfilTutor")
    @ApiOperation(
            value= "Encontrar Eventos de Calendario del perfil de tutor del usuario autenticado",
            notes= """
                    Posibles respuestas:\s
                    • "List<EventoCalendarioDTO>. Devuelve el listado de eventos del perfil de tutor del usuario autenticado
                    """
    )
    public ResponseEntity<?> findByPerfilUsuarioTutor() {
        Usuario usuario = usuarioService.findByUsername(getUsernameUsuario());
        List<EventoCalendario> eventosPerfilUsuarioTutor = service.findByPerfilUsuario(usuario.getId(), PerfilUsuario.TUTOR_PROFILE);
        return ResponseEntity.ok(eventosPerfilUsuarioTutor.stream().map(mapper::toDTO).toList());
    }

    @GetMapping("/perfilAlumno")
    @ApiOperation(
            value= "Encontrar Eventos de Calendario del perfil de alumno del usuario autenticado",
            notes= """
                    Posibles respuestas:\s
                    • "List<EventoCalendarioDTO>. Devuelve el listado de eventos del perfil de alumno del usuario autenticado
                    """
    )
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
