package es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigInteger;
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
	private List<MateriaTutorEntity> materiasTutor;

	//uni-directional many-to-one association to UsuarioEntity
	@ManyToOne
	@JoinColumn(name="id_user")
	private UsuarioEntity usuario;

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

	public List<MateriaTutorEntity> getMateriasTutor() {
		return this.materiasTutor;
	}

	public void setMateriasTutor(List<MateriaTutorEntity> materiasTutor) {
		this.materiasTutor = materiasTutor;
	}

	public MateriaTutorEntity addTutorSubject(MateriaTutorEntity tutorSubject) {
		getMateriasTutor().add(tutorSubject);
		tutorSubject.setTutor(this);

		return tutorSubject;
	}

	public MateriaTutorEntity removeTutorSubject(MateriaTutorEntity tutorSubject) {
		getMateriasTutor().remove(tutorSubject);
		tutorSubject.setTutor(null);

		return tutorSubject;
	}

	public UsuarioEntity getUsuario() {
		return this.usuario;
	}

	public void setUsuario(UsuarioEntity usuario) {
		this.usuario = usuario;
	}

}