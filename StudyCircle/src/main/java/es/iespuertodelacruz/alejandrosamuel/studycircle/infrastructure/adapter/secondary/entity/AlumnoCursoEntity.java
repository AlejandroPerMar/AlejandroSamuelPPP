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
	private CursoEntity curso;

	//uni-directional many-to-one association to AlumnoEntity
	@ManyToOne
	@JoinColumn(name="id_student")
	private AlumnoEntity alumno;

	public AlumnoCursoEntity() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public CursoEntity getCurso() {
		return this.curso;
	}

	public void setCurso(CursoEntity curso) {
		this.curso = curso;
	}

	public AlumnoEntity getAlumno() {
		return this.alumno;
	}

	public void setAlumno(AlumnoEntity alumno) {
		this.alumno = alumno;
	}

}