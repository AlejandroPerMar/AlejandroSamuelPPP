package es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.primary.controller.v2;

import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.model.Amistad;
import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.model.Usuario;
import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.port.primary.IAmistadService;
import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.port.primary.IUsuarioService;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.primary.dto.AmistadDTO;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.primary.mapper.AmistadDTOMapper;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.config.SwaggerConfig;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.enums.EstadosAmistad;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.enums.RespuestasAmistad;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.security.UserDetailsLogin;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.utils.ObjectUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

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
    private IAmistadService amistadService;

    @Autowired
    private AmistadDTOMapper mapper;

    @PostMapping
    @ApiOperation(
            value= "Solicitar nueva amistad",
            notes= """
                    Parámetros solicitados:\s
                    • "AmistadDTO. Amistad a crear
                    
                    Posibles respuestas:\s
                    • "ALREADY_EXISTING_FRIENDSHIP" (String). Indica que la relación de amistad entre ambos usuarios ya existe
                    • "FORBIDDEN_FRIENDSHIP_FOR_USER" (String). Indica que la amistad no es posible para el usuario autenticado
                    • "NON_EXISTING_USER1_OR_USER2" (String). Indica que el usuario1 y/o usuario2 no existen
                    • "INVALID_FRIENDSHIP_FORMAT" (String). Indica que el formato de la amistad no es válido
                    • "AmistadDTO. Devuelve la amistad que se ha creado exitosamente
                    """
    )
    public ResponseEntity<?> create(@RequestBody AmistadDTO amistadDTO) {
        if(ObjectUtils.notNullNorEmpty(amistadDTO, amistadDTO.getUsuario1(), amistadDTO.getUsuario2())) {
            Usuario usuario1 = usuarioService.findById(amistadDTO.getUsuario1().getId());
            Usuario usuario2 = usuarioService.findById(amistadDTO.getUsuario2().getId());
            if(ObjectUtils.notNullNorEmpty(usuario1, usuario2)) {
                // Comprobamos que no exista ya una amistad entre ambos usuarios
                if(Objects.nonNull(amistadService.findAmistadByIds(usuario1.getId(), usuario2.getId())))
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(RespuestasAmistad.ALREADY_EXISTING_FRIENDSHIP.name());
                Usuario usuario = usuarioService.findByUsername(getUsernameUsuario());
                boolean amistadUsuario = usuario1.getId().equals(usuario.getId());
                // Comprobamos que el us que realiza la petición es el us1 de la amistad y us1 y us2 no son el mismo
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

    @GetMapping("{id}")
    @ApiOperation(
            value= "Encontrar la amistad con el ID indicado",
            notes= """
                    Parámetros solicitados:\s
                    • "Integer id. ID de la Amistad a buscar
                    
                    Posibles respuestas:\s
                    • "FORBIDDEN_FRIENDSHIP_FOR_USER" (String). Indica que la amistad no está disponible para el usuario autenticado
                    • "AmistadDTO. Devuelve la amistad con el ID indicado
                    """
    )
    public ResponseEntity<?> findById(@PathVariable("id") Integer id) {
        Usuario usuario = usuarioService.findByUsername(getUsernameUsuario());
        Amistad amistad = service.findById(id);
        if(Objects.nonNull(amistad) &&
                Stream.of(amistad.getUsuario1().getId(), amistad.getUsuario2().getId())
                        .anyMatch(idAmistad -> idAmistad.equals(usuario.getId()))) {
            return ResponseEntity.ok(mapper.toDTO(amistad));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(RespuestasAmistad.FORBIDDEN_FRIENDSHIP_FOR_USER.name());
    }

    @GetMapping("/usuario")
    @ApiOperation(
            value= "Encontrar las amistades del usuario autenticado",
            notes= """
                    Posibles respuestas:\s
                    • "List<UsuarioDTO>. Devuelve la lista de usuarios con una relación de Amistad activa con el usuario autenticado
                    """
    )
    public ResponseEntity<?> findAmistadesByUsuario() {
        Usuario usuario = usuarioService.findByUsername(getUsernameUsuario());
        List<Usuario> amistadesByIdUsuario = service.findAmistadesByIdUsuario(usuario.getId());
        return ResponseEntity.ok(amistadesByIdUsuario.stream().map(mapper::toDTO).toList());
    }

    @PostMapping("/accept")
    @ApiOperation(
            value= "Aceptar Amistad",
            notes= """
                    Parámetros solicitados:\s
                    • "Integer idUsuarioAmistad. ID del usuario que ha solicitado la amistad
                    
                    Posibles respuestas:\s
                    • "ALREADY_ACCEPTED_FRIENDSHIP" (String). Indica que la Amistad indicada ya se ha aceptado
                    • "FORBIDDEN_FRIENDSHIP_FOR_USER" (String). Indica que la amistad no está disponible para el usuario autenticado
                    • "AmistadDTO. Devuelve la amistad que se ha aceptado exitosamente
                    """
    )
    public ResponseEntity<?> aceptarAmistad(@RequestParam("idUsuarioAmistad") Integer idUsuarioAmistad) {
        Usuario usuario = usuarioService.findByUsername(getUsernameUsuario());
        Amistad amistad = service.findAmistadByIds(usuario.getId(), idUsuarioAmistad);
        if(Objects.nonNull(amistad) && amistad.getUsuario2().getId().equals(usuario.getId())) {
            if(amistad.getEstado().equals(EstadosAmistad.FRIENDSHIP_ACCEPTED.name()))
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(RespuestasAmistad.ALREADY_ACCEPTED_FRIENDSHIP.name());
            amistad = service.accept(amistad.getId());
            return ResponseEntity.ok(mapper.toDTO(amistad));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(RespuestasAmistad.FORBIDDEN_FRIENDSHIP_FOR_USER.name());
    }

    @PostMapping("/remove")
    @ApiOperation(
            value= "Eliminar Amistad",
            notes= """
                    Parámetros solicitados:\s
                    • "Integer idUsuarioAmistad. ID del usuario que forma la amistad a eliminar
                    
                    Posibles respuestas:\s
                    • "FORBIDDEN_FRIENDSHIP_FOR_USER" (String). Indica que la amistad no está disponible para el usuario autenticado
                    • "REMOVED_FRIENDSHIP" (String). Indica que la amistad se ha eliminado correctamente
                    """
    )
    public ResponseEntity<?> eliminarAmistad(@RequestParam("idUsuarioAmistad") Integer idUsuarioAmistad) {
        Usuario usuario = usuarioService.findByUsername(getUsernameUsuario());
        Amistad amistad = service.findAmistadByIds(usuario.getId(), idUsuarioAmistad);
        // Se comprueba que la amistad exista y que el usuario autenticado sea participe
        if(Objects.nonNull(amistad)  &&
                Stream.of(amistad.getUsuario1().getId(), amistad.getUsuario2().getId())
                        .anyMatch(idUsuario -> idUsuario.equals(usuario.getId()))) {
            service.remove(amistad.getId());
            return ResponseEntity.ok(RespuestasAmistad.REMOVED_FRIENDSHIP.name());
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(RespuestasAmistad.FORBIDDEN_FRIENDSHIP_FOR_USER.name());
    }

    private String getUsernameUsuario() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return ((UserDetailsLogin) principal).getUsername();
    }

}
