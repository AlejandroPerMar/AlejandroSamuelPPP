package es.iespuertodelacruz.alejandrosamuel.studycircle.domain.model;

import java.math.BigInteger;

public class MateriaAlumno {

	private int id;

	private BigInteger fechaCreacion;

	private String estado;

	private Alumno alumno;

	private MateriaTutor materiaTutor;

	public MateriaAlumno() {
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

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Alumno getAlumno() {
		return alumno;
	}

	public void setAlumno(Alumno alumno) {
		this.alumno = alumno;
	}

	public MateriaTutor getMateriaTutor() {
		return materiaTutor;
	}

	public void setMateriaTutor(MateriaTutor materiaTutor) {
		this.materiaTutor = materiaTutor;
	}
}
