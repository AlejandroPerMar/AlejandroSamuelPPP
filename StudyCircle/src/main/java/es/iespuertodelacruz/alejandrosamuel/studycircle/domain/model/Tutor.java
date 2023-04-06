package es.iespuertodelacruz.alejandrosamuel.studycircle.domain.model;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.List;


public class Tutor {

	private int id;
	private BigInteger fechaCreacion;
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

	public BigInteger getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(BigInteger fechaCreacion) {
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
