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
        curso.setPrecioHora(in.getPrecioHora());
        curso.setMateriaTutor(toDomain(in.getMateriaTutor()));
        curso.setAlumnosCurso(in.getAlumnosCurso().stream().map(this::toDomainPost).toList());
        return curso;
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
