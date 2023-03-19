package es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary;

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
@NamedQuery(name="PerfilAlumno.findAll", query="SELECT p FROM PerfilAlumno p")
public class PerfilAlumno implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@Column(name="created_at")
	private BigInteger fechaCreacion;

	@Column(name="id_user")
	private int usuario;

	//bi-directional many-to-one association to Anuncio
	@OneToMany(mappedBy="student")
	private List<Anuncio> announcements;

	//bi-directional many-to-one association to EventoCalendario
	@OneToMany(mappedBy="student")
	private List<EventoCalendario> calendarEvents;

	//bi-directional many-to-one association to NivelEstudios
	@ManyToOne
	@JoinColumn(name="id_study_level")
	private NivelEstudios studyLevel;

	//bi-directional many-to-one association to EstudianteMateria
	@OneToMany(mappedBy="student")
	private List<EstudianteMateria> studentsTutorSubjects;

	//bi-directional one-to-one association to Usuario
	@OneToOne(mappedBy="student")
	private Usuario user;

	public PerfilAlumno() {
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

	public int getUsuario() {
		return this.usuario;
	}

	public void setUsuario(int usuario) {
		this.usuario = usuario;
	}

	public List<Anuncio> getAnnouncements() {
		return this.announcements;
	}

	public void setAnnouncements(List<Anuncio> announcements) {
		this.announcements = announcements;
	}

	public Anuncio addAnnouncement(Anuncio announcement) {
		getAnnouncements().add(announcement);
		announcement.setStudent(this);

		return announcement;
	}

	public Anuncio removeAnnouncement(Anuncio announcement) {
		getAnnouncements().remove(announcement);
		announcement.setStudent(null);

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
		calendarEvent.setStudent(this);

		return calendarEvent;
	}

	public EventoCalendario removeCalendarEvent(EventoCalendario calendarEvent) {
		getCalendarEvents().remove(calendarEvent);
		calendarEvent.setStudent(null);

		return calendarEvent;
	}

	public NivelEstudios getStudyLevel() {
		return this.studyLevel;
	}

	public void setStudyLevel(NivelEstudios studyLevel) {
		this.studyLevel = studyLevel;
	}

	public List<EstudianteMateria> getStudentsTutorSubjects() {
		return this.studentsTutorSubjects;
	}

	public void setStudentsTutorSubjects(List<EstudianteMateria> studentsTutorSubjects) {
		this.studentsTutorSubjects = studentsTutorSubjects;
	}

	public EstudianteMateria addStudentsTutorSubject(EstudianteMateria studentsTutorSubject) {
		getStudentsTutorSubjects().add(studentsTutorSubject);
		studentsTutorSubject.setStudent(this);

		return studentsTutorSubject;
	}

	public EstudianteMateria removeStudentsTutorSubject(EstudianteMateria studentsTutorSubject) {
		getStudentsTutorSubjects().remove(studentsTutorSubject);
		studentsTutorSubject.setStudent(null);

		return studentsTutorSubject;
	}

	public Usuario getUser() {
		return this.user;
	}

	public void setUser(Usuario user) {
		this.user = user;
	}

}