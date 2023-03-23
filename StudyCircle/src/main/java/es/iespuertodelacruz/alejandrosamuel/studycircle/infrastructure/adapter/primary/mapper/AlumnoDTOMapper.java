package es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.primary.mapper;

import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.model.Alumno;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.primary.dto.AlumnoDTO;

public class AlumnoDTOMapper {

	public Alumno toDomain(AlumnoDTO in) {
		if(in == null)
			return null;
		
		Alumno alumno = new Alumno();
		alumno.setId(in.getId());
		alumno.setFechaCreacion(in.getFechaCreacion());
		alumno.setNivelEstudios(in.getNivelEstudios());
		alumno.setUsuario(in.getUsuario());
		alumno.setActividades(in.getActividades());
		alumno.setAnuncios(in.getAnuncios());
		alumno.setEventosCalendario(in.getEventosCalendario());
		alumno.setMateriasAlumno(in.getMateriasAlumno());
		return alumno;
	}
	
	public AlumnoDTO toDTO(Alumno in) {
		if(in == null)
			return null;
		
		AlumnoDTO alumno = new AlumnoDTO();
		alumno.setId(in.getId());
		alumno.setFechaCreacion(in.getFechaCreacion());
		alumno.setNivelEstudios(in.getNivelEstudios());
		alumno.setUsuario(in.getUsuario());
		alumno.setActividades(in.getActividades());
		alumno.setAnuncios(in.getAnuncios());
		alumno.setEventosCalendario(in.getEventosCalendario());
		alumno.setMateriasAlumno(in.getMateriasAlumno());
		return alumno;
	}	
}
