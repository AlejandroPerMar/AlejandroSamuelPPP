package es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.primary.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.math.BigInteger;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class EventoCalendarioDTO {

	private Integer id;
	private BigInteger fechaCreacion;
	private String descripcion;
	private String nombre;
	private BigInteger fechaEvento;
	private ActividadDTO actividad;
	private String perfilUsuario;
	private UsuarioDTO usuario;

	public EventoCalendarioDTO() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
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

	public ActividadDTO getActividad() {
		return actividad;
	}

	public void setActividad(ActividadDTO actividad) {
		this.actividad = actividad;
	}

	public UsuarioDTO getUsuario() {
		return usuario;
	}

	public void setUsuario(UsuarioDTO usuario) {
		this.usuario = usuario;
	}

	public String getPerfilUsuario() {
		return perfilUsuario;
	}

	public void setPerfilUsuario(String perfilUsuario) {
		this.perfilUsuario = perfilUsuario;
	}
}
