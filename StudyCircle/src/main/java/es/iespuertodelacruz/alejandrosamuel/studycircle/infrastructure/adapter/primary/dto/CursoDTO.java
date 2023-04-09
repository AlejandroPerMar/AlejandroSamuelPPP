package es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.primary.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.math.BigInteger;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class CursoDTO {
	
	private int id;
	private Double precioHora;
	private String estado;
	private String titulo;
	private BigInteger fechaCreacion;
	private MateriaTutorDTO materiaTutor;
	private List<ActividadCursoDTO> actividadesCurso;
	private List<AlumnoCursoDTO> alumnosCurso;

	public CursoDTO() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Double getPrecioHora() {
		return precioHora;
	}

	public void setPrecioHora(Double precioHora) {
		this.precioHora = precioHora;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public BigInteger getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(BigInteger fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public MateriaTutorDTO getMateriaTutor() {
		return materiaTutor;
	}

	public void setMateriaTutor(MateriaTutorDTO materiaTutor) {
		this.materiaTutor = materiaTutor;
	}

	public List<ActividadCursoDTO> getActividadesCurso() {
		return actividadesCurso;
	}

	public void setActividadesCurso(List<ActividadCursoDTO> actividadesCurso) {
		this.actividadesCurso = actividadesCurso;
	}

	public List<AlumnoCursoDTO> getAlumnosCurso() {
		return alumnosCurso;
	}

	public void setAlumnosCurso(List<AlumnoCursoDTO> alumnosCurso) {
		this.alumnosCurso = alumnosCurso;
	}
}
