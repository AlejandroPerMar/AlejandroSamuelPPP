package es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigInteger;


/**
 * The persistent class for the alerts database table.
 * 
 */
@Entity
@Table(name="alerts")
@NamedQuery(name="AlertaEntity.findAll", query="SELECT a FROM AlertaEntity a")
public class AlertaEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@Column(name="created_at")
	private BigInteger fechaCreacion;

	@Column(name="message")
	private String mensaje;

	//uni-directional many-to-one association to EstadoAlertaEntity
	@ManyToOne
	@JoinColumn(name="id_status")
	private EstadoAlertaEntity alertStatus;

	//uni-directional many-to-one association to TipoAlertaEntity
	@ManyToOne
	@JoinColumn(name="id_type")
	private TipoAlertaEntity alertType;

	//bi-directional many-to-one association to UsuarioEntity
	@ManyToOne
	@JoinColumn(name="id_user")
	private UsuarioEntity user;

	public AlertaEntity() {
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

	public String getMensaje() {
		return this.mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	public EstadoAlertaEntity getAlertStatus() {
		return this.alertStatus;
	}

	public void setAlertStatus(EstadoAlertaEntity alertStatus) {
		this.alertStatus = alertStatus;
	}

	public TipoAlertaEntity getAlertType() {
		return this.alertType;
	}

	public void setAlertType(TipoAlertaEntity alertType) {
		this.alertType = alertType;
	}

	public UsuarioEntity getUser() {
		return this.user;
	}

	public void setUser(UsuarioEntity user) {
		this.user = user;
	}

}