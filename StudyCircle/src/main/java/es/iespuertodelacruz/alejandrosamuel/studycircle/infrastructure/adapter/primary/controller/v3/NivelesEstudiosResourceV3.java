package es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.primary.controller.v3;

import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.model.NivelEstudios;
import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.port.primary.INivelEstudiosService;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.primary.dto.NivelEstudiosDTO;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.primary.mapper.NivelEstudiosDTOMapper;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.config.SwaggerConfig;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.enums.RespuestasNivelEstudios;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.utils.ObjectUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Api(tags = {SwaggerConfig.NIVEL_ESTUDIOS_V3_TAG})
@RestController
@CrossOrigin
@RequestMapping("/api/v3/estudios")
public class NivelesEstudiosResourceV3 {

    @Autowired
    private INivelEstudiosService service;

    @Autowired
    private NivelEstudiosDTOMapper mapper;

    @PersistenceContext
    private EntityManager entityManager;

    @PostMapping
    @ApiOperation(
            value= "Crear Nivel de Estudios",
            notes= """
                    Parámetros solicitados:\s
                    • "NivelEstudiosDTO nivelEstudiosDTO. Nivel de Estudios con los datos necesarios para crearlo
                    
                    Posibles respuestas:\s
                    • "STUDY_LEVEL_NOT_CREATED" (String). Indica que el Nivel de Estudios no se ha creado
                    • "NivelEstudiosDTO. Devuelve el Nivel de Estudios que se ha creado
                    """
    )
    public ResponseEntity<?> create(@RequestBody NivelEstudiosDTO nivelEstudiosDTO) {
        if(!ObjectUtils.notNullNorEmpty(nivelEstudiosDTO, nivelEstudiosDTO.getNombre()))
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(RespuestasNivelEstudios.STUDY_LEVEL_NOT_CREATED.name());
        NivelEstudios nivelEstudios = service.create(mapper.toDomain(nivelEstudiosDTO));
        entityManager.clear();
        nivelEstudios = service.findById(nivelEstudios.getId());
        return ResponseEntity.ok(mapper.toDTO(nivelEstudios));
    }

    @PutMapping
    @ApiOperation(
            value= "Actualizar Nivel de Estudios",
            notes= """
                    Parámetros solicitados:\s
                    • "NivelEstudiosDTO nivelEstudiosDTO. Nivel de Estudios con los nuevos valores
                    
                    Posibles respuestas:\s
                    • "STUDY_LEVEL_NOT_FOUND" (String). Indica que el Nivel de Estudios no se ha encontrado
                    • "NivelEstudiosDTO. Devuelve el Nivel de Estudios que se ha encontrado
                    """
    )
    public ResponseEntity<?> update(@RequestBody NivelEstudiosDTO nivelEstudiosDTO) {
        if(Objects.isNull(service.findById(nivelEstudiosDTO.getId())))
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(RespuestasNivelEstudios.STUDY_LEVEL_NOT_FOUND.name());

        return ResponseEntity.ok(mapper.toDTO(service.update(mapper.toDomain(nivelEstudiosDTO))));
    }

    @DeleteMapping("{id}")
    @ApiOperation(
            value= "Eliminar Nivel de Estudios",
            notes= """
                    Parámetros solicitados:\s
                    • "Integer id. ID del Nivel de Estudios a eliminar
                    
                    Posibles respuestas:\s
                    • "STUDY_LEVEL_DELETED" (String). Indica que se ha eliminado el Nivel de Estudios
                    • "STUDY_LEVEL_NOT_DELETED" (String). Indica que no se ha podido eliminar el Nivel de Estudios
                    """
    )
    public ResponseEntity<?> delete(@PathVariable("id") Integer id) {
        boolean eliminado = service.delete(id);

        if(eliminado)
            return ResponseEntity.ok(RespuestasNivelEstudios.STUDY_LEVEL_DELETED.name());
        else
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(RespuestasNivelEstudios.STUDY_LEVEL_NOT_DELETED.name());
    }

    @GetMapping("{id}")
    @ApiOperation(
            value= "Encontrar Nivel de Estudios por ID",
            notes= """
                    Parámetros solicitados:\s
                    • "Integer id. ID del Nivel de Estudios a buscar
                    
                    Posibles respuestas:\s
                    • "STUDY_LEVEL_NOT_FOUND" (String). Indica que no se ha encontrado el Nivel de Estudios
                    • "NivelEstudiosDTO. Devuelve el Nivel de Estudios encontrado
                    """
    )
    public ResponseEntity<?> findById(@PathVariable("id") Integer id) {
        NivelEstudios nivelEstudios = service.findById(id);
        return (Objects.nonNull(nivelEstudios)) ? ResponseEntity.ok(mapper.toDTO(nivelEstudios))
                : ResponseEntity.status(HttpStatus.BAD_REQUEST).body(RespuestasNivelEstudios.STUDY_LEVEL_NOT_FOUND.name());
    }

    @GetMapping
    @ApiOperation(
            value= "Encontrar todos los Niveles de Estudio",
            notes= """
                    Posibles respuestas:\s
                    • "List<NivelEstudiosDTO>. Devuelve listado de todas los Niveles de Estudio disponibles
                    """
    )
    public ResponseEntity<?> findAll() {
        List<NivelEstudios> niveles = service.findAll();
        return ResponseEntity.ok(niveles.stream().map(mapper::toDTO).collect(Collectors.toList()));
    }
}
