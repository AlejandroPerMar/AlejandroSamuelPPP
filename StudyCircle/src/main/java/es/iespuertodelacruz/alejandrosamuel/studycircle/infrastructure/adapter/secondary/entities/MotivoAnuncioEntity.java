package es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the announcement_reasons database table.
 * 
 */
@Entity
@Table(name="announcement_reasons")
@NamedQuery(name="MotivoAnuncioEntity.findAll", query="SELECT m FROM MotivoAnuncioEntity m")
public class MotivoAnuncioEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@Column(name="reason")
	private String motivo;

	public MotivoAnuncioEntity() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMotivo() {
		return this.motivo;
	}

	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}

}