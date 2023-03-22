package es.iespuertodelacruz.alejandrosamuel.studycircle.domain.model;

import java.util.List;

public class MateriaTutor {

	private int id;

	private List<MateriaAlumno> alumnosMaterias;

	private Materia materia;

	private Tutor tutor;

	public MateriaTutor() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<MateriaAlumno> getAlumnosMaterias() {
		return alumnosMaterias;
	}

	public void setAlumnosMaterias(List<MateriaAlumno> alumnosMaterias) {
		this.alumnosMaterias = alumnosMaterias;
	}

	public Materia getMateria() {
		return materia;
	}

	public void setMateria(Materia materia) {
		this.materia = materia;
	}

	public Tutor getTutor() {
		return tutor;
	}

	public void setTutor(Tutor tutor) {
		this.tutor = tutor;
	}
}
