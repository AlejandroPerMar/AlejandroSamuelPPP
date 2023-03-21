package es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the announcement_statuses database table.
 * 
 */
@Entity
@Table(name="announcement_statuses")
@NamedQuery(name="EstadoAnuncioEntity.findAll", query="SELECT e FROM EstadoAnuncioEntity e")
public class EstadoAnuncioEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@Column(name="status")
	private String estado;

	public EstadoAnuncioEntity() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEstado() {
		return this.estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

}