package es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.mapper;

import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.model.AlertaCursoAlumno;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.entity.AlertaCursoAlumnoEntity;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Objects;

public class AlertaCursoAlumnoEntityMapper {

    @Autowired
    private CursoEntityMapper cursoEntityMapper;

    @Autowired
    private UsuarioEntityMapper usuarioEntityMapper;

    public AlertaCursoAlumno toDomain(AlertaCursoAlumnoEntity in) {
        if(Objects.isNull(in)) return null;

        AlertaCursoAlumno alertaCursoAlumno = new AlertaCursoAlumno();
        alertaCursoAlumno.setId(in.getId());
        alertaCursoAlumno.setCurso(cursoEntityMapper.toDomain(in.getCurso()));
        alertaCursoAlumno.setUsuario(usuarioEntityMapper.toDomain(in.getUsuario()));
        alertaCursoAlumno.setEstado(in.getEstado());
        return alertaCursoAlumno;
    }
}
