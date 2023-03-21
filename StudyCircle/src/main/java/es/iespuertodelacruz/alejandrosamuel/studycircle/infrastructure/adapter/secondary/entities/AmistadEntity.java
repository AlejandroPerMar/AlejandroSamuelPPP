package es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.entities;

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

	@Column(name="id_user1")
	private int idUser1;

	@Column(name="id_user2")
	private int idUser2;

	@Column(name="status")
	private String estado;

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

	public int getIdUser1() {
		return this.idUser1;
	}

	public void setIdUser1(int idUser1) {
		this.idUser1 = idUser1;
	}

	public int getIdUser2() {
		return this.idUser2;
	}

	public void setIdUser2(int idUser2) {
		this.idUser2 = idUser2;
	}

	public String getEstado() {
		return this.estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

}