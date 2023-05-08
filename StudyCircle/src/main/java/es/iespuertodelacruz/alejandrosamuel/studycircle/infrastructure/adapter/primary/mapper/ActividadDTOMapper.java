package es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.primary.mapper;

import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.model.Actividad;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.primary.dto.ActividadDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ActividadDTOMapper {

    @Autowired
    private DTOJustIdMapper dtoJustIdMapper;
	
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
        actividad.setCurso(dtoJustIdMapper.toDomain(in.getCurso()));

        return actividad;
    }

    public ActividadDTO toDTO(Actividad in) {
        ActividadDTO actividad = new ActividadDTO();

        actividad.setId(in.getId());
        actividad.setNombre(in.getNombre());
        actividad.setDescripcion(in.getDescripcion());
        actividad.setCurso(dtoJustIdMapper.toDTO((in.getCurso())));
        actividad.setFechaActividad(in.getFechaActividad());
        actividad.setFechaCreacion(in.getFechaCreacion());

        return actividad;
    }

}
