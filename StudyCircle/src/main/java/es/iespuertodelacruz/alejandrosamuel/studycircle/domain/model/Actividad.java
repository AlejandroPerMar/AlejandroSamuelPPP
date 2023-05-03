package es.iespuertodelacruz.alejandrosamuel.studycircle.domain.model;


import java.math.BigInteger;


public class Actividad {

	private int id;

	private BigInteger fechaCreacion;

	private String descripcion;

	private String nombre;

	private BigInteger fechaActividad;

	private Curso curso;

	public Actividad() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public BigInteger getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(BigInteger fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public BigInteger getFechaActividad() {
		return fechaActividad;
	}

	public void setFechaActividad(BigInteger fechaActividad) {
		this.fechaActividad = fechaActividad;
	}

	public Curso getCurso() {
		return curso;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
	}
}
