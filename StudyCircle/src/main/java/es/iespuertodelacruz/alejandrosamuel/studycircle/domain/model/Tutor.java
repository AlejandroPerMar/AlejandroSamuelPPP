package es.iespuertodelacruz.alejandrosamuel.studycircle.domain.model;

import java.math.BigInteger;
import java.util.List;


public class Tutor {

	private Integer id;
	private BigInteger fechaCreacion;
	private List<MateriaTutor> materiasTutor;
	private Usuario usuario;

	public Tutor() {
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

	public List<MateriaTutor> getMateriasTutor() {
		return materiasTutor;
	}

	public void setMateriasTutor(List<MateriaTutor> materiasTutor) {
		this.materiasTutor = materiasTutor;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
}
