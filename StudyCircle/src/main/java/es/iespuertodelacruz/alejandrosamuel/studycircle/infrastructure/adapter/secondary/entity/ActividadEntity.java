package es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigInteger;


/**
 * The persistent class for the activities database table.
 * 
 */
@Entity
@Table(name="activities")
@NamedQuery(name="ActividadEntity.findAll", query="SELECT a FROM ActividadEntity a")
public class ActividadEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@Column(name="created_at")
	private BigInteger fechaCreacion;

	@Column(name="description")
	private String descripcion;

	@Column(name="name")
	private String nombre;

	@Column(name="status")
	private String estado;

	@Column(name="time_activity")
	private BigInteger fechaActividad;

	public ActividadEntity() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public BigInteger getFechaCreacion() {
		return this.fechaCreacion;
	}

	public void setFechaCreacion(BigInteger fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getEstado() {
		return this.estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public BigInteger getFechaActividad() {
		return this.fechaActividad;
	}

	public void setFechaActividad(BigInteger fechaActividad) {
		this.fechaActividad = fechaActividad;
	}

}