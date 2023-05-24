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
	private Integer id;

	@Column(name="created_at")
	private BigInteger fechaCreacion;

	@Column(name="title")
	private String titulo;

	//uni-directional many-to-one association to MateriaTutorEntity
	@ManyToOne
	@JoinColumn(name="id_tutor_subject")
	private MateriaTutorEntity materiaTutor;


	//uni-directional many-to-many association to ActividadEntity
	@OneToMany(mappedBy = "curso", cascade = CascadeType.REMOVE)
	private List<ActividadEntity> actividades;

	//bi-directional many-to-one association to AlumnoEntity
	@ManyToMany
	@JoinTable(
			name="course_students"
			, joinColumns={
			@JoinColumn(name="id_course")
	}
			, inverseJoinColumns={
			@JoinColumn(name="id_student")
	}
	)
	private List<AlumnoEntity> alumnos;

	public CursoEntity() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public List<ActividadEntity> getActividades() {
		return actividades;
	}

	public void setActividades(List<ActividadEntity> actividades) {
		this.actividades = actividades;
	}

	public List<AlumnoEntity> getAlumnos() {
		return alumnos;
	}

	public void setAlumnos(List<AlumnoEntity> alumnos) {
		this.alumnos = alumnos;
	}
}