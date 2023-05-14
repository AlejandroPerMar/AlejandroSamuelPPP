package es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.primary.controller.v2;

import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.model.Materia;
import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.model.Tutor;
import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.port.primary.IMateriaService;
import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.port.primary.ITutorService;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.primary.mapper.MateriaDTOMapper;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.config.SwaggerConfig;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.enums.RespuestasMateria;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.security.UserDetailsLogin;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@Api(tags = {SwaggerConfig.MATERIA_V2_TAG})
@RestController
@CrossOrigin
@RequestMapping("/api/v2/materias")
public class MateriasResourceV2 {

    @Autowired
    private IMateriaService service;

    @Autowired
    private ITutorService tutorService;

    @Autowired
    private MateriaDTOMapper mapper;

    @GetMapping("/nivelEstudios")
    public ResponseEntity<?> findByNivelEstudiosId(@RequestParam("idNivelEstudios") Integer idNivelEstudios) {
        return ResponseEntity.ok(service.findByNivelEstudiosId(idNivelEstudios).stream().map(mapper::toDTO).toList());
    }

    @GetMapping("{id}")
    public ResponseEntity<?> findById(@PathVariable("id") Integer id) {
        Materia materia = service.findById(id);

        if(Objects.isNull(materia))
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(RespuestasMateria.SUBJECT_NOT_FOUND.name());

        return ResponseEntity.ok(mapper.toDTO(materia));
    }

    @GetMapping("/tutor")
    public ResponseEntity<?> findByTutor() {
        Tutor tutor = tutorService.findTutorByUsername(getUsernameUsuario());
        if(Objects.isNull(tutor))
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(RespuestasMateria.TUTOR_PROFILE_NOT_CREATED.name());
        List<Materia> materiasTutor = service.findByTutor(tutor.getId());
        return ResponseEntity.ok(materiasTutor.stream().map(mapper::toDTO).toList());
    }

    private String getUsernameUsuario() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return ((UserDetailsLogin) principal).getUsername();
    }
}
