package es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.primary.dto;

import java.math.BigInteger;


public class EventoCalendarioDTO {

	private int id;
	private BigInteger fechaCreacion;
	private String descripcion;
	private String nombre;
	private BigInteger fechaEvento;
	private ActividadDTO activity;
	private UsuarioDTO user;

	public EventoCalendarioDTO() {
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

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public BigInteger getFechaEvento() {
		return fechaEvento;
	}

	public void setFechaEvento(BigInteger fechaEvento) {
		this.fechaEvento = fechaEvento;
	}

	public ActividadDTO getActivity() {
		return activity;
	}

	public void setActivity(ActividadDTO activity) {
		this.activity = activity;
	}

	public UsuarioDTO getUser() {
		return user;
	}

	public void setUser(UsuarioDTO user) {
		this.user = user;
	}
}
