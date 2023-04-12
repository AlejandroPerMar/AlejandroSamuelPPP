package es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.mapper;

import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.model.Alumno;
import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.model.Materia;
import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.model.NivelEstudios;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.entity.AlumnoEntity;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.entity.MateriaEntity;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.entity.NivelEstudiosEntity;

import java.util.List;
import java.util.stream.Collectors;

public class AlumnoEntityMapper {

    public Alumno toDomain(AlumnoEntity in) {
        Alumno alumno = new Alumno();
        alumno.setId(in.getId());
		/*alumno.setNivelEstudios(toDomain(in.getStudyLevel()));
		List<Materia> materias = in.getSubjects()
				.stream()
				.map(m->toDomain(m))
				.collect(Collectors.toList());
		alumno.setMaterias(materias);*/
        return alumno;
    }

    public AlumnoEntity toEntityGet(Alumno in) {
        AlumnoEntity alumno = new AlumnoEntity();
        alumno.setId(in.getId());
		alumno.setNivelEstudios(toEntity(in.getNivelEstudios()));
		List<MateriaEntity> materiasEntity = in.getMaterias()
				.stream()
				.map(m->toEntity(m))
				.collect(Collectors.toList());
		alumno.setMaterias(materiasEntity);
        return alumno;
    }

    public AlumnoEntity toEntityPost(Alumno in) {
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

    public AlumnoEntity toEntityPut(Alumno in) {
        AlumnoEntity alumno = new AlumnoEntity();
        alumno.setId(in.getId());
		alumno.setNivelEstudios(toEntity(in.getNivelEstudios()));
		List<MateriaEntity> materiasEntity = in.getMaterias()
				.stream()
				.map(m->toEntity(m))
				.collect(Collectors.toList());
		alumno.setMaterias(materiasEntity);
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
