package es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.primary.dto;


public class MateriaTutorDTO {

	private int id;
	private MateriaDTO subject;
	private TutorDTO tutorDTO;

	public MateriaTutorDTO() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public MateriaDTO getSubject() {
		return subject;
	}

	public void setSubject(MateriaDTO subject) {
		this.subject = subject;
	}

	public TutorDTO getTutor() {
		return tutorDTO;
	}

	public void setTutor(TutorDTO tutorDTO) {
		this.tutorDTO = tutorDTO;
	}
}
