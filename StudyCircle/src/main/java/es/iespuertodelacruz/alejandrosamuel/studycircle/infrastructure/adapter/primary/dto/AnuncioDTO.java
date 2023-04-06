package es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.primary.dto;

import java.math.BigInteger;

public class AnuncioDTO {

	private int id;
	private BigInteger fechaCreacion;
	private String descripcion;
	private String motivo;
	private String estado;
	private String titulo;
	private MateriaDTO subject;
	private UsuarioDTO user;

	public AnuncioDTO() {
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

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getMotivo() {
		return motivo;
	}

	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public MateriaDTO getSubject() {
		return subject;
	}

	public void setSubject(MateriaDTO subject) {
		this.subject = subject;
	}

	public UsuarioDTO getUser() {
		return user;
	}

	public void setUser(UsuarioDTO user) {
		this.user = user;
	}
}
