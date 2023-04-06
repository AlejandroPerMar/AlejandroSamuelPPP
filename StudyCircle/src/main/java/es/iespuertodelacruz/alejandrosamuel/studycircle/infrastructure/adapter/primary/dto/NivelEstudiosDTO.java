package es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.primary.dto;

import java.math.BigInteger;
import java.util.List;


public class NivelEstudiosDTO {

	private int id;
	private BigInteger fechaCreacion;
	private String nombre;
	private List<MateriaDTO> subjects;

	public NivelEstudiosDTO() {
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

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<MateriaDTO> getSubjects() {
		return subjects;
	}

	public void setSubjects(List<MateriaDTO> subjects) {
		this.subjects = subjects;
	}
}
