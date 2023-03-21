package es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.entities;

import java.io.Serializable;
import javax.persistence.*;
import java.math.BigInteger;


/**
 * The persistent class for the announcements database table.
 * 
 */
@Entity
@Table(name="announcements")
@NamedQuery(name="AnuncioEntity.findAll", query="SELECT a FROM AnuncioEntity a")
public class AnuncioEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@Column(name="created_at")
	private BigInteger fechaCreacion;

	@Column(name="description")
	private String descripcion;

	@Column(name="title")
	private String titulo;

	//uni-directional many-to-one association to MotivoAnuncioEntity
	@ManyToOne
	@JoinColumn(name="id_reason")
	private MotivoAnuncioEntity announcementReason;

	//uni-directional many-to-one association to EstadoAnuncioEntity
	@ManyToOne
	@JoinColumn(name="id_status")
	private EstadoAnuncioEntity announcementStatus;

	//bi-directional many-to-one association to AlumnoEntity
	@ManyToOne
	@JoinColumn(name="id_student")
	private AlumnoEntity student;

	//uni-directional many-to-one association to MateriaEntity
	@ManyToOne
	@JoinColumn(name="id_subject")
	private MateriaEntity subject;

	//bi-directional many-to-one association to TutorEntity
	@ManyToOne
	@JoinColumn(name="id_tutor")
	private TutorEntity tutor;

	public AnuncioEntity() {
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

	public String getTitulo() {
		return this.titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public MotivoAnuncioEntity getAnnouncementReason() {
		return this.announcementReason;
	}

	public void setAnnouncementReason(MotivoAnuncioEntity announcementReason) {
		this.announcementReason = announcementReason;
	}

	public EstadoAnuncioEntity getAnnouncementStatus() {
		return this.announcementStatus;
	}

	public void setAnnouncementStatus(EstadoAnuncioEntity announcementStatus) {
		this.announcementStatus = announcementStatus;
	}

	public AlumnoEntity getStudent() {
		return this.student;
	}

	public void setStudent(AlumnoEntity student) {
		this.student = student;
	}

	public MateriaEntity getSubject() {
		return this.subject;
	}

	public void setSubject(MateriaEntity subject) {
		this.subject = subject;
	}

	public TutorEntity getTutor() {
		return this.tutor;
	}

	public void setTutor(TutorEntity tutor) {
		this.tutor = tutor;
	}

}