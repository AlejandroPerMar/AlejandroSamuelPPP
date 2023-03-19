package es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigInteger;


/**
 * The persistent class for the announcements database table.
 * 
 */
@Entity
@Table(name="announcements")
@NamedQuery(name="Anuncio.findAll", query="SELECT a FROM Anuncio a")
public class Anuncio implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@Column(name="created_at")
	private BigInteger fechaCreacion;

	@Column(name="description")
	private String descripcion;

	@Column(name="id_reason")
	private int idReason;

	@Column(name="id_status")
	private int idStatus;

	@Column(name="title")
	private String titulo;

	//bi-directional many-to-one association to PerfilTutor
	@ManyToOne
	@JoinColumn(name="id_tutor")
	private PerfilTutor tutor;

	//bi-directional many-to-one association to PerfilAlumno
	@ManyToOne
	@JoinColumn(name="id_student")
	private PerfilAlumno student;

	//bi-directional many-to-one association to Materia
	@ManyToOne
	@JoinColumn(name="id_subject")
	private Materia subject;

	public Anuncio() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public BigInteger getFechaCreacion() {
		return this.fechaCreacion;
	}

	public void setFechaCreacion(BigInteger fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public int getIdReason() {
		return this.idReason;
	}

	public void setIdReason(int idReason) {
		this.idReason = idReason;
	}

	public int getIdStatus() {
		return this.idStatus;
	}

	public void setIdStatus(int idStatus) {
		this.idStatus = idStatus;
	}

	public String getTitulo() {
		return this.titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public PerfilTutor getTutor() {
		return this.tutor;
	}

	public void setTutor(PerfilTutor tutor) {
		this.tutor = tutor;
	}

	public PerfilAlumno getStudent() {
		return this.student;
	}

	public void setStudent(PerfilAlumno student) {
		this.student = student;
	}

	public Materia getSubject() {
		return this.subject;
	}

	public void setSubject(Materia subject) {
		this.subject = subject;
	}

}