package es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.mapper;

import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.model.*;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.entity.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class TutorEntityMapper {

    @Autowired
    private EntityJustIdMapper entityJustIdMapper;

    public TutorEntity toEntityPost(Tutor in) {
        TutorEntity tutor = new TutorEntity();
        tutor.setUsuario(entityJustIdMapper.toEntity(in.getUsuario()));
        if(Objects.nonNull(in.getMateriasTutor()))
            tutor.setMateriasTutor(in.getMateriasTutor().stream().map(this::toEntity).toList());
        tutor.setFechaCreacion(new BigInteger(String.valueOf(new Date().getTime())));
        return tutor;
    }

    public TutorEntity toEntityPut(Tutor in) {
        TutorEntity tutor = new TutorEntity();
        if(Objects.nonNull(in.getMateriasTutor()))
            tutor.setMateriasTutor(in.getMateriasTutor().stream().map(this::toEntity).toList());
        return tutor;
    }

    public Tutor toDomain(TutorEntity in) {
        Tutor tutor = new Tutor();
        tutor.setId(in.getId());
        if(Objects.nonNull(in.getMateriasTutor()))
            tutor.setMateriasTutor(in.getMateriasTutor().stream().map(this::toDomain).toList());
        tutor.setUsuario(entityJustIdMapper.toDomain(in.getUsuario()));
        return tutor;
    }

    public MateriaTutor toDomain(MateriaTutorEntity in) {
        MateriaTutor materiaTutor = new MateriaTutor();
        materiaTutor.setId(in.getId());
        materiaTutor.setTutor(entityJustIdMapper.toDomain(in.getTutor()));
        materiaTutor.setMateria(entityJustIdMapper.toDomain(in.getMateria()));
        if(Objects.nonNull(in.getCursosTutor())) {
            List<Curso> cursos = in.getCursosTutor().stream().map(entityJustIdMapper::toDomain).toList();
            materiaTutor.setCursosTutor(cursos);
        }
        return materiaTutor;
    }

    public MateriaTutorEntity toEntity(MateriaTutor in) {
        MateriaTutorEntity materiaTutor = new MateriaTutorEntity();
        materiaTutor.setId(in.getId());
        materiaTutor.setTutor(entityJustIdMapper.toEntity(in.getTutor()));
        materiaTutor.setMateria(entityJustIdMapper.toEntity(in.getMateria()));
        return materiaTutor;
    }

}
