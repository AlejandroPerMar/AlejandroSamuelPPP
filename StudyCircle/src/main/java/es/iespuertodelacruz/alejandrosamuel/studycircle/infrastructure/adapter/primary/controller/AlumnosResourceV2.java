package es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.primary.controller;

import java.math.BigInteger;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.model.Alumno;
import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.port.primary.IAlumnoService;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.primary.dto.AlumnoDTO;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.primary.mapper.AlumnoDTOMapper;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.config.SwaggerConfig;
import io.swagger.annotations.Api;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

@Api(tags = {SwaggerConfig.ALUMNO_TAG})
@RestController
@CrossOrigin
@RequestMapping("api/v2/alumnos")
public class AlumnosResourceV2 {

	@Autowired
	private IAlumnoService service;
	
	@GetMapping(params = "id")
	public ResponseEntity<?> getAlumnoById(@RequestParam("id") Integer id) {
		AlumnoDTOMapper mapper = new AlumnoDTOMapper();
		AlumnoDTO alumnoDTO = mapper.toDTO(service.findAlumnoById(id));
		if(alumnoDTO == null)
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Alumno no encontrado");
		
		return ResponseEntity.ok().body(alumnoDTO);
	}
	
	@GetMapping(params = "idUsuario")
	public ResponseEntity<?> getAlumnoByIdUsuario(@RequestParam("idUsuario") Integer id) {
		AlumnoDTOMapper mapper = new AlumnoDTOMapper();
		AlumnoDTO alumnoDTO = mapper.toDTO(service.findAlumnoByIdUsuario(id));
		if(alumnoDTO == null)
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Alumno no encontrado");
		
		return ResponseEntity.ok().body(alumnoDTO);
	}
	
	@GetMapping(params = "username")
	public ResponseEntity<?> getAlumnoByUsername(@RequestParam("username") String username) {
		AlumnoDTOMapper mapper = new AlumnoDTOMapper();
		AlumnoDTO alumnoDTO = mapper.toDTO(service.findAlumnoByUsername(username));
		if(alumnoDTO == null)
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Alumno no encontrado");
		
		return ResponseEntity.ok().body(alumnoDTO);
	}
	
	@PostMapping
	public ResponseEntity<?> createAlumno(@RequestBody AlumnoDTO alumnoDTO) {
		AlumnoDTOMapper mapper = new AlumnoDTOMapper();
		Alumno alumno = mapper.toDomain(alumnoDTO);
		if(alumno == null)
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Formato incorrecto");
		alumno.setFechaCreacion(getFechaInBigInteger());
		alumnoDTO = mapper.toDTO(service.create(alumno));
		
		if(alumnoDTO == null) 
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Alumno no guardado");

		return ResponseEntity.ok().body(alumnoDTO);
	}
	
	@PutMapping
	public ResponseEntity<?> updateAlumno(@RequestBody AlumnoDTO alumnoDTO) {
		AlumnoDTOMapper mapper = new AlumnoDTOMapper();
		Alumno alumno = mapper.toDomain(alumnoDTO);
		if(alumno == null)
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Formato incorrecto");
		alumnoDTO = mapper.toDTO(service.update(alumno));
		if(alumnoDTO == null) 
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Alumno no guardado");

		return ResponseEntity.ok().body(alumnoDTO);
	}
	
	private BigInteger getFechaInBigInteger() {
		return new BigInteger(new Date().getTime() + "");
	}
}
