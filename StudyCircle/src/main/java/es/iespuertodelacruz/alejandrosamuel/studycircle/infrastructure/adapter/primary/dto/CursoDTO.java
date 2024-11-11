package es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.primary.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.math.BigInteger;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class CursoDTO {
	
	private Integer id;
	private String estado;
	private String titulo;
	private BigInteger fechaCreacion;
	private MateriaTutorDTO materiaTutor;
	private List<ActividadDTO> actividades;
	private List<AlumnoDTO> alumnos;

	public CursoDTO() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public List<ActividadDTO> getActividades() {
		return actividades;
	}

	public void setActividades(List<ActividadDTO> actividades) {
		this.actividades = actividades;
	}

	public List<AlumnoDTO> getAlumnos() {
		return alumnos;
	}

	public void setAlumnos(List<AlumnoDTO> alumnos) {
		this.alumnos = alumnos;
	}
}
