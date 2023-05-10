package es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.primary.controller.v2;

import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.model.Amistad;
import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.model.Usuario;
import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.port.primary.IAmistadService;
import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.port.primary.IUsuarioService;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.primary.dto.AmistadDTO;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.primary.mapper.AmistadDTOMapper;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.config.SwaggerConfig;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.enums.RespuestasAmistad;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.security.UserDetailsLogin;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.utils.ObjectUtils;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@Api(tags = {SwaggerConfig.AMISTAD_V2_TAG})
@RestController
@CrossOrigin
@RequestMapping("/api/v2/amistades")
public class AmistadesResourceV2 {

    @Autowired
    private IAmistadService service;

    @Autowired
    private IUsuarioService usuarioService;

    @Autowired
    private AmistadDTOMapper mapper;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody AmistadDTO amistadDTO) {
        if(ObjectUtils.notNullNorEmpty(amistadDTO, amistadDTO.getUsuario1(), amistadDTO.getUsuario2())) {
            Usuario usuario1 = usuarioService.findById(amistadDTO.getUsuario1().getId());
            Usuario usuario2 = usuarioService.findById(amistadDTO.getUsuario2().getId());
            if(ObjectUtils.notNullNorEmpty(usuario1, usuario2)) {
                Usuario usuario = usuarioService.findByUsername(getUsernameUsuario());
                boolean amistadUsuario = usuario1.getId().equals(usuario.getId());
                if(amistadUsuario && !usuario1.getId().equals(usuario2.getId())) {
                    Amistad amistad = service.create(mapper.toDomain(amistadDTO));
                    return ResponseEntity.ok(mapper.toDTO(amistad));
                }
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(RespuestasAmistad.FORBIDDEN_FRIENDSHIP_FOR_USER.name());
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(RespuestasAmistad.NON_EXISTING_USER1_OR_USER2.name());
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(RespuestasAmistad.INVALID_FRIENDSHIP_FORMAT.name());
    }

    private String getUsernameUsuario() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return ((UserDetailsLogin) principal).getUsername();
    }

}
