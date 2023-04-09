package es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.primary.controller.v2;

import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.model.NivelEstudios;
import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.port.primary.INivelEstudiosService;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.primary.dto.NivelEstudiosDTO;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.primary.mapper.NivelEstudiosDTOMapper;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.config.SwaggerConfig;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Api(tags = {SwaggerConfig.NIVEL_ESTUDIOS_V2_TAG})
@Controller
@CrossOrigin
@RequestMapping("/api/v2/estudios")
public class NivelesEstudiosResourceV2 {

    @Autowired
    private INivelEstudiosService nivelEstudiosService;

    @Autowired
    private NivelEstudiosDTOMapper mapper;

    @GetMapping(params = "nombre")
    public ResponseEntity<?> findByNombre(@RequestParam("nombre") String nombre) {
        NivelEstudios nivelEstudios = nivelEstudiosService.findByNombre(nombre);
        return (nivelEstudios != null) ? ResponseEntity.ok(mapper.toDTO(nivelEstudios))
                : ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No se ha encontrado");
    }

    @GetMapping
    public ResponseEntity<?> findAll() {
        List<NivelEstudios> niveles = nivelEstudiosService.findAll();
        return ResponseEntity.ok(niveles.stream().map(n -> mapper.toDTO(n)).collect(Collectors.toList()));
    }
}
