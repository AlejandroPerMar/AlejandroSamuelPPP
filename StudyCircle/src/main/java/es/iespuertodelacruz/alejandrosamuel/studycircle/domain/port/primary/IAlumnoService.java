package es.iespuertodelacruz.alejandrosamuel.studycircle.domain.port.primary;

import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.model.Alumno;

public interface IAlumnoService {

	Alumno create(Alumno alumno);
	
	Alumno update(Alumno alumno);
	
	Alumno findAlumnoById(Integer id);
	
	Alumno findAlumnoByIdUsuario(Integer id);
	
	Alumno findAlumnoByUsername(String username);
}
