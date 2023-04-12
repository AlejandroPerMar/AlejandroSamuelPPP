package es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.primary.controller.v3;

import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.model.Materia;
import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.port.primary.IMateriaService;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.primary.dto.MateriaDTO;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.primary.mapper.MateriaDTOMapper;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.config.SwaggerConfig;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<?> create(@RequestBody MateriaDTO request) {
        Materia materia = mapper.toDomainPost(request);
        materia = service.create(materia);

        return ResponseEntity.ok(mapper.toDTO(materia));
    }

    @GetMapping(params = "idNivelEstudios")
    public ResponseEntity<?> findByNivelEstudiosId(@RequestParam("idNivelEstudios") Integer idNivelEstudios) {
        return ResponseEntity.ok(service.findByNivelEstudiosId(idNivelEstudios).stream().map(m -> mapper.toDTO(m)).toList());
    }

    @GetMapping(params = "nombreNivelEstudios")
    public ResponseEntity<?> findByNivelEstudiosNombre(@RequestParam("nombreNivelEstudios") String nombreNivelEstudios) {
        return ResponseEntity.ok(service.findByNivelEstudiosNombre(nombreNivelEstudios).stream().map(m -> mapper.toDTO(m)).toList());
    }

    @GetMapping(params = "id")
    public ResponseEntity<?> findById(@RequestParam("id") Integer id) {
        Materia materia = service.findById(id);

        if(materia == null)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("no se ha encontrado ninguna materia con id " + id);

        return ResponseEntity.ok(mapper.toDTO(materia));
    }

    @GetMapping(params = "nombre")
    public ResponseEntity<?> findByNombre(@RequestParam("nombre") String nombre) {
        Materia materia = service.findByNombre(nombre);

        if(materia == null)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("no se ha encontrado ninguna materia con id " + nombre);

        return ResponseEntity.ok(mapper.toDTO(materia));
    }

    @DeleteMapping(params = "id")
    public ResponseEntity<?> delete(@RequestParam("id") Integer id) {
        boolean eliminado = service.delete(id);

        if(!eliminado)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("no se ha podido eliminar la materia con id " + id +
                    ", tenga en cuenta que una materia que se encuentre vinculada con otras entidades no puede ser eliminada");

        return ResponseEntity.ok("materia con id " + id + " eliminada");

    }
}
