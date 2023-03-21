package es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the activity_statuses database table.
 * 
 */
@Entity
@Table(name="activity_statuses")
@NamedQuery(name="EstadoActividadEntity.findAll", query="SELECT e FROM EstadoActividadEntity e")
public class EstadoActividadEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@Column(name="status")
	private String estado;

	public EstadoActividadEntity() {
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