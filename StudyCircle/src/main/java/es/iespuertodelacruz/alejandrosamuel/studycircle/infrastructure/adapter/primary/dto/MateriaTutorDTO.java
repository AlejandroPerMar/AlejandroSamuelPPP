package es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.primary.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.model.Curso;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class MateriaTutorDTO {

	private int id;
	private MateriaDTO materia;
	private TutorDTO tutor;
	private List<Curso> cursosTutor;

	public MateriaTutorDTO() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public MateriaDTO getMateria() {
		return materia;
	}

	public void setMateria(MateriaDTO materia) {
		this.materia = materia;
	}

	public TutorDTO getTutor() {
		return tutor;
	}

	public void setTutor(TutorDTO tutor) {
		this.tutor = tutor;
	}

	public List<Curso> getCursosTutor() {
		return cursosTutor;
	}

	public void setCursosTutor(List<Curso> cursosTutor) {
		this.cursosTutor = cursosTutor;
	}
}
