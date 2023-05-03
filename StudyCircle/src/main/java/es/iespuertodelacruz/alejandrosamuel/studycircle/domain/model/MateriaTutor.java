package es.iespuertodelacruz.alejandrosamuel.studycircle.domain.model;


import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.primary.dto.CursoDTO;

import java.util.List;

public class MateriaTutor {

	private int id;
	private Materia materia;
	private Tutor tutor;
	private List<CursoDTO> cursosTutor;

	public MateriaTutor() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public List<CursoDTO> getCursosTutor() {
		return cursosTutor;
	}

	public void setCursosTutor(List<CursoDTO> cursosTutor) {
		this.cursosTutor = cursosTutor;
	}
}
