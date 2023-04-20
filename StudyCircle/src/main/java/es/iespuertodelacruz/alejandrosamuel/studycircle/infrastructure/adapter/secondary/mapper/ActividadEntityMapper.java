package es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.mapper;

import java.math.BigInteger;
import java.util.Date;

import org.springframework.stereotype.Component;

import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.model.Actividad;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.entity.ActividadEntity;

@Component
public class ActividadEntityMapper {

	public Actividad toDomain(ActividadEntity in) {
		Actividad actividad = new Actividad();

		actividad.setId(in.getId());
		actividad.setNombre(in.getNombre());
        //actividad.setDescripcion(toDomain(in.getDescripcion()));

        return actividad;
    }

    public ActividadEntity toEntityPost(Actividad in) {
    	ActividadEntity actividad = new ActividadEntity();

    	actividad.setFechaCreacion(new BigInteger(new Date().getTime() + ""));
    	actividad.setNombre(in.getNombre());
       // actividad.setDescripcion(toEntity(in.getDescripcion()));

        return actividad;
    }

    public ActividadEntity toEntityPut(Actividad in) {
    	ActividadEntity actividad = new ActividadEntity();

    	actividad.setNombre(in.getNombre());
       // materia.setDescripcion(toEntity(in.getDescripcion()));

        return actividad;
    }

    public ActividadEntity toEntity(Actividad in) {
    	ActividadEntity actividad = new ActividadEntity();
        return actividad;
    }

    /*
    public NivelEstudiosEntity toEntity(NivelEstudios in) {
        NivelEstudiosEntity nivelEstudios = new NivelEstudiosEntity();
        nivelEstudios.setId(in.getId());
        return nivelEstudios;
    }

    public NivelEstudios toDomain(NivelEstudiosEntity in) {
        NivelEstudios nivelEstudios = new NivelEstudios();
        nivelEstudios.setId(in.getId());
        return nivelEstudios;
    }
    */
	
}
