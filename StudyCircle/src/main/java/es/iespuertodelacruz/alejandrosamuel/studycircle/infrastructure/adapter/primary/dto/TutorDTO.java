package es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.primary.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.math.BigInteger;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class TutorDTO {

	private int id;
	private BigInteger fechaCreacion;
	private List<MateriaTutorDTO> materiasTutor;
	private UsuarioDTO usuario;

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

	public List<MateriaTutorDTO> getMateriasTutor() {
		return materiasTutor;
	}

	public void setMateriasTutor(List<MateriaTutorDTO> materiasTutor) {
		this.materiasTutor = materiasTutor;
	}

	public UsuarioDTO getUsuario() {
		return usuario;
	}

	public void setUsuario(UsuarioDTO usuario) {
		this.usuario = usuario;
	}
}
