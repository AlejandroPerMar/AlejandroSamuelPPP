package es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigInteger;


/**
 * The persistent class for the alerts database table.
 * 
 */
@Entity
@Table(name="friendship_alerts")
@NamedQuery(name="AlertaAmistadEntity.findAll", query="SELECT a FROM AlertaAmistadEntity a")
public class AlertaAmistadEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;

	@Column(name="created_at")
	private BigInteger fechaCreacion;

	@Column(name="status")
	private String estado;

	//uni-directional many-to-one association to AmistadEntity
	@ManyToOne
	@JoinColumn(name="id_friendship")
	private AmistadEntity amistad;

	//uni-directional many-to-one association to UsuarioEntity
	@ManyToOne
	@JoinColumn(name="id_user")
	private UsuarioEntity usuario;

	public AlertaAmistadEntity() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public BigInteger getFechaCreacion() {
		return this.fechaCreacion;
	}

	public void setFechaCreacion(BigInteger fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public String getEstado() {
		return this.estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public UsuarioEntity getUsuario() {
		return this.usuario;
	}

	public void setUsuario(UsuarioEntity usuario) {
		this.usuario = usuario;
	}

	public AmistadEntity getAmistad() {
		return amistad;
	}

	public void setAmistad(AmistadEntity amistad) {
		this.amistad = amistad;
	}
}