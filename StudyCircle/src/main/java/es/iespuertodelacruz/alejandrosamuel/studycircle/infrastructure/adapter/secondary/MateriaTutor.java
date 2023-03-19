package es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the tutor_subjects database table.
 * 
 */
@Entity
@Table(name="tutor_subjects")
@NamedQuery(name="MateriaTutor.findAll", query="SELECT m FROM MateriaTutor m")
public class MateriaTutor implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	//bi-directional many-to-one association to EstudianteMateria
	@OneToMany(mappedBy="tutorSubject")
	private List<EstudianteMateria> studentsTutorSubjects;

	//bi-directional many-to-one association to PerfilTutor
	@ManyToOne
	@JoinColumn(name="id_tutor")
	private PerfilTutor tutor;

	//bi-directional many-to-one association to Materia
	@ManyToOne
	@JoinColumn(name="id_subject")
	private Materia subject;

	public MateriaTutor() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<EstudianteMateria> getStudentsTutorSubjects() {
		return this.studentsTutorSubjects;
	}

	public void setStudentsTutorSubjects(List<EstudianteMateria> studentsTutorSubjects) {
		this.studentsTutorSubjects = studentsTutorSubjects;
	}

	public EstudianteMateria addStudentsTutorSubject(EstudianteMateria studentsTutorSubject) {
		getStudentsTutorSubjects().add(studentsTutorSubject);
		studentsTutorSubject.setTutorSubject(this);

		return studentsTutorSubject;
	}

	public EstudianteMateria removeStudentsTutorSubject(EstudianteMateria studentsTutorSubject) {
		getStudentsTutorSubjects().remove(studentsTutorSubject);
		studentsTutorSubject.setTutorSubject(null);

		return studentsTutorSubject;
	}

	public PerfilTutor getTutor() {
		return this.tutor;
	}

	public void setTutor(PerfilTutor tutor) {
		this.tutor = tutor;
	}

	public Materia getSubject() {
		return this.subject;
	}

	public void setSubject(Materia subject) {
		this.subject = subject;
	}

}