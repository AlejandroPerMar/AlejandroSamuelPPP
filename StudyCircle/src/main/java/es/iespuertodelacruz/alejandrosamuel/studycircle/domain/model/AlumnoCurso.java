package es.iespuertodelacruz.alejandrosamuel.studycircle.domain.model;


public class AlumnoCurso {
	
	private int id;
	private Curso curso;
	private Alumno alumno;

	public AlumnoCurso() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Curso getCurso() {
		return curso;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
	}

	public Alumno getAlumno() {
		return alumno;
	}

	public void setAlumno(Alumno alumno) {
		this.alumno = alumno;
	}
}
