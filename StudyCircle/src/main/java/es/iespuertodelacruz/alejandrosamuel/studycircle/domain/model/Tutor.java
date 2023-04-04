package es.iespuertodelacruz.alejandrosamuel.studycircle.domain.model;

import java.sql.Timestamp;
import java.util.List;


public class Tutor {

	private int id;
	private Timestamp fechaCreacion;
	private List<MateriaTutor> tutorSubjects;
	private Usuario user;

	public Tutor() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Timestamp getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Timestamp fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public List<MateriaTutor> getTutorSubjects() {
		return tutorSubjects;
	}

	public void setTutorSubjects(List<MateriaTutor> tutorSubjects) {
		this.tutorSubjects = tutorSubjects;
	}

	public Usuario getUser() {
		return user;
	}

	public void setUser(Usuario user) {
		this.user = user;
	}
}
