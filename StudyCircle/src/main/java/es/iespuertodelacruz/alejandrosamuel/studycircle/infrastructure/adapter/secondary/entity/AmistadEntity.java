package es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;


/**
 * The persistent class for the friendships database table.
 * 
 */
@Entity
@Table(name="friendships")
@NamedQuery(name="AmistadEntity.findAll", query="SELECT a FROM AmistadEntity a")
public class AmistadEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@Column(name="created_at")
	private Timestamp fechaCreacion;

	@Column(name="friends_from")
	private Timestamp fechaAmistad;

	@Column(name="status")
	private String estado;

	//uni-directional many-to-one association to UsuarioEntity
	@ManyToOne
	@JoinColumn(name="id_user1")
	private UsuarioEntity user1;

	//uni-directional many-to-one association to UsuarioEntity
	@ManyToOne
	@JoinColumn(name="id_user2")
	private UsuarioEntity user2;

	public AmistadEntity() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Timestamp getFechaCreacion() {
		return this.fechaCreacion;
	}

	public void setFechaCreacion(Timestamp fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public Timestamp getFechaAmistad() {
		return this.fechaAmistad;
	}

	public void setFechaAmistad(Timestamp fechaAmistad) {
		this.fechaAmistad = fechaAmistad;
	}

	public String getEstado() {
		return this.estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
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