package es.iespuertodelacruz.alejandrosamuel.studycircle.domain.model;

import java.math.BigInteger;
import java.util.List;

public class Curso {
	
	private int id;
	private Double precioHora;
	private String estado;
	private String titulo;
	private BigInteger fechaCreacion;
	private MateriaTutor materiaTutor;
	private List<ActividadCurso> actividadesCurso;
	private List<AlumnoCurso> alumnosCurso;

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

	public BigInteger getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(BigInteger fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public MateriaTutor getMateriaTutor() {
		return materiaTutor;
	}

	public void setMateriaTutor(MateriaTutor materiaTutor) {
		this.materiaTutor = materiaTutor;
	}

	public List<ActividadCurso> getActividadesCurso() {
		return actividadesCurso;
	}

	public void setActividadesCurso(List<ActividadCurso> actividadesCurso) {
		this.actividadesCurso = actividadesCurso;
	}

	public List<AlumnoCurso> getAlumnosCurso() {
		return alumnosCurso;
	}

	public void setAlumnosCurso(List<AlumnoCurso> alumnosCurso) {
		this.alumnosCurso = alumnosCurso;
	}
}
