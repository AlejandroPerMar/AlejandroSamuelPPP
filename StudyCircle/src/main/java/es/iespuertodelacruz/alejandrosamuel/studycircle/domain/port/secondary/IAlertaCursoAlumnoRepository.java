package es.iespuertodelacruz.alejandrosamuel.studycircle.domain.port.secondary;

import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.model.AlertaCursoAlumno;

import java.util.List;

public interface IAlertaCursoAlumnoRepository {
    void create(Integer idUsuarioAlumno, Integer idCurso);

    AlertaCursoAlumno findById(Integer idAlertaCursoAlumno);

    List<AlertaCursoAlumno> findAlertasCursoAlumnoByUsername(String username);

    void delete(Integer id);
}
