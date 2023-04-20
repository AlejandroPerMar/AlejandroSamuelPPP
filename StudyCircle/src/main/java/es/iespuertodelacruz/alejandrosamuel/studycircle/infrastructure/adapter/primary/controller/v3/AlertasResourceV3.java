package es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.primary.controller.v3;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.model.Alerta;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.primary.dto.AlertaDTO;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.config.SwaggerConfig;
import io.swagger.annotations.Api;

@Api(tags = {SwaggerConfig.ALERTA_V3_TAG})
@RestController
@CrossOrigin
@RequestMapping("/api/v3/alertas")
public class AlertasResourceV3 {

    @Autowired
    private IAlertasService service;

    @Autowired
    private AlertasDTOMapper mapper;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody AlertaDTO request) {
    	AlertasDTOMapper mapper = new AlertasDTOMapper();

    	Alerta alerta = service.create(mapper.toDomain(request));

        return ResponseEntity.status(HttpStatus.OK).body(mapper.toDTO(alerta));
    }

    @PutMapping
    public ResponseEntity<?> update(@RequestBody AlertaDTO request) {
        if(service.findById(request.getId()) == null)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Nivel de estudios no existente");

        return ResponseEntity.ok(mapper.toDTO(service.update(mapper.toDomain(request))));
    }

    @DeleteMapping(params = "id")
    public ResponseEntity<?> delete(@RequestParam("id") Integer id) {
        boolean eliminado = service.delete(id);

        if(eliminado)
            return ResponseEntity.ok("Nivel de estudios eliminado correctamente");
        else
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No se ha eliminado ningún nivel de estudios");
    }

    @GetMapping(params = "id")
    public ResponseEntity<?> findById(@RequestParam("id") Integer id) {
    	Alerta alerta = service.findById(id);
        return (alerta != null) ? ResponseEntity.ok(mapper.toDTO(alerta))
                : ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No se ha encontrado");
    }

    @GetMapping
    public ResponseEntity<?> findAll() {
        List<Alerta> alertas = service.findAll();
        return ResponseEntity.ok(alertas.stream().map(n -> mapper.toDTO(n)).collect(Collectors.toList()));
    }

    @GetMapping(params = "nombre")
    public ResponseEntity<?> findByNombre(@RequestParam("nombre") String nombre) {
    	Alerta alerta = service.findByNombre(nombre);
        return (alerta != null) ? ResponseEntity.ok(mapper.toDTO(alerta))
                : ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No se ha encontrado");
    }
}