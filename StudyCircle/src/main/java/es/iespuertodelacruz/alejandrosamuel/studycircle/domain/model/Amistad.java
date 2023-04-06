package es.iespuertodelacruz.alejandrosamuel.studycircle.domain.model;

import java.math.BigInteger;

public class Amistad {

	private int id;
	private BigInteger fechaCreacion;
	private BigInteger fechaAmistad;
	private String estado;
	private Usuario user1;
	private Usuario user2;

	public Amistad() {
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

	public BigInteger getFechaAmistad() {
		return fechaAmistad;
	}

	public void setFechaAmistad(BigInteger fechaAmistad) {
		this.fechaAmistad = fechaAmistad;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Usuario getUser1() {
		return user1;
	}

	public void setUser1(Usuario user1) {
		this.user1 = user1;
	}

	public Usuario getUser2() {
		return user2;
	}

	public void setUser2(Usuario user2) {
		this.user2 = user2;
	}
}
