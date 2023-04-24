package es.iespuertodelacruz.alejandrosamuel.studycircle.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.model.Alerta;
import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.port.primary.IAlertaService;
import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.port.secondary.IAlertaRepository;

@Service
public class AlertaService implements IAlertaService {


	@Autowired
	private IAlertaRepository repository;
	
	@Override
	public Alerta findById(Integer id) {
		return repository.findById(id);
	}

	@Override
	public Alerta findByType(String tipo) {
		return repository.findByType(tipo);
	}

	@Override
	public Alerta create(Alerta actividad) {
		return repository.create(actividad);
	}

	@Override
	public Alerta update(Alerta actividad) {
		return repository.update(actividad);
	}

	@Override
	public boolean delete(Integer id) {
		return repository.delete(id);
	}

	@Override
	public List<Alerta> findAll() {
		return repository.findAll();
	}
}
