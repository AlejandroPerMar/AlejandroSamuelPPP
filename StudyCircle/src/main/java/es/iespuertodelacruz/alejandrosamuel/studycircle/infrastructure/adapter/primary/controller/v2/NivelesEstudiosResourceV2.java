package es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.primary.controller.v2;

import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.model.NivelEstudios;
import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.port.primary.INivelEstudiosService;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.primary.mapper.NivelEstudiosDTOMapper;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.config.SwaggerConfig;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Api(tags = {SwaggerConfig.NIVEL_ESTUDIOS_V2_TAG})
@RestController
@CrossOrigin
@RequestMapping("/api/v2/estudios")
public class NivelesEstudiosResourceV2 {

    @Autowired
    private INivelEstudiosService nivelEstudiosService;

    @Autowired
    private NivelEstudiosDTOMapper mapper;

    @GetMapping
    @ApiOperation(
            value= "Encontrar todos los Niveles de Estudio",
            notes= """
                    Posibles respuestas:\s
                    • "List<NivelEstudiosDTO>. Devuelve el listado de Niveles de Estudio con sus Materias mapeadas
                    """
    )
    public ResponseEntity<?> findAll() {
        List<NivelEstudios> niveles = nivelEstudiosService.findAll();
        return ResponseEntity.ok(niveles.stream().map(n -> mapper.toDTO(n)).collect(Collectors.toList()));
    }
}
