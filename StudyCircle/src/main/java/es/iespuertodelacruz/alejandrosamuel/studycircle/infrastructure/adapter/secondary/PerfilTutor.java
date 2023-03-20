package es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the tutors database table.
 * 
 */
@Entity
@Table(name="tutors")
@NamedQuery(name="PerfilTutor.findAll", query="SELECT p FROM PerfilTutor p")
public class PerfilTutor implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@Column(name="created_at")
	private Timestamp fechaCreacion;

	@Column(name="id_user")
	private int usuario;

	//bi-directional many-to-one association to Actividad
	@OneToMany(mappedBy="tutor")
	private List<Actividad> activities;

	//bi-directional many-to-one association to Anuncio
	@OneToMany(mappedBy="tutor")
	private List<Anuncio> announcements;

	//bi-directional many-to-one association to EventoCalendario
	@OneToMany(mappedBy="tutor")
	private List<EventoCalendario> calendarEvents;

	//bi-directional many-to-one association to TarifaTutor
	@OneToMany(mappedBy="tutor")
	private List<TarifaTutor> tutorRates;

	//bi-directional many-to-one association to MateriaTutor
	@OneToMany(mappedBy="tutor")
	private List<MateriaTutor> tutorSubjects;

	//bi-directional one-to-one association to Usuario
	@OneToOne(mappedBy="tutor")
	private UsuarioEntity user;

	public PerfilTutor() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Timestamp getFechaCreacion() {
		return this.fechaCreacion;
	}

	public void setFechaCreacion(Timestamp fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public int getUsuario() {
		return this.usuario;
	}

	public void setUsuario(int usuario) {
		this.usuario = usuario;
	}

	public List<Actividad> getActivities() {
		return this.activities;
	}

	public void setActivities(List<Actividad> activities) {
		this.activities = activities;
	}

	public Actividad addActivity(Actividad activity) {
		getActivities().add(activity);
		activity.setTutor(this);

		return activity;
	}

	public Actividad removeActivity(Actividad activity) {
		getActivities().remove(activity);
		activity.setTutor(null);

		return activity;
	}

	public List<Anuncio> getAnnouncements() {
		return this.announcements;
	}

	public void setAnnouncements(List<Anuncio> announcements) {
		this.announcements = announcements;
	}

	public Anuncio addAnnouncement(Anuncio announcement) {
		getAnnouncements().add(announcement);
		announcement.setTutor(this);

		return announcement;
	}

	public Anuncio removeAnnouncement(Anuncio announcement) {
		getAnnouncements().remove(announcement);
		announcement.setTutor(null);

		return announcement;
	}

	public List<EventoCalendario> getCalendarEvents() {
		return this.calendarEvents;
	}

	public void setCalendarEvents(List<EventoCalendario> calendarEvents) {
		this.calendarEvents = calendarEvents;
	}

	public EventoCalendario addCalendarEvent(EventoCalendario calendarEvent) {
		getCalendarEvents().add(calendarEvent);
		calendarEvent.setTutor(this);

		return calendarEvent;
	}

	public EventoCalendario removeCalendarEvent(EventoCalendario calendarEvent) {
		getCalendarEvents().remove(calendarEvent);
		calendarEvent.setTutor(null);

		return calendarEvent;
	}

	public List<TarifaTutor> getTutorRates() {
		return this.tutorRates;
	}

	public void setTutorRates(List<TarifaTutor> tutorRates) {
		this.tutorRates = tutorRates;
	}

	public TarifaTutor addTutorRate(TarifaTutor tutorRate) {
		getTutorRates().add(tutorRate);
		tutorRate.setTutor(this);

		return tutorRate;
	}

	public TarifaTutor removeTutorRate(TarifaTutor tutorRate) {
		getTutorRates().remove(tutorRate);
		tutorRate.setTutor(null);

		return tutorRate;
	}

	public List<MateriaTutor> getTutorSubjects() {
		return this.tutorSubjects;
	}

	public void setTutorSubjects(List<MateriaTutor> tutorSubjects) {
		this.tutorSubjects = tutorSubjects;
	}

	public MateriaTutor addTutorSubject(MateriaTutor tutorSubject) {
		getTutorSubjects().add(tutorSubject);
		tutorSubject.setTutor(this);

		return tutorSubject;
	}

	public MateriaTutor removeTutorSubject(MateriaTutor tutorSubject) {
		getTutorSubjects().remove(tutorSubject);
		tutorSubject.setTutor(null);

		return tutorSubject;
	}

	public UsuarioEntity getUser() {
		return this.user;
	}

	public void setUser(UsuarioEntity user) {
		this.user = user;
	}

}