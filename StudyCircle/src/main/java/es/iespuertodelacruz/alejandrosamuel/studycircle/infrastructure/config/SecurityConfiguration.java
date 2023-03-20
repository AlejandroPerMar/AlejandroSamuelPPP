package es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.config;



import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.security.JwtFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

  private final JwtFilter jwtAuthFilter;
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
				"/login",
				"/register",				
				"/api/v1/**").permitAll()
          
        .antMatchers("/api/v3/**").hasRole("ADMIN")
        .anyRequest().authenticated()  

        .and()
        .sessionManagement()
        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and()
        .authenticationProvider(authenticationProvider)
        .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

    return http.build();
  }

public SecurityConfiguration(JwtFilter jwtAuthFilter, AuthenticationProvider authenticationProvider) {
	super();
	this.jwtAuthFilter = jwtAuthFilter;
	this.authenticationProvider = authenticationProvider;
}
}
