package es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.mapper;

import java.math.BigInteger;
import java.util.Date;

import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.model.Curso;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.entity.CursoEntity;
import org.springframework.stereotype.Component;

import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.model.Actividad;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.entity.ActividadEntity;

@Component
public class ActividadEntityMapper {

	public Actividad toDomain(ActividadEntity in) {
		Actividad actividad = new Actividad();

		actividad.setId(in.getId());
		actividad.setNombre(in.getNombre());
        actividad.setDescripcion(in.getDescripcion());
        actividad.setFechaActividad(in.getFechaActividad());
        actividad.setCurso(toDomain(in.getCurso()));
        actividad.setFechaCreacion(in.getFechaCreacion());

        return actividad;
    }

    public ActividadEntity toEntityPost(Actividad in) {
        ActividadEntity actividad = new ActividadEntity();

        actividad.setFechaCreacion(new BigInteger(String.valueOf(new Date().getTime())));
        actividad.setNombre(in.getNombre());
        actividad.setDescripcion(in.getDescripcion());
        actividad.setFechaActividad(in.getFechaActividad());
        actividad.setCurso(toEntity(in.getCurso()));

        return actividad;
    }

    public ActividadEntity toEntityPut(Actividad in) {
        ActividadEntity actividad = new ActividadEntity();

        actividad.setNombre(in.getNombre());
        actividad.setDescripcion(in.getDescripcion());
        actividad.setFechaActividad(in.getFechaActividad());

        return actividad;
    }

    private Curso toDomain(CursoEntity in) {
        Curso curso = new Curso();
        curso.setId(in.getId());
        return curso;
    }

    private CursoEntity toEntity(Curso in) {
        CursoEntity curso = new CursoEntity();
        curso.setId(in.getId());
        return curso;
    }



    public ActividadEntity toEntity(Actividad in) {
    	ActividadEntity actividad = new ActividadEntity();
        return actividad;
    }
	
}
