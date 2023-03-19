package es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigInteger;
import java.util.List;


/**
 * The persistent class for the subjects database table.
 * 
 */
@Entity
@Table(name="subjects")
@NamedQuery(name="Materia.findAll", query="SELECT m FROM Materia m")
public class Materia implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@Column(name="created_at")
	private BigInteger fechaCreacion;

	@Column(name="name")
	private String nombre;

	//bi-directional many-to-one association to Actividad
	@OneToMany(mappedBy="subject")
	private List<Actividad> activities;

	//bi-directional many-to-one association to Anuncio
	@OneToMany(mappedBy="subject")
	private List<Anuncio> announcements;

	//bi-directional many-to-one association to NivelEstudios
	@ManyToOne
	@JoinColumn(name="id_study_level")
	private NivelEstudios studyLevel;

	//bi-directional many-to-one association to TarifaTutor
	@OneToMany(mappedBy="subject")
	private List<TarifaTutor> tutorRates;

	//bi-directional many-to-one association to MateriaTutor
	@OneToMany(mappedBy="subject")
	private List<MateriaTutor> tutorSubjects;

	public Materia() {
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

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<Actividad> getActivities() {
		return this.activities;
	}

	public void setActivities(List<Actividad> activities) {
		this.activities = activities;
	}

	public Actividad addActivity(Actividad activity) {
		getActivities().add(activity);
		activity.setSubject(this);

		return activity;
	}

	public Actividad removeActivity(Actividad activity) {
		getActivities().remove(activity);
		activity.setSubject(null);

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
		announcement.setSubject(this);

		return announcement;
	}

	public Anuncio removeAnnouncement(Anuncio announcement) {
		getAnnouncements().remove(announcement);
		announcement.setSubject(null);

		return announcement;
	}

	public NivelEstudios getStudyLevel() {
		return this.studyLevel;
	}

	public void setStudyLevel(NivelEstudios studyLevel) {
		this.studyLevel = studyLevel;
	}

	public List<TarifaTutor> getTutorRates() {
		return this.tutorRates;
	}

	public void setTutorRates(List<TarifaTutor> tutorRates) {
		this.tutorRates = tutorRates;
	}

	public TarifaTutor addTutorRate(TarifaTutor tutorRate) {
		getTutorRates().add(tutorRate);
		tutorRate.setSubject(this);

		return tutorRate;
	}

	public TarifaTutor removeTutorRate(TarifaTutor tutorRate) {
		getTutorRates().remove(tutorRate);
		tutorRate.setSubject(null);

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
		tutorSubject.setSubject(this);

		return tutorSubject;
	}

	public MateriaTutor removeTutorSubject(MateriaTutor tutorSubject) {
		getTutorSubjects().remove(tutorSubject);
		tutorSubject.setSubject(null);

		return tutorSubject;
	}

}