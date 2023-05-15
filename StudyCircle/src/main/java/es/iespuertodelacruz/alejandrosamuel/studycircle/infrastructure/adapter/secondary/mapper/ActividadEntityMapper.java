package es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.mapper;

import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.model.Actividad;
import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.model.Curso;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.entity.ActividadEntity;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.entity.CursoEntity;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigInteger;
import java.util.Date;
import java.util.Objects;

public class ActividadEntityMapper {

    @Autowired
    private EntityJustIdMapper entityJustIdMapper;

    @Autowired
    private CursoEntityMapper cursoEntityMapper;

	public Actividad toDomain(ActividadEntity in) {
        if(Objects.isNull(in)) return null;

        Actividad actividad = new Actividad();
		actividad.setId(in.getId());
		actividad.setNombre(in.getNombre());
        actividad.setDescripcion(in.getDescripcion());
        actividad.setFechaActividad(in.getFechaActividad());
        actividad.setCurso(cursoEntityMapper.toDomain(in.getCurso()));
        actividad.setFechaCreacion(in.getFechaCreacion());

        return actividad;
    }

    public ActividadEntity toEntityPost(Actividad in) {
        if(Objects.isNull(in)) return null;

        ActividadEntity actividad = new ActividadEntity();
        actividad.setFechaCreacion(new BigInteger(String.valueOf(new Date().getTime())));
        actividad.setNombre(in.getNombre());
        actividad.setDescripcion(in.getDescripcion());
        actividad.setFechaActividad(in.getFechaActividad());
        actividad.setCurso(entityJustIdMapper.toEntity(in.getCurso()));

        return actividad;
    }

    public ActividadEntity toEntityPut(Actividad in) {
        if(Objects.isNull(in)) return null;

        ActividadEntity actividad = new ActividadEntity();
        actividad.setNombre(in.getNombre());
        actividad.setDescripcion(in.getDescripcion());
        actividad.setFechaActividad(in.getFechaActividad());

        return actividad;
    }

    private Curso toDomain(CursoEntity in) {
        if(Objects.isNull(in)) return null;

        Curso curso = new Curso();
        curso.setId(in.getId());
        curso.setTitulo(in.getTitulo());
        return curso;
    }
	
}
