package es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigInteger;


/**
 * The persistent class for the chat_messages database table.
 * 
 */
@Entity
@Table(name="chat_messages")
@NamedQuery(name="MensajeChatEntity.findAll", query="SELECT m FROM MensajeChatEntity m")
public class MensajeChatEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@Column(name="id_user_receptor")
	private int idUserReceptor;

	@Column(name="id_user_sender")
	private int idUserSender;

	@Column(name="message")
	private String mensaje;

	@Column(name="send_at")
	private BigInteger fechaEnvio;

	@Column(name="status")
	private String estado;

	public MensajeChatEntity() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIdUserReceptor() {
		return this.idUserReceptor;
	}

	public void setIdUserReceptor(int idUserReceptor) {
		this.idUserReceptor = idUserReceptor;
	}

	public int getIdUserSender() {
		return this.idUserSender;
	}

	public void setIdUserSender(int idUserSender) {
		this.idUserSender = idUserSender;
	}

	public String getMensaje() {
		return this.mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	public BigInteger getFechaEnvio() {
		return this.fechaEnvio;
	}

	public void setFechaEnvio(BigInteger fechaEnvio) {
		this.fechaEnvio = fechaEnvio;
	}

	public String getEstado() {
		return this.estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

}