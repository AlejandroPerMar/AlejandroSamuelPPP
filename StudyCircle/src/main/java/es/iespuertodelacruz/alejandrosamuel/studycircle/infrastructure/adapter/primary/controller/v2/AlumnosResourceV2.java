package es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.primary.controller.v2;

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
		usuarioService.findById(alumnoDTO.getUsuario().getId());
		Alumno alumno = mapper.toDomain(alumnoDTO);
		alumnoDTO = mapper.toDTO(service.create(alumno));
		
		if(alumnoDTO == null) 
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Alumno no creado");

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

	private String getUsernameUsuario() {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String nombreAutenticado = ((UserDetailsLogin) principal).getUsername();
		return nombreAutenticado;
	}
}
