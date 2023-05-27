package es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.primary.controller.v2;

import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.model.Tutor;
import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.model.Usuario;
import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.port.primary.ITutorService;
import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.port.primary.IUsuarioService;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.primary.dto.MateriaDTO;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.primary.dto.TutorDTO;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.primary.mapper.DTOJustIdMapper;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.primary.mapper.MateriaDTOMapper;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.primary.mapper.TutorDTOMapper;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.config.SwaggerConfig;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.enums.RespuestasTutor;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.enums.Roles;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.security.UserDetailsLogin;
import io.swagger.annotations.Api;
import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.model.Rol;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@Api(tags = {SwaggerConfig.TUTOR_V2_TAG})
@RestController
@CrossOrigin
@RequestMapping("/api/v2/tutores")
public class TutoresResourceV2 {

    @Autowired
    private ITutorService service;

    @Autowired
    private TutorDTOMapper mapper;

    @Autowired
    private IUsuarioService usuarioService;

    @Autowired
    private MateriaDTOMapper materiaDTOMapper;

    @Autowired
    private DTOJustIdMapper dtoJustIdMapper;

    @GetMapping
    @ApiOperation(
            value= "Obtener el perfil de tutor del usuario autenticado",
            notes= """
                    Posibles respuestas:\s
                    • "TUTOR_PROFILE_NOT_FOUND" (String). Indica que el usuario no tiene perfil de tutor creado
                    • "TutorDTO. Devuelve el perfil de tutor del usuario autenticado
                    """
    )
    public ResponseEntity<?> getTutor() {
        Tutor tutor = service.findTutorByUsername(getUsernameUsuario());
        TutorDTO tutorDTO = mapper.toDTO(tutor);
        if(Objects.isNull(tutorDTO))
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(RespuestasTutor.TUTOR_PROFILE_NOT_FOUND.name());

        return ResponseEntity.ok().body(tutorDTO);
    }

    @PostMapping
    @ApiOperation(
            value= "Crear perfil de tutor al usuario autenticado",
            notes= """
                    Parámetros solicitados:\s
                    • "List<MateriaDTO> materias. Listados de materias que el usuario ha indicado que puede impartir
                    
                    Posibles respuestas:\s
                    • "TUTOR_PROFILE_ALREADY_CREATED" (String). Indica que ya existe un perfil de tutor para el usuario autenticado
                    • "TUTOR_PROFILE_NOT_CREATED" (String). Indica que no se ha podido crear el perfil de tutor
                    • "TutorDTO. Devuelve el perfil de tutor que se acaba de crear para el usuario autenticado
                    """
    )
    public ResponseEntity<?> createTutor(@RequestBody List<MateriaDTO> materias) {
        Usuario usuario = usuarioService.findByUsername(getUsernameUsuario());
        if(Objects.nonNull(service.findTutorByIdUsuario(usuario.getId())))
            return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body(RespuestasTutor.TUTOR_PROFILE_ALREADY_CREATED.name());

        TutorDTO tutorDTO = mapper.toDTO(service.create(usuario, materias.stream().map(dtoJustIdMapper::toDomain).toList()));
        if(Objects.isNull(tutorDTO))
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(RespuestasTutor.TUTOR_PROFILE_NOT_CREATED.name());

        return ResponseEntity.ok().body(tutorDTO);
    }

    @PutMapping
    @ApiOperation(
            value= "Actualizar perfil de tutor al usuario autenticado",
            notes= """
                    Parámetros solicitados:\s
                    • "List<MateriaDTO> materias. Listados de materias que el usuario ha indicado que puede impartir
                    
                    Posibles respuestas:\s
                    • "TUTOR_PROFILE_NOT_UPDATED" (String). Indica que no se ha podido actualizar el perfil de tutor para el usuario autenticado
                    • "TUTOR_PROFILE_NOT_FOUND" (String). Indica que no se ha encontrado un perfil de tutor para el usuario autenticado
                    • "TutorDTO. Devuelve el perfil de tutor que se acaba de crear para el usuario autenticado
                    """
    )
    public ResponseEntity<?> updateTutor(@RequestBody List<MateriaDTO> materias) {
        Usuario usuario = usuarioService.findByUsername(getUsernameUsuario());
        if(usuario.getRoles().stream().map(Rol::getRol).anyMatch(r -> r.equals(Roles.ROLE_TUTOR.name()))) {
            TutorDTO tutorDTO = mapper.toDTO(service.update(usuario, materias.stream().map(dtoJustIdMapper::toDomain).toList()));
            if(Objects.isNull(tutorDTO))
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(RespuestasTutor.TUTOR_PROFILE_NOT_UPDATED);

            return ResponseEntity.ok().body(tutorDTO);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(RespuestasTutor.TUTOR_PROFILE_NOT_FOUND);

    }

    @GetMapping("/numeroAlumnos")
    @ApiOperation(
            value = "Obtener cantidad de alumnos diferentes de los cursos del tutor",
            notes = """
                    Posibles respuestas:s
                    • Integer numAlumnos. Indica la cantidad de alumnos diferentes que tiene el tutor en sus cursos
                    • "TUTOR_PROFILE_NOT_CREATED" (String). Indica que no hay un perfil de tutor para el usuario autenticado
                    """
    )
    public ResponseEntity<?> getNumeroAlumnosTutor() {
        Tutor tutor = service.findTutorByUsername(getUsernameUsuario());
        if(Objects.nonNull(tutor)) {
            Integer numAlumnos = service.countStudents(tutor);
            return ResponseEntity.ok(numAlumnos);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(RespuestasTutor.TUTOR_PROFILE_NOT_CREATED.name());

    }

    private String getUsernameUsuario() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return ((UserDetailsLogin) principal).getUsername();
    }
}
