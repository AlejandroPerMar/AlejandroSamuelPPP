package es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.service;

import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.model.AlertaCursoAlumno;
import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.port.secondary.IAlertaCursoAlumnoRepository;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.entity.AlertaCursoAlumnoEntity;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.mapper.AlertaCursoAlumnoEntityMapper;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.repository.AlertaCursoAlumnoEntityJPARepository;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.repository.CursoEntityJPARepository;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.repository.UsuarioEntityJPARepository;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.enums.EstadosAlerta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
public class AlertaCursoAlumnoEntityService implements IAlertaCursoAlumnoRepository {

    @Autowired
    private AlertaCursoAlumnoEntityJPARepository repository;

    @Autowired
    private CursoEntityJPARepository cursoRepository;

    @Autowired
    private UsuarioEntityJPARepository usuarioRepository;

    @Autowired
    private AlertaCursoAlumnoEntityMapper alertaCursoAlumnoEntityMapper;

    @Override
    public void create(Integer idUsuarioAlumno, Integer idCurso) {
        AlertaCursoAlumnoEntity alertaCursoAlumnoEntity = new AlertaCursoAlumnoEntity();
        alertaCursoAlumnoEntity.setFechaCreacion(new BigInteger(String.valueOf(new Date().getTime())));
        alertaCursoAlumnoEntity.setCurso(cursoRepository.findById(idCurso).orElse(null));
        alertaCursoAlumnoEntity.setEstado(EstadosAlerta.NEW_ALERT.name());
        alertaCursoAlumnoEntity.setUsuario(usuarioRepository.findById(idUsuarioAlumno).orElse(null));
        repository.save(alertaCursoAlumnoEntity);
    }

    @Override
    public AlertaCursoAlumno findById(Integer idAlertaCursoAlumno) {
        AlertaCursoAlumnoEntity alertaCursoAlumnoEntity = repository.findById(idAlertaCursoAlumno).orElse(null);
        return alertaCursoAlumnoEntityMapper.toDomain(alertaCursoAlumnoEntity);

    }

    @Override
    public List<AlertaCursoAlumno> findAlertasCursoAlumnoByUsername(String username) {
        List<AlertaCursoAlumnoEntity> alertasCursoAlumnoByUsername = repository.findAlertasCursoAlumnoByUsername(username);
        return alertasCursoAlumnoByUsername.stream().map(alertaCursoAlumnoEntityMapper::toDomain).toList();
    }

    @Override
    public void delete(Integer id) {
        AlertaCursoAlumnoEntity alertaCursoAlumnoEntity = repository.findById(id).orElse(null);
        repository.delete(alertaCursoAlumnoEntity);
    }
}
