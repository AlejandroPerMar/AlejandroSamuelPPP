package es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.math.BigInteger;


/**
 * The persistent class for the friendships database table.
 * 
 */
@Entity
@Table(name="friendships")
@NamedQuery(name="Amistad.findAll", query="SELECT a FROM Amistad a")
public class Amistad implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	@Column(name="created_at")
	private BigInteger fechaCreacion;

	@Column(name="friends_from")
	private Timestamp friendsFrom;

	@Column(name="id_status")
	private int idStatus;

	//bi-directional many-to-one association to Usuario
	@ManyToOne
	@JoinColumn(name="id_user1")
	private Usuario user1;

	//bi-directional many-to-one association to Usuario
	@ManyToOne
	@JoinColumn(name="id_user2")
	private Usuario user2;

	public Amistad() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public BigInteger getFechaCreacion() {
		return this.fechaCreacion;
	}

	public void setFechaCreacion(BigInteger fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public Timestamp getFriendsFrom() {
		return this.friendsFrom;
	}

	public void setFriendsFrom(Timestamp friendsFrom) {
		this.friendsFrom = friendsFrom;
	}

	public int getIdStatus() {
		return this.idStatus;
	}

	public void setIdStatus(int idStatus) {
		this.idStatus = idStatus;
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