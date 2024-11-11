package es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.entity;

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
@NamedQuery(name="NivelEstudiosEntity.findAll", query="SELECT n FROM NivelEstudiosEntity n")
public class NivelEstudiosEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	@Column(name="created_at")
	private BigInteger fechaCreacion;

	@Column(name="name")
	private String nombre;

	//bi-directional many-to-one association to MateriaEntity
	@OneToMany(mappedBy="nivelEstudios")
	private List<MateriaEntity> materias;

	public NivelEstudiosEntity() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
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

	public List<MateriaEntity> getMaterias() {
		return this.materias;
	}

	public void setMaterias(List<MateriaEntity> materias) {
		this.materias = materias;
	}

	public MateriaEntity addSubject(MateriaEntity subject) {
		getMaterias().add(subject);
		subject.setNivelEstudios(this);

		return subject;
	}

	public MateriaEntity removeSubject(MateriaEntity subject) {
		getMaterias().remove(subject);
		subject.setNivelEstudios(null);

		return subject;
	}

}