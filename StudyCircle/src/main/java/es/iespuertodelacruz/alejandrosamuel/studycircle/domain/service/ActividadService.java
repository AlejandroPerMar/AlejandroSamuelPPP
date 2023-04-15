package es.iespuertodelacruz.alejandrosamuel.studycircle.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.model.Actividad;
import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.port.primary.IActividadService;
import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.port.secondary.IActividadRepository;

public class ActividadService implements IActividadService {

	@Autowired
	private IActividadRepository repository;

	@Override
	public Actividad findById(Integer id) {
		return repository.findById(id);
	}

	@Override
	public Actividad findByNombre(String nombre) {
		return repository.findByNombre(nombre);
	}

	@Override
	public Actividad create(Actividad actividad) {
		return repository.create(actividad);
	}

	@Override
	public Actividad update(Actividad actividad) {
		return repository.update(actividad);
	}

	@Override
	public boolean delete(Integer id) {
		return repository.delete(id);
	}

	@Override
	public List<Actividad> findAll() {
		return repository.findAll();
	}

	@Override
	public List<Actividad> findByCourse(String name) {
		return repository.findByCourse(name);
	}

}
