package es.iespuertodelacruz.alejandrosamuel.studycircle.domain.model;


public class ActividadCurso {
	
	private int id;

	private Actividad actividad;

	private Curso curso;

	public ActividadCurso() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Actividad getActividad() {
		return actividad;
	}

	public void setActividad(Actividad actividad) {
		this.actividad = actividad;
	}

	public Curso getCurso() {
		return curso;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
	}
}
