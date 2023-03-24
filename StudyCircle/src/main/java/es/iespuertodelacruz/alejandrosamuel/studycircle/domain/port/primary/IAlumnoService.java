package es.iespuertodelacruz.alejandrosamuel.studycircle.domain.port.primary;

import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.model.Alumno;

public interface IAlumnoService {

	public Alumno create(Alumno alumno);
	
	public Alumno update(Alumno alumno);
	
	public Alumno findAlumnoById(Integer id);
	
	public Alumno findAlumnoByIdUsuario(Integer id);
	
	public Alumno findAlumnoByUsername(String username);
}
