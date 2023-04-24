package es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.primary.mapper;

import org.springframework.stereotype.Component;

import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.model.Alerta;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.primary.dto.AlertaDTO;

@Component
public class AlertaDTOMapper {
	
    public Alerta toDomain(AlertaDTO in) {
    	Alerta alerta = new Alerta();

    	alerta.setId(in.getId());

        return alerta;
    }

    public Alerta toDomainPost(AlertaDTO in) {
    	Alerta alerta = new Alerta();

    	alerta.setId(in.getId());
    	alerta.setEstado(in.getEstado());

        return alerta;
    }

    public AlertaDTO toDTO(Alerta in) {
    	AlertaDTO alerta = new AlertaDTO();

    	alerta.setId(in.getId());
    	alerta.setEstado(in.getEstado());

        return alerta;
    }
    
}