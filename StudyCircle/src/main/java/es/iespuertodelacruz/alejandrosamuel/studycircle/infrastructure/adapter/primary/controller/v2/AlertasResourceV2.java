package es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.primary.controller.v2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.model.Alerta;
import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.port.primary.IAlertaService;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.config.SwaggerConfig;
import io.swagger.annotations.Api;

@Api(tags = {SwaggerConfig.ALERTA_V2_TAG})
@RestController
@CrossOrigin
@RequestMapping("/api/v2/alertas")
public class AlertasResourceV2 {

    @Autowired
    private IAlertaService service;

    @Autowired
    private AlertaDTOMapper mapper;

    @GetMapping(params = "id")
    public ResponseEntity<?> findById(@RequestParam("id") Integer id) {
        return ResponseEntity.ok(service.findById(id).stream().map(m -> mapper.toDTO(m)).toList());
    }


    @GetMapping(params = "nombre")
    public ResponseEntity<?> findByNombre(@RequestParam("nombre") String nombre) {
        Alerta alerta = service.findByNombre(nombre);

        if(alerta	 == null)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No se ha encontrado ninguna alerta con el nombre: " + nombre);

        return ResponseEntity.ok(mapper.toDTO(materia));
    }
    
    @GetMapping(params = "id")
    public ResponseEntity<?> findById(@RequestParam("id") Integer id) {
    	Alerta alerta = service.findById(id);

        if(alerta == null)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No se ha encontrado ninguna alerta con id " + id);

        return ResponseEntity.ok(mapper.toDTO(alerta));
    }

}