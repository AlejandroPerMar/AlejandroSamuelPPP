package es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.mapper;

import java.math.BigInteger;
import java.util.Date;

import org.springframework.stereotype.Component;

import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.model.Alerta;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.entity.AlertaEntity;

@Component
public class AlertaEntityMapper {

	public Alerta toDomain(AlertaEntity in) {
		Alerta alerta = new Alerta();

		alerta.setId(in.getId());

        return alerta;
    }

    public AlertaEntity toEntityPost(Alerta in) {
    	AlertaEntity alerta = new AlertaEntity();

    	alerta.setFechaCreacion(new BigInteger(new Date().getTime() + ""));

        return alerta;
    }

    public AlertaEntity toEntityPut(Alerta in) {
    	AlertaEntity alerta = new AlertaEntity();

        return alerta;
    }

    public AlertaEntity toEntity(Alerta in) {
    	AlertaEntity alerta = new AlertaEntity();
        return alerta;
    }

	
}