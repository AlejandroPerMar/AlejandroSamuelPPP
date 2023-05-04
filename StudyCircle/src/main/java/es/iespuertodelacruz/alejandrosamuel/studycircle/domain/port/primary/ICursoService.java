package es.iespuertodelacruz.alejandrosamuel.studycircle.domain.port.primary;


import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.model.Curso;

import java.util.List;

public interface ICursoService {

    Curso findById(Integer id);

    List<Curso> findByIdTutor(Integer id);

    List<Curso> findByAlumno(Integer id);

    Curso create(Curso curso);

    Curso update(Curso curso);

    boolean delete(Integer id);

}
