package es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.entity;

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
	private Integer id;

	@Column(name="created_at")
	private BigInteger fechaCreacion;

	@Column(name="description")
	private String descripcion;

	@Column(name="reason")
	private String motivo;

	@Column(name="title")
	private String titulo;

	//uni-directional many-to-one association to MateriaEntity
	@ManyToOne
	@JoinColumn(name="id_subject")
	private MateriaEntity materia;

	//uni-directional many-to-one association to UsuarioEntity
	@ManyToOne
	@JoinColumn(name="id_user")
	private UsuarioEntity usuario;

	public AnuncioEntity() {
	}

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
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

	public String getMotivo() {
		return this.motivo;
	}

	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}

	public String getTitulo() {
		return this.titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public MateriaEntity getMateria() {
		return this.materia;
	}

	public void setMateria(MateriaEntity materia) {
		this.materia = materia;
	}

	public UsuarioEntity getUsuario() {
		return this.usuario;
	}

	public void setUsuario(UsuarioEntity usuario) {
		this.usuario = usuario;
	}

}