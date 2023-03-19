package es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigInteger;


/**
 * The persistent class for the chat_messages database table.
 * 
 */
@Entity
@Table(name="chat_messages")
@NamedQuery(name="MensajeChat.findAll", query="SELECT m FROM MensajeChat m")
public class MensajeChat implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@Column(name="id_status")
	private int idStatus;

	@Column(name="message")
	private String mensaje;

	@Column(name="send_at")
	private BigInteger fechaEnvio;

	//bi-directional many-to-one association to Usuario
	@ManyToOne
	@JoinColumn(name="id_user_sender")
	private Usuario user1;

	//bi-directional many-to-one association to Usuario
	@ManyToOne
	@JoinColumn(name="id_user_receptor")
	private Usuario user2;

	public MensajeChat() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getIdStatus() {
		return this.idStatus;
	}

	public void setIdStatus(int idStatus) {
		this.idStatus = idStatus;
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

	public Usuario getUser1() {
		return this.user1;
	}

	public void setUser1(Usuario user1) {
		this.user1 = user1;
	}

	public Usuario getUser2() {
		return this.user2;
	}

	public void setUser2(Usuario user2) {
		this.user2 = user2;
	}

}