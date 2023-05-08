package es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.mapper;

import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.model.*;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.entity.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Objects;

public class CursoEntityMapper {

    @Autowired
    private EntityJustIdMapper entityJustIdMapper;

    public Curso toDomain(CursoEntity in) {
        if(Objects.isNull(in)) return null;

        Curso curso = new Curso();
        curso.setId(in.getId());
        curso.setTitulo(in.getTitulo());
        curso.setFechaCreacion(in.getFechaCreacion());
        if(Objects.nonNull(in.getActividades()))
            curso.setActividades(in.getActividades().stream().map(entityJustIdMapper::toDomain).toList());
        if(Objects.nonNull(in.getAlumnos()))
            curso.setAlumnos(in.getAlumnos().stream().map(entityJustIdMapper::toDomain).toList());
        curso.setMateriaTutor(toDomain(in.getMateriaTutor()));
        return curso;
    }

    public CursoEntity toEntity(Curso in) {
        if(Objects.isNull(in)) return null;

        CursoEntity curso = new CursoEntity();
        curso.setId(in.getId());
        curso.setTitulo(in.getTitulo());
        curso.setFechaCreacion(in.getFechaCreacion());
        if(Objects.nonNull(in.getActividades()))
            curso.setActividades(in.getActividades().stream().map(entityJustIdMapper::toEntity).toList());
        if(Objects.nonNull(in.getAlumnos()))
            curso.setAlumnos(in.getAlumnos().stream().map(entityJustIdMapper::toEntity).toList());
        curso.setMateriaTutor(entityJustIdMapper.toEntity(in.getMateriaTutor()));
        return curso;
    }

    public MateriaTutor toDomain(MateriaTutorEntity in) {
        MateriaTutor materiaTutor = new MateriaTutor();
        materiaTutor.setId(in.getId());
        Materia materia = new Materia();
        materia.setId(in.getMateria().getId());
        materiaTutor.setMateria(materia);
        Tutor tutor = new Tutor();
        tutor.setId(in.getTutor().getId());
        materiaTutor.setTutor(tutor);
        return materiaTutor;
    }

}
