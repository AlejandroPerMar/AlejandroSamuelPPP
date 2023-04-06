package es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigInteger;
import java.sql.Timestamp;


/**
 * The persistent class for the confirmation_token database table.
 * 
 */
@Entity
@Table(name="confirmation_token")
@NamedQuery(name="TokenConfirmacionEntity.findAll", query="SELECT t FROM TokenConfirmacionEntity t")
public class TokenConfirmacionEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@Column(name="created_at")
	private BigInteger fechaCreacion;

	@Column(name="expires_at")
	private BigInteger fechaExpiracion;

	@Column(name="confirmed_at")
	private BigInteger fechaConfirmacion;

	private String token;

	//uni-directional many-to-one association to UsuarioEntity
	@ManyToOne
	@JoinColumn(name="id_user")
	private UsuarioEntity user;

	public TokenConfirmacionEntity() {
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
	public BigInteger getFechaExpiracion() {
		return fechaExpiracion;
	}

	public void setFechaExpiracion(BigInteger fechaExpiracion) {
		this.fechaExpiracion = fechaExpiracion;
	}

	public BigInteger getFechaConfirmacion() {
		return fechaConfirmacion;
	}

	public void setFechaConfirmacion(BigInteger fechaConfirmacion) {
		this.fechaConfirmacion = fechaConfirmacion;
	}

	public String getToken() {
		return this.token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public UsuarioEntity getUser() {
		return this.user;
	}

	public void setUser(UsuarioEntity user) {
		this.user = user;
	}


}