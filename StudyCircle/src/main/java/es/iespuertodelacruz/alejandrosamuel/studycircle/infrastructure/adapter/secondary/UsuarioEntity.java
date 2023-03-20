package es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Timestamp;
import java.math.BigInteger;
import java.util.List;


/**
 * The persistent class for the users database table.
 * 
 */
@Entity
@Table(name="users")
@NamedQuery(name="Usuario.findAll", query="SELECT u FROM UsuarioEntity u")
public class UsuarioEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@Column(name="created_at")
	private BigInteger fechaCreacion;

	private String email;

	@Column(name="email_verification_sent_at")
	private BigInteger fechaEnvioMailVerificacion;

	@Column(name="email_verification_token")
	private String tokenVerificacionMail;

	@Column(name="email_verified_at")
	private Timestamp fechaEmailVerificado;

	@Column(name="full_name")
	private String nombre;

	private String hashpswd;

	@Column(name="username")
	private String nickname;

	//bi-directional many-to-one association to Alerta
	@OneToMany(mappedBy="user")
	private List<Alerta> alerts;

	//bi-directional many-to-one association to MensajeChat
	@OneToMany(mappedBy="user1")
	private List<MensajeChat> chatMessages1;

	//bi-directional many-to-one association to MensajeChat
	@OneToMany(mappedBy="user2")
	private List<MensajeChat> chatMessages2;

	//bi-directional many-to-one association to Amistad
	@OneToMany(mappedBy="user1")
	private List<Amistad> friendships1;

	//bi-directional many-to-one association to Amistad
	@OneToMany(mappedBy="user2")
	private List<Amistad> friendships2;

	//bi-directional many-to-one association to UserStatus
	@ManyToOne
	@JoinColumn(name="id_status")
	private UserStatus userStatus;

	//bi-directional one-to-one association to PerfilAlumno
	@OneToOne
	@JoinColumn(name="id")
	private PerfilAlumno student;

	//bi-directional one-to-one association to PerfilTutor
	@OneToOne
	@JoinColumn(name="id")
	private PerfilTutor tutor;

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

	public BigInteger getFechaEnvioMailVerificacion() {
		return this.fechaEnvioMailVerificacion;
	}

	public void setFechaEnvioMailVerificacion(BigInteger fechaEnvioMailVerificacion) {
		this.fechaEnvioMailVerificacion = fechaEnvioMailVerificacion;
	}

	public String getTokenVerificacionMail() {
		return this.tokenVerificacionMail;
	}

	public void setTokenVerificacionMail(String tokenVerificacionMail) {
		this.tokenVerificacionMail = tokenVerificacionMail;
	}

	public Timestamp getFechaEmailVerificado() {
		return this.fechaEmailVerificado;
	}

	public void setFechaEmailVerificado(Timestamp fechaEmailVerificado) {
		this.fechaEmailVerificado = fechaEmailVerificado;
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

	public String getNickname() {
		return this.nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public List<Alerta> getAlerts() {
		return this.alerts;
	}

	public void setAlerts(List<Alerta> alerts) {
		this.alerts = alerts;
	}

	public Alerta addAlert(Alerta alert) {
		getAlerts().add(alert);
		alert.setUser(this);

		return alert;
	}

	public Alerta removeAlert(Alerta alert) {
		getAlerts().remove(alert);
		alert.setUser(null);

		return alert;
	}

	public List<MensajeChat> getChatMessages1() {
		return this.chatMessages1;
	}

	public void setChatMessages1(List<MensajeChat> chatMessages1) {
		this.chatMessages1 = chatMessages1;
	}

	public MensajeChat addChatMessages1(MensajeChat chatMessages1) {
		getChatMessages1().add(chatMessages1);
		chatMessages1.setUser1(this);

		return chatMessages1;
	}

	public MensajeChat removeChatMessages1(MensajeChat chatMessages1) {
		getChatMessages1().remove(chatMessages1);
		chatMessages1.setUser1(null);

		return chatMessages1;
	}

	public List<MensajeChat> getChatMessages2() {
		return this.chatMessages2;
	}

	public void setChatMessages2(List<MensajeChat> chatMessages2) {
		this.chatMessages2 = chatMessages2;
	}

	public MensajeChat addChatMessages2(MensajeChat chatMessages2) {
		getChatMessages2().add(chatMessages2);
		chatMessages2.setUser2(this);

		return chatMessages2;
	}

	public MensajeChat removeChatMessages2(MensajeChat chatMessages2) {
		getChatMessages2().remove(chatMessages2);
		chatMessages2.setUser2(null);

		return chatMessages2;
	}

	public List<Amistad> getFriendships1() {
		return this.friendships1;
	}

	public void setFriendships1(List<Amistad> friendships1) {
		this.friendships1 = friendships1;
	}

	public Amistad addFriendships1(Amistad friendships1) {
		getFriendships1().add(friendships1);
		friendships1.setUser1(this);

		return friendships1;
	}

	public Amistad removeFriendships1(Amistad friendships1) {
		getFriendships1().remove(friendships1);
		friendships1.setUser1(null);

		return friendships1;
	}

	public List<Amistad> getFriendships2() {
		return this.friendships2;
	}

	public void setFriendships2(List<Amistad> friendships2) {
		this.friendships2 = friendships2;
	}

	public Amistad addFriendships2(Amistad friendships2) {
		getFriendships2().add(friendships2);
		friendships2.setUser2(this);

		return friendships2;
	}

	public Amistad removeFriendships2(Amistad friendships2) {
		getFriendships2().remove(friendships2);
		friendships2.setUser2(null);

		return friendships2;
	}

	public UserStatus getUserStatus() {
		return this.userStatus;
	}

	public void setUserStatus(UserStatus userStatus) {
		this.userStatus = userStatus;
	}

	public PerfilAlumno getStudent() {
		return this.student;
	}

	public void setStudent(PerfilAlumno student) {
		this.student = student;
	}

	public PerfilTutor getTutor() {
		return this.tutor;
	}

	public void setTutor(PerfilTutor tutor) {
		this.tutor = tutor;
	}

}