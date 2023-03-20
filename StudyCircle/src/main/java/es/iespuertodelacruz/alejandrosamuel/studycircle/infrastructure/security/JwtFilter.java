package es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.security;

import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;


@Component
public class JwtFilter extends OncePerRequestFilter {

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

			UserDetailsLogin userDetails = new UserDetailsLogin();
			userDetails.setUsername(username);
			userDetails.setRoles(roles);

			UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails, null,
					userDetails.getAuthorities());
			authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
			SecurityContextHolder.getContext().setAuthentication(authToken);
		}

		filterChain.doFilter(request, response);
	}
}
