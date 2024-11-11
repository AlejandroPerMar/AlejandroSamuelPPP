package es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.primary.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.math.BigInteger;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class AmistadDTO {

	private Integer id;
	private BigInteger fechaCreacion;
	private BigInteger fechaAmistad;
	private String estado;
	private UsuarioDTO usuario1;
	private UsuarioDTO usuario2;

	public AmistadDTO() {
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

	public UsuarioDTO getUsuario1() {
		return usuario1;
	}

	public void setUsuario1(UsuarioDTO usuario1) {
		this.usuario1 = usuario1;
	}

	public UsuarioDTO getUsuario2() {
		return usuario2;
	}

	public void setUsuario2(UsuarioDTO usuario2) {
		this.usuario2 = usuario2;
	}
}
