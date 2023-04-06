package es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.primary.dto;

import java.math.BigInteger;

public class AmistadDTO {

	private int id;
	private BigInteger fechaCreacion;
	private BigInteger fechaAmistad;
	private String estado;
	private UsuarioDTO user1;
	private UsuarioDTO user2;

	public AmistadDTO() {
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

	public UsuarioDTO getUser1() {
		return user1;
	}

	public void setUser1(UsuarioDTO user1) {
		this.user1 = user1;
	}

	public UsuarioDTO getUser2() {
		return user2;
	}

	public void setUser2(UsuarioDTO user2) {
		this.user2 = user2;
	}
}
