package es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.primary.controller.v3;

import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.model.NivelEstudios;
import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.port.primary.INivelEstudiosService;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.primary.dto.NivelEstudiosDTO;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.primary.mapper.NivelEstudiosDTOMapper;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.config.SwaggerConfig;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Api(tags = {SwaggerConfig.NIVEL_ESTUDIOS_V3_TAG})
@RestController
@CrossOrigin
@RequestMapping("/api/v3/estudios")
public class NivelesEstudiosResourceV3 {

    @Autowired
    private INivelEstudiosService nivelEstudiosService;

    @Autowired
    private NivelEstudiosDTOMapper mapper;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody NivelEstudiosDTO request) {
        NivelEstudiosDTOMapper mapper = new NivelEstudiosDTOMapper();

        NivelEstudios nivelEstudios = nivelEstudiosService.create(mapper.toDomain(request));

        return ResponseEntity.status(HttpStatus.OK).body(mapper.toDTO(nivelEstudios));
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody NivelEstudiosDTO request) {
        if(nivelEstudiosService.findById(request.getId()) == null)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Nivel de estudios no existente");

        return ResponseEntity.ok(mapper.toDTO(nivelEstudiosService.update(mapper.toDomain(request))));
    }

    @GetMapping(params = "id")
    public ResponseEntity<?> findById(@RequestParam("id") Integer id) {
        NivelEstudios nivelEstudios = nivelEstudiosService.findById(id);
        return (nivelEstudios != null) ? ResponseEntity.ok(mapper.toDTO(nivelEstudios))
                : ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No se ha encontrado");
    }

    @GetMapping
    public ResponseEntity<?> findAll() {
        List<NivelEstudios> niveles = nivelEstudiosService.findAll();
        return ResponseEntity.ok(niveles.stream().map(n -> mapper.toDTO(n)).collect(Collectors.toList()));
    }

    @GetMapping(params = "nombre")
    public ResponseEntity<?> findByNombre(@RequestParam("nombre") String nombre) {
        NivelEstudios nivelEstudios = nivelEstudiosService.findByNombre(nombre);
        return (nivelEstudios != null) ? ResponseEntity.ok(mapper.toDTO(nivelEstudios))
                : ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No se ha encontrado");
    }
}
