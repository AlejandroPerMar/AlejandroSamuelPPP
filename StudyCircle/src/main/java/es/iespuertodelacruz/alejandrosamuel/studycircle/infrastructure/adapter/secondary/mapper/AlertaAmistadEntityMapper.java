package es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.mapper;

import java.math.BigInteger;
import java.util.Date;

import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.model.AlertaAmistad;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.entity.AlertaAmistadEntity;

public class AlertaAmistadEntityMapper {

	public AlertaAmistad toDomain(AlertaAmistadEntity in) {
		AlertaAmistad alerta = new AlertaAmistad();

		alerta.setId(in.getId());

        return alerta;
    }

    public AlertaAmistadEntity toEntityPost(AlertaAmistad in) {
    	AlertaAmistadEntity alerta = new AlertaAmistadEntity();

    	alerta.setFechaCreacion(new BigInteger(new Date().getTime() + ""));

        return alerta;
    }

    public AlertaAmistadEntity toEntityPut(AlertaAmistad in) {
    	AlertaAmistadEntity alerta = new AlertaAmistadEntity();

        alerta.setFechaCreacion(new BigInteger(String.valueOf(new Date().getTime())));
        return alerta;
    }

    public AlertaAmistadEntity toEntity(AlertaAmistad in) {
    	AlertaAmistadEntity alerta = new AlertaAmistadEntity();
        return alerta;
    }

	
}