package es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.primary.dto;

import java.math.BigInteger;
import java.util.List;

public class UsuarioDTO {
	
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

	private List<AlertaDTO> alertas;

	private List<RolDTO> roles;

	private AlumnoDTO alumno;

	private TutorDTO tutor;

	public UsuarioDTO() {
	}

	public int getId() {
		return id;
	}

	public List<AlertaDTO> getAlertas() {
		return alertas;
	}

	public void setAlertas(List<AlertaDTO> alertas) {
		this.alertas = alertas;
	}

	public List<RolDTO> getRoles() {
		return roles;
	}

	public void setRoles(List<RolDTO> roles) {
		this.roles = roles;
	}

	public AlumnoDTO getAlumno() {
		return alumno;
	}

	public void setAlumno(AlumnoDTO alumno) {
		this.alumno = alumno;
	}

	public TutorDTO getTutor() {
		return tutor;
	}

	public void setTutor(TutorDTO tutor) {
		this.tutor = tutor;
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
}
