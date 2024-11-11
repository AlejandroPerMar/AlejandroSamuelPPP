package es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.primary.mapper;

import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.model.*;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.primary.dto.*;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.entity.MateriaTutorEntity;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Objects;

public class CursoDTOMapper {

    @Autowired
    private DTOJustIdMapper dtoJustIdMapper;
    
    public Curso toDomainPost(CursoDTO in) {
        Curso curso = new Curso();
        curso.setTitulo(in.getTitulo());
        curso.setMateriaTutor(dtoJustIdMapper.toDomain(in.getMateriaTutor()));
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

    public AlumnoDTO toDTO(Alumno in) {
        AlumnoDTO alumno = new AlumnoDTO();
        alumno.setId(in.getId());
        alumno.setUsuario(toDTO(in.getUsuario()));
        return alumno;
    }

    private MateriaTutorDTO toDTO(MateriaTutor in) {
        MateriaTutorDTO materiaTutor = new MateriaTutorDTO();
        materiaTutor.setId(in.getId());
        MateriaDTO materia = null;
        if(Objects.nonNull(in.getMateria())) {
            materia = new MateriaDTO();
            materia.setId(in.getMateria().getId());
            materia.setNombre(in.getMateria().getNombre());
        }
        materiaTutor.setMateria(materia);
        TutorDTO tutor = null;
        if(Objects.nonNull(in.getTutor())) {
            tutor = new TutorDTO();
            tutor.setId(in.getTutor().getId());
            tutor.setUsuario(toDTO(in.getTutor().getUsuario()));
        }
        materiaTutor.setTutor(tutor);
        return materiaTutor;
    }

    public UsuarioDTO toDTO(Usuario in) {
        UsuarioDTO usuario = new UsuarioDTO();
        usuario.setId(in.getId());
        usuario.setUsername(in.getUsername());
        usuario.setNombreCompleto(in.getNombreCompleto());
        return usuario;
    }

    public ActividadDTO toDTO(Actividad in) {
        ActividadDTO actividad = new ActividadDTO();
        actividad.setId(in.getId());
        actividad.setNombre(in.getNombre());
        actividad.setFechaActividad(in.getFechaActividad());
        actividad.setDescripcion(in.getDescripcion());
        CursoDTO curso = new CursoDTO();
        curso.setMateriaTutor(toDTO(in.getCurso().getMateriaTutor()));
        actividad.setCurso(curso);
        return actividad;
    }
}
