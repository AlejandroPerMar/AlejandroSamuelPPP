package es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.List;


/**
 * The persistent class for the users database table.
 * 
 */
@Entity
@Table(name="users")
@NamedQuery(name="UsuarioEntity.findAll", query="SELECT u FROM UsuarioEntity u")
public class UsuarioEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@Column(name="created_at")
	private BigInteger fechaCreacion;

	private String email;

	@Column(name="full_name")
	private String nombreCompleto;

	private String hashpswd;

	@Column(name="status")
	private String estado;

	private String username;

	//uni-directional many-to-many association to RolEntity
	@ManyToMany
	@JoinTable(
		name="role_user"
		, joinColumns={
			@JoinColumn(name="id_user")
			}
		, inverseJoinColumns={
			@JoinColumn(name="id_role")
			}
		)
	private List<RolEntity> roles;

	public UsuarioEntity() {
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

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNombreCompleto() {
		return this.nombreCompleto;
	}

	public void setNombreCompleto(String nombreCompleto) {
		this.nombreCompleto = nombreCompleto;
	}

	public String getHashpswd() {
		return this.hashpswd;
	}

	public void setHashpswd(String hashpswd) {
		this.hashpswd = hashpswd;
	}

	public String getEstado() {
		return this.estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public List<RolEntity> getRoles() {
		return this.roles;
	}

	public void setRoles(List<RolEntity> roles) {
		this.roles = roles;
	}

}