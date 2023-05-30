package es.iespuertodelacruz.alejandrosamuel.studycircle.domain.service;

import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.model.AlertaCursoAlumno;
import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.port.primary.IAlertaCursoAlumnoService;
import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.port.secondary.IAlertaCursoAlumnoRepository;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.entity.AlertaCursoAlumnoEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlertaCursoAlumnoService implements IAlertaCursoAlumnoService {


    @Autowired
    private IAlertaCursoAlumnoRepository repository;

    @Override
    public void create(Integer idUsuarioAlumno, Integer idCurso) {
        repository.create(idUsuarioAlumno, idCurso);
    }

    @Override
    public AlertaCursoAlumno findById(Integer idAlertaCursoAlumno) {
        return repository.findById(idAlertaCursoAlumno);
    }

    @Override
    public List<AlertaCursoAlumno> findAlertasCursoAlumnoByUsername(String username) {
        return repository.findAlertasCursoAlumnoByUsername(username);
    }
}
