package es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.entity;

import java.io.Serial;
import java.io.Serializable;
import javax.persistence.*;
import java.math.BigInteger;
import java.util.List;


/**
 * The persistent class for the course database table.
 * 
 */
@Entity
@Table(name="course")
@NamedQuery(name="CursoEntity.findAll", query="SELECT c FROM CursoEntity c")
public class CursoEntity implements Serializable {
	@Serial
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@Column(name="price_per_hour")
	private Double precioHora;

	@Column(name="status")
	private String estado;

	@Column(name="created_at")
	private BigInteger fechaCreacion;

	@Column(name="title")
	private String titulo;

	//uni-directional many-to-one association to MateriaTutorEntity
	@ManyToOne
	@JoinColumn(name="id_tutor_subject")
	private MateriaTutorEntity materiaTutor;

	//bi-directional many-to-one association to ActividadCursoEntity
	@OneToMany(mappedBy="curso", cascade = CascadeType.REMOVE)
	private List<ActividadCursoEntity> actividadesCurso;

	//bi-directional many-to-one association to AlumnoCursoEntity
	@OneToMany(mappedBy="curso", cascade = CascadeType.REMOVE)
	private List<AlumnoCursoEntity> alumnosCurso;

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

	public BigInteger getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(BigInteger fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public MateriaTutorEntity getMateriaTutor() {
		return this.materiaTutor;
	}

	public void setMateriaTutor(MateriaTutorEntity materiaTutor) {
		this.materiaTutor = materiaTutor;
	}

	public List<ActividadCursoEntity> getActividadesCurso() {
		return this.actividadesCurso;
	}

	public void setActividadesCurso(List<ActividadCursoEntity> actividadesCurso) {
		this.actividadesCurso = actividadesCurso;
	}

	public ActividadCursoEntity addCourseActivity(ActividadCursoEntity courseActivity) {
		getActividadesCurso().add(courseActivity);
		courseActivity.setCurso(this);

		return courseActivity;
	}

	public ActividadCursoEntity removeCourseActivity(ActividadCursoEntity courseActivity) {
		getActividadesCurso().remove(courseActivity);
		courseActivity.setCurso(null);

		return courseActivity;
	}

	public List<AlumnoCursoEntity> getAlumnosCurso() {
		return this.alumnosCurso;
	}

	public void setAlumnosCurso(List<AlumnoCursoEntity> alumnosCurso) {
		this.alumnosCurso = alumnosCurso;
	}

	public AlumnoCursoEntity addCourseStudent(AlumnoCursoEntity courseStudent) {
		getAlumnosCurso().add(courseStudent);
		courseStudent.setCurso(this);

		return courseStudent;
	}

	public AlumnoCursoEntity removeCourseStudent(AlumnoCursoEntity courseStudent) {
		getAlumnosCurso().remove(courseStudent);
		courseStudent.setCurso(null);

		return courseStudent;
	}

}