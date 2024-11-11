package es.iespuertodelacruz.alejandrosamuel.studycircle.domain.service;

import java.util.List;

import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.model.AlertaAmistad;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.model.AlertaActividad;
import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.port.primary.IAlertaService;
import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.port.secondary.IAlertaRepository;

@Service
public class AlertaService implements IAlertaService {


	@Autowired
	private IAlertaRepository repository;

	@Override
	public List<AlertaActividad> findAlertasActividadByUsername(String username) {
		return repository.findAlertasActividadByUsername(username);
	}

	@Override
	public List<AlertaAmistad> findAlertasAmistadByUsername(String usernameUsuario) {
		return repository.findAlertasAmistadByUsername(usernameUsuario);
	}
}
