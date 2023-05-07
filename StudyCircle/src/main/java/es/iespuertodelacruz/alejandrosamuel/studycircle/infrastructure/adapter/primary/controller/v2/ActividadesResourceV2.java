package es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.primary.controller.v2;

import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicBoolean;

import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.model.Curso;
import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.model.Tutor;
import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.port.primary.ICursoService;
import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.port.primary.ITutorService;
import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.port.primary.IUsuarioService;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.enums.RespuestasActividad;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.security.UserDetailsLogin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping(params = "id")
    public ResponseEntity<?> findById(@RequestParam("id") Integer id) {
        Actividad actividad = service.findById(id);
        if(Objects.isNull(actividad))
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(RespuestasActividad.ACTIVITY_NOT_FOUND.name());

        Curso curso = cursoService.findById(actividad.getCurso().getId());
        int idTutorActividad = curso.getMateriaTutor().getTutor().getId();
        Tutor tutor = tutorService.findTutorByUsername(getUsernameUsuario());
        if(idTutorActividad == tutor.getId())
            return ResponseEntity.ok(mapper.toDTO(actividad));

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(RespuestasActividad.ACTIVITY_FORBIDDEN.name());
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody ActividadDTO request) {
        Tutor tutor = tutorService.findTutorByUsername(getUsernameUsuario());
        AtomicBoolean cursoExistente = new AtomicBoolean(false);
        tutor.getMateriasTutor().forEach(mt ->  {
            Optional<Curso> curso = mt.getCursosTutor().stream().filter(c -> c.getId() == request.getCurso().getId()).findFirst();
            if(curso.isPresent())
                cursoExistente.set(true);
        });

        if(!cursoExistente.get())
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(RespuestasActividad.COURSE_ACTIVITY_NOT_VALID.name());

        Actividad actividad = mapper.toDomainPost(request);
        actividad = service.create(actividad);
        return ResponseEntity.ok(mapper.toDTO(actividad));
    }

    
    @PutMapping
    public ResponseEntity<?> update(@RequestBody ActividadDTO request) {
        if(Objects.isNull(service.findById(request.getId())))
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(RespuestasActividad.ACTIVITY_NOT_FOUND.name());

        return ResponseEntity.ok(mapper.toDTO(service.update(mapper.toDomainPut(request))));
    }

    
    @DeleteMapping(params = "id")
    public ResponseEntity<?> delete(@RequestParam("id") Integer id) {
        Actividad actividad = service.findById(id);
        if(Objects.isNull(actividad))
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(RespuestasActividad.ACTIVITY_NOT_FOUND.name());
        Curso curso = cursoService.findById(actividad.getCurso().getId());
        Tutor tutor = tutorService.findTutorByUsername(getUsernameUsuario());
        if(curso.getMateriaTutor().getTutor().getId() == tutor.getId()) {
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
