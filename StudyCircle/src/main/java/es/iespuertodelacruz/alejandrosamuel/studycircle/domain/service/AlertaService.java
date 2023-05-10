package es.iespuertodelacruz.alejandrosamuel.studycircle.domain.service;

import java.util.List;

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
	public AlertaActividad findById(Integer id) {
		return repository.findById(id);
	}

	@Override
	public List<AlertaActividad> findByUsername(String username) {
		return repository.findByUsername(username);
	}

	@Override
	public AlertaActividad create(AlertaActividad actividad) {
		return repository.create(actividad);
	}

	@Override
	public AlertaActividad update(AlertaActividad actividad) {
		return repository.update(actividad);
	}

	@Override
	public boolean delete(Integer id) {
		return repository.delete(id);
	}

	@Override
	public List<AlertaActividad> findAll() {
		return repository.findAll();
	}
}
