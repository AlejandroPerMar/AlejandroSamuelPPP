package es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the alert_types database table.
 * 
 */
@Entity
@Table(name="alert_types")
@NamedQuery(name="TipoAlertaEntity.findAll", query="SELECT t FROM TipoAlertaEntity t")
public class TipoAlertaEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@Column(name="type")
	private String tipo;

	public TipoAlertaEntity() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTipo() {
		return this.tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

}