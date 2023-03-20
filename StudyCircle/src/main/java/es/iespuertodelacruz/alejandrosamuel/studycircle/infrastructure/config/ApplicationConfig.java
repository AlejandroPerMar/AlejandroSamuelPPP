package es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.UsuarioEntity;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.UsuarioEntityJPARepository;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.security.UserDetailsLogin;

@Configuration
public class ApplicationConfig {

	public ApplicationConfig(UsuarioEntityJPARepository repository) {
		super();
		this.repository = repository;
	}

	private final UsuarioEntityJPARepository repository;

	@Bean
	public UserDetailsService userDetailsService() {
		
		return username -> {
			UsuarioEntity ur = repository.findByName(username);
			UserDetailsLogin user = new UserDetailsLogin();
		    user.setUsername(ur.getNombre());
		    user.setPassword(ur.getHashpswd());
		    /*
		     * Generar una List<String> con los roles
		     */
		    user.setRoles(ur.getRoles());
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
