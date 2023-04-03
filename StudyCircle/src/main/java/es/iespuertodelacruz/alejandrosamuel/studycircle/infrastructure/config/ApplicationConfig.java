package es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.entity.RolEntity;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.entity.UsuarioEntity;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.repository.UsuarioEntityJPARepository;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.security.UserDetailsLogin;
import java.util.List;
import java.util.ArrayList;

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
			UsuarioEntity ur = repository.findByUsername(username);
			UserDetailsLogin user = new UserDetailsLogin();
		    user.setUsername(ur.getUsername());
		    user.setPassword(ur.getHashpswd());
		    
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
