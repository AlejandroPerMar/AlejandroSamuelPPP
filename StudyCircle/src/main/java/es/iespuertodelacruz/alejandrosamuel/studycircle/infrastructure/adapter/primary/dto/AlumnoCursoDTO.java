package es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.primary.dto;


public class AlumnoCursoDTO {
	
	private int id;
	private CursoDTO course;
	private AlumnoDTO student;

	public AlumnoCursoDTO() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public CursoDTO getCourse() {
		return course;
	}

	public void setCourse(CursoDTO course) {
		this.course = course;
	}

	public AlumnoDTO getStudent() {
		return student;
	}

	public void setStudent(AlumnoDTO student) {
		this.student = student;
	}
}
