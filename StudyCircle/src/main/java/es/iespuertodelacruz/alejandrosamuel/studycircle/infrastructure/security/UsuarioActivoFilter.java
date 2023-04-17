package es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.security;

import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.model.enums.EstadosUsuario;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.entity.UsuarioEntity;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.repository.UsuarioEntityJPARepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.Ordered;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Component
public class UsuarioActivoFilter extends OncePerRequestFilter {

    @Autowired
    private UsuarioEntityJPARepository usuarioRepository;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,  Object handler)  throws Exception {
        UsuarioEntity usuario = usuarioRepository.findByUsername(getUsernameUsuario()).orElse(null);
        if (usuario.getEstado().equals(EstadosUsuario.STATUS_PENDING_VERIFICATION)) {
            response.sendError(HttpServletResponse.SC_FORBIDDEN, usuario.getEstado());
            return false;
        }
        return true;
    }

    private String getUsernameUsuario() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        UserDetailsLogin userDetailsLogin = ((UserDetailsLogin) principal);
        if(userDetailsLogin != null)
            return userDetailsLogin.getUsername();

        return null;
    }

    @Override
    public int getOrder() {
            return 1;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
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
