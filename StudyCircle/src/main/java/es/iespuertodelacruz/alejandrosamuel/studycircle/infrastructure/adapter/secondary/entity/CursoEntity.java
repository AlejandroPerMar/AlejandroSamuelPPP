package es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the course database table.
 * 
 */
@Entity
@Table(name="course")
@NamedQuery(name="CursoEntity.findAll", query="SELECT c FROM CursoEntity c")
public class CursoEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@Column(name="price_per_hour")
	private Double precioHora;

	@Column(name="status")
	private String estado;

	@Column(name="title")
	private String titulo;

	//uni-directional many-to-one association to MateriaTutorEntity
	@ManyToOne
	@JoinColumn(name="id_tutor_subject")
	private MateriaTutorEntity tutorSubject;

	//bi-directional many-to-one association to ActividadCursoEntity
	@OneToMany(mappedBy="course")
	private List<ActividadCursoEntity> courseActivities;

	//bi-directional many-to-one association to AlumnoCursoEntity
	@OneToMany(mappedBy="course")
	private List<AlumnoCursoEntity> courseStudents;

	public CursoEntity() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Double getPrecioHora() {
		return this.precioHora;
	}

	public void setPrecioHora(Double precioHora) {
		this.precioHora = precioHora;
	}

	public String getEstado() {
		return this.estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getTitulo() {
		return this.titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public MateriaTutorEntity getTutorSubject() {
		return this.tutorSubject;
	}

	public void setTutorSubject(MateriaTutorEntity tutorSubject) {
		this.tutorSubject = tutorSubject;
	}

	public List<ActividadCursoEntity> getCourseActivities() {
		return this.courseActivities;
	}

	public void setCourseActivities(List<ActividadCursoEntity> courseActivities) {
		this.courseActivities = courseActivities;
	}

	public ActividadCursoEntity addCourseActivity(ActividadCursoEntity courseActivity) {
		getCourseActivities().add(courseActivity);
		courseActivity.setCourse(this);

		return courseActivity;
	}

	public ActividadCursoEntity removeCourseActivity(ActividadCursoEntity courseActivity) {
		getCourseActivities().remove(courseActivity);
		courseActivity.setCourse(null);

		return courseActivity;
	}

	public List<AlumnoCursoEntity> getCourseStudents() {
		return this.courseStudents;
	}

	public void setCourseStudents(List<AlumnoCursoEntity> courseStudents) {
		this.courseStudents = courseStudents;
	}

	public AlumnoCursoEntity addCourseStudent(AlumnoCursoEntity courseStudent) {
		getCourseStudents().add(courseStudent);
		courseStudent.setCourse(this);

		return courseStudent;
	}

	public AlumnoCursoEntity removeCourseStudent(AlumnoCursoEntity courseStudent) {
		getCourseStudents().remove(courseStudent);
		courseStudent.setCourse(null);

		return courseStudent;
	}

}