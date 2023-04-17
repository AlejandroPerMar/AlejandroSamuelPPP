package es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.security;

import java.io.IOException;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;


@Component
public class JwtFilter extends OncePerRequestFilter implements Ordered {

	@Autowired
	private JwtService jwtService;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		final String authHeader = request.getHeader("Authorization");
		final String jwt;
		String username = null;
		String bearer = "Bearer ";
		if (authHeader == null || !authHeader.startsWith(bearer)) {
			filterChain.doFilter(request, response);
			return;
		}
		jwt = authHeader.substring(bearer.length());

		List<String> roles = null;

		username = jwtService.extractUsername(jwt);
		roles = jwtService.extractRoles(jwt);

		if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
			logger.debug("Token received: {}" + jwt);
			logger.debug("Username: {}" + username);
			logger.debug("Roles: {}" + roles);
			UserDetailsLogin userDetails = new UserDetailsLogin();
			userDetails.setUsername(username);
			userDetails.setRoles(roles);
			logger.debug("UserDetailsLogin created: {}" + userDetails.getRoles().size());
			UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails, null,
					userDetails.getAuthorities());
			logger.debug("Authentication token created: {}" + authToken);
			authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

			logger.debug("Authentication token created: {}" + authToken);
			SecurityContextHolder.getContext().setAuthentication(authToken);
			logger.debug("Authentication token created: {}" + authToken);
			logger.debug("Security context updated with authentication token.");

		}

		filterChain.doFilter(request, response);

		logger.debug("Filter chain continued.");
	}
}
