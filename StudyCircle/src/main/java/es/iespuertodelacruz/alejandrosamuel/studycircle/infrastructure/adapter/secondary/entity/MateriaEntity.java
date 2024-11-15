package es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.entity;


import java.io.Serializable;
import javax.persistence.*;
import java.math.BigInteger;


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
	private Integer id;

	@Column(name="created_at")
	private BigInteger fechaCreacion;

	@Column(name="name")
	private String nombre;

	//bi-directional many-to-one association to NivelEstudiosEntity
	@ManyToOne
	@JoinColumn(name="id_study_level")
	private NivelEstudiosEntity nivelEstudios;

	public MateriaEntity() {
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

	public NivelEstudiosEntity getNivelEstudios() {
		return this.nivelEstudios;
	}

	public void setNivelEstudios(NivelEstudiosEntity nivelEstudios) {
		this.nivelEstudios = nivelEstudios;
	}

}