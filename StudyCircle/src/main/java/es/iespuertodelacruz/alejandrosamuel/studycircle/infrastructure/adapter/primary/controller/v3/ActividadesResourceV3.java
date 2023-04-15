package es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.primary.controller.v3;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.model.Actividad;
import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.port.primary.IActividadService;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.primary.dto.ActividadDTO;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.primary.mapper.ActividadDTOMapper;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.config.SwaggerConfig;
import io.swagger.annotations.Api;

@Api(tags = {SwaggerConfig.ACTIVIDAD_V3_TAG})
@RestController
@CrossOrigin
@RequestMapping("/api/v3/actividades")
public class ActividadesResourceV3 {

    @Autowired
    private IActividadService service;

    @Autowired
    private ActividadDTOMapper mapper;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody ActividadDTO request) {
        Actividad materia = mapper.toDomainPost(request);
        materia = service.create(materia);

        return ResponseEntity.ok(mapper.toDTO(materia));
    }

    @GetMapping(params = "nombreActividad")
    public ResponseEntity<?> findByCourse(@RequestParam("nombreCurso") String nombreCurso) {
        return ResponseEntity.ok(service.findByCourse(nombreCurso).stream().map(m -> mapper.toDTO(m)).toList());
    }

    @GetMapping(params = "id")
    public ResponseEntity<?> findById(@RequestParam("id") Integer id) {
    	Actividad actividad = service.findById(id);

        if(actividad == null)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No se ha encontrado ninguna actividad con id " + id);

        return ResponseEntity.ok(mapper.toDTO(actividad));
    }

    @GetMapping(params = "nombre")
    public ResponseEntity<?> findByNombre(@RequestParam("nombre") String nombre) {
        Actividad actividad = service.findByNombre(nombre);

        if(actividad == null)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No se ha encontrado ninguna actividad con id " + nombre);

        return ResponseEntity.ok(mapper.toDTO(actividad));
    }

    @DeleteMapping(params = "id")
    public ResponseEntity<?> delete(@RequestParam("id") Integer id) {
        boolean eliminado = service.delete(id);

        if(!eliminado)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No se ha podido eliminar la actividad con id " + id +
                    ", tenga en cuenta que una actividad que se encuentre vinculada con otras entidades no puede ser eliminada");

        return ResponseEntity.ok("Actividad con id " + id + " eliminada");

    }
}
