package es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.mapper;

import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.model.Alumno;
import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.model.Materia;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.entity.AlumnoEntity;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Objects;

public class AlumnoEntityMapper {

    @Autowired
    private MateriaEntityMapper materiaEntityMapper;

    @Autowired
    private EntityJustIdMapper entityJustIdMapper;

    public Alumno toDomain(AlumnoEntity in) {
        Alumno alumno = new Alumno();
        alumno.setId(in.getId());
		alumno.setNivelEstudios(entityJustIdMapper.toDomain(in.getNivelEstudios()));
        if(Objects.nonNull(in.getMaterias())) {
            List<Materia> materias = in.getMaterias().stream().map(materiaEntityMapper::toDomain).toList();
            alumno.setMaterias(materias);
        }
        alumno.setUsuario(entityJustIdMapper.toDomain(in.getUsuario()));
        return alumno;
    }

    public Alumno toDomainPost(AlumnoEntity in) {
        Alumno alumno = new Alumno();
        alumno.setId(in.getId());
        alumno.setNivelEstudios(entityJustIdMapper.toDomain(in.getNivelEstudios()));
        if(Objects.nonNull(in.getMaterias())) {
            List<Materia> materias = in.getMaterias().stream().map(entityJustIdMapper::toDomain).toList();
            alumno.setMaterias(materias);
        }
        alumno.setUsuario(entityJustIdMapper.toDomain(in.getUsuario()));
        return alumno;
    }

    public AlumnoEntity toEntityPost(Alumno in) {
        AlumnoEntity alumno = new AlumnoEntity();
        alumno.setFechaCreacion(in.getFechaCreacion());
        alumno.setNivelEstudios(entityJustIdMapper.toEntity(in.getNivelEstudios()));
        if(Objects.nonNull(in.getMaterias()))
            alumno.setMaterias(in.getMaterias().stream().map(entityJustIdMapper::toEntity).toList());
        alumno.setUsuario(entityJustIdMapper.toEntity(in.getUsuario()));
        return alumno;
    }

    public AlumnoEntity toEntityPut(Alumno in) {
        AlumnoEntity alumno = new AlumnoEntity();
        alumno.setId(in.getId());
		alumno.setNivelEstudios(entityJustIdMapper.toEntity(in.getNivelEstudios()));
        if(Objects.nonNull(in.getMaterias()))
            alumno.setMaterias(in.getMaterias().stream().map(m->entityJustIdMapper.toEntity(m)).toList());
        return alumno;
    }

}
