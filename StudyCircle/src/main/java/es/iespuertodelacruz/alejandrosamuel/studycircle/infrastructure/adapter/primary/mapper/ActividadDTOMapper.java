package es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.primary.mapper;

import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.model.Curso;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.primary.dto.CursoDTO;
import org.springframework.stereotype.Component;

import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.model.Actividad;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.primary.dto.ActividadDTO;

@Component
public class ActividadDTOMapper {
	
    public Actividad toDomainPut(ActividadDTO in) {
    	Actividad actividad = new Actividad();

        actividad.setId(in.getId());
        actividad.setNombre(in.getNombre());
        actividad.setDescripcion(in.getDescripcion());
        actividad.setFechaActividad(in.getFechaActividad());

        return actividad;
    }

    public Actividad toDomainPost(ActividadDTO in) {
        Actividad actividad = new Actividad();

        actividad.setNombre(in.getNombre());
        actividad.setDescripcion(in.getDescripcion());
        actividad.setFechaActividad(in.getFechaActividad());
        actividad.setCurso(toDomain(in.getCurso()));

        return actividad;
    }

    public ActividadDTO toDTO(Actividad in) {
        ActividadDTO actividad = new ActividadDTO();

        actividad.setId(in.getId());
        actividad.setNombre(in.getNombre());
        actividad.setDescripcion(in.getDescripcion());
        actividad.setCurso(toDTO(in.getCurso()));
        actividad.setFechaActividad(in.getFechaActividad());
        actividad.setFechaCreacion(in.getFechaCreacion());

        return actividad;
    }

    private CursoDTO toDTO(Curso in) {
        CursoDTO curso = new CursoDTO();
        curso.setId(in.getId());
        return curso;
    }

    public Curso toDomain(CursoDTO in) {
        Curso curso = new Curso();
        curso.setId(in.getId());
        return curso;
    }



}
