package es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.primary.controller.v2;
import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.model.Anuncio;
import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.model.Usuario;
import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.port.primary.IAnuncioService;
import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.port.primary.IUsuarioService;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.primary.dto.AnuncioDTO;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.primary.mapper.AnuncioDTOMapper;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.config.SwaggerConfig;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.enums.MotivosAnuncio;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.enums.RespuestasAnuncio;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.security.UserDetailsLogin;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.utils.ObjectUtils;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Objects;

@Api(tags = {SwaggerConfig.ANUNCIO_V2_TAG})
@RestController
@CrossOrigin
@RequestMapping("/api/v2/anuncios")
public class AnunciosResourceV2 {

    @Autowired
    private IAnuncioService service;

    @Autowired
    private IUsuarioService usuarioService;

    @Autowired
    private AnuncioDTOMapper mapper;

    @PersistenceContext
    private EntityManager entityManager;

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable("id") Integer idAnuncio) {
        Anuncio anuncio = service.findById(idAnuncio);
        if(Objects.isNull(anuncio)) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(RespuestasAnuncio.NON_EXISTING_ANNOUNCEMENT.name());

        return ResponseEntity.ok(mapper.toDTO(anuncio));
    }

    @GetMapping
    public ResponseEntity<?> findByAll() {
        List<Anuncio> anuncios = service.findAll();
        if(Objects.isNull(anuncios)) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(RespuestasAnuncio.NON_EXISTING_ANNOUNCEMENTS.name());

        return ResponseEntity.ok(anuncios.stream().map(mapper::toDTO));
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody AnuncioDTO anuncioDTO) {
        if(ObjectUtils.notNullNorEmpty(anuncioDTO,
                anuncioDTO.getTitulo(),
                anuncioDTO.getDescripcion(),
                anuncioDTO.getMateria(),
                anuncioDTO.getMotivo())) {
            try {
                MotivosAnuncio.valueOf(anuncioDTO.getMotivo());
            } catch (IllegalArgumentException e) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(RespuestasAnuncio.INVALID_DTO_ANNOUNCEMENT.name());
            }
            Usuario usuario = usuarioService.findByUsername(getUsernameUsuario());
            Anuncio anuncio = mapper.toDomain(anuncioDTO);
            anuncio.setUsuario(usuario);
            anuncio = service.create(anuncio);
            entityManager.clear();
            return ResponseEntity.ok(mapper.toDTO(service.findById(anuncio.getId())));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(RespuestasAnuncio.INVALID_DTO_ANNOUNCEMENT.name());
    }

    private String getUsernameUsuario() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return ((UserDetailsLogin) principal).getUsername();
    }

}
