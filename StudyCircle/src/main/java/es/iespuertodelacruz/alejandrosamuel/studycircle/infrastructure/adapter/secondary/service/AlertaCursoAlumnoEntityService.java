package es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.service;

import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.port.secondary.IAlertaCursoAlumnoRepository;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.entity.AlertaCursoAlumnoEntity;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.repository.AlertaCursoAlumnoEntityJPARepository;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.repository.CursoEntityJPARepository;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.repository.UsuarioEntityJPARepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;

@Service
public class AlertaCursoAlumnoEntityService implements IAlertaCursoAlumnoRepository {

    @Autowired
    private AlertaCursoAlumnoEntityJPARepository repository;

    @Autowired
    private CursoEntityJPARepository cursoRepository;

    @Autowired
    private UsuarioEntityJPARepository usuarioRepository;

    @Override
    public void create(Integer idUsuarioAlumno, Integer idCurso) {
        AlertaCursoAlumnoEntity alertaCursoAlumnoEntity = new AlertaCursoAlumnoEntity();
        alertaCursoAlumnoEntity.setFechaCreacion(new BigInteger(String.valueOf(new Date())));
        alertaCursoAlumnoEntity.setCurso(cursoRepository.findById(idCurso).orElse(null));
        alertaCursoAlumnoEntity.setUsuario(usuarioRepository.findById(idUsuarioAlumno).orElse(null));
        repository.save(alertaCursoAlumnoEntity);
    }

    @Override
    public AlertaCursoAlumnoEntity findById(Integer idAlertaCursoAlumnoEntity) {
        return repository.findById(idAlertaCursoAlumnoEntity).orElse(null);
    }

    @Override
    public List<AlertaCursoAlumnoEntity> findAlertasCursoAlumnoByUsername(String username) {
        return repository.findAlertasCursoAlumnoByUsername(username);
    }
}
