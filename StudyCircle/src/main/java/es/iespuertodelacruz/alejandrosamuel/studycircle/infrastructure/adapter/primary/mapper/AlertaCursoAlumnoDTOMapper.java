package es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.primary.mapper;

import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.model.AlertaCursoAlumno;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.primary.dto.AlertaCursoAlumnoDTO;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Objects;

public class AlertaCursoAlumnoDTOMapper {
    @Autowired
    private CursoDTOMapper cursoDTOMapper;
    @Autowired
    private UsuarioDTOMapper usuarioDTOMapper;
    public AlertaCursoAlumnoDTO toDTO(AlertaCursoAlumno in) {
        if(Objects.nonNull(in)) return null;

        AlertaCursoAlumnoDTO alertaCursoAlumnoDTO = new AlertaCursoAlumnoDTO();
        alertaCursoAlumnoDTO.setId(in.getId());
        alertaCursoAlumnoDTO.setCurso(cursoDTOMapper.toDTOStudent(in.getCurso()));
        alertaCursoAlumnoDTO.setEstado(in.getEstado());
        alertaCursoAlumnoDTO.setUsuario(usuarioDTOMapper.toDTO(in.getUsuario()));
        return alertaCursoAlumnoDTO;
    }
}
