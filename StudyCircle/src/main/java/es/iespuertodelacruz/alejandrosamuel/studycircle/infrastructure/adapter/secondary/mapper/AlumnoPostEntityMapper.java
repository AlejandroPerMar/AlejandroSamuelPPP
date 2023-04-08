package es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.mapper;

import java.util.stream.Collectors;

import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.model.Alumno;
import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.model.Materia;
import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.model.NivelEstudios;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.entity.AlumnoEntity;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.entity.MateriaEntity;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.entity.NivelEstudiosEntity;

public class AlumnoPostEntityMapper {

	public Alumno toDomain(AlumnoEntity in) {
		Alumno alumno = new Alumno();
		alumno.setId(in.getId());
		alumno.setNivelEstudios(toDomain(in.getNivelEstudios()));
		if(in.getMaterias() == null)
			alumno.setMaterias(null);
		else
			alumno.setMaterias(in.getMaterias()
					.stream()
					.map(this::toDomain)
					.collect(Collectors.toList()));
		return alumno;
	}
	
	public AlumnoEntity toEntity(Alumno in) {
		AlumnoEntity alumno = new AlumnoEntity();
		alumno.setFechaCreacion(in.getFechaCreacion());
		alumno.setNivelEstudios(toEntity(in.getNivelEstudios()));
		if(in.getMaterias() == null)
			alumno.setMaterias(null);
		else
			alumno.setMaterias(in.getMaterias()
				.stream()
				.map(this::toEntity)
				.collect(Collectors.toList()));
		return alumno;
	}
	
	private MateriaEntity toEntity(Materia in) {
		MateriaEntity materia = new MateriaEntity();
		materia.setId(in.getId());
		return materia;
	}
	
	private Materia toDomain(MateriaEntity in) {
		Materia materia = new Materia();
		materia.setId(in.getId());
		return materia;
	}
	
	private NivelEstudiosEntity toEntity(NivelEstudios in) {
		NivelEstudiosEntity nivelEstudios = new NivelEstudiosEntity();
		nivelEstudios.setId(in.getId());
		return nivelEstudios;
	}
	
	private NivelEstudios toDomain(NivelEstudiosEntity in) {
		NivelEstudios nivelEstudios = new NivelEstudios();
		nivelEstudios.setId(in.getId());
		return nivelEstudios;
	}
}
