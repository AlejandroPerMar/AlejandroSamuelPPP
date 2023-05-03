package es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.security;

import java.io.Serial;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class UserDetailsLogin  implements UserDetails {
	
	@Serial
	private static final long serialVersionUID = 1L;
	
	String username;
	String password;
	String estado;
	List<String> roles;

	public UserDetailsLogin() {}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
	    List<GrantedAuthority> authorities = new ArrayList<>();

		Logger LOGGER = LoggerFactory.getLogger(UserDetailsLogin.class);
		LOGGER.debug("SIZE of roles is: " + roles.size());
	    roles.forEach(r -> authorities.add(new SimpleGrantedAuthority(r)));

		LOGGER.debug("SIZE of authorities is: " + authorities.size());

	    return authorities;
	}
	
	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return password;
	}
	
	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public boolean isEnabled() {
		return this.estado.equals("STATUS_ACTIVE");
	}
	
	public List<String> getRoles() {
		return roles;
	}
	
	public void setRoles(List<String> roles) {
		this.roles = roles;
	}
}
