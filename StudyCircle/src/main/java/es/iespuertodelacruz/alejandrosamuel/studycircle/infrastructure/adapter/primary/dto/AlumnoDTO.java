package es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.primary.dto;

import java.math.BigInteger;
import java.util.List;

public class AlumnoDTO {

	private int id;
	private BigInteger fechaCreacion;
	private NivelEstudiosDTO studyLevel;
	private List<MateriaDTO> subjects;
	private UsuarioDTO user;

	public AlumnoDTO() {
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

	public NivelEstudiosDTO getStudyLevel() {
		return studyLevel;
	}

	public void setStudyLevel(NivelEstudiosDTO studyLevel) {
		this.studyLevel = studyLevel;
	}

	public List<MateriaDTO> getSubjects() {
		return subjects;
	}

	public void setSubjects(List<MateriaDTO> subjects) {
		this.subjects = subjects;
	}

	public UsuarioDTO getUser() {
		return user;
	}

	public void setUser(UsuarioDTO user) {
		this.user = user;
	}
}
