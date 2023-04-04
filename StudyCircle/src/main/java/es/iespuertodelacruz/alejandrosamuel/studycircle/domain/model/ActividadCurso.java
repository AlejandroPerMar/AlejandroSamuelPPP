package es.iespuertodelacruz.alejandrosamuel.studycircle.domain.model;

import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.entity.ActividadEntity;

public class ActividadCurso {
	
	private int id;

	private ActividadEntity activity;

	private Curso course;

	public ActividadCurso() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public ActividadEntity getActivity() {
		return activity;
	}

	public void setActivity(ActividadEntity activity) {
		this.activity = activity;
	}

	public Curso getCourse() {
		return course;
	}

	public void setCourse(Curso course) {
		this.course = course;
	}
}
