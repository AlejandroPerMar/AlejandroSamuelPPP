package es.iespuertodelacruz.alejandrosamuel.studycircle.domain.model;

import java.sql.Timestamp;


public class Actividad {

	private int id;

	private Timestamp fechaCreacion;

	private String descripcion;

	private String nombre;

	private String estado;

	private Timestamp fechaActividad;

	public Actividad() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Timestamp getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Timestamp fechaCreacion) {
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

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Timestamp getFechaActividad() {
		return fechaActividad;
	}

	public void setFechaActividad(Timestamp fechaActividad) {
		this.fechaActividad = fechaActividad;
	}
}
