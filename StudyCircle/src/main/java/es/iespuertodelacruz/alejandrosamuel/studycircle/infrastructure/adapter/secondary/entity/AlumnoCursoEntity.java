package es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the course_students database table.
 * 
 */
@Entity
@Table(name="course_students")
@NamedQuery(name="AlumnoCursoEntity.findAll", query="SELECT a FROM AlumnoCursoEntity a")
public class AlumnoCursoEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	//bi-directional many-to-one association to CursoEntity
	@ManyToOne
	@JoinColumn(name="id_course")
	private CursoEntity course;

	//uni-directional many-to-one association to AlumnoEntity
	@ManyToOne
	@JoinColumn(name="id_student")
	private AlumnoEntity student;

	public AlumnoCursoEntity() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public CursoEntity getCourse() {
		return this.course;
	}

	public void setCourse(CursoEntity course) {
		this.course = course;
	}

	public AlumnoEntity getStudent() {
		return this.student;
	}

	public void setStudent(AlumnoEntity student) {
		this.student = student;
	}

}