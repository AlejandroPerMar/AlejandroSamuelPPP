package es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigInteger;
import java.sql.Timestamp;


/**
 * The persistent class for the calendar_events database table.
 * 
 */
@Entity
@Table(name="calendar_events")
@NamedQuery(name="EventoCalendarioEntity.findAll", query="SELECT e FROM EventoCalendarioEntity e")
public class EventoCalendarioEntity implements Serializable {
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

	@Column(name="time_event")
	private BigInteger fechaEvento;

	//uni-directional many-to-one association to ActividadEntity
	@ManyToOne
	@JoinColumn(name="id_activity")
	private ActividadEntity activity;

	//uni-directional many-to-one association to UsuarioEntity
	@ManyToOne
	@JoinColumn(name="id_user")
	private UsuarioEntity user;

	public EventoCalendarioEntity() {
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

	public BigInteger getFechaEvento() {
		return this.fechaEvento;
	}

	public void setFechaEvento(BigInteger fechaEvento) {
		this.fechaEvento = fechaEvento;
	}

	public ActividadEntity getActivity() {
		return this.activity;
	}

	public void setActivity(ActividadEntity activity) {
		this.activity = activity;
	}

	public UsuarioEntity getUser() {
		return this.user;
	}

	public void setUser(UsuarioEntity user) {
		this.user = user;
	}

}