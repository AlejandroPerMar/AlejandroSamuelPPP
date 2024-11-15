package es.iespuertodelacruz.alejandrosamuel.studycircle.domain.model;



import java.util.List;

public class MateriaTutor {

	private Integer id;
	private Materia materia;
	private Tutor tutor;
	private List<Curso> cursosTutor;

	public MateriaTutor() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
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

	public List<Curso> getCursosTutor() {
		return cursosTutor;
	}

	public void setCursosTutor(List<Curso> cursosTutor) {
		this.cursosTutor = cursosTutor;
	}
}
