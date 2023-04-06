package es.iespuertodelacruz.alejandrosamuel.studycircle.domain.model;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.List;


public class Usuario {

	private int id;
	private BigInteger fechaCreacion;
	private String email;
	private String nombreCompleto;
	private String hashpswd;
	private String estado;
	private String username;
	private List<Rol> roles;

	public Usuario() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public BigInteger getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(BigInteger fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNombreCompleto() {
		return nombreCompleto;
	}

	public void setNombreCompleto(String nombreCompleto) {
		this.nombreCompleto = nombreCompleto;
	}

	public String getHashpswd() {
		return hashpswd;
	}

	public void setHashpswd(String hashpswd) {
		this.hashpswd = hashpswd;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public List<Rol> getRoles() {
		return roles;
	}

	public void setRoles(List<Rol> roles) {
		this.roles = roles;
	}
}
