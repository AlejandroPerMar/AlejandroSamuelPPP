package es.iespuertodelacruz.alejandrosamuel.studycircle.domain.model;

import java.math.BigInteger;
import java.util.List;

public class Alumno {

	private int id;
	private BigInteger fechaCreacion;
	private NivelEstudios studyLevel;
	private List<Materia> subjects;
	private Usuario user;

	public Alumno() {
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

	public NivelEstudios getStudyLevel() {
		return studyLevel;
	}

	public void setStudyLevel(NivelEstudios studyLevel) {
		this.studyLevel = studyLevel;
	}

	public List<Materia> getSubjects() {
		return subjects;
	}

	public void setSubjects(List<Materia> subjects) {
		this.subjects = subjects;
	}

	public Usuario getUser() {
		return user;
	}

	public void setUser(Usuario user) {
		this.user = user;
	}
}
