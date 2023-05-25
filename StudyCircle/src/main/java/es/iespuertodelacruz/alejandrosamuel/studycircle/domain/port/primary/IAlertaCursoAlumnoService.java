package es.iespuertodelacruz.alejandrosamuel.studycircle.domain.port.primary;

import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.entity.AlertaCursoAlumnoEntity;

public interface IAlertaCursoAlumnoService {

    void create(Integer idUsuarioAlumno, Integer idCurso);
    AlertaCursoAlumnoEntity findById(Integer idAlertaCursoAlumnoEntity);
}
