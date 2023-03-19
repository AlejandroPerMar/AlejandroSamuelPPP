package es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigInteger;


/**
 * The persistent class for the students_tutor_subjects database table.
 * 
 */
@Entity
@Table(name="students_tutor_subjects")
@NamedQuery(name="EstudianteMateria.findAll", query="SELECT e FROM EstudianteMateria e")
public class EstudianteMateria implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@Column(name="created_at")
	private BigInteger fechaCreacion;

	@Column(name="id_status")
	private int idStatus;

	//bi-directional many-to-one association to PerfilAlumno
	@ManyToOne
	@JoinColumn(name="id_student")
	private PerfilAlumno student;

	//bi-directional many-to-one association to MateriaTutor
	@ManyToOne
	@JoinColumn(name="id_tutor_subject")
	private MateriaTutor tutorSubject;

	public EstudianteMateria() {
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

	public int getIdStatus() {
		return this.idStatus;
	}

	public void setIdStatus(int idStatus) {
		this.idStatus = idStatus;
	}

	public PerfilAlumno getStudent() {
		return this.student;
	}

	public void setStudent(PerfilAlumno student) {
		this.student = student;
	}

	public MateriaTutor getTutorSubject() {
		return this.tutorSubject;
	}

	public void setTutorSubject(MateriaTutor tutorSubject) {
		this.tutorSubject = tutorSubject;
	}

}