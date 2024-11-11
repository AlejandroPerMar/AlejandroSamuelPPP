package es.iespuertodelacruz.alejandrosamuel.studycircle.domain.port.primary;

import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.model.AlertaCursoAlumno;

import java.util.List;

public interface IAlertaCursoAlumnoService {

    void create(Integer idUsuarioAlumno, Integer idCurso);
    AlertaCursoAlumno findById(Integer idAlertaCursoAlumnoEntity);

    List<AlertaCursoAlumno> findAlertasCursoAlumnoByUsername(String username);

    void delete(Integer id);
}
