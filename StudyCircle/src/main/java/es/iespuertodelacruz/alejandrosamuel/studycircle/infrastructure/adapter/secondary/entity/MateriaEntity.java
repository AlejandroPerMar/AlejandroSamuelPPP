package es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigInteger;
import java.util.List;


/**
 * The persistent class for the subjects database table.
 * 
 */
@Entity
@Table(name="subjects")
@NamedQuery(name="MateriaEntity.findAll", query="SELECT m FROM MateriaEntity m")
public class MateriaEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@Column(name="created_at")
	private BigInteger fechaCreacion;

	@Column(name="name")
	private String nombre;

	//bi-directional many-to-many association to AlumnoEntity
	@ManyToMany(mappedBy="subjects")
	private List<AlumnoEntity> students;

	//bi-directional many-to-one association to NivelEstudiosEntity
	@ManyToOne
	@JoinColumn(name="id_study_level")
	private NivelEstudiosEntity studyLevel;

	public MateriaEntity() {
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

	public List<AlumnoEntity> getStudents() {
		return this.students;
	}

	public void setStudents(List<AlumnoEntity> students) {
		this.students = students;
	}

	public NivelEstudiosEntity getStudyLevel() {
		return this.studyLevel;
	}

	public void setStudyLevel(NivelEstudiosEntity studyLevel) {
		this.studyLevel = studyLevel;
	}

}