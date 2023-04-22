package es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.config;

import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.service.HTMLBuilder;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.primary.mapper.*;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.mapper.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.entity.UsuarioEntity;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.repository.UsuarioEntityJPARepository;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.security.UserDetailsLogin;

import java.util.List;
import java.util.ArrayList;
import java.util.Optional;

@Configuration
public class ApplicationConfig {

	public ApplicationConfig(UsuarioEntityJPARepository repository) {
		super();
		this.repository = repository;
	}

	private final UsuarioEntityJPARepository repository;

	@Bean
	public NivelEstudiosDTOMapper nivelEstudiosDTOMapper() {
		return new NivelEstudiosDTOMapper();
	}

	@Bean
	public NivelEstudiosEntityMapper nivelEstudiosEntityMapper() {
		return new NivelEstudiosEntityMapper();
	}

	@Bean
	public MateriaDTOMapper materiaDTOMapper() {
		return new MateriaDTOMapper();
	}

	@Bean
	public MateriaEntityMapper materiaEntityMapper() {
		return new MateriaEntityMapper();
	}

	@Bean
	public AlumnoDTOMapper alumnoDTOMapper() {
		return new AlumnoDTOMapper();
	}

	@Bean
	public AlumnoEntityMapper alumnoEntityMapper() {
		return new AlumnoEntityMapper();
	}

	@Bean
	public UsuarioDTOMapper usuarioDTOMapper() {
		return new UsuarioDTOMapper();
	}

	@Bean
	public UsuarioEntityMapper usuarioEntityMapper() {
		return new UsuarioEntityMapper();
	}

	@Bean
	public HTMLBuilder htmlBuilder() {
		return new HTMLBuilder();
	}

	@Bean
	public TutorDTOMapper tutorDTOMapper() {
		return new TutorDTOMapper();
	}

	@Bean
	public TutorEntityMapper tutorEntityMapper() {
		return new TutorEntityMapper();
	}

	@Bean
	public CursoDTOMapper cursoDTOMapper() {
		return new CursoDTOMapper();
	}

	@Bean
	public CursoEntityMapper cursoEntityMapper() {
		return new CursoEntityMapper();
	}

	@Bean
	public UserDetailsService userDetailsService() {
		
		return username -> {
			Optional<UsuarioEntity> optUsuario = repository.findByUsername(username);
			UsuarioEntity ur = optUsuario.orElse(null);
			UserDetailsLogin user = new UserDetailsLogin();
		    user.setUsername(ur.getUsername());
		    user.setPassword(ur.getHashpswd());
			user.setEstado(ur.getEstado());
		    /*
		     * Generar una List<String> con los roles
		     */
		    List<String> roles = new ArrayList<>();
		    
		    ur.getRoles().stream().map(r -> roles.add(r.getRol()));
		    user.setRoles(roles);
		    
			return user;
		};
				//.orElseThrow(() -> new UsernameNotFoundException("User not found"));
	}

	@Bean
	public AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(userDetailsService());
		authProvider.setPasswordEncoder(passwordEncoder());
		return authProvider;
	}

	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
		return config.getAuthenticationManager();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
