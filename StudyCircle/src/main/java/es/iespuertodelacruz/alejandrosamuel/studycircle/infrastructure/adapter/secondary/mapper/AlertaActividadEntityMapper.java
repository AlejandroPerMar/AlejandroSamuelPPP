package es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.mapper;

import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.model.AlertaActividad;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.entity.AlertaActividadEntity;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Objects;

public class AlertaActividadEntityMapper {

    @Autowired ActividadEntityMapper actividadEntityMapper;

	public AlertaActividad toDomain(AlertaActividadEntity in) {
        if(Objects.isNull(in)) return null;

		AlertaActividad alerta = new AlertaActividad();
		alerta.setId(in.getId());
        alerta.setEstado(in.getEstado());
        alerta.setActividad(actividadEntityMapper.toDomain(in.getActividad()));
        return alerta;
    }
	
}