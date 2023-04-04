package es.iespuertodelacruz.alejandrosamuel.studycircle.domain.model;

import java.sql.Timestamp;


public class Materia {

	private int id;
	private Timestamp fechaCreacion;
	private String nombre;
	private NivelEstudios studyLevel;

	public Materia() {
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

	public NivelEstudios getStudyLevel() {
		return studyLevel;
	}

	public void setStudyLevel(NivelEstudios studyLevel) {
		this.studyLevel = studyLevel;
	}
}
