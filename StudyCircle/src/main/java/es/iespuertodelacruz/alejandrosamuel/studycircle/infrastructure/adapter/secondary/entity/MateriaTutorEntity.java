package es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.entity;

import java.io.Serializable;
import javax.persistence.*;


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

	//uni-directional many-to-one association to MateriaEntity
	@ManyToOne
	@JoinColumn(name="id_subject")
	private MateriaEntity materia;

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

	public MateriaEntity getMateria() {
		return this.materia;
	}

	public void setMateria(MateriaEntity materia) {
		this.materia = materia;
	}

	public TutorEntity getTutor() {
		return this.tutor;
	}

	public void setTutor(TutorEntity tutor) {
		this.tutor = tutor;
	}

}