package es.iespuertodelacruz.alejandrosamuel.studycircle.domain.model;

import java.math.BigInteger;
import java.util.List;

public class Alumno {

	private int id;

	private BigInteger fechaCreacion;

	private List<Actividad> actividades;

	private List<Anuncio> anuncios;

	private List<EventoCalendario> eventosCalendario;

	private NivelEstudios nivelEstudios;

	private List<MateriaAlumno> materiasAlumno;

	private Usuario usuario;
	
	public Alumno() {}

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

	public List<Actividad> getActividades() {
		return actividades;
	}

	public void setActividades(List<Actividad> actividades) {
		this.actividades = actividades;
	}

	public List<Anuncio> getAnuncios() {
		return anuncios;
	}

	public void setAnuncios(List<Anuncio> anuncios) {
		this.anuncios = anuncios;
	}

	public List<EventoCalendario> getEventosCalendario() {
		return eventosCalendario;
	}

	public void setEventosCalendario(List<EventoCalendario> eventosCalendario) {
		this.eventosCalendario = eventosCalendario;
	}

	public NivelEstudios getNivelEstudios() {
		return nivelEstudios;
	}

	public void setNivelEstudios(NivelEstudios nivelEstudios) {
		this.nivelEstudios = nivelEstudios;
	}

	public List<MateriaAlumno> getMateriasAlumno() {
		return materiasAlumno;
	}

	public void setMateriasAlumno(List<MateriaAlumno> materiasAlumno) {
		this.materiasAlumno = materiasAlumno;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
}
