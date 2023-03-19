package es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigInteger;
import java.util.List;


/**
 * The persistent class for the activities database table.
 * 
 */
@Entity
@Table(name="activities")
@NamedQuery(name="Actividad.findAll", query="SELECT a FROM Actividad a")
public class Actividad implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@Column(name="created_at")
	private BigInteger fechaCreacion;

	@Column(name="description")
	private String descripcion;

	@Column(name="id_status")
	private int idStatus;

	@Column(name="name")
	private String nombre;

	@Column(name="time_activity")
	private BigInteger fechaActividad;

	//bi-directional many-to-one association to PerfilTutor
	@ManyToOne
	@JoinColumn(name="id_tutor")
	private PerfilTutor tutor;

	//bi-directional many-to-one association to Materia
	@ManyToOne
	@JoinColumn(name="id_subject")
	private Materia subject;

	//bi-directional many-to-one association to EventoCalendario
	@OneToMany(mappedBy="activity")
	private List<EventoCalendario> calendarEvents;

	public Actividad() {
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

	public int getIdStatus() {
		return this.idStatus;
	}

	public void setIdStatus(int idStatus) {
		this.idStatus = idStatus;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public BigInteger getFechaActividad() {
		return this.fechaActividad;
	}

	public void setFechaActividad(BigInteger fechaActividad) {
		this.fechaActividad = fechaActividad;
	}

	public PerfilTutor getTutor() {
		return this.tutor;
	}

	public void setTutor(PerfilTutor tutor) {
		this.tutor = tutor;
	}

	public Materia getSubject() {
		return this.subject;
	}

	public void setSubject(Materia subject) {
		this.subject = subject;
	}

	public List<EventoCalendario> getCalendarEvents() {
		return this.calendarEvents;
	}

	public void setCalendarEvents(List<EventoCalendario> calendarEvents) {
		this.calendarEvents = calendarEvents;
	}

	public EventoCalendario addCalendarEvent(EventoCalendario calendarEvent) {
		getCalendarEvents().add(calendarEvent);
		calendarEvent.setActivity(this);

		return calendarEvent;
	}

	public EventoCalendario removeCalendarEvent(EventoCalendario calendarEvent) {
		getCalendarEvents().remove(calendarEvent);
		calendarEvent.setActivity(null);

		return calendarEvent;
	}

}