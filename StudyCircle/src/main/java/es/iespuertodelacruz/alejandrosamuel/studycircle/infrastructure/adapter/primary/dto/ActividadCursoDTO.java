package es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.primary.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ActividadCursoDTO {
	
	private int id;

	private ActividadDTO actividad;

	private CursoDTO curso;

	public ActividadCursoDTO() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public ActividadDTO getActividad() {
		return actividad;
	}

	public void setActividad(ActividadDTO actividad) {
		this.actividad = actividad;
	}

	public CursoDTO getCurso() {
		return curso;
	}

	public void setCurso(CursoDTO curso) {
		this.curso = curso;
	}
}
