package es.iespuertodelacruz.alejandrosamuel.studycircle.domain.model;

import java.math.BigInteger;


public class AlertaAmistad {

	private Integer id;
	private BigInteger fechaCreacion;
	private String estado;
	private Amistad amistad;
	private Usuario usuario;

	public AlertaAmistad() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public BigInteger getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(BigInteger fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Amistad getAmistad() {
		return amistad;
	}

	public void setAmistad(Amistad amistad) {
		this.amistad = amistad;
	}
}
