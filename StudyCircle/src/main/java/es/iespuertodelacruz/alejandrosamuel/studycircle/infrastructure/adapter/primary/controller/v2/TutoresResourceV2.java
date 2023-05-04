package es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.primary.controller.v2;

import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.model.Tutor;
import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.model.Usuario;
import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.model.enums.RespuestasTutor;
import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.port.primary.ITutorService;
import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.port.primary.IUsuarioService;
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

    @GetMapping
    public ResponseEntity<?> getTutor() {
        Tutor tutor = service.findTutorByUsername(getUsernameUsuario());
        TutorDTO tutorDTO = mapper.toDTO(tutor);
        if(Objects.isNull(tutorDTO))
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(RespuestasTutor.TUTOR_PROFILE_NOT_FOUND.name());

        return ResponseEntity.ok().body(tutorDTO);
    }

    @PostMapping
    public ResponseEntity<?> createTutor(@RequestBody List<MateriaDTO> materias) {
        Usuario usuario = usuarioService.findByUsername(getUsernameUsuario());
        if(Objects.nonNull(service.findTutorByIdUsuario(usuario.getId())))
            return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body(RespuestasTutor.TUTOR_PROFILE_ALREADY_CREATED.name());

        TutorDTO tutorDTO = mapper.toDTO(service.create(usuario, materias.stream().map(materiaDTOMapper::toDomain).toList()));
        if(Objects.isNull(tutorDTO))
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(RespuestasTutor.TUTOR_PROFILE_NOT_CREATED.name());

        return ResponseEntity.ok().body(tutorDTO);
    }

    @PutMapping
    public ResponseEntity<?> updateTutor(@RequestBody List<MateriaDTO> materias) {
        Usuario usuario = usuarioService.findByUsername(getUsernameUsuario());
        if(usuario.getRoles().stream().map(Rol::getRol).anyMatch(r -> r.equals(Roles.ROLE_TUTOR.name()))) {
            TutorDTO tutorDTO = mapper.toDTO(service.update(usuario, materias.stream().map(materiaDTOMapper::toDomain).toList()));
            if(Objects.isNull(tutorDTO))
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(RespuestasTutor.TUTOR_PROFILE_NOT_UPDATED);

            return ResponseEntity.ok().body(tutorDTO);
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(RespuestasTutor.TUTOR_PROFILE_NOT_FOUND);

    }

    private String getUsernameUsuario() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return ((UserDetailsLogin) principal).getUsername();
    }
}
