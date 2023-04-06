package es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.primary.dto;

import java.math.BigInteger;
import java.util.List;


public class TutorDTO {

	private int id;
	private BigInteger fechaCreacion;
	private List<MateriaTutorDTO> tutorSubjects;
	private UsuarioDTO user;

	public TutorDTO() {
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

	public List<MateriaTutorDTO> getTutorSubjects() {
		return tutorSubjects;
	}

	public void setTutorSubjects(List<MateriaTutorDTO> tutorSubjects) {
		this.tutorSubjects = tutorSubjects;
	}

	public UsuarioDTO getUser() {
		return user;
	}

	public void setUser(UsuarioDTO user) {
		this.user = user;
	}
}
