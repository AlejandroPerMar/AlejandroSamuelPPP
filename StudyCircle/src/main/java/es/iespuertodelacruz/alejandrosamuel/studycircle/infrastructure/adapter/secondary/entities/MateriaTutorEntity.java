package es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the tutor_subjects database table.
 * 
 */
@Entity
@Table(name="tutor_subjects")
@NamedQuery(name="MateriaTutorEntity.findAll", query="SELECT m FROM MateriaTutorEntity m")
public class MateriaTutorEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	//bi-directional many-to-one association to MateriaAlumnoEntity
	@OneToMany(mappedBy="tutorSubject")
	private List<MateriaAlumnoEntity> studentsTutorSubjects;

	//uni-directional many-to-one association to MateriaEntity
	@ManyToOne
	@JoinColumn(name="id_subject")
	private MateriaEntity subject;

	//bi-directional many-to-one association to TutorEntity
	@ManyToOne
	@JoinColumn(name="id_tutor")
	private TutorEntity tutor;

	public MateriaTutorEntity() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<MateriaAlumnoEntity> getStudentsTutorSubjects() {
		return this.studentsTutorSubjects;
	}

	public void setStudentsTutorSubjects(List<MateriaAlumnoEntity> studentsTutorSubjects) {
		this.studentsTutorSubjects = studentsTutorSubjects;
	}

	public MateriaAlumnoEntity addStudentsTutorSubject(MateriaAlumnoEntity studentsTutorSubject) {
		getStudentsTutorSubjects().add(studentsTutorSubject);
		studentsTutorSubject.setTutorSubject(this);

		return studentsTutorSubject;
	}

	public MateriaAlumnoEntity removeStudentsTutorSubject(MateriaAlumnoEntity studentsTutorSubject) {
		getStudentsTutorSubjects().remove(studentsTutorSubject);
		studentsTutorSubject.setTutorSubject(null);

		return studentsTutorSubject;
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