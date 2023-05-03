package es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.mapper;

import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.model.*;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.entity.*;

import java.util.Objects;

public class CursoEntityMapper {
    public Curso toDomain(CursoEntity in) {
        if(Objects.isNull(in)) return null;

        Curso curso = new Curso();
        curso.setId(in.getId());
        curso.setTitulo(in.getTitulo());
        curso.setFechaCreacion(in.getFechaCreacion());
        if(Objects.nonNull(in.getActividadesCurso()))
            curso.setActividadesCurso(in.getActividadesCurso().stream().map(this::toDomain).toList());
        if(Objects.nonNull(in.getAlumnosCurso()))
            curso.setAlumnosCurso(in.getAlumnosCurso().stream().map(this::toDomain).toList());
        curso.setMateriaTutor(toDomain(in.getMateriaTutor()));
        return curso;
    }

    public CursoEntity toEntity(Curso in) {
        if(Objects.isNull(in)) return null;

        CursoEntity curso = new CursoEntity();
        curso.setId(in.getId());
        curso.setTitulo(in.getTitulo());
        curso.setFechaCreacion(in.getFechaCreacion());
        if(Objects.nonNull(in.getActividadesCurso()))
            curso.setActividadesCurso(in.getActividadesCurso().stream().map(this::toEntity).toList());
        if(Objects.nonNull(in.getAlumnosCurso()))
            curso.setAlumnosCurso(in.getAlumnosCurso().stream().map(this::toEntity).toList());
        curso.setMateriaTutor(toDomain(in.getMateriaTutor()));
        return curso;
    }

    private ActividadCursoEntity toEntity(ActividadCurso in) {
        ActividadCursoEntity actividadCurso = new ActividadCursoEntity();
        actividadCurso.setId(in.getId());
        return actividadCurso;
    }

    private MateriaTutorEntity toDomain(MateriaTutor in) {
        MateriaTutorEntity materiaTutor = new MateriaTutorEntity();
        materiaTutor.setId(in.getId());
        return materiaTutor;
    }

    public ActividadCurso toDomain(ActividadCursoEntity in) {
        ActividadCurso actividadCurso = new ActividadCurso();
        actividadCurso.setId(in.getId());
        Actividad actividad = new Actividad();
        actividad.setId(in.getActividad().getId());
        actividadCurso.setActividad(actividad);
        Curso curso = new Curso();
        curso.setId(in.getCurso().getId());
        actividadCurso.setCurso(curso);
        return actividadCurso;
    }

    public AlumnoCurso toDomain(AlumnoCursoEntity in) {
        AlumnoCurso alumnoCurso = new AlumnoCurso();
        alumnoCurso.setId(in.getId());
        Alumno alumno = new Alumno();
        alumno.setId(in.getAlumno().getId());
        alumnoCurso.setAlumno(alumno);
        Curso curso = new Curso();
        curso.setId(in.getCurso().getId());
        alumnoCurso.setCurso(curso);
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
        if(Objects.isNull(in)) return null;

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
