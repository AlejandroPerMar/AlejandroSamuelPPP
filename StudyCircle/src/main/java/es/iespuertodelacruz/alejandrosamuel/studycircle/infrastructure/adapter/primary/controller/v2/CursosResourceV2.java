package es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.primary.controller.v2;

import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.model.Alumno;
import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.model.Curso;
import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.model.MateriaTutor;
import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.model.Tutor;
import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.port.primary.*;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.primary.dto.CursoDTO;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.primary.mapper.CursoDTOMapper;
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
    private IMateriaService materiaService;

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
                    curso.getAlumnos(),
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

    @PutMapping("/agregarAlumno")
    @ApiOperation(
            value= "Agregar alumno al Curso",
            notes= """
                    Parámetros solicitados:\s
                    • "Integer idCurso. ID del curso al que se quiere agregar al alumno
                    • "Integer idAlumno. ID del alumno a agregar al curso
                    
                    Posibles respuestas:\s
                    • "NON_AUTHENTICATED_OWNER" (String). Indica que el curso no es propiedad del usuario autenticado
                    • "NON_EXISTING_COURSE_OR_STUDENT" (String). Indica que el curso y/o alumno indicados no existen
                    • "INVALID_PARAMETERS" (String). Indica que los parámetros indicados no son válidos
                    • "TUTOR_PROFILE_NOT_CREATED" (String). Indica que el usuario autenticado no cuenta con perfil de tutor
                    • "CursoDTO. Devuelve el curso actualizado
                    """
    )
    public ResponseEntity<?> addAlumno(@RequestParam("idCurso") Integer idCurso,
                                       @RequestParam("idAlumno") Integer idAlumno) {
        Tutor tutor = tutorService.findTutorByUsername(getUsernameUsuario());
        if(Objects.nonNull(tutor)) {
            if(ObjectUtils.notNullNorEmpty(idAlumno, idCurso)) {
                Curso curso = cursoService.findById(idCurso);
                Alumno alumno = alumnoService.findAlumnoById(idAlumno);
                if(ObjectUtils.notNullNorEmpty(curso, alumno)) {
                    if(curso.getMateriaTutor().getTutor().getId().equals(tutor.getId())) {
                        cursoService.addAlumnoFromCurso(curso, alumno);
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

    private String getUsernameUsuario() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return ((UserDetailsLogin) principal).getUsername();
    }
}
