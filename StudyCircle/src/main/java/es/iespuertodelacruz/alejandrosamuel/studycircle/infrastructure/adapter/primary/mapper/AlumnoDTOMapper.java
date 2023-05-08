package es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.primary.mapper;

import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.model.Alumno;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.primary.dto.AlumnoDTO;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Objects;

public class AlumnoDTOMapper {

	@Autowired
	private DTOJustIdMapper dtoJustIdMapper;
	
	public Alumno toDomain(AlumnoDTO in) {
		if(Objects.isNull(in))
			return null;
		
		Alumno alumno = new Alumno();
		alumno.setId(in.getId());
		alumno.setNivelEstudios(dtoJustIdMapper.toDomain(in.getNivelEstudios()));
		if(Objects.isNull(in.getMaterias()))
			alumno.setMaterias(null);
		else
			alumno.setMaterias(in.getMaterias().stream().map(dtoJustIdMapper::toDomain).toList());
		alumno.setUsuario(dtoJustIdMapper.toDomain(in.getUsuario()));
		return alumno;
	}

	public Alumno toDomainPost(AlumnoDTO in) {
		if(Objects.isNull(in))
			return null;

		Alumno alumno = new Alumno();
		alumno.setNivelEstudios(dtoJustIdMapper.toDomain(in.getNivelEstudios()));
		if(Objects.nonNull(in.getMaterias()))
			alumno.setMaterias(in.getMaterias().stream().map(dtoJustIdMapper::toDomain).toList());
		else
			alumno.setMaterias(null);
		alumno.setUsuario(dtoJustIdMapper.toDomain(in.getUsuario()));

		return alumno;
	}

	public AlumnoDTO toDTOGet(Alumno in) {
		if(Objects.isNull(in))
			return null;
		
		AlumnoDTO alumno = new AlumnoDTO();
		alumno.setId(in.getId());
		alumno.setNivelEstudios(dtoJustIdMapper.toDTO(in.getNivelEstudios()));
		if(Objects.isNull(in.getMaterias()))
			alumno.setMaterias(null);
		else
			alumno.setMaterias(in.getMaterias().stream().map(dtoJustIdMapper::toDTO).toList());
		alumno.setUsuario(dtoJustIdMapper.toDTO(in.getUsuario()));
		return alumno;
	}

}
