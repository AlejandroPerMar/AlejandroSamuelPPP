package es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.primary.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.model.Curso;

import java.math.BigInteger;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ActividadDTO {

	private int id;

	private BigInteger fechaCreacion;

	private String descripcion;

	private String nombre;

	private BigInteger fechaActividad;

	private CursoDTO curso;


	public ActividadDTO() {
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

	public CursoDTO getCurso() {
		return curso;
	}

	public void setCurso(CursoDTO curso) {
		this.curso = curso;
	}
}
