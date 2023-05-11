package es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.mapper;


import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.model.AlertaAmistad;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.entity.AlertaAmistadEntity;
import org.springframework.beans.factory.annotation.Autowired;

public class AlertaAmistadEntityMapper {

    @Autowired
    private AmistadEntityMapper amistadEntityMapper;

	public AlertaAmistad toDomain(AlertaAmistadEntity in) {
		AlertaAmistad alerta = new AlertaAmistad();

		alerta.setId(in.getId());
        alerta.setEstado(in.getEstado());
        alerta.setAmistad(amistadEntityMapper.toDomain(in.getAmistad()));
        return alerta;
    }
	
}