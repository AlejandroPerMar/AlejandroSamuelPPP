package es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigInteger;
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

	@Column(name="email_verification_sent_at")
	private BigInteger fechaEnvioCorreoVerificacion;

	@Column(name="email_verification_token")
	private String tokenVerificacion;

	@Column(name="email_verified_at")
	private BigInteger fechaVerificacion;

	@Column(name="full_name")
	private String nombre;

	private String hashpswd;

	private String username;

	//bi-directional many-to-one association to AlertaEntity
	@OneToMany(mappedBy="user")
	private List<AlertaEntity> alerts;

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

	//bi-directional one-to-one association to AlumnoEntity
	@OneToOne
	@JoinColumn(name="id")
	private AlumnoEntity student;

	//bi-directional one-to-one association to TutorEntity
	@OneToOne
	@JoinColumn(name="id")
	private TutorEntity tutor;

	//uni-directional many-to-one association to EstadoUsuarioEntity
	@ManyToOne
	@JoinColumn(name="id_status")
	private EstadoUsuarioEntity userStatus;

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

	public BigInteger getFechaEnvioCorreoVerificacion() {
		return this.fechaEnvioCorreoVerificacion;
	}

	public void setFechaEnvioCorreoVerificacion(BigInteger fechaEnvioCorreoVerificacion) {
		this.fechaEnvioCorreoVerificacion = fechaEnvioCorreoVerificacion;
	}

	public String getTokenVerificacion() {
		return this.tokenVerificacion;
	}

	public void setTokenVerificacion(String tokenVerificacion) {
		this.tokenVerificacion = tokenVerificacion;
	}

	public BigInteger getFechaVerificacion() {
		return this.fechaVerificacion;
	}

	public void setFechaVerificacion(BigInteger fechaVerificacion) {
		this.fechaVerificacion = fechaVerificacion;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getHashpswd() {
		return this.hashpswd;
	}

	public void setHashpswd(String hashpswd) {
		this.hashpswd = hashpswd;
	}

	public String getUsername() {
		return this.username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public List<AlertaEntity> getAlerts() {
		return this.alerts;
	}

	public void setAlerts(List<AlertaEntity> alerts) {
		this.alerts = alerts;
	}

	public AlertaEntity addAlert(AlertaEntity alert) {
		getAlerts().add(alert);
		alert.setUser(this);

		return alert;
	}

	public AlertaEntity removeAlert(AlertaEntity alert) {
		getAlerts().remove(alert);
		alert.setUser(null);

		return alert;
	}

	public List<RolEntity> getRoles() {
		return this.roles;
	}

	public void setRoles(List<RolEntity> roles) {
		this.roles = roles;
	}

	public AlumnoEntity getStudent() {
		return this.student;
	}

	public void setStudent(AlumnoEntity student) {
		this.student = student;
	}

	public TutorEntity getTutor() {
		return this.tutor;
	}

	public void setTutor(TutorEntity tutor) {
		this.tutor = tutor;
	}

	public EstadoUsuarioEntity getUserStatus() {
		return this.userStatus;
	}

	public void setUserStatus(EstadoUsuarioEntity userStatus) {
		this.userStatus = userStatus;
	}

}