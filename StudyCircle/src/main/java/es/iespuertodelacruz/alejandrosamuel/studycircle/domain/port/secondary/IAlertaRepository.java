package es.iespuertodelacruz.alejandrosamuel.studycircle.domain.port.secondary;

import java.util.List;

import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.model.AlertaActividad;

public interface IAlertaRepository {

    AlertaActividad findById(Integer id);

    List<AlertaActividad> findByUsername(String username);

    AlertaActividad create(AlertaActividad alerta);

    AlertaActividad update(AlertaActividad alerta);

    boolean delete(Integer id);

    List<AlertaActividad> findAll();
	
}
