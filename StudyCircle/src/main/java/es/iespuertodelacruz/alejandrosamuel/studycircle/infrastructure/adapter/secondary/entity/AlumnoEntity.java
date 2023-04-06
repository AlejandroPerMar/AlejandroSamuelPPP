package es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the students database table.
 * 
 */
@Entity
@Table(name="students")
@NamedQuery(name="AlumnoEntity.findAll", query="SELECT a FROM AlumnoEntity a")
public class AlumnoEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@Column(name="created_at")
	private BigInteger fechaCreacion;

	//uni-directional many-to-one association to NivelEstudiosEntity
	@ManyToOne
	@JoinColumn(name="id_study_level")
	private NivelEstudiosEntity studyLevel;

	//uni-directional many-to-many association to MateriaEntity
	@ManyToMany
	@JoinTable(
		name="student_subjects"
		, joinColumns={
			@JoinColumn(name="id_student")
			}
		, inverseJoinColumns={
			@JoinColumn(name="id_subject")
			}
		)
	private List<MateriaEntity> subjects;

	//uni-directional many-to-one association to UsuarioEntity
	@ManyToOne
	@JoinColumn(name="id_user")
	private UsuarioEntity user;

	public AlumnoEntity() {
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

	public NivelEstudiosEntity getStudyLevel() {
		return this.studyLevel;
	}

	public void setStudyLevel(NivelEstudiosEntity studyLevel) {
		this.studyLevel = studyLevel;
	}

	public List<MateriaEntity> getSubjects() {
		return this.subjects;
	}

	public void setSubjects(List<MateriaEntity> subjects) {
		this.subjects = subjects;
	}

	public UsuarioEntity getUser() {
		return this.user;
	}

	public void setUser(UsuarioEntity user) {
		this.user = user;
	}

}