package es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.primary.dto;

import java.math.BigInteger;
import java.util.List;

public class AlumnoDTO {

	private int id;
	private BigInteger fechaCreacion;
	private NivelEstudiosDTO nivelEstudios;
	private List<MateriaDTO> materias;
	private UsuarioDTO usuario;

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

	public NivelEstudiosDTO getNivelEstudios() {
		return nivelEstudios;
	}

	public void setNivelEstudios(NivelEstudiosDTO nivelEstudios) {
		this.nivelEstudios = nivelEstudios;
	}

	public List<MateriaDTO> getMaterias() {
		return materias;
	}

	public void setMaterias(List<MateriaDTO> materias) {
		this.materias = materias;
	}

	public UsuarioDTO getUsuario() {
		return usuario;
	}

	public void setUsuario(UsuarioDTO usuario) {
		this.usuario = usuario;
	}
}
