package es.iespuertodelacruz.alejandrosamuel.studycircle.domain.model;

import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.entity.AlumnoEntity;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.entity.CursoEntity;

public class AlumnoCurso {
	
	private int id;
	private CursoEntity course;
	private AlumnoEntity student;

	public AlumnoCurso() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public CursoEntity getCourse() {
		return course;
	}

	public void setCourse(CursoEntity course) {
		this.course = course;
	}

	public AlumnoEntity getStudent() {
		return student;
	}

	public void setStudent(AlumnoEntity student) {
		this.student = student;
	}
}
