package es.iespuertodelacruz.alejandrosamuel.studycircle.domain.model;


public class ActividadCurso {
	
	private int id;

	private Actividad activity;

	private Curso course;

	public ActividadCurso() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Actividad getActivity() {
		return activity;
	}

	public void setActivity(Actividad activity) {
		this.activity = activity;
	}

	public Curso getCourse() {
		return course;
	}

	public void setCourse(Curso course) {
		this.course = course;
	}
}
