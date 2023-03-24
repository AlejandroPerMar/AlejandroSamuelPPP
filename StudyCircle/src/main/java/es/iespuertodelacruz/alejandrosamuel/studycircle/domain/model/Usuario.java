package es.iespuertodelacruz.alejandrosamuel.studycircle.domain.model;

import java.math.BigInteger;
import java.util.List;

public class Usuario {

	private int id;

	private BigInteger fechaCreacion;

	private String email;

	private BigInteger fechaEnvioEmailVerificacion;

	private String tokenVerificacionEmail;

	private BigInteger fechaVerificacion;

	private String nombre;

	private String hashpswd;

	private String estado;

	private String username;

	private List<Alerta> alertas;

	private List<Rol> roles;

	private Alumno alumno;

	private Tutor tutor;

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

	public BigInteger getFechaEnvioEmailVerificacion() {
		return fechaEnvioEmailVerificacion;
	}

	public void setFechaEnvioEmailVerificacion(BigInteger fechaEnvioEmailVerificacion) {
		this.fechaEnvioEmailVerificacion = fechaEnvioEmailVerificacion;
	}

	public String getTokenVerificacionEmail() {
		return tokenVerificacionEmail;
	}

	public void setTokenVerificacionEmail(String tokenVerificacionEmail) {
		this.tokenVerificacionEmail = tokenVerificacionEmail;
	}

	public BigInteger getFechaVerificacion() {
		return fechaVerificacion;
	}

	public void setFechaVerificacion(BigInteger fechaVerificacion) {
		this.fechaVerificacion = fechaVerificacion;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
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

	public List<Alerta> getAlertas() {
		return alertas;
	}

	public void setAlertas(List<Alerta> alertas) {
		this.alertas = alertas;
	}

	public List<Rol> getRoles() {
		return roles;
	}

	public void setRoles(List<Rol> roles) {
		this.roles = roles;
	}

	public Alumno getAlumno() {
		return alumno;
	}

	public void setAlumno(Alumno alumno) {
		this.alumno = alumno;
	}

	public Tutor getTutor() {
		return tutor;
	}

	public void setTutor(Tutor tutor) {
		this.tutor = tutor;
	}
}
