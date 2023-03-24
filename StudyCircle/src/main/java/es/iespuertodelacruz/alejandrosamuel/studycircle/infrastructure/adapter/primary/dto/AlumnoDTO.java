package es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.primary.dto;

import java.math.BigInteger;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class AlumnoDTO {
	private int id;

	private BigInteger fechaCreacion;

	private List<ActividadDTO> actividades;

	private List<AnuncioDTO> anuncios;

	private List<EventoCalendarioDTO> eventosCalendario;

	private NivelEstudiosDTO nivelEstudios;

	private List<MateriaAlumnoDTO> materiasAlumno;

	private List<MateriaDTO> materias;

	private UsuarioDTO usuario;
	
	public AlumnoDTO() {}

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

	public List<ActividadDTO> getActividades() {
		return actividades;
	}

	public void setActividades(List<ActividadDTO> actividades) {
		this.actividades = actividades;
	}

	public List<AnuncioDTO> getAnuncios() {
		return anuncios;
	}

	public void setAnuncios(List<AnuncioDTO> anuncios) {
		this.anuncios = anuncios;
	}

	public List<EventoCalendarioDTO> getEventosCalendario() {
		return eventosCalendario;
	}

	public void setEventosCalendario(List<EventoCalendarioDTO> eventosCalendario) {
		this.eventosCalendario = eventosCalendario;
	}

	public NivelEstudiosDTO getNivelEstudios() {
		return nivelEstudios;
	}

	public void setNivelEstudios(NivelEstudiosDTO nivelEstudios) {
		this.nivelEstudios = nivelEstudios;
	}

	public List<MateriaAlumnoDTO> getMateriasAlumno() {
		return materiasAlumno;
	}

	public void setMateriasAlumno(List<MateriaAlumnoDTO> materiasAlumno) {
		this.materiasAlumno = materiasAlumno;
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
