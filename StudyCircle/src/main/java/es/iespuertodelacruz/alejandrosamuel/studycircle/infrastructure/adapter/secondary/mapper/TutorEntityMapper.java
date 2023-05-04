package es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.mapper;

import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.model.*;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.entity.*;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import java.util.Objects;

public class TutorEntityMapper {
    public TutorEntity toEntityPost(Tutor in) {
        TutorEntity tutor = new TutorEntity();
        tutor.setUsuario(toEntity(in.getUsuario()));
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
        tutor.setUsuario(toDomain(in.getUsuario()));
        return tutor;
    }

    public Tutor toDomainOnlyId(TutorEntity in) {
        Tutor tutor = new Tutor();
        tutor.setId(in.getId());
        return tutor;
    }

    public TutorEntity toEntity(Tutor in) {
        TutorEntity tutor = new TutorEntity();
        tutor.setId(in.getId());
        return tutor;
    }

    public MateriaTutor toDomain(MateriaTutorEntity in) {
        MateriaTutor materiaTutor = new MateriaTutor();
        materiaTutor.setId(in.getId());
        materiaTutor.setTutor(toDomainOnlyId(in.getTutor()));
        materiaTutor.setMateria(toDomain(in.getMateria()));
        List<Curso> cursos = in.getCursosTutor().stream().map(this::toDomain).toList();
        materiaTutor.setCursosTutor(cursos);
        return materiaTutor;
    }

    private Curso toDomain(CursoEntity in) {
        Curso curso = new Curso();
        curso.setId(in.getId());
        return curso;
    }

    private Materia toDomain(MateriaEntity in) {
        Materia materia = new Materia();
        materia.setId(in.getId());
        return materia;
    }

    public MateriaTutorEntity toEntity(MateriaTutor in) {
        MateriaTutorEntity materiaTutor = new MateriaTutorEntity();
        materiaTutor.setId(in.getId());
        return materiaTutor;
    }

    public MateriaEntity toEntity(Materia in) {
        MateriaEntity materia = new MateriaEntity();
        materia.setId(in.getId());
        return materia;
    }

    public Usuario toDomain(UsuarioEntity in) {
        Usuario usuario = new Usuario();
        usuario.setId(in.getId());
        return usuario;
    }

    public UsuarioEntity toEntity(Usuario in) {
        UsuarioEntity usuario = new UsuarioEntity();
        usuario.setId(in.getId());
        return usuario;
    }
}
