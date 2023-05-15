package es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.primary.controller.v2;

import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.model.Usuario;
import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.port.primary.IUsuarioService;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.primary.dto.AlumnoDTO;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.primary.mapper.DTOJustIdMapper;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.enums.RespuestasAlumno;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.security.UserDetailsLogin;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

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

	@Autowired
	private DTOJustIdMapper dtoJustIdMapper;
	
	@GetMapping
	@ApiOperation(
			value= "Encontrar el perfil de alumno del usuario autenticado",
			notes= """
                    Posibles respuestas:\s
                    • "STUDENT_PROFILE_NOT_FOUND" (String). Indica que no se ha encontrado perfil de Alumno para el usuario autenticado
                    • AlumnoDTO. Devuelve el perfil de Alumno del usuario autenticado
                    """
	)
	public ResponseEntity<?> getAlumno() {
		Alumno alumno = service.findAlumnoByUsername(getUsernameUsuario());
		if(Objects.isNull(alumno))
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(RespuestasAlumno.STUDENT_PROFILE_NOT_FOUND.name());

		AlumnoDTO alumnoDTO = mapper.toDTOGet(alumno);
		return ResponseEntity.ok().body(alumnoDTO);
	}
	
	@PostMapping
	@ApiOperation(
			value= "Encontrar las alertas de amistades del usuario autenticado",
			notes= """
                    Parámetros solicitados:\s
                    • "AlumnoDTO. Alumno a crear
                    
                    Posibles respuestas:\s
                    • "STUDENT_PROFILE_ALREADY_CREATED" (String). Indica que ya existe un perfil de alumno para el usuario autenticado
                    • "STUDENT_PROFILE_NOT_CREATED" (String). Indica que no se ha podido crear el perfil de alumno para el usuario autenticado
                    • "AlumnoDTO. Devuelve el perfil de Alumno en formato JSON que se acaba de crear para el usuario autenticado
                    """
	)
	public ResponseEntity<?> createAlumno(@RequestBody AlumnoDTO alumnoDTO) {
		Usuario usuario = usuarioService.findByUsername(getUsernameUsuario());
		if(Objects.nonNull(service.findAlumnoByIdUsuario(usuario.getId())))
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).contentType(MediaType.TEXT_PLAIN).body(RespuestasAlumno.STUDENT_PROFILE_ALREADY_CREATED.name());
		alumnoDTO.setUsuario(dtoJustIdMapper.toDTO(usuario));
		Alumno alumno = mapper.toDomainPost(alumnoDTO);
		alumnoDTO = mapper.toDTOGet(service.create(alumno));
		if(Objects.isNull(alumnoDTO))
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).contentType(MediaType.TEXT_PLAIN).body(RespuestasAlumno.STUDENT_PROFILE_NOT_CREATED.name());

		return ResponseEntity.ok().body(alumnoDTO);
	}
	
	@PutMapping
	@ApiOperation(
			value= "Actualizar el perfil de alumno del usuario autenticado",
			notes= """
                    Parámetros solicitados:\s
                    • "AlumnoDTO. Alumno a actualizar
                    
                    Posibles respuestas:\s
                    • "STUDENT_PROFILE_NOT_FOUND" (String). Indica que no se ha encontrado un perfil de alumno para el usuario autenticado
                    • "AlumnoDTO. Devuelve la lista de alertas correspondiente al usuario, aunque esté vacía
                    """
	)
	public ResponseEntity<?> updateAlumno(@RequestBody AlumnoDTO alumnoDTO) {
		Alumno alumnoExistente = service.findAlumnoByUsername(getUsernameUsuario());
		if(Objects.nonNull(alumnoExistente)) {
			Alumno alumno = mapper.toDomain(alumnoDTO);
			alumnoExistente.setNivelEstudios(alumno.getNivelEstudios());
			alumnoExistente.setMaterias(alumno.getMaterias());
			alumnoDTO = mapper.toDTOGet(service.update(alumnoExistente));

			return ResponseEntity.ok().body(alumnoDTO);
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(RespuestasAlumno.STUDENT_PROFILE_NOT_FOUND.name());

	}

	private String getUsernameUsuario() {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		return ((UserDetailsLogin) principal).getUsername();
	}
}
