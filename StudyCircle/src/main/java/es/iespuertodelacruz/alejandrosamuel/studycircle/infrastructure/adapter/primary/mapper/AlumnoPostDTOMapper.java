package es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.primary.mapper;

import java.util.List;
import java.util.stream.Collectors;

import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.model.Alumno;
import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.model.Materia;
import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.model.NivelEstudios;
import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.model.Usuario;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.primary.dto.AlumnoDTO;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.primary.dto.MateriaDTO;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.primary.dto.NivelEstudiosDTO;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.primary.dto.UsuarioDTO;

public class AlumnoPostDTOMapper {

	public Alumno toDomain(AlumnoDTO in) {
		if(in == null)
			return null;
		
		Alumno alumno = new Alumno();
		alumno.setNivelEstudios(toDomain(in.getNivelEstudios()));
		alumno.setUsuario(toDomain(in.getUsuario()));
		List<Materia> materias = in.getMaterias()
				.stream()
				.map(m->toDomain(m))
				.collect(Collectors.toList());
		alumno.setMaterias(materias);
		return alumno;
	}
	
	public AlumnoDTO toDTO(Alumno in) {
		if(in == null)
			return null;
		
		AlumnoDTO alumno = new AlumnoDTO();
		alumno.setNivelEstudios(toDTO(in.getNivelEstudios()));
		alumno.setUsuario(toDTO(in.getUsuario()));
		List<MateriaDTO> materias = in.getMaterias()
				.stream()
				.map(m->toDTO(m))
				.collect(Collectors.toList());
		alumno.setMaterias(materias);
		return alumno;
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
