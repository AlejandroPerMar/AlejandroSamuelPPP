package es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.primary.mapper;

import java.util.List;
import java.util.stream.Collectors;

import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.model.Actividad;
import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.model.Alumno;
import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.model.Anuncio;
import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.model.EventoCalendario;
import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.model.Materia;
import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.model.NivelEstudios;
import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.model.Usuario;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.primary.dto.ActividadDTO;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.primary.dto.AlumnoDTO;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.primary.dto.AnuncioDTO;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.primary.dto.EventoCalendarioDTO;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.primary.dto.MateriaDTO;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.primary.dto.NivelEstudiosDTO;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.primary.dto.UsuarioDTO;

public class AlumnoDTOMapper {
	
	public Alumno toDomain(AlumnoDTO in) {
		if(in == null)
			return null;
		
		Alumno alumno = new Alumno();
		alumno.setId(in.getId());
		return alumno;
	}
	
	public AlumnoDTO toDTO(Alumno in) {
		if(in == null)
			return null;
		
		AlumnoDTO alumno = new AlumnoDTO();
		alumno.setId(in.getId());
		return alumno;
	}
	
	public Actividad toDomain(ActividadDTO in) {
		if(in == null)
			return null;
		
		Actividad actividad = new Actividad();
		actividad.setId(in.getId());
		return actividad;
	}
	
	public ActividadDTO toDTO(Actividad in) {
		if(in == null)
			return null;
		
		ActividadDTO actividad = new ActividadDTO();
		actividad.setId(in.getId());
		return actividad;
	}
	
	public Anuncio toDomain(AnuncioDTO in) {
		if(in == null)
			return null;
		
		Anuncio anuncio = new Anuncio();
		anuncio.setId(in.getId());
		return anuncio;
	}
	
	public AnuncioDTO toDTO(Anuncio in) {
		if(in == null)
			return null;
		
		AnuncioDTO anuncio = new AnuncioDTO();
		anuncio.setId(in.getId());
		return anuncio;
	}
	
	public EventoCalendario toDomain(EventoCalendarioDTO in) {
		if(in == null)
			return null;
		
		EventoCalendario eventoCalendario = new EventoCalendario();
		eventoCalendario.setId(in.getId());
		return eventoCalendario;
	}
	
	public EventoCalendarioDTO toDTO(EventoCalendario in) {
		if(in == null)
			return null;
		
		EventoCalendarioDTO eventoCalendario = new EventoCalendarioDTO();
		eventoCalendario.setId(in.getId());
		return eventoCalendario;
	}
	
	public NivelEstudios toDomain(NivelEstudiosDTO in) {
		if(in == null)
			return null;
		
		NivelEstudios nivelEstudios = new NivelEstudios();
		nivelEstudios.setId(in.getId());
		return nivelEstudios;
	}
	
	public NivelEstudiosDTO toDTO(NivelEstudios in) {
		if(in == null)
			return null;
		
		NivelEstudiosDTO nivelEstudios = new NivelEstudiosDTO();
		nivelEstudios.setId(in.getId());
		return nivelEstudios;
	}
	
	public Usuario toDomain(UsuarioDTO in) {
		if(in == null)
			return null;
		
		Usuario usuario = new Usuario();
		usuario.setId(in.getId());
		return usuario;
	}
	
	public UsuarioDTO toDTO(Usuario in) {
		if(in == null)
			return null;
		
		UsuarioDTO usuario = new UsuarioDTO();
		usuario.setId(in.getId());
		return usuario;
	}
	public Materia toDomain(MateriaDTO in) {
		if(in == null)
			return null;
		
		Materia materia = new Materia();
		materia.setId(in.getId());
		return materia;
	}
	
	public MateriaDTO toDTO(Materia in) {
		if(in == null)
			return null;
		
		MateriaDTO materia = new MateriaDTO();
		materia.setId(in.getId());
		return materia;
	}
}
