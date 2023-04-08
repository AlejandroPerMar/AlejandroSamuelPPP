package es.iespuertodelacruz.alejandrosamuel.studycircle.domain.model;


public class MateriaTutor {

	private int id;
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
