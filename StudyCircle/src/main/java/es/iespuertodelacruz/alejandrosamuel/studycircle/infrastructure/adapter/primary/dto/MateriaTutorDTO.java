package es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.primary.dto;


public class MateriaTutorDTO {

	private int id;
	private MateriaDTO materia;
	private TutorDTO tutor;

	public MateriaTutorDTO() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public MateriaDTO getMateria() {
		return materia;
	}

	public void setMateria(MateriaDTO materia) {
		this.materia = materia;
	}

	public TutorDTO getTutor() {
		return tutor;
	}

	public void setTutor(TutorDTO tutor) {
		this.tutor = tutor;
	}
}
