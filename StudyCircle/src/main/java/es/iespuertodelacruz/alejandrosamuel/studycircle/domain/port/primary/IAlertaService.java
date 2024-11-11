package es.iespuertodelacruz.alejandrosamuel.studycircle.domain.port.primary;

import java.util.List;

import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.model.AlertaActividad;
import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.model.AlertaAmistad;

public interface IAlertaService {

    List<AlertaActividad> findAlertasActividadByUsername(String username);

    List<AlertaAmistad> findAlertasAmistadByUsername(String usernameUsuario);
}
