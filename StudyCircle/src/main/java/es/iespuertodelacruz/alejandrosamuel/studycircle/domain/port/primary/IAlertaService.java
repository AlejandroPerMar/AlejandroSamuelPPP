package es.iespuertodelacruz.alejandrosamuel.studycircle.domain.port.primary;

import java.util.List;

import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.model.AlertaActividad;

public interface IAlertaService {

    AlertaActividad findById(Integer id);

    List<AlertaActividad> findByUsername(String username);

    AlertaActividad create(AlertaActividad alerta);

    AlertaActividad update(AlertaActividad alerta);

    boolean delete(Integer id);

    List<AlertaActividad> findAll();
	
}
