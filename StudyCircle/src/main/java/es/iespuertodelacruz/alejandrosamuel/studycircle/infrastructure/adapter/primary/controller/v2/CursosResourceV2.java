package es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.primary.controller.v2;

import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.model.Alumno;
import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.model.Curso;
import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.model.Tutor;
import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.port.primary.IAlumnoService;
import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.port.primary.ICursoService;
import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.port.primary.ITutorService;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.primary.dto.CursoDTO;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.primary.mapper.CursoDTOMapper;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.config.SwaggerConfig;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.enums.RespuestasCurso;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.security.UserDetailsLogin;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.utils.ObjectUtils;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

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
    private CursoDTOMapper mapper;

    @Autowired
    private IAlumnoService alumnoService;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody CursoDTO curso) {
        if(ObjectUtils.notNullNorEmpty(curso))
            if(ObjectUtils.notNullNorEmpty(curso.getTitulo(),
                    curso.getAlumnos(),
                    curso.getMateriaTutor(),
                    curso.getMateriaTutor().getMateria())) {
                Tutor tutor = tutorService.findTutorByUsername(getUsernameUsuario());
                Curso cursoPost = mapper.toDomainPost(curso);
                cursoPost.getMateriaTutor().setTutor(tutor);
                return ResponseEntity.ok(mapper.toDTOStudent(cursoService.create(cursoPost)));
            }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(RespuestasCurso.COURSE_DTO_NOT_VALID.name());
    }

    @PutMapping("/eliminarAlumno")
    public ResponseEntity<?> removeAlumno(@RequestParam("idCurso") Integer idCurso, @RequestParam("idAlumno") Integer idAlumno) {
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
    public ResponseEntity<?> addAlumno(@RequestParam("idCurso") Integer idCurso, @RequestParam("idAlumno") Integer idAlumno) {
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

    @GetMapping(path = "/alumno")
    public ResponseEntity<?> findByStudent() {
        Alumno alumno = alumnoService.findAlumnoByUsername(getUsernameUsuario());
        if(Objects.nonNull(alumno)) {
            List<Curso> cursosByAlumno = cursoService.findByAlumno(alumno.getId());
            return ResponseEntity.ok(cursosByAlumno.stream().map(mapper::toDTOStudent).toList());
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(RespuestasCurso.STUDENT_PROFILE_NOT_CREATED.name());
    }

    @GetMapping(path = "/tutor")
    public ResponseEntity<?> findByTutor() {
        Tutor tutor = tutorService.findTutorByUsername(getUsernameUsuario());
        if(Objects.nonNull(tutor)) {
            List<Curso> cursosByTutor = cursoService.findByIdTutor(tutor.getId());
            return ResponseEntity.ok(cursosByTutor.stream().map(mapper::toDTOTutor).toList());
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(RespuestasCurso.STUDENT_PROFILE_NOT_CREATED.name());
    }

    private String getUsernameUsuario() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return ((UserDetailsLogin) principal).getUsername();
    }
}
