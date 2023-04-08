package es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.primary.dto;

import java.math.BigInteger;


public class MateriaDTO {

	private int id;
	private BigInteger fechaCreacion;
	private String nombre;
	private NivelEstudiosDTO nivelEstudios;

	public MateriaDTO() {
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

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public NivelEstudiosDTO getNivelEstudios() {
		return nivelEstudios;
	}

	public void setNivelEstudios(NivelEstudiosDTO nivelEstudios) {
		this.nivelEstudios = nivelEstudios;
	}
}
