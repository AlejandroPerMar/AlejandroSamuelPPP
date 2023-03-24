package es.iespuertodelacruz.alejandrosamuel.studycircle.domain.model;

import java.math.BigInteger;
import java.util.List;

public class Tutor {

	private int id;

	private BigInteger fechaCreacion;

	private List<Actividad> actividades;

	private List<Anuncio> anuncios;

	private List<EventoCalendario> eventosCalendario;

	private List<Tarifa> tarifas;

	private List<MateriaTutor> materiasTutor;

	private Usuario usuario;

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

	public List<Tarifa> getTarifas() {
		return tarifas;
	}

	public void setTarifas(List<Tarifa> tarifas) {
		this.tarifas = tarifas;
	}

	public List<MateriaTutor> getMateriasTutor() {
		return materiasTutor;
	}

	public void setMateriasTutor(List<MateriaTutor> materiasTutor) {
		this.materiasTutor = materiasTutor;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
}
