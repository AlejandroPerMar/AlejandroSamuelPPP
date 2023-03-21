package es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigInteger;


/**
 * The persistent class for the students_tutor_subjects database table.
 * 
 */
@Entity
@Table(name="students_tutor_subjects")
@NamedQuery(name="MateriaAlumnoEntity.findAll", query="SELECT m FROM MateriaAlumnoEntity m")
public class MateriaAlumnoEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@Column(name="created_at")
	private BigInteger fechaCreacion;

	//bi-directional many-to-one association to AlumnoEntity
	@ManyToOne
	@JoinColumn(name="id_student")
	private AlumnoEntity student;

	//uni-directional many-to-one association to EstadoMateriaAlumnoEntity
	@ManyToOne
	@JoinColumn(name="id_status")
	private EstadoMateriaAlumnoEntity studentsTutorSubjectsStatus;

	//bi-directional many-to-one association to MateriaTutorEntity
	@ManyToOne
	@JoinColumn(name="id_tutor_subject")
	private MateriaTutorEntity tutorSubject;

	public MateriaAlumnoEntity() {
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

	public AlumnoEntity getStudent() {
		return this.student;
	}

	public void setStudent(AlumnoEntity student) {
		this.student = student;
	}

	public EstadoMateriaAlumnoEntity getStudentsTutorSubjectsStatus() {
		return this.studentsTutorSubjectsStatus;
	}

	public void setStudentsTutorSubjectsStatus(EstadoMateriaAlumnoEntity studentsTutorSubjectsStatus) {
		this.studentsTutorSubjectsStatus = studentsTutorSubjectsStatus;
	}

	public MateriaTutorEntity getTutorSubject() {
		return this.tutorSubject;
	}

	public void setTutorSubject(MateriaTutorEntity tutorSubject) {
		this.tutorSubject = tutorSubject;
	}

}