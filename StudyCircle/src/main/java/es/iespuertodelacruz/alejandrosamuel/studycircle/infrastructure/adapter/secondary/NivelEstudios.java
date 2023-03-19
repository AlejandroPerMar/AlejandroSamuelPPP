package es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigInteger;
import java.util.List;


/**
 * The persistent class for the study_levels database table.
 * 
 */
@Entity
@Table(name="study_levels")
@NamedQuery(name="NivelEstudios.findAll", query="SELECT n FROM NivelEstudios n")
public class NivelEstudios implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@Column(name="created_at")
	private BigInteger fechaCreacion;

	@Column(name="name")
	private String nombre;

	//bi-directional many-to-one association to PerfilAlumno
	@OneToMany(mappedBy="studyLevel")
	private List<PerfilAlumno> students;

	//bi-directional many-to-one association to Materia
	@OneToMany(mappedBy="studyLevel")
	private List<Materia> subjects;

	public NivelEstudios() {
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

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<PerfilAlumno> getStudents() {
		return this.students;
	}

	public void setStudents(List<PerfilAlumno> students) {
		this.students = students;
	}

	public PerfilAlumno addStudent(PerfilAlumno student) {
		getStudents().add(student);
		student.setStudyLevel(this);

		return student;
	}

	public PerfilAlumno removeStudent(PerfilAlumno student) {
		getStudents().remove(student);
		student.setStudyLevel(null);

		return student;
	}

	public List<Materia> getSubjects() {
		return this.subjects;
	}

	public void setSubjects(List<Materia> subjects) {
		this.subjects = subjects;
	}

	public Materia addSubject(Materia subject) {
		getSubjects().add(subject);
		subject.setStudyLevel(this);

		return subject;
	}

	public Materia removeSubject(Materia subject) {
		getSubjects().remove(subject);
		subject.setStudyLevel(null);

		return subject;
	}

}