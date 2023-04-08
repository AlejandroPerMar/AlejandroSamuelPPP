package es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigInteger;


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
	private BigInteger fechaCreacion;

	@Column(name="friends_from")
	private BigInteger fechaAmistad;

	@Column(name="status")
	private String estado;

	//uni-directional many-to-one association to UsuarioEntity
	@ManyToOne
	@JoinColumn(name="id_user1")
	private UsuarioEntity usuario1;

	//uni-directional many-to-one association to UsuarioEntity
	@ManyToOne
	@JoinColumn(name="id_user2")
	private UsuarioEntity usuario2;

	public AmistadEntity() {
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

	public BigInteger getFechaAmistad() {
		return this.fechaAmistad;
	}

	public void setFechaAmistad(BigInteger fechaAmistad) {
		this.fechaAmistad = fechaAmistad;
	}

	public String getEstado() {
		return this.estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public UsuarioEntity getUsuario1() {
		return this.usuario1;
	}

	public void setUsuario1(UsuarioEntity usuario1) {
		this.usuario1 = usuario1;
	}

	public UsuarioEntity getUsuario2() {
		return this.usuario2;
	}

	public void setUsuario2(UsuarioEntity usuario2) {
		this.usuario2 = usuario2;
	}

}