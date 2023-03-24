package es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigDecimal;


/**
 * The persistent class for the tutor_rates database table.
 * 
 */
@Entity
@Table(name="tutor_rates")
@NamedQuery(name="TarifaEntity.findAll", query="SELECT t FROM TarifaEntity t")
public class TarifaEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@Column(name="price_per__hour")
	private BigDecimal precioHora;

	//uni-directional many-to-one association to MateriaEntity
	@ManyToOne
	@JoinColumn(name="id_subject")
	private MateriaEntity subject;

	//bi-directional many-to-one association to TutorEntity
	@ManyToOne
	@JoinColumn(name="id_tutor")
	private TutorEntity tutor;

	public TarifaEntity() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public BigDecimal getPrecioHora() {
		return this.precioHora;
	}

	public void setPrecioHora(BigDecimal precioHora) {
		this.precioHora = precioHora;
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