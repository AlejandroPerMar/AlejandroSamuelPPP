package es.iespuertodelacruz.alejandrosamuel.studycircle.domain.model;


public class AlumnoCurso {
	
	private int id;
	private Curso course;
	private Alumno student;

	public AlumnoCurso() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Curso getCourse() {
		return course;
	}

	public void setCourse(Curso course) {
		this.course = course;
	}

	public Alumno getStudent() {
		return student;
	}

	public void setStudent(Alumno student) {
		this.student = student;
	}
}
