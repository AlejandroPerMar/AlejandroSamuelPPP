package es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.primary.mapper;

import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.model.*;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.primary.dto.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Objects;

public class CursoDTOMapper {

    @Autowired
    private DTOJustIdMapper dtoJustIdMapper;
    
    public Curso toDomainPost(CursoDTO in) {
        Curso curso = new Curso();
        curso.setTitulo(in.getTitulo());
        curso.setMateriaTutor(dtoJustIdMapper.toDomain(in.getMateriaTutor()));
        curso.setAlumnos(in.getAlumnos().stream().map(dtoJustIdMapper::toDomain).toList());
        return curso;
    }

    public CursoDTO toDTOStudent(Curso in) {
        CursoDTO curso = new CursoDTO();
        curso.setId(in.getId());
        curso.setTitulo(in.getTitulo());
        curso.setMateriaTutor(toDTO(in.getMateriaTutor()));
        if(Objects.nonNull(in.getActividades()))
            curso.setActividades(in.getActividades().stream().map(dtoJustIdMapper::toDTO).toList());
        return curso;
    }

    public CursoDTO toDTOTutor(Curso in) {
        CursoDTO curso = new CursoDTO();
        curso.setId(in.getId());
        curso.setTitulo(in.getTitulo());
        curso.setMateriaTutor(toDTO(in.getMateriaTutor()));
        if(Objects.nonNull(in.getActividades()))
            curso.setActividades(in.getActividades().stream().map(dtoJustIdMapper::toDTO).toList());
        if(Objects.nonNull(in.getAlumnos()))
            curso.setAlumnos(in.getAlumnos().stream().map(dtoJustIdMapper::toDTO).toList());
        return curso;
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

}
