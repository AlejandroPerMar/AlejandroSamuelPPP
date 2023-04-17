package es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.config;



import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.security.UsuarioActivoFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.security.JwtFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

  private final JwtFilter jwtAuthFilter;

  private final UsuarioActivoFilter usuarioActivoFilter;
  private final AuthenticationProvider authenticationProvider;

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
	  http
	    .cors()
	    .and()
        .csrf()
        .disable()
        .authorizeRequests()
        .antMatchers("/",
				"/swagger-ui.html",
				"/swagger-ui/**",
				"/v2/**",
				"configuration/**",
				"/swagger*/**",
				"/webjars/**",
				"/api/login",
				"/api/register",
				"/api/register/**",
				"/login",
				"/register",				
				"/api/v1/**").permitAll()
          
        .antMatchers("/api/v3/**").hasRole("ADMIN")
		//.antMatchers("/api/v2/alumnos").hasRole("STUDENT")
		.antMatchers("/api/v2/tutores").access("hasRole('USER') and !getUser().getEstado().equals('STATUS_ACTIVE')")
		//.antMatchers("/api/v2/alumnos/**").hasRole("STUDENT")
		//.antMatchers("/api/v2/tutores/**").hasRole("TUTOR")
        .anyRequest().authenticated()  

        .and()
        .sessionManagement()
        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and()
        .authenticationProvider(authenticationProvider)
        .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

    return http.build();
  }

  public UserDetails getUser() {
	  Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	  if (authentication != null && authentication.isAuthenticated()) {
		  Object principal = authentication.getPrincipal();
		  if (principal instanceof UserDetails) {
			  String username = ((UserDetails) principal).getUsername();
			  return (UserDetails) principal;
		  } else {
			  String username = principal.toString();
			  return null;
		  }
	  }
  }

  public SecurityConfiguration(JwtFilter jwtAuthFilter, UsuarioActivoFilter usuarioActivoFilter, AuthenticationProvider authenticationProvider) {
	  super();
	  this.jwtAuthFilter = jwtAuthFilter;
	  this.usuarioActivoFilter = usuarioActivoFilter;
	  this.authenticationProvider = authenticationProvider;
  }
}
