package es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.mapper;

import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.model.Alumno;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.entity.AlumnoEntity;

public class AlumnoEntityMapper {

	public Alumno toDomain(AlumnoEntity in) {
		Alumno alumno = new Alumno();
		return alumno;
	}
	
	public AlumnoEntity toEntity(Alumno in) {
		AlumnoEntity alumno = new AlumnoEntity();
		return alumno;
	}
}
