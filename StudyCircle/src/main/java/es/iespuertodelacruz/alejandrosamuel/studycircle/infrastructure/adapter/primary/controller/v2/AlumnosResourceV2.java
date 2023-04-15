package es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.primary.controller.v2;

import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.model.Usuario;
import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.port.primary.IUsuarioService;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.primary.dto.AlumnoDTO;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.security.UserDetailsLogin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.model.Alumno;
import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.port.primary.IAlumnoService;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.primary.mapper.AlumnoDTOMapper;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.config.SwaggerConfig;
import io.swagger.annotations.Api;

@Api(tags = {SwaggerConfig.ALUMNO_TAG})
@RestController
@CrossOrigin
@RequestMapping("api/v2/alumnos")
public class AlumnosResourceV2 {

	@Autowired
	private IAlumnoService service;

	@Autowired
	private IUsuarioService usuarioService;

	@Autowired
	private AlumnoDTOMapper mapper;
	
	@GetMapping
	public ResponseEntity<?> getAlumno() {
		Alumno alumno = service.findAlumnoByUsername(getUsernameUsuario());
		AlumnoDTO alumnoDTO = mapper.toDTOGet(alumno);
		if(alumnoDTO == null)
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Alumno no encontrado");
		
		return ResponseEntity.ok().body(alumnoDTO);
	}
	
	@PostMapping
	public ResponseEntity<?> createAlumno(@RequestBody AlumnoDTO alumnoDTO) {
		Usuario usuario = usuarioService.findByUsername(getUsernameUsuario());
		alumnoDTO.setUsuario(mapper.toDTO(usuario));
		Alumno alumno = mapper.toDomain(alumnoDTO);
		alumnoDTO = mapper.toDTOGet(service.create(alumno));
		if(alumnoDTO == null) 
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Alumno no creado");

		return ResponseEntity.ok().body(alumnoDTO);
	}
	
	@PutMapping
	public ResponseEntity<?> updateAlumno(@RequestBody AlumnoDTO alumnoDTO) {
		Alumno alumno = mapper.toDomain(alumnoDTO);
		if(alumno == null)
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Formato incorrecto");
		alumno.setId(service.findAlumnoByUsername(getUsernameUsuario()).getId());
		alumnoDTO = mapper.toDTOGet(service.update(alumno));
		if(alumnoDTO == null) 
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Alumno no guardado");

		return ResponseEntity.ok().body(alumnoDTO);
	}

	private String getUsernameUsuario() {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return ((UserDetailsLogin) principal).getUsername();
	}
}
