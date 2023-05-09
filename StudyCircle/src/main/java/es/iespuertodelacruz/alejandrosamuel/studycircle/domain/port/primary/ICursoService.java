package es.iespuertodelacruz.alejandrosamuel.studycircle.domain.port.primary;


import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.model.Alumno;
import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.model.Curso;

import java.util.List;

public interface ICursoService {

    Curso findById(Integer id);

    List<Curso> findByIdTutor(Integer id);

    List<Curso> findByAlumno(Integer id);

    Curso create(Curso curso);

    Curso changeTituloCurso(Integer idCurso, String titulo);

    Curso update(Curso curso);

    boolean delete(Integer id);

    void removeAlumnoFromCurso(Curso curso, Alumno alumno);

    void addAlumnoFromCurso(Curso curso, Alumno alumno);
}
