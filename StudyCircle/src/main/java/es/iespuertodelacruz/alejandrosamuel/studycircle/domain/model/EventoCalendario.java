package es.iespuertodelacruz.alejandrosamuel.studycircle.domain.model;

import java.math.BigInteger;
import java.sql.Timestamp;


public class EventoCalendario {

	private int id;
	private BigInteger fechaCreacion;
	private String descripcion;
	private String nombre;
	private BigInteger fechaEvento;
	private Actividad activity;
	private Usuario user;

	public EventoCalendario() {
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

	public Actividad getActivity() {
		return activity;
	}

	public void setActivity(Actividad activity) {
		this.activity = activity;
	}

	public Usuario getUser() {
		return user;
	}

	public void setUser(Usuario user) {
		this.user = user;
	}
}
