package es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.mapper;

import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.model.AlertaActividad;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.entity.AlertaActividadEntity;

import java.math.BigInteger;
import java.util.Date;

public class AlertaActividadEntityMapper {

	public AlertaActividad toDomain(AlertaActividadEntity in) {
		AlertaActividad alerta = new AlertaActividad();

		alerta.setId(in.getId());

        return alerta;
    }

    public AlertaActividadEntity toEntityPost(AlertaActividad in) {
    	AlertaActividadEntity alerta = new AlertaActividadEntity();

    	alerta.setFechaCreacion(new BigInteger(String.valueOf(new Date().getTime())));

        return alerta;
    }

    public AlertaActividadEntity toEntityPut(AlertaActividad in) {
    	AlertaActividadEntity alerta = new AlertaActividadEntity();

        return alerta;
    }

    public AlertaActividadEntity toEntity(AlertaActividad in) {
    	AlertaActividadEntity alerta = new AlertaActividadEntity();
        return alerta;
    }

	
}