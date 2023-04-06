package es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the tutors database table.
 * 
 */
@Entity
@Table(name="tutors")
@NamedQuery(name="TutorEntity.findAll", query="SELECT t FROM TutorEntity t")
public class TutorEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@Column(name="created_at")
	private BigInteger fechaCreacion;

	//bi-directional many-to-one association to MateriaTutorEntity
	@OneToMany(mappedBy="tutor")
	private List<MateriaTutorEntity> tutorSubjects;

	//uni-directional many-to-one association to UsuarioEntity
	@ManyToOne
	@JoinColumn(name="id_user")
	private UsuarioEntity user;

	public TutorEntity() {
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

	public List<MateriaTutorEntity> getTutorSubjects() {
		return this.tutorSubjects;
	}

	public void setTutorSubjects(List<MateriaTutorEntity> tutorSubjects) {
		this.tutorSubjects = tutorSubjects;
	}

	public MateriaTutorEntity addTutorSubject(MateriaTutorEntity tutorSubject) {
		getTutorSubjects().add(tutorSubject);
		tutorSubject.setTutor(this);

		return tutorSubject;
	}

	public MateriaTutorEntity removeTutorSubject(MateriaTutorEntity tutorSubject) {
		getTutorSubjects().remove(tutorSubject);
		tutorSubject.setTutor(null);

		return tutorSubject;
	}

	public UsuarioEntity getUser() {
		return this.user;
	}

	public void setUser(UsuarioEntity user) {
		this.user = user;
	}

}