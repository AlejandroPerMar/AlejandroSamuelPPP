package es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.primary.controller.v2;

import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.model.Alumno;
import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.model.Tutor;
import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.model.Usuario;
import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.port.primary.ITutorService;
import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.port.primary.IUsuarioService;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.primary.dto.AlumnoDTO;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.primary.dto.MateriaDTO;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.primary.dto.TutorDTO;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.primary.mapper.MateriaDTOMapper;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.primary.mapper.TutorDTOMapper;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.config.SwaggerConfig;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.security.UserDetailsLogin;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(SwaggerConfig.TUTOR_V2_TAG)
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

    @GetMapping
    public ResponseEntity<?> getTutor() {
        Tutor tutor = service.findTutorByUsername(getUsernameUsuario());
        TutorDTO tutorDTO = mapper.toDTOGet(tutor);
        if(tutorDTO == null)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Tutor no encontrado");

        return ResponseEntity.ok().body(tutorDTO);
    }

    @PostMapping
    public ResponseEntity<?> createTutor(@RequestBody TutorDTO tutorDTO, @RequestBody List<MateriaDTO> materias) {
        Usuario usuario = usuarioService.findByUsername(getUsernameUsuario());
        tutorDTO.setUsuario(mapper.toDTO(usuario));
        Tutor tutor = mapper.toDomain(tutorDTO);
        tutorDTO = mapper.toDTOGet(service.create(tutor, materias.stream().map(m -> materiaDTOMapper.toDomain(m)).toList()));
        if(tutorDTO == null)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Alumno no creado");

        return ResponseEntity.ok().body(tutorDTO);
    }

    /*@PutMapping
    public ResponseEntity<?> updateAlumno(@RequestBody AlumnoDTO alumnoDTO) {
        Alumno alumno = mapper.toDomain(alumnoDTO);
        if(alumno == null)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Formato incorrecto");
        alumno.setId(service.findAlumnoByUsername(getUsernameUsuario()).getId());
        alumnoDTO = mapper.toDTOGet(service.update(alumno));
        if(alumnoDTO == null)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Alumno no guardado");

        return ResponseEntity.ok().body(alumnoDTO);
    }*/

    private String getUsernameUsuario() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return ((UserDetailsLogin) principal).getUsername();
    }
}
