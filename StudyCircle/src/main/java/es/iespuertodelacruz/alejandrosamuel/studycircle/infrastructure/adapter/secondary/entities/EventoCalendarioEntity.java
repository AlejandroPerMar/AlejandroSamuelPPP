package es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigInteger;


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

	//bi-directional many-to-one association to AlumnoEntity
	@ManyToOne
	@JoinColumn(name="id_student")
	private AlumnoEntity student;

	//bi-directional many-to-one association to TutorEntity
	@ManyToOne
	@JoinColumn(name="id_tutor")
	private TutorEntity tutor;

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

	public AlumnoEntity getStudent() {
		return this.student;
	}

	public void setStudent(AlumnoEntity student) {
		this.student = student;
	}

	public TutorEntity getTutor() {
		return this.tutor;
	}

	public void setTutor(TutorEntity tutor) {
		this.tutor = tutor;
	}

}