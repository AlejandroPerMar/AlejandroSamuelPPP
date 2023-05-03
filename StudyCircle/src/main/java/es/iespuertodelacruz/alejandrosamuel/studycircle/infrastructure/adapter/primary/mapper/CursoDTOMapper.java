package es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.primary.mapper;

import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.model.Alumno;
import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.model.AlumnoCurso;
import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.model.Curso;
import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.model.MateriaTutor;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.primary.dto.AlumnoCursoDTO;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.primary.dto.AlumnoDTO;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.primary.dto.CursoDTO;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.primary.dto.MateriaTutorDTO;

public class CursoDTOMapper {
    
    public Curso toDomainPost(CursoDTO in) {
        Curso curso = new Curso();
        curso.setTitulo(in.getTitulo());
        curso.setMateriaTutor(toDomain(in.getMateriaTutor()));
        curso.setAlumnosCurso(in.getAlumnosCurso().stream().map(this::toDomainPost).toList());
        return curso;
    }

    public CursoDTO toDTO(Curso in) {
        CursoDTO curso = new CursoDTO();
        curso.setId(in.getId());
        curso.setTitulo(in.getTitulo());
        curso.setMateriaTutor(toDTO(in.getMateriaTutor()));
        curso.setAlumnosCurso(in.getAlumnosCurso().stream().map(this::toDTO).toList());
        return curso;
    }

    private AlumnoCursoDTO toDTO(AlumnoCurso in) {
        AlumnoCursoDTO alumnoCurso = new AlumnoCursoDTO();
        alumnoCurso.setAlumno(toDTO(in.getAlumno()));
        return alumnoCurso;
    }

    private AlumnoDTO toDTO(Alumno in) {
        AlumnoDTO alumno = new AlumnoDTO();
        alumno.setId(in.getId());
        return alumno;
    }

    private MateriaTutorDTO toDTO(MateriaTutor in) {
        MateriaTutorDTO materiaTutor = new MateriaTutorDTO();
        materiaTutor.setId(in.getId());
        return materiaTutor;
    }

    private MateriaTutor toDomain(MateriaTutorDTO in) {
        MateriaTutor materiaTutor = new MateriaTutor();
        materiaTutor.setId(in.getId());
        return materiaTutor;
    }
    
    private AlumnoCurso toDomainPost(AlumnoCursoDTO in) {
        AlumnoCurso alumnoCurso = new AlumnoCurso();
        alumnoCurso.setAlumno(toDomain(in.getAlumno()));
        return alumnoCurso;
    }

    private Alumno toDomain(AlumnoDTO in) {
        Alumno alumno = new Alumno();
        alumno.setId(in.getId());
        return alumno;
    }
}
