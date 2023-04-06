package es.iespuertodelacruz.alejandrosamuel.studycircle.domain.model;

import java.math.BigInteger;
import java.sql.Timestamp;


public class Materia {

	private int id;
	private BigInteger fechaCreacion;
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

	public NivelEstudios getStudyLevel() {
		return studyLevel;
	}

	public void setStudyLevel(NivelEstudios studyLevel) {
		this.studyLevel = studyLevel;
	}
}
