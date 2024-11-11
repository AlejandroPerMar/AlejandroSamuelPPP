package es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.primary.controller.v2;

import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;

import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.model.Alumno;
import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.model.Curso;
import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.model.Tutor;
import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.port.primary.IAlumnoService;
import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.port.primary.ICursoService;
import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.port.primary.ITutorService;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.enums.RespuestasActividad;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.security.UserDetailsLogin;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.utils.ObjectUtils;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.*;

import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.model.Actividad;
import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.port.primary.IActividadService;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.primary.dto.ActividadDTO;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.primary.mapper.ActividadDTOMapper;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.config.SwaggerConfig;
import io.swagger.annotations.Api;

@Api(tags = {SwaggerConfig.ACTIVIDAD_V2_TAG})
@RestController
@CrossOrigin
@RequestMapping("/api/v2/actividades")
public class ActividadesResourceV2 {

    @Autowired
    private IActividadService service;

    @Autowired
    private ICursoService cursoService;

    @Autowired
    private ITutorService tutorService;

    @Autowired
    private ActividadDTOMapper mapper;

    @Autowired
    private IAlumnoService alumnoService;

    @GetMapping("{id}")
    @ApiOperation(
            value= "Obtener Actividad por su ID",
            notes= """
                    Parámetros solicitados:\s
                    • "Integer id. ID de la actividad a buscar
                    
                    Posibles respuestas:\s
                    • "ACTIVITY_NOT_FOUND" (String). Indica que no se ha encontrado ninguna actividad con ese ID
                    • "ACTIVITY_FORBIDDEN" (String). Indica que la actividad con ese ID no pertenece al usuario autenticado
                    • ActividadDTO. Se devuelve en formato JSON la actividad con el ID indicado
                    """
    )
    public ResponseEntity<?> findById(@PathVariable("id") Integer id) {
        Actividad actividad = service.findById(id);
        if(Objects.isNull(actividad))
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(RespuestasActividad.ACTIVITY_NOT_FOUND.name());

        Curso curso = cursoService.findById(actividad.getCurso().getId());
        Integer idTutorActividad = curso.getMateriaTutor().getTutor().getId();
        Tutor tutor = tutorService.findTutorByUsername(getUsernameUsuario());
        if(Objects.equals(idTutorActividad, tutor.getId()))
            return ResponseEntity.ok(mapper.toDTO(actividad));

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(RespuestasActividad.ACTIVITY_FORBIDDEN.name());
    }

    @PostMapping
    @ApiOperation(
            value= "Crear nueva Actividad",
            notes= """
                    Parámetros solicitados:\s
                    • "ActividadDTO. Actividad a crear
                    
                    Posibles respuestas:\s
                    • "TUTOR_PROFILE_NOT_CREATED" (String). Indica que el usuario autenticado no tiene un perfil de tutor creado
                    • "INVALID_ACTIVITY_FORMAT" (String). Indica que la actividad aportada no tiene un formato válido
                    • "COURSE_ACTIVITY_NOT_VALID" (String). Indica que el curso vinculado a la actividad no es válido para el usuario autenticado
                    • ActividadDTO. Se devuelve en formato JSON la actividad creada exitosamente
                    """
    )
    public ResponseEntity<?> create(@RequestBody ActividadDTO request) {
        Tutor tutor = tutorService.findTutorByUsername(getUsernameUsuario());
        if(Objects.isNull(tutor)) return ResponseEntity.status(HttpStatus.FORBIDDEN).body(RespuestasActividad.TUTOR_PROFILE_NOT_CREATED.name());

        if(!ObjectUtils.notNullNorEmpty(request, request.getCurso(), request.getNombre(), request.getDescripcion(), request.getFechaActividad()))
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(RespuestasActividad.INVALID_ACTIVITY_FORMAT.name());  // Si los parámetros de la actividad recibida no son válidos

        AtomicBoolean cursoExistente = new AtomicBoolean(false);
        tutor.getMateriasTutor().forEach(mt ->  {
            Optional<Curso> curso = mt.getCursosTutor().stream()
                    .filter(c -> Objects.equals(c.getId(), request.getCurso().getId())).findFirst();    //Comprobamos que el curso vinculado a la actividad sea del tutor autenticado
            if(curso.isPresent())
                cursoExistente.set(true);
        });

        if(!cursoExistente.get())
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(RespuestasActividad.COURSE_ACTIVITY_NOT_VALID.name());

        Actividad actividad = mapper.toDomainPost(request);
        actividad = service.create(actividad);
        return ResponseEntity.ok(mapper.toDTO(actividad));
    }

    @GetMapping("/numeroActividadesPendientes/{idUsuario}")
    @ApiOperation(
            value = "Obtener cantidad de actividades pendientes",
            notes = """
                    Posibles respuestas:s
                    • "STUDENT_PROFILE_NOT_CREATED" (String). Indica que el usuario indicado no tiene un perfil de alumno creado
                    • Integer numeroActividadesPendientes. Se da la cantidad de actividades abiertas hasta la fecha actual que tiene el alumno
                    """
    )
    public ResponseEntity<?> getNumeroActividadesPendientes(@PathVariable("idUsuario") Integer idUsuario) {
        Alumno alumno = alumnoService.findAlumnoByIdUsuario(idUsuario);
        if(Objects.nonNull(alumno)) {
            Integer numeroActividadesPendientes = service.getNumeroActividadesPendientes(alumno.getId());
            return ResponseEntity.ok(numeroActividadesPendientes);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(RespuestasActividad.STUDENT_PROFILE_NOT_CREATED.name());
    }
    
    @PutMapping
    @ApiOperation(
            value= "Actualizar una Actividad",
            notes= """
                    Parámetros solicitados:\s
                    • "ActividadDTO. Actividad a actualizar
                    
                    Posibles respuestas:\s
                    • "ACTIVITY_NOT_FOUND" (String). Indica que la actividad que se intenta actualizar no se ha podido encontrar
                    • "ACTIVITY_FORBIDDEN" (String). Indica que la actividad que se intenta actualizar no pertenece al usuario autenticado
                    • ActividadDTO. Se devuelve en formato JSON la actividad actualizada exitosamente
                    """
    )
    public ResponseEntity<?> update(@RequestBody ActividadDTO request) {
        Actividad actividad = service.findById(request.getId());
        if(Objects.isNull(actividad))
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(RespuestasActividad.ACTIVITY_NOT_FOUND.name());
        Tutor tutor = tutorService.findTutorByUsername(getUsernameUsuario());
        if(actividad.getCurso().getMateriaTutor().getTutor().getId().equals(tutor.getId()))
            return ResponseEntity.ok(mapper.toDTO(service.update(mapper.toDomainPut(request))));

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(RespuestasActividad.ACTIVITY_FORBIDDEN.name());
    }

    
    @DeleteMapping("/{id}")
    @ApiOperation(
            value= "Eliminar una Actividad",
            notes= """
                    Parámetros solicitados:\s
                    • "Integer id. ID de la actividad a eliminar
                    
                    Posibles respuestas:\s
                    • "ACTIVITY_NOT_FOUND" (String). Indica que no se ha encontrado la actividad con el ID indicado
                    • "ACTIVITY_NOT_REMOVED" (String). Indica que la actividad no se ha podido eliminar por no pertenecer al usuario
                    • "ACTIVITY_REMOVED" (String). Indica que la actividad se ha borrado exitosamente
                    """
    )
    public ResponseEntity<?> delete(@PathVariable("id") Integer id) {
        Actividad actividad = service.findById(id);
        if(Objects.isNull(actividad))
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(RespuestasActividad.ACTIVITY_NOT_FOUND.name());
        Curso curso = cursoService.findById(actividad.getCurso().getId());
        Tutor tutor = tutorService.findTutorByUsername(getUsernameUsuario());
        if(Objects.equals(curso.getMateriaTutor().getTutor().getId(), tutor.getId())) {
            boolean eliminado = service.delete(id);
            if(eliminado)
                return ResponseEntity.ok(RespuestasActividad.ACTIVITY_REMOVED.name());
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(RespuestasActividad.ACTIVITY_NOT_REMOVED.name());


    }

    private String getUsernameUsuario() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return ((UserDetailsLogin) principal).getUsername();
    }
}
