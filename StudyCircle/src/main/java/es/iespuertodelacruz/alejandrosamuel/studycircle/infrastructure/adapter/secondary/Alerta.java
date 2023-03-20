package es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigInteger;


/**
 * The persistent class for the alerts database table.
 * 
 */
@Entity
@Table(name="alerts")
@NamedQuery(name="Alerta.findAll", query="SELECT a FROM Alerta a")
public class Alerta implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@Column(name="created_at")
	private BigInteger fechaCreacion;

	@Column(name="id_status")
	private int idStatus;

	@Column(name="message")
	private String mensaje;

	@Column(name="type")
	private String tipo;

	//bi-directional many-to-one association to Usuario
	@ManyToOne
	@JoinColumn(name="id_user")
	private UsuarioEntity user;

	public Alerta() {
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

	public String getTipo() {
		return this.tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public UsuarioEntity getUser() {
		return this.user;
	}

	public void setUser(UsuarioEntity user) {
		this.user = user;
	}

}