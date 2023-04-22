package es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.mapper;

import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.model.*;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.entity.*;

public class CursoEntityMapper {
    public Curso toDomain(CursoEntity in) {
        if(in == null)
            return null;

        Curso curso = new Curso();
        curso.setId(in.getId());
        curso.setTitulo(in.getTitulo());
        curso.setPrecioHora(in.getPrecioHora());
        curso.setFechaCreacion(in.getFechaCreacion());
        curso.setActividadesCurso(in.getActividadesCurso().stream().map(this::toDomain).toList());
        curso.setAlumnosCurso(in.getAlumnosCurso().stream().map(this::toDomain).toList());
        curso.setMateriaTutor(toDomain(in.getMateriaTutor()));
        return curso;
    }

    public CursoEntity toEntity(Curso in) {
        if(in == null)
            return null;

        CursoEntity curso = new CursoEntity();
        curso.setId(in.getId());
        return curso;
    }

    public ActividadCurso toDomain(ActividadCursoEntity in) {
        ActividadCurso actividadCurso = new ActividadCurso();
        actividadCurso.setId(in.getId());
        return actividadCurso;
    }

    public AlumnoCurso toDomain(AlumnoCursoEntity in) {
        AlumnoCurso alumnoCurso = new AlumnoCurso();
        alumnoCurso.setId(in.getId());
        return alumnoCurso;
    }

    public MateriaTutor toDomain(MateriaTutorEntity in) {
        MateriaTutor materiaTutor = new MateriaTutor();
        materiaTutor.setId(in.getId());
        return materiaTutor;
    }

    public Alumno toDomain(AlumnoEntity in) {
        Alumno alumno = new Alumno();
        alumno.setId(in.getId());
        return alumno;
    }

    public AlumnoEntity toEntity(Alumno in) {
        AlumnoEntity alumno = new AlumnoEntity();
        alumno.setId(in.getId());
        return alumno;
    }

    public AlumnoCursoEntity toEntity(AlumnoCurso in) {
        AlumnoCursoEntity alumnoCurso = new AlumnoCursoEntity();
        alumnoCurso.setAlumno(toEntity(in.getAlumno()));
        alumnoCurso.setCurso(toEntity(in.getCurso()));
        return alumnoCurso;
    }

    public MateriaTutorEntity toEntity(MateriaTutor in) {
        MateriaTutorEntity materiaTutor = new MateriaTutorEntity();
        materiaTutor.setId(in.getId());
        return materiaTutor;
    }
}
