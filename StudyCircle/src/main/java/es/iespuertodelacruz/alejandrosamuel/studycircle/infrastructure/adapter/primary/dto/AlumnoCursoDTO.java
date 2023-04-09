package es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.primary.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class AlumnoCursoDTO {
	
	private int id;
	private CursoDTO curso;
	private AlumnoDTO alumno;

	public AlumnoCursoDTO() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public CursoDTO getCurso() {
		return curso;
	}

	public void setCurso(CursoDTO curso) {
		this.curso = curso;
	}

	public AlumnoDTO getAlumno() {
		return alumno;
	}

	public void setAlumno(AlumnoDTO alumno) {
		this.alumno = alumno;
	}
}
