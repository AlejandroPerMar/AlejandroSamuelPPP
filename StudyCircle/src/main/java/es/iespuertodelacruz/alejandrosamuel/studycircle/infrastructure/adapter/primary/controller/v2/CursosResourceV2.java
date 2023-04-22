package es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.primary.controller.v2;

import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.model.Curso;
import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.model.Tutor;
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

@Api(tags = {SwaggerConfig.MATERIA_V2_TAG})
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

    @PostMapping
    public ResponseEntity<?> create(@RequestBody CursoDTO curso) {
        if(ObjectUtils.notNullNorEmpty(curso)) {
            if(ObjectUtils.notNullNorEmpty(curso.getTitulo(), curso.getPrecioHora(), 
                    curso.getAlumnosCurso(), curso.getMateriaTutor())) {
                Tutor tutor = tutorService.findTutorByUsername(getUsernameUsuario());
                Curso cursoPost = mapper.toDomainPost(curso);
                cursoPost.getMateriaTutor().setTutor(tutor);
                cursoService.create(cursoPost);
            }
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(RespuestasCurso.COURSE_DTO_NOT_VALID);
    }

    private String getUsernameUsuario() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return ((UserDetailsLogin) principal).getUsername();
    }
}
