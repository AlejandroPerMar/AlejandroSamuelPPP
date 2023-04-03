package es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigInteger;
import java.util.Date;
import java.util.UUID;


/**
 * The persistent class for the confirmation_token database table.
 * 
 */
@Entity
@Table(name="confirmation_token")
@NamedQuery(name="ConfirmacionToken.findAll", query="SELECT c FROM ConfirmacionToken c")
public class ConfirmacionToken implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@Column(name="created_at")
	private BigInteger fechaCreacion;

	@OneToOne(targetEntity = UsuarioEntity.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "id_user")
    private UsuarioEntity user;

	private String token;

	public ConfirmacionToken() {
	}
	
	public ConfirmacionToken(UsuarioEntity user) {
        this.user = user;
        fechaCreacion = new BigInteger(new Date().getTime() + "");
        token = UUID.randomUUID().toString();
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

	public UsuarioEntity getUser() {
		return user;
	}

	public void setUser(UsuarioEntity user) {
		this.user = user;
	}

	public String getToken() {
		return this.token;
	}

	public void setToken(String token) {
		this.token = token;
	}

}