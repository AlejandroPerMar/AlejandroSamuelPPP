package es.iespuertodelacruz.alejandrosamuel.studycircle.domain.port.secondary;

import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.model.Alumno;

public interface IAlumnoRepository {
	
	public Alumno create(Alumno alumno);
	
	public Alumno update(Alumno alumno);
	
	public Alumno findAlumnoById(Integer id);
	
	public Alumno findAlumnoByIdUsuario(Integer id);

	public Alumno findAlumnoByUsername(String username);
}
