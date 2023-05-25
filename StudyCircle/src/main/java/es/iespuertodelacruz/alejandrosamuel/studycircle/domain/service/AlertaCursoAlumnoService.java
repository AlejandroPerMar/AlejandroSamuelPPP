package es.iespuertodelacruz.alejandrosamuel.studycircle.domain.service;

import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.port.primary.IAlertaCursoAlumnoService;
import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.port.secondary.IAlertaCursoAlumnoRepository;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.entity.AlertaCursoAlumnoEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AlertaCursoAlumnoService implements IAlertaCursoAlumnoService {


    @Autowired
    private IAlertaCursoAlumnoRepository repository;

    @Override
    public void create(Integer idUsuarioAlumno, Integer idCurso) {
        repository.create(idUsuarioAlumno, idCurso);
    }

    @Override
    public AlertaCursoAlumnoEntity findById(Integer idAlertaCursoAlumnoEntity) {
        return repository.findById(idAlertaCursoAlumnoEntity);
    }
}
