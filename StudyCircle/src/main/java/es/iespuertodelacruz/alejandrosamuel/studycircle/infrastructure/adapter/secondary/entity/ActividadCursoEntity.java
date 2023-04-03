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
	private ActividadEntity activity;

	//bi-directional many-to-one association to CursoEntity
	@ManyToOne
	@JoinColumn(name="id_course")
	private CursoEntity course;

	public ActividadCursoEntity() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public ActividadEntity getActivity() {
		return this.activity;
	}

	public void setActivity(ActividadEntity activity) {
		this.activity = activity;
	}

	public CursoEntity getCourse() {
		return this.course;
	}

	public void setCourse(CursoEntity course) {
		this.course = course;
	}

}