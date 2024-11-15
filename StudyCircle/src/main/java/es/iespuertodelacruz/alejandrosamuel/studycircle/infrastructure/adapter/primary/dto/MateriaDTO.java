package es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.primary.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.math.BigInteger;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class MateriaDTO {

	private Integer id;
	private BigInteger fechaCreacion;
	private String nombre;
	private NivelEstudiosDTO nivelEstudios;

	public MateriaDTO() {
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
