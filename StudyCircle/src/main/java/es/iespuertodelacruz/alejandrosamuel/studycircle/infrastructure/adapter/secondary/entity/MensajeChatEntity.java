package es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.entity;

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

	@Column(name="message")
	private String mensaje;

	@Column(name="send_at")
	private BigInteger fechaEnvio;

	private String status;

	//uni-directional many-to-one association to UsuarioEntity
	@ManyToOne
	@JoinColumn(name="id_user_sender")
	private UsuarioEntity user1;

	//uni-directional many-to-one association to UsuarioEntity
	@ManyToOne
	@JoinColumn(name="id_user_receptor")
	private UsuarioEntity user2;

	public MensajeChatEntity() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
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

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public UsuarioEntity getUser1() {
		return this.user1;
	}

	public void setUser1(UsuarioEntity user1) {
		this.user1 = user1;
	}

	public UsuarioEntity getUser2() {
		return this.user2;
	}

	public void setUser2(UsuarioEntity user2) {
		this.user2 = user2;
	}

}