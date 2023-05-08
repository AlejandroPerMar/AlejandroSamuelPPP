package es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.mapper;

import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.model.Actividad;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.entity.ActividadEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigInteger;
import java.util.Date;

@Component
public class ActividadEntityMapper {

    @Autowired
    private EntityJustIdMapper entityJustIdMapper;

	public Actividad toDomain(ActividadEntity in) {
		Actividad actividad = new Actividad();

		actividad.setId(in.getId());
		actividad.setNombre(in.getNombre());
        actividad.setDescripcion(in.getDescripcion());
        actividad.setFechaActividad(in.getFechaActividad());
        actividad.setCurso(entityJustIdMapper.toDomain(in.getCurso()));
        actividad.setFechaCreacion(in.getFechaCreacion());

        return actividad;
    }

    public ActividadEntity toEntityPost(Actividad in) {
        ActividadEntity actividad = new ActividadEntity();

        actividad.setFechaCreacion(new BigInteger(String.valueOf(new Date().getTime())));
        actividad.setNombre(in.getNombre());
        actividad.setDescripcion(in.getDescripcion());
        actividad.setFechaActividad(in.getFechaActividad());
        actividad.setCurso(entityJustIdMapper.toEntity(in.getCurso()));

        return actividad;
    }

    public ActividadEntity toEntityPut(Actividad in) {
        ActividadEntity actividad = new ActividadEntity();

        actividad.setNombre(in.getNombre());
        actividad.setDescripcion(in.getDescripcion());
        actividad.setFechaActividad(in.getFechaActividad());

        return actividad;
    }
	
}
