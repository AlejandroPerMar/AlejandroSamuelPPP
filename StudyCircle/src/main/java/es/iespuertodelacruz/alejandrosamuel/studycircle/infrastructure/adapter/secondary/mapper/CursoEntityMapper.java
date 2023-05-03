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
        if(Objects.nonNull(in.getActividades()))
            curso.setActividades(in.getActividades().stream().map(this::toDomain).toList());
        if(Objects.nonNull(in.getAlumnos()))
            curso.setAlumnos(in.getAlumnos().stream().map(this::toDomain).toList());
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
            curso.setActividades(in.getActividades().stream().map(this::toEntity).toList());
        if(Objects.nonNull(in.getAlumnos()))
            curso.setAlumnos(in.getAlumnos().stream().map(this::toEntity).toList());
        curso.setMateriaTutor(toDomain(in.getMateriaTutor()));
        return curso;
    }

    private Actividad toDomain(ActividadEntity in) {
        Actividad actividad = new Actividad();
        actividad.setId(in.getId());
        return actividad;
    }

    private ActividadEntity toEntity(Actividad in) {
        ActividadEntity actividad = new ActividadEntity();
        actividad.setId(in.getId());
        return actividad;
    }

    private MateriaTutorEntity toDomain(MateriaTutor in) {
        MateriaTutorEntity materiaTutor = new MateriaTutorEntity();
        materiaTutor.setId(in.getId());
        return materiaTutor;
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

    public MateriaTutorEntity toEntity(MateriaTutor in) {
        MateriaTutorEntity materiaTutor = new MateriaTutorEntity();
        materiaTutor.setId(in.getId());
        return materiaTutor;
    }
}
