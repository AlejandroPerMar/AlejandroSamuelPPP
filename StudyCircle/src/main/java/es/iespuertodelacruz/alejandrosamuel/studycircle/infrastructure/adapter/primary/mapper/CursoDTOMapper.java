package es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.primary.mapper;

import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.model.*;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.primary.dto.*;

import java.util.Objects;

public class CursoDTOMapper {
    
    public Curso toDomainPost(CursoDTO in) {
        Curso curso = new Curso();
        curso.setTitulo(in.getTitulo());
        curso.setMateriaTutor(toDomain(in.getMateriaTutor()));
        curso.setAlumnos(in.getAlumnos().stream().map(this::toDomain).toList());
        return curso;
    }

    public CursoDTO toDTOStudent(Curso in) {
        CursoDTO curso = new CursoDTO();
        curso.setId(in.getId());
        curso.setTitulo(in.getTitulo());
        curso.setMateriaTutor(toDTO(in.getMateriaTutor()));
        if(Objects.nonNull(in.getActividades()))
            curso.setActividades(in.getActividades().stream().map(this::toDTO).toList());
        return curso;
    }

    public CursoDTO toDTOTutor(Curso in) {
        CursoDTO curso = new CursoDTO();
        curso.setId(in.getId());
        curso.setTitulo(in.getTitulo());
        curso.setMateriaTutor(toDTO(in.getMateriaTutor()));
        if(Objects.nonNull(in.getActividades()))
            curso.setActividades(in.getActividades().stream().map(this::toDTO).toList());
        if(Objects.nonNull(in.getAlumnos()))
            curso.setAlumnos(in.getAlumnos().stream().map(this::toDTO).toList());
        return curso;
    }

    private ActividadDTO toDTO(Actividad in) {
        ActividadDTO actividad = new ActividadDTO();
        actividad.setId(in.getId());
        return actividad;
    }

    private AlumnoDTO toDTO(Alumno in) {
        AlumnoDTO alumno = new AlumnoDTO();
        alumno.setId(in.getId());
        return alumno;
    }

    private Alumno toDomain(AlumnoDTO in) {
        Alumno alumno = new Alumno();
        alumno.setId(in.getId());
        return alumno;
    }

    private MateriaTutorDTO toDTO(MateriaTutor in) {
        MateriaTutorDTO materiaTutor = new MateriaTutorDTO();
        materiaTutor.setId(in.getId());
        MateriaDTO materia = new MateriaDTO();
        materia.setId(in.getMateria().getId());
        materiaTutor.setMateria(materia);
        TutorDTO tutor = new TutorDTO();
        tutor.setId(in.getTutor().getId());
        materiaTutor.setTutor(tutor);
        return materiaTutor;
    }

    private MateriaTutor toDomain(MateriaTutorDTO in) {
        MateriaTutor materiaTutor = new MateriaTutor();
        materiaTutor.setId(in.getId());
        return materiaTutor;
    }
}
