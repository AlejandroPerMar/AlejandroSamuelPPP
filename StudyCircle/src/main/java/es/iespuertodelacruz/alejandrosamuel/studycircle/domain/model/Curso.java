package es.iespuertodelacruz.alejandrosamuel.studycircle.domain.model;


import java.math.BigInteger;
import java.util.List;

public class Curso {
	
	private int id;
	private String titulo;
	private BigInteger fechaCreacion;
	private MateriaTutor materiaTutor;
	private List<Actividad> actividades;
	private List<Alumno> alumnos;

	public Curso() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public MateriaTutor getMateriaTutor() {
		return materiaTutor;
	}

	public void setMateriaTutor(MateriaTutor materiaTutor) {
		this.materiaTutor = materiaTutor;
	}

	public List<Actividad> getActividades() {
		return actividades;
	}

	public void setActividades(List<Actividad> actividades) {
		this.actividades = actividades;
	}

	public List<Alumno> getAlumnos() {
		return alumnos;
	}

	public void setAlumnos(List<Alumno> alumnos) {
		this.alumnos = alumnos;
	}
}
