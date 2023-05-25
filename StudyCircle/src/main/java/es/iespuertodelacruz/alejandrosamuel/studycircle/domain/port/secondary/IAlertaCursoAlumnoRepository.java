package es.iespuertodelacruz.alejandrosamuel.studycircle.domain.port.secondary;

import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.entity.AlertaCursoAlumnoEntity;

import java.util.List;

public interface IAlertaCursoAlumnoRepository {
    void create(Integer idUsuarioAlumno, Integer idCurso);

    AlertaCursoAlumnoEntity findById(Integer idAlertaCursoAlumnoEntity);

    List<AlertaCursoAlumnoEntity> findAlertasCursoAlumnoByUsername(String username);
}
