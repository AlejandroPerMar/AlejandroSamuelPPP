package es.iespuertodelacruz.alejandrosamuel.studycircle.domain.model;

import java.util.List;

public class Curso {
	
	private int id;
	private Double precioHora;
	private String estado;
	private String titulo;
	private MateriaTutor tutorSubject;
	private List<ActividadCurso> courseActivities;
	private List<AlumnoCurso> courseStudents;

	public Curso() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Double getPrecioHora() {
		return precioHora;
	}

	public void setPrecioHora(Double precioHora) {
		this.precioHora = precioHora;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public MateriaTutor getTutorSubject() {
		return tutorSubject;
	}

	public void setTutorSubject(MateriaTutor tutorSubject) {
		this.tutorSubject = tutorSubject;
	}

	public List<ActividadCurso> getCourseActivities() {
		return courseActivities;
	}

	public void setCourseActivities(List<ActividadCurso> courseActivities) {
		this.courseActivities = courseActivities;
	}

	public List<AlumnoCurso> getCourseStudents() {
		return courseStudents;
	}

	public void setCourseStudents(List<AlumnoCurso> courseStudents) {
		this.courseStudents = courseStudents;
	}
}
