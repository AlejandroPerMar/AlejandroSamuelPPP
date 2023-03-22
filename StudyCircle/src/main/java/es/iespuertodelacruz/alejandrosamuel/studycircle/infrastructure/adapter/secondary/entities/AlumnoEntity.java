package es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigInteger;
import java.util.List;


/**
 * The persistent class for the students database table.
 * 
 */
@Entity
@Table(name="students")
@NamedQuery(name="AlumnoEntity.findAll", query="SELECT a FROM AlumnoEntity a")
public class AlumnoEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@Column(name="created_at")
	private BigInteger fechaCreacion;

	//bi-directional many-to-many association to ActividadEntity
	@ManyToMany(mappedBy="students")
	private List<ActividadEntity> activities;

	//bi-directional many-to-one association to AnuncioEntity
	@OneToMany(mappedBy="student")
	private List<AnuncioEntity> announcements;

	//bi-directional many-to-one association to EventoCalendarioEntity
	@OneToMany(mappedBy="student")
	private List<EventoCalendarioEntity> calendarEvents;

	//uni-directional many-to-one association to NivelEstudiosEntity
	@ManyToOne
	@JoinColumn(name="id_study_level")
	private NivelEstudiosEntity studyLevel;

	//bi-directional many-to-one association to MateriaAlumnoEntity
	@OneToMany(mappedBy="student")
	private List<MateriaAlumnoEntity> studentsTutorSubjects;

	//bi-directional one-to-one association to UsuarioEntity
	@OneToOne(mappedBy="student")
	private UsuarioEntity user;

	public AlumnoEntity() {
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

	public List<AnuncioEntity> getAnnouncements() {
		return this.announcements;
	}

	public void setAnnouncements(List<AnuncioEntity> announcements) {
		this.announcements = announcements;
	}

	public AnuncioEntity addAnnouncement(AnuncioEntity announcement) {
		getAnnouncements().add(announcement);
		announcement.setStudent(this);

		return announcement;
	}

	public AnuncioEntity removeAnnouncement(AnuncioEntity announcement) {
		getAnnouncements().remove(announcement);
		announcement.setStudent(null);

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
		calendarEvent.setStudent(this);

		return calendarEvent;
	}

	public EventoCalendarioEntity removeCalendarEvent(EventoCalendarioEntity calendarEvent) {
		getCalendarEvents().remove(calendarEvent);
		calendarEvent.setStudent(null);

		return calendarEvent;
	}

	public NivelEstudiosEntity getStudyLevel() {
		return this.studyLevel;
	}

	public void setStudyLevel(NivelEstudiosEntity studyLevel) {
		this.studyLevel = studyLevel;
	}

	public List<MateriaAlumnoEntity> getStudentsTutorSubjects() {
		return this.studentsTutorSubjects;
	}

	public void setStudentsTutorSubjects(List<MateriaAlumnoEntity> studentsTutorSubjects) {
		this.studentsTutorSubjects = studentsTutorSubjects;
	}

	public MateriaAlumnoEntity addStudentsTutorSubject(MateriaAlumnoEntity studentsTutorSubject) {
		getStudentsTutorSubjects().add(studentsTutorSubject);
		studentsTutorSubject.setStudent(this);

		return studentsTutorSubject;
	}

	public MateriaAlumnoEntity removeStudentsTutorSubject(MateriaAlumnoEntity studentsTutorSubject) {
		getStudentsTutorSubjects().remove(studentsTutorSubject);
		studentsTutorSubject.setStudent(null);

		return studentsTutorSubject;
	}

	public UsuarioEntity getUser() {
		return this.user;
	}

	public void setUser(UsuarioEntity user) {
		this.user = user;
	}

}