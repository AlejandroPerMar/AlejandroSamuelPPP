package es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigInteger;


/**
 * The persistent class for the calendar_events database table.
 * 
 */
@Entity
@Table(name="calendar_events")
@NamedQuery(name="EventoCalendario.findAll", query="SELECT e FROM EventoCalendario e")
public class EventoCalendario implements Serializable {
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

	//bi-directional many-to-one association to Actividad
	@ManyToOne
	@JoinColumn(name="id_activity")
	private Actividad activity;

	//bi-directional many-to-one association to PerfilTutor
	@ManyToOne
	@JoinColumn(name="id_tutor")
	private PerfilTutor tutor;

	//bi-directional many-to-one association to PerfilAlumno
	@ManyToOne
	@JoinColumn(name="id_student")
	private PerfilAlumno student;

	public EventoCalendario() {
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

	public Actividad getActivity() {
		return this.activity;
	}

	public void setActivity(Actividad activity) {
		this.activity = activity;
	}

	public PerfilTutor getTutor() {
		return this.tutor;
	}

	public void setTutor(PerfilTutor tutor) {
		this.tutor = tutor;
	}

	public PerfilAlumno getStudent() {
		return this.student;
	}

	public void setStudent(PerfilAlumno student) {
		this.student = student;
	}

}