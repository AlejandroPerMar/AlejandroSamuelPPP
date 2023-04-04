package es.iespuertodelacruz.alejandrosamuel.studycircle.domain.model;

import java.sql.Timestamp;
import java.util.List;


public class NivelEstudios {

	private int id;
	private Timestamp fechaCreacion;
	private String nombre;
	private List<Materia> subjects;

	public NivelEstudios() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Timestamp getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Timestamp fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<Materia> getSubjects() {
		return subjects;
	}

	public void setSubjects(List<Materia> subjects) {
		this.subjects = subjects;
	}
}
