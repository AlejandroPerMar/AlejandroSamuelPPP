package es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.entities;

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
@NamedQuery(name="ActividadEntity.findAll", query="SELECT a FROM ActividadEntity a")
public class ActividadEntity implements Serializable {
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

	@Column(name="status")
	private String estado;

	@Column(name="time_activity")
	private BigInteger fechaActividad;

	//bi-directional many-to-many association to AlumnoEntity
	@ManyToMany
	@JoinTable(
		name="activities_students"
		, joinColumns={
			@JoinColumn(name="id_activity")
			}
		, inverseJoinColumns={
			@JoinColumn(name="id_student")
			}
		)
	private List<AlumnoEntity> students;

	//uni-directional many-to-one association to MateriaEntity
	@ManyToOne
	@JoinColumn(name="id_subject")
	private MateriaEntity subject;

	//bi-directional many-to-one association to TutorEntity
	@ManyToOne
	@JoinColumn(name="id_tutor")
	private TutorEntity tutor;

	public ActividadEntity() {
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

	public String getEstado() {
		return this.estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public BigInteger getFechaActividad() {
		return this.fechaActividad;
	}

	public void setFechaActividad(BigInteger fechaActividad) {
		this.fechaActividad = fechaActividad;
	}

	public List<AlumnoEntity> getStudents() {
		return this.students;
	}

	public void setStudents(List<AlumnoEntity> students) {
		this.students = students;
	}

	public MateriaEntity getSubject() {
		return this.subject;
	}

	public void setSubject(MateriaEntity subject) {
		this.subject = subject;
	}

	public TutorEntity getTutor() {
		return this.tutor;
	}

	public void setTutor(TutorEntity tutor) {
		this.tutor = tutor;
	}

}