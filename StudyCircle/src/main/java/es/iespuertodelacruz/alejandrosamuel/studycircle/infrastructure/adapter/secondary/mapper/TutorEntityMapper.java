package es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.mapper;

import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.model.Materia;
import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.model.MateriaTutor;
import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.model.Tutor;
import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.model.Usuario;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.entity.MateriaEntity;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.entity.MateriaTutorEntity;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.entity.TutorEntity;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.entity.UsuarioEntity;

import java.math.BigInteger;
import java.util.Date;

public class TutorEntityMapper {
    public TutorEntity toEntityPost(Tutor in) {
        TutorEntity tutor = new TutorEntity();
        tutor.setUsuario(toEntity(in.getUsuario()));
        tutor.setMateriasTutor(in.getMateriasTutor().stream().map(this::toEntity).toList());
        tutor.setFechaCreacion(new BigInteger(String.valueOf(new Date().getTime())));
        return tutor;
    }

    public TutorEntity toEntityPut(Tutor in) {
        TutorEntity tutor = new TutorEntity();
        tutor.setMateriasTutor(in.getMateriasTutor().stream().map(this::toEntity).toList());
        return tutor;
    }

    public Tutor toDomain(TutorEntity in) {
        Tutor tutor = new Tutor();
        tutor.setId(in.getId());
        tutor.setMateriasTutor(in.getMateriasTutor().stream().map(this::toDomain).toList());
        tutor.setUsuario(toDomain(in.getUsuario()));
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
        return materiaTutor;
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
