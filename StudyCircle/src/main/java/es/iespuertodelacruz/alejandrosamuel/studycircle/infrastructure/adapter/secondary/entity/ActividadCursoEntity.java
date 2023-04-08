package es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.entity;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the course_activities database table.
 * 
 */
@Entity
@Table(name="course_activities")
@NamedQuery(name="ActividadCursoEntity.findAll", query="SELECT a FROM ActividadCursoEntity a")
public class ActividadCursoEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	//uni-directional many-to-one association to ActividadEntity
	@ManyToOne
	@JoinColumn(name="id_activity")
	private ActividadEntity actividad;

	//bi-directional many-to-one association to CursoEntity
	@ManyToOne
	@JoinColumn(name="id_course")
	private CursoEntity curso;

	public ActividadCursoEntity() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public ActividadEntity getActividad() {
		return this.actividad;
	}

	public void setActividad(ActividadEntity actividad) {
		this.actividad = actividad;
	}

	public CursoEntity getCurso() {
		return this.curso;
	}

	public void setCurso(CursoEntity curso) {
		this.curso = curso;
	}

}