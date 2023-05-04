package es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.primary.mapper;

import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.model.Actividad;
import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.model.Alumno;
import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.model.Anuncio;
import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.model.EventoCalendario;
import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.model.Materia;
import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.model.NivelEstudios;
import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.model.Usuario;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.primary.dto.*;

import java.util.Objects;

public class AlumnoDTOMapper {
	
	public Alumno toDomain(AlumnoDTO in) {
		if(Objects.isNull(in))
			return null;
		
		Alumno alumno = new Alumno();
		alumno.setId(in.getId());
		alumno.setNivelEstudios(toDomain(in.getNivelEstudios()));
		if(Objects.isNull(in.getMaterias()))
			alumno.setMaterias(null);
		else
			alumno.setMaterias(in.getMaterias().stream().map(this::toDomain).toList());
		alumno.setUsuario(toDomain(in.getUsuario()));
		return alumno;
	}

	public Alumno toDomainPost(AlumnoDTO in) {
		if(Objects.isNull(in))
			return null;

		Alumno alumno = new Alumno();
		alumno.setNivelEstudios(toDomain(in.getNivelEstudios()));
		if(Objects.nonNull(in.getMaterias()))
			alumno.setMaterias(in.getMaterias().stream().map(this::toDomain).toList());
		else
			alumno.setMaterias(null);
		alumno.setUsuario(toDomain(in.getUsuario()));

		return alumno;
	}

	public AlumnoDTO toDTOGet(Alumno in) {
		if(Objects.isNull(in))
			return null;
		
		AlumnoDTO alumno = new AlumnoDTO();
		alumno.setId(in.getId());
		alumno.setNivelEstudios(toDTO(in.getNivelEstudios()));
		if(Objects.isNull(in.getMaterias()))
			alumno.setMaterias(null);
		else
			alumno.setMaterias(in.getMaterias().stream().map(this::toDTO).toList());
		alumno.setUsuario(toDTO(in.getUsuario()));
		return alumno;
	}
	
	public Actividad toDomain(ActividadDTO in) {
		if(Objects.isNull(in))
			return null;
		
		Actividad actividad = new Actividad();
		actividad.setId(in.getId());
		return actividad;
	}
	
	public ActividadDTO toDTO(Actividad in) {
		if(Objects.isNull(in))
			return null;
		
		ActividadDTO actividad = new ActividadDTO();
		actividad.setId(in.getId());
		return actividad;
	}
	
	public Anuncio toDomain(AnuncioDTO in) {
		if(Objects.isNull(in))
			return null;
		
		Anuncio anuncio = new Anuncio();
		anuncio.setId(in.getId());
		return anuncio;
	}
	
	public AnuncioDTO toDTO(Anuncio in) {
		if(Objects.isNull(in))
			return null;
		
		AnuncioDTO anuncio = new AnuncioDTO();
		anuncio.setId(in.getId());
		return anuncio;
	}
	
	public EventoCalendario toDomain(EventoCalendarioDTO in) {
		if(Objects.isNull(in))
			return null;
		
		EventoCalendario eventoCalendario = new EventoCalendario();
		eventoCalendario.setId(in.getId());
		return eventoCalendario;
	}
	
	public EventoCalendarioDTO toDTO(EventoCalendario in) {
		if(Objects.isNull(in))
			return null;
		
		EventoCalendarioDTO eventoCalendario = new EventoCalendarioDTO();
		eventoCalendario.setId(in.getId());
		return eventoCalendario;
	}
	
	public NivelEstudios toDomain(NivelEstudiosDTO in) {
		if(Objects.isNull(in))
			return null;
		
		NivelEstudios nivelEstudios = new NivelEstudios();
		nivelEstudios.setId(in.getId());
		return nivelEstudios;
	}
	
	public NivelEstudiosDTO toDTO(NivelEstudios in) {
		if(Objects.isNull(in))
			return null;
		
		NivelEstudiosDTO nivelEstudios = new NivelEstudiosDTO();
		nivelEstudios.setId(in.getId());
		return nivelEstudios;
	}
	
	public Usuario toDomain(UsuarioDTO in) {
		if(Objects.isNull(in))
			return null;
		
		Usuario usuario = new Usuario();
		usuario.setId(in.getId());
		return usuario;
	}
	
	public UsuarioDTO toDTO(Usuario in) {
		if(Objects.isNull(in))
			return null;
		
		UsuarioDTO usuario = new UsuarioDTO();
		usuario.setId(in.getId());
		return usuario;
	}
	public Materia toDomain(MateriaDTO in) {
		if(Objects.isNull(in))
			return null;
		
		Materia materia = new Materia();
		materia.setId(in.getId());
		return materia;
	}
	
	public MateriaDTO toDTO(Materia in) {
		if(Objects.isNull(in))
			return null;
		
		MateriaDTO materia = new MateriaDTO();
		materia.setId(in.getId());
		return materia;
	}
}
