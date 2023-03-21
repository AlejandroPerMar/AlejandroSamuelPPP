package es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the friendship_statuses database table.
 * 
 */
@Entity
@Table(name="friendship_statuses")
@NamedQuery(name="EstadoAmistadEntity.findAll", query="SELECT e FROM EstadoAmistadEntity e")
public class EstadoAmistadEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@Column(name="status")
	private String estado;

	public EstadoAmistadEntity() {
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