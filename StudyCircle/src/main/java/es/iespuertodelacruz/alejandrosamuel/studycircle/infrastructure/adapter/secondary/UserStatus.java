package es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the user_statuses database table.
 * 
 */
@Entity
@Table(name="user_statuses")
@NamedQuery(name="UserStatus.findAll", query="SELECT u FROM UserStatus u")
public class UserStatus implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private String status;

	//bi-directional many-to-one association to Usuario
	@OneToMany(mappedBy="userStatus")
	private List<Usuario> users;

	public UserStatus() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getStatus() {
		return this.status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<Usuario> getUsers() {
		return this.users;
	}

	public void setUsers(List<Usuario> users) {
		this.users = users;
	}

	public Usuario addUser(Usuario user) {
		getUsers().add(user);
		user.setUserStatus(this);

		return user;
	}

	public Usuario removeUser(Usuario user) {
		getUsers().remove(user);
		user.setUserStatus(null);

		return user;
	}

}