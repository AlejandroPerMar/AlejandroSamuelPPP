package es.iespuertodelacruz.alejandrosamuel.studycircle.domain.port.secondary;

import java.util.List;

import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.model.AlertaActividad;
import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.model.AlertaAmistad;

public interface IAlertaRepository {

    List<AlertaAmistad> findAlertasAmistadByUsername(String usernameUsuario);

    List<AlertaActividad> findAlertasActividadByUsername(String username);
}
