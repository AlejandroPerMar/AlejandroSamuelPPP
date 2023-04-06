package es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.primary.dto;

import java.math.BigInteger;
import java.util.List;

public class CursoDTO {
	
	private int id;
	private Double precioHora;
	private String estado;
	private String titulo;
	private BigInteger fechaCreacion;
	private MateriaTutorDTO tutorSubject;
	private List<ActividadCursoDTO> courseActivities;
	private List<AlumnoCursoDTO> courseStudents;

	public CursoDTO() {
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

	public BigInteger getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(BigInteger fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public MateriaTutorDTO getTutorSubject() {
		return tutorSubject;
	}

	public void setTutorSubject(MateriaTutorDTO tutorSubject) {
		this.tutorSubject = tutorSubject;
	}

	public List<ActividadCursoDTO> getCourseActivities() {
		return courseActivities;
	}

	public void setCourseActivities(List<ActividadCursoDTO> courseActivities) {
		this.courseActivities = courseActivities;
	}

	public List<AlumnoCursoDTO> getCourseStudents() {
		return courseStudents;
	}

	public void setCourseStudents(List<AlumnoCursoDTO> courseStudents) {
		this.courseStudents = courseStudents;
	}
}
