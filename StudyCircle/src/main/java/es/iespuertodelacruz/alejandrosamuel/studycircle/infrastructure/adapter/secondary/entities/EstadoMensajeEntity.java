package es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the chat_messages_statuses database table.
 * 
 */
@Entity
@Table(name="chat_messages_statuses")
@NamedQuery(name="EstadoMensajeEntity.findAll", query="SELECT e FROM EstadoMensajeEntity e")
public class EstadoMensajeEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@Column(name="status")
	private String estado;

	public EstadoMensajeEntity() {
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