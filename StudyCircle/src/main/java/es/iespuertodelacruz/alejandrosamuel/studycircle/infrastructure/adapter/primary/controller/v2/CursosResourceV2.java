package es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.primary.controller.v2;

import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.model.*;
import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.port.primary.*;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.primary.dto.CursoDTO;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.primary.mapper.CursoDTOMapper;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.entity.AlertaCursoAlumnoEntity;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.mapper.EntityJustIdMapper;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.config.SwaggerConfig;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.enums.RespuestasCurso;
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
import java.util.Objects;

@Api(tags = {SwaggerConfig.CURSO_V2_TAG})
@RestController
@CrossOrigin
@RequestMapping("/api/v2/cursos")
public class CursosResourceV2 {

    @Autowired
    private ICursoService cursoService;
    
    @Autowired
    private ITutorService tutorService;

    @Autowired
    private IMateriaTutorService materiaTutorService;

    @Autowired
    private CursoDTOMapper mapper;

    @Autowired
    private IAlumnoService alumnoService;

    @Autowired
    private IUsuarioService usuarioService;

    @Autowired
    private IAmistadService amistadService;

    @Autowired
    private IAlertaCursoAlumnoService alertaCursoAlumnoService;

    @Autowired
    private EntityJustIdMapper entityJustIdMapper;

    @PersistenceContext
    private EntityManager entityManager;

    @PostMapping
    @ApiOperation(
            value= "Crear nuevo Curso",
            notes= """
                    Parámetros solicitados:\s
                    • "CursoDTO. Curso a crear
                    
                    Posibles respuestas:\s
                    • "NON_AUTHORIZED_SUBJECT" (String). Indica que la materia vinculada al curso en creación no está disponible para el usuario autenticado
                    • "COURSE_DTO_NOT_VALID" (String). Indica que el curso aportado no cuenta con datos válidos
                    • "CursoDTO. Devuelve el curso que se ha creado exitosamente
                    """
    )
    public ResponseEntity<?> create(@RequestBody CursoDTO curso) {
        if(ObjectUtils.notNullNorEmpty(curso))
            if(ObjectUtils.notNullNorEmpty(curso.getTitulo(),
                    curso.getMateriaTutor(),
                    curso.getMateriaTutor().getMateria())) {
                Tutor tutor = tutorService.findTutorByUsername(getUsernameUsuario());
                MateriaTutor materiaTutor = materiaTutorService.findByMateriaTutor(curso.getMateriaTutor().getMateria().getId(), tutor.getId());
                if(Objects.nonNull(materiaTutor)) {
                    Curso cursoPost = mapper.toDomainPost(curso);
                    cursoPost.setMateriaTutor(materiaTutor);
                    cursoPost = cursoService.create(cursoPost);
                    entityManager.clear();
                    Curso cursoDomain = cursoService.findById(cursoPost.getId());
                    return ResponseEntity.ok(mapper.toDTOTutor(cursoDomain));
                }
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(RespuestasCurso.NON_AUTHORIZED_SUBJECT.name());
            }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(RespuestasCurso.COURSE_DTO_NOT_VALID.name());
    }

    @PutMapping("/changeTituloCurso")
    @ApiOperation(
            value= "Cambiar título de Curso",
            notes= """
                    Parámetros solicitados:\s
                    • "Integer idCurso. Curso a cambiar el título
                    • "String titulo. Nuevo título del curso
                    
                    Posibles respuestas:\s
                    • "NON_AUTHENTICATED_OWNER" (String). Indica que el curso no es propiedad del usuario autenticado
                    • "NON_EXISTING_COURSE" (String). Indica que el curso indicado no existe
                    • "INVALID_PARAMETERS" (String). Indica que los parámetros indicados no son válidos
                    • "TUTOR_PROFILE_NOT_CREATED" (String). Indica que el usuario autenticado no cuenta con perfil de tutor
                    • "CursoDTO. Devuelve el curso con el título actualizado
                    """
    )
    public ResponseEntity<?> changeTituloCurso(@RequestParam("idCurso") Integer idCurso, @RequestParam("titulo") String titulo) {
        Tutor tutor = tutorService.findTutorByUsername(getUsernameUsuario());
        if(Objects.nonNull(tutor)) {
            if(ObjectUtils.notNullNorEmpty(idCurso, titulo)) {
                Curso curso = cursoService.findById(idCurso);
                if(Objects.nonNull(curso)) {
                    if(curso.getMateriaTutor().getTutor().getId().equals(tutor.getId())) {  //Comprobamos que el curso pertenezca al usuario autenticado
                        curso = cursoService.changeTituloCurso(idCurso, titulo);
                        return ResponseEntity.ok(mapper.toDTOTutor(curso));
                    }
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(RespuestasCurso.NON_AUTHENTICATED_OWNER.name());
                }
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(RespuestasCurso.NON_EXISTING_COURSE.name());
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(RespuestasCurso.INVALID_PARAMETERS.name());
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(RespuestasCurso.TUTOR_PROFILE_NOT_CREATED.name());
    }

    @PutMapping("/eliminarAlumno")
    @ApiOperation(
            value= "Eliminar alumno de Curso",
            notes= """
                    Parámetros solicitados:\s
                    • "Integer idCurso. ID del curso del que se quiere eliminar al alumno
                    • "Integer idAlumno. ID del alumno a eliminar del curso
                    
                    Posibles respuestas:\s
                    • "NON_AUTHENTICATED_OWNER" (String). Indica que el curso no es propiedad del usuario autenticado
                    • "NON_EXISTING_COURSE_OR_STUDENT" (String). Indica que el curso y/o alumno indicados no existen
                    • "INVALID_PARAMETERS" (String). Indica que los parámetros indicados no son válidos
                    • "TUTOR_PROFILE_NOT_CREATED" (String). Indica que el usuario autenticado no cuenta con perfil de tutor
                    • "CursoDTO. Devuelve el curso actualizado
                    """
    )
    public ResponseEntity<?> removeAlumno(@RequestParam("idCurso") Integer idCurso,
                                          @RequestParam("idAlumno") Integer idAlumno) {
        Tutor tutor = tutorService.findTutorByUsername(getUsernameUsuario());
        if(Objects.nonNull(tutor)) {
            if(ObjectUtils.notNullNorEmpty(idAlumno, idCurso)) {
                Curso curso = cursoService.findById(idCurso);
                Alumno alumno = alumnoService.findAlumnoById(idAlumno);
                if(ObjectUtils.notNullNorEmpty(curso, alumno)) {
                    if(curso.getMateriaTutor().getTutor().getId().equals(tutor.getId())) {
                        cursoService.removeAlumnoFromCurso(curso, alumno);
                        return ResponseEntity.ok(mapper.toDTOTutor(cursoService.findById(idCurso)));
                    }
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(RespuestasCurso.NON_AUTHENTICATED_OWNER.name());
                }
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(RespuestasCurso.NON_EXISTING_COURSE_OR_STUDENT.name());
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(RespuestasCurso.INVALID_PARAMETERS.name());
        }


        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(RespuestasCurso.TUTOR_PROFILE_NOT_CREATED.name());
    }

    @PutMapping("aceptarInvitacionCursoAlumno")
    @ApiOperation(
            value= "Aceptar invitación al curso",
            notes= """
                    Parámetros solicitados:\s
                    • "Integer idAlertaCursoAlumno. ID de la alerta que se ha generado para el alumno (sólo él tiene acceso)
                    
                    Posibles respuestas:\s
                    • "STUDENT_OR_COURSE_NOT_FOUND" (String). Indica que el alumno o curso
                    • "ALERT_NOT_FOUND" (String). Indica que no se ha encontrado la alerta
                    • "CursoDTO. Devuelve el curso actualizado
                    """
    )
    public ResponseEntity<?> aceptarInvitacionCurso(@RequestParam("idAlertaCursoAlumno") Integer idAlertaCursoAlumno) {
        Alumno alumno = alumnoService.findAlumnoByUsername(getUsernameUsuario());
        AlertaCursoAlumno alertaCursoAlumno = alertaCursoAlumnoService.findById(idAlertaCursoAlumno);
        if(Objects.isNull(alertaCursoAlumno)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(RespuestasCurso.ALERT_NOT_FOUND.name());
        }
        Curso curso = alertaCursoAlumno.getCurso();
        if(ObjectUtils.notNullNorEmpty(alumno, curso)) {
            cursoService.addAlumnoToCurso(curso, alumno);
            alertaCursoAlumnoService.delete(alertaCursoAlumno.getId());
            entityManager.clear();
            return ResponseEntity.ok(mapper.toDTOTutor(cursoService.findById(curso.getId())));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(RespuestasCurso.STUDENT_OR_COURSE_NOT_FOUND.name());
    }

    @PostMapping("/inviteStudent")
    @ApiOperation(
            value = "Invitar alumno a curso",
            notes = """
                    Parámetros solicitados:\s
                    • "Integer idUser. ID del usuario al que se quiere invitar al curso
                    • "Integer idCourse. ID del curso al que se quiere invitar
                    
                    Posibles respuestas:\s
                    • "NON_AUTHENTICATED_OWNER" (String). Indica que el curso no es propiedad del usuario autenticado
                    • "STUDENT_NOT_FOUND_IN_CONTACTS" (String). Indica que el alumno que se quiere invitar no se encuentra en la lista de contactos del usuario
                    • "STUDENT_INVITED" (String). Indica que se ha invitado con éxito al alumno
                    • "STUDENT_OR_COURSE_NOT_FOUND" (String). Indica que el alumno o curso no se han encontrado
                    """
    )
    public ResponseEntity<?> inviteStudentToCourse(@RequestParam("idUser") Integer idUser, @RequestParam("idCourse") Integer idCourse) {
        Tutor tutor = tutorService.findTutorByUsername(getUsernameUsuario());
        Usuario usuario = usuarioService.findByUsername(getUsernameUsuario());
        Alumno alumno = alumnoService.findAlumnoByIdUsuario(idUser);
        Usuario usuarioAlumno = usuarioService.findById(idUser);
        Curso curso = cursoService.findById(idCourse);
        if(curso.getAlumnos().stream().anyMatch(a -> a.getId().equals(alumno.getId())) ||
                alertaCursoAlumnoService.findAlertasCursoAlumnoByUsername(usuarioAlumno.getUsername()).stream().anyMatch(a -> a.getCurso().getId().equals(idCourse))) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(RespuestasCurso.USER_ALREADY_IN_COURSE_OR_INVITED.name());
        }
        if(ObjectUtils.notNullNorEmpty(tutor, curso)) {
            if(!curso.getMateriaTutor().getTutor().getId().equals(tutor.getId())) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(RespuestasCurso.NON_AUTHENTICATED_OWNER.name());
            }
        }
        if(Objects.isNull(alumno) ||
                amistadService.findAmistadesByIdUsuario(usuario.getId()).stream().filter(us -> us.getId().equals(idUser)).findFirst().isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(RespuestasCurso.STUDENT_NOT_FOUND_IN_CONTACTS.name());
        }
        if(ObjectUtils.notNullNorEmpty(alumno, curso)) {
            alertaCursoAlumnoService.create(idUser, curso.getId());
            return ResponseEntity.ok(RespuestasCurso.STUDENT_INVITED.name());
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(RespuestasCurso.STUDENT_OR_COURSE_NOT_FOUND.name());
    }

    @DeleteMapping("/refuseInvitacion/{idInvitacion}")
    @ApiOperation(
            value = "Rechazar invitación a curso",
            notes = """
                    Parámetros solicitados:\s
                    • "Integer idInvitacion. ID de la alerta generada
                    
                    Posibles respuestas:\s
                    • "NON_AUTHENTICATED_OWNER" (String). Indica que el curso no es propiedad del usuario autenticado
                    • "STUDENT_NOT_FOUND_IN_CONTACTS" (String). Indica que el alumno que se quiere invitar no se encuentra en la lista de contactos del usuario
                    • "STUDENT_INVITED" (String). Indica que se ha invitado con éxito al alumno
                    • "STUDENT_OR_COURSE_NOT_FOUND" (String). Indica que el alumno o curso no se han encontrado
                    """
    )
    public ResponseEntity<?> rechazarInvitación(@PathVariable("idInvitacion") Integer idInvitacion) {
        Usuario usuario = usuarioService.findByUsername(getUsernameUsuario());
        AlertaCursoAlumno alerta = alertaCursoAlumnoService.findById(idInvitacion);
        if(ObjectUtils.notNullNorEmpty(usuario, alerta)) {
            if(alerta.getUsuario().getId().equals(usuario.getId())) {
                alertaCursoAlumnoService.delete(alerta.getId());
                return ResponseEntity.ok(RespuestasCurso.INVITATION_REMOVED.name());
            }
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(RespuestasCurso.STUDENT_OR_INVITATION_NOT_FOUND.name());
    }

    @GetMapping("/alumno")
    @ApiOperation(
            value= "Encontrar Cursos en los que está inscrito el alumno",
            notes= """
                    Posibles respuestas:\s
                    • "STUDENT_PROFILE_NOT_CREATED" (String). Indica que el usuario no tiene perfil de alumno creado
                    • "List<CursoDTO>. Devuelve el listado de cursos del alumno
                    """
    )
    public ResponseEntity<?> findByStudent() {
        Alumno alumno = alumnoService.findAlumnoByUsername(getUsernameUsuario());
        if(Objects.nonNull(alumno)) {
            List<Curso> cursosByAlumno = cursoService.findByAlumno(alumno.getId());
            return ResponseEntity.ok(cursosByAlumno.stream().map(mapper::toDTOStudent).toList());
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(RespuestasCurso.STUDENT_PROFILE_NOT_CREATED.name());
    }

    @GetMapping("/tutor")
    @ApiOperation(
            value= "Encontrar Cursos en los que está inscrito el tutor",
            notes= """
                    Posibles respuestas:\s
                    • "TUTOR_PROFILE_NOT_CREATED" (String). Indica que el usuario no tiene perfil de tutor creado
                    • "List<CursoDTO>. Devuelve el listado de cursos del tutor
                    """
    )
    public ResponseEntity<?> findByTutor() {
        Tutor tutor = tutorService.findTutorByUsername(getUsernameUsuario());
        if(Objects.nonNull(tutor)) {
            List<Curso> cursosByTutor = cursoService.findByIdTutor(tutor.getId());
            return ResponseEntity.ok(cursosByTutor.stream().map(mapper::toDTOTutor).toList());
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(RespuestasCurso.TUTOR_PROFILE_NOT_CREATED.name());
    }

    @GetMapping("/tutor/cantidadCursos/{idUsuario}")
    @ApiOperation(
            value= "Encontrar Cursos en los que está inscrito el tutor",
            notes= """
                    Posibles respuestas:\s
                    • "TUTOR_PROFILE_NOT_CREATED" (String). Indica que el usuario no tiene perfil de tutor creado
                    • "List<CursoDTO>. Devuelve la cantidad de cursos del tutor
                    """
    )
    public ResponseEntity<?> findCantidadCursosByTutor(@PathVariable("idUsuario") Integer idUsuario) {
        Tutor tutor = tutorService.findTutorByIdUsuario(idUsuario);
        if(Objects.nonNull(tutor)) {
            List<Curso> cursosByTutor = cursoService.findByIdTutor(tutor.getId());
            return ResponseEntity.ok(cursosByTutor.size());
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(RespuestasCurso.TUTOR_PROFILE_NOT_CREATED.name());
    }

    @GetMapping("/alumno/cantidadCursos/{idUsuario}")
    @ApiOperation(
            value= "Encontrar Cursos en los que está inscrito el alumno",
            notes= """
                    Posibles respuestas:\s
                    • "STUDENT_PROFILE_NOT_CREATED" (String). Indica que el usuario no tiene perfil de alumno creado
                    • "List<CursoDTO>. Devuelve la cantidad de cursos del tutor
                    """
    )
    public ResponseEntity<?> findCantidadCursosByAlumno(@PathVariable("idUsuario") Integer idUsuario) {
        Alumno alumno = alumnoService.findAlumnoByIdUsuario(idUsuario);
        if(Objects.nonNull(alumno)) {
            List<Curso> cursosByAlumno = cursoService.findByAlumno(alumno.getId());
            return ResponseEntity.ok(cursosByAlumno.size());
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(RespuestasCurso.STUDENT_PROFILE_NOT_CREATED.name());
    }

    @DeleteMapping("{id}")
    @ApiOperation(
            value = "Eliminar Curso por ID",
            notes = """
                    Posibles respuestas:\s
                    • "COURSE_REMOVED" (String). Indica que el curso se ha eliminado correctamente
                    • "COURSE_NOT_REMOVED" (String). Indica que el curso no se ha podido eliminar
                    """
    )
    public ResponseEntity<?> deleteCourse(@PathVariable("id") Integer id) {
        Tutor tutor = tutorService.findTutorByUsername(getUsernameUsuario());
        if(Objects.nonNull(tutor)) {
            List<Curso> cursosByTutor = cursoService.findByIdTutor(tutor.getId());
            Curso cursoEncontrado = cursosByTutor.stream().filter(c -> c.getId().equals(id)).findFirst().orElse(null);
            if(Objects.nonNull(cursoEncontrado)) {
                boolean delete = cursoService.delete(id);
                if(delete) {
                    return ResponseEntity.ok(RespuestasCurso.COURSE_REMOVED.name());
                }
            }
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(RespuestasCurso.COURSE_NOT_REMOVED.name());
    }

    private String getUsernameUsuario() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return ((UserDetailsLogin) principal).getUsername();
    }
}
