package es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigInteger;
import java.util.List;


/**
 * The persistent class for the tutors database table.
 * 
 */
@Entity
@Table(name="tutors")
@NamedQuery(name="TutorEntity.findAll", query="SELECT t FROM TutorEntity t")
public class TutorEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@Column(name="created_at")
	private BigInteger fechaCreacion;

	//bi-directional many-to-one association to ActividadEntity
	@OneToMany(mappedBy="tutor")
	private List<ActividadEntity> activities;

	//bi-directional many-to-one association to AnuncioEntity
	@OneToMany(mappedBy="tutor")
	private List<AnuncioEntity> announcements;

	//bi-directional many-to-one association to EventoCalendarioEntity
	@OneToMany(mappedBy="tutor")
	private List<EventoCalendarioEntity> calendarEvents;

	//bi-directional many-to-one association to TarifaEntity
	@OneToMany(mappedBy="tutor")
	private List<TarifaEntity> tutorRates;

	//bi-directional many-to-one association to MateriaTutorEntity
	@OneToMany(mappedBy="tutor")
	private List<MateriaTutorEntity> tutorSubjects;

	//bi-directional one-to-one association to UsuarioEntity
	@OneToOne(mappedBy="tutor")
	private UsuarioEntity user;

	public TutorEntity() {
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

	public List<ActividadEntity> getActivities() {
		return this.activities;
	}

	public void setActivities(List<ActividadEntity> activities) {
		this.activities = activities;
	}

	public ActividadEntity addActivity(ActividadEntity activity) {
		getActivities().add(activity);
		activity.setTutor(this);

		return activity;
	}

	public ActividadEntity removeActivity(ActividadEntity activity) {
		getActivities().remove(activity);
		activity.setTutor(null);

		return activity;
	}

	public List<AnuncioEntity> getAnnouncements() {
		return this.announcements;
	}

	public void setAnnouncements(List<AnuncioEntity> announcements) {
		this.announcements = announcements;
	}

	public AnuncioEntity addAnnouncement(AnuncioEntity announcement) {
		getAnnouncements().add(announcement);
		announcement.setTutor(this);

		return announcement;
	}

	public AnuncioEntity removeAnnouncement(AnuncioEntity announcement) {
		getAnnouncements().remove(announcement);
		announcement.setTutor(null);

		return announcement;
	}

	public List<EventoCalendarioEntity> getCalendarEvents() {
		return this.calendarEvents;
	}

	public void setCalendarEvents(List<EventoCalendarioEntity> calendarEvents) {
		this.calendarEvents = calendarEvents;
	}

	public EventoCalendarioEntity addCalendarEvent(EventoCalendarioEntity calendarEvent) {
		getCalendarEvents().add(calendarEvent);
		calendarEvent.setTutor(this);

		return calendarEvent;
	}

	public EventoCalendarioEntity removeCalendarEvent(EventoCalendarioEntity calendarEvent) {
		getCalendarEvents().remove(calendarEvent);
		calendarEvent.setTutor(null);

		return calendarEvent;
	}

	public List<TarifaEntity> getTutorRates() {
		return this.tutorRates;
	}

	public void setTutorRates(List<TarifaEntity> tutorRates) {
		this.tutorRates = tutorRates;
	}

	public TarifaEntity addTutorRate(TarifaEntity tutorRate) {
		getTutorRates().add(tutorRate);
		tutorRate.setTutor(this);

		return tutorRate;
	}

	public TarifaEntity removeTutorRate(TarifaEntity tutorRate) {
		getTutorRates().remove(tutorRate);
		tutorRate.setTutor(null);

		return tutorRate;
	}

	public List<MateriaTutorEntity> getTutorSubjects() {
		return this.tutorSubjects;
	}

	public void setTutorSubjects(List<MateriaTutorEntity> tutorSubjects) {
		this.tutorSubjects = tutorSubjects;
	}

	public MateriaTutorEntity addTutorSubject(MateriaTutorEntity tutorSubject) {
		getTutorSubjects().add(tutorSubject);
		tutorSubject.setTutor(this);

		return tutorSubject;
	}

	public MateriaTutorEntity removeTutorSubject(MateriaTutorEntity tutorSubject) {
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