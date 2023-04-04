package es.iespuertodelacruz.alejandrosamuel.studycircle.domain.model;


public class MateriaTutor {

	private int id;
	private Materia subject;
	private Tutor tutor;

	public MateriaTutor() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Materia getSubject() {
		return subject;
	}

	public void setSubject(Materia subject) {
		this.subject = subject;
	}

	public Tutor getTutor() {
		return tutor;
	}

	public void setTutor(Tutor tutor) {
		this.tutor = tutor;
	}
}
