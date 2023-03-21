package es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.entities;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the students_tutor_subjects_statuses database table.
 * 
 */
@Entity
@Table(name="students_tutor_subjects_statuses")
@NamedQuery(name="EstadoMateriaAlumnoEntity.findAll", query="SELECT e FROM EstadoMateriaAlumnoEntity e")
public class EstadoMateriaAlumnoEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;

	@Column(name="status")
	private String estado;

	public EstadoMateriaAlumnoEntity() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEstado() {
		return this.estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

}