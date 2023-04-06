package es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.primary.dto;


public class ActividadCursoDTO {
	
	private int id;

	private ActividadDTO activity;

	private CursoDTO course;

	public ActividadCursoDTO() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public ActividadDTO getActivity() {
		return activity;
	}

	public void setActivity(ActividadDTO activity) {
		this.activity = activity;
	}

	public CursoDTO getCourse() {
		return course;
	}

	public void setCourse(CursoDTO course) {
		this.course = course;
	}
}
