package es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the tutor_rates database table.
 * 
 */
@Entity
@Table(name="tutor_rates")
@NamedQuery(name="TarifaTutor.findAll", query="SELECT t FROM TarifaTutor t")
public class TarifaTutor implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@Column(name="price_per__hour")
	private Double precioHora;

	//bi-directional many-to-one association to PerfilTutor
	@ManyToOne
	@JoinColumn(name="id_tutor")
	private PerfilTutor tutor;

	//bi-directional many-to-one association to Materia
	@ManyToOne
	@JoinColumn(name="id_subject")
	private Materia subject;

	public TarifaTutor() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Double getPrecioHora() {
		return this.precioHora;
	}

	public void setPrecioHora(Double precioHora) {
		this.precioHora = precioHora;
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