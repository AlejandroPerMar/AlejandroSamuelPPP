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
            curso.setActividades(in.getActividades().stream().map(this::toDomain).toList());
        if(Objects.nonNull(in.getAlumnos()))
            curso.setAlumnos(in.getAlumnos().stream().map(this::toDomain).toList());
        curso.setMateriaTutor(toDomain(in.getMateriaTutor()));
        return curso;
    }

    private Alumno toDomain(AlumnoEntity in) {
        if(Objects.isNull(in)) return null;

        Alumno alumno = new Alumno();
        alumno.setId(in.getId());
        alumno.setUsuario(toDomain(in.getUsuario()));
        return alumno;
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

    public Actividad toDomain(ActividadEntity in) {
        if(Objects.isNull(in)) return null;

        Actividad actividad = new Actividad();
        actividad.setId(in.getId());
        actividad.setNombre(in.getNombre());
        return actividad;
    }

    public MateriaTutor toDomain(MateriaTutorEntity in) {
        if(Objects.isNull(in)) return null;

        MateriaTutor materiaTutor = new MateriaTutor();
        materiaTutor.setId(in.getId());
        Materia materia = null;
        if(Objects.nonNull(in.getMateria())) {
            materia = new Materia();
            materia.setId(in.getMateria().getId());
            materia.setNombre(in.getMateria().getNombre());
        }
        materiaTutor.setMateria(materia);
        Tutor tutor = null;
        if(Objects.nonNull(in.getTutor())) {
            tutor = new Tutor();
            tutor.setId(in.getTutor().getId());
            tutor.setUsuario(toDomain(in.getTutor().getUsuario()));
        }
        materiaTutor.setTutor(tutor);
        return materiaTutor;
    }

    public Usuario toDomain(UsuarioEntity in) {
        if(Objects.isNull(in)) return null;

        Usuario usuario = new Usuario();
        usuario.setId(in.getId());
        usuario.setUsername(in.getUsername());
        usuario.setNombreCompleto(in.getNombreCompleto());
        return usuario;
    }

}
