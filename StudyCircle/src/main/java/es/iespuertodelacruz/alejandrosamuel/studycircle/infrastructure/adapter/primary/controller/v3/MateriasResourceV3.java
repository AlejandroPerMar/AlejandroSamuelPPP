package es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.primary.controller.v3;

import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.model.Materia;
import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.port.primary.IMateriaService;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.primary.dto.MateriaDTO;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.primary.mapper.MateriaDTOMapper;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.config.SwaggerConfig;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.enums.RespuestasMateria;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@Api(tags = {SwaggerConfig.MATERIA_V3_TAG})
@RestController
@CrossOrigin
@RequestMapping("/api/v3/materias")
public class MateriasResourceV3 {

    @Autowired
    private IMateriaService service;

    @Autowired
    private MateriaDTOMapper mapper;

    @PostMapping
    @ApiOperation(
            value= "Crear Materia",
            notes= """
                    Parámetros solicitados:\s
                    • "MateriaDTO materiaDTO. Materia con los datos necesarios para crearla
                    
                    Posibles respuestas:\s
                    • "SUBJECT_NOT_VALID" (String). Indica que la materia no es válida
                    • "MateriaDTO. Devuelve la materia que se ha creado
                    """
    )
    public ResponseEntity<?> create(@RequestBody MateriaDTO materiaDTO) {
        Materia materia = mapper.toDomainPost(materiaDTO);
        if(Objects.isNull(materia)) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(RespuestasMateria.SUBJECT_NOT_VALID.name());
        materia = service.create(materia);

        return ResponseEntity.ok(mapper.toDTO(materia));
    }

    @GetMapping("{id}")
    @ApiOperation(
            value= "Encontrar Materia por el ID indicado",
            notes= """
                    Parámetros solicitados:\s
                    • "Integer id. ID de la Materia a buscar
                    
                    Posibles respuestas:\s
                    • "SUBJECT_NOT_FOUND" (String). Indica que no se ha encontrado la Materia con el ID indicado
                    • "MateriaDTO. Devuelve la Materia con el ID indicado
                    """
    )
    public ResponseEntity<?> findById(@PathVariable("id") Integer id) {
        Materia materia = service.findById(id);

        if(Objects.isNull(materia))
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(RespuestasMateria.SUBJECT_NOT_FOUND.name());

        return ResponseEntity.ok(mapper.toDTO(materia));
    }

    @DeleteMapping("{id}")
    @ApiOperation(
            value= "Eliminar Materia por el ID indicado",
            notes= """
                    Parámetros solicitados:\s
                    • "Integer id. ID de la Materia a eliminar
                    
                    Posibles respuestas:\s
                    • "SUBJECT_NOT_FOUND" (String). Indica que no se ha encontrado la materia
                    • "SUBJECT_NOT_DELETED" (String). Indica que no se ha podido eliminar la materia
                    • "MateriaDTO. Devuelve la Materia con el ID indicado
                    """
    )
    public ResponseEntity<?> delete(@PathVariable("id") Integer id) {
        Materia materia = service.findById(id);
        if(Objects.isNull(materia)) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(RespuestasMateria.SUBJECT_NOT_FOUND.name());

        if(!service.delete(id))
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(RespuestasMateria.SUBJECT_NOT_DELETED.name());

        return ResponseEntity.ok(RespuestasMateria.SUBJECT_DELETED.name());

    }
}
