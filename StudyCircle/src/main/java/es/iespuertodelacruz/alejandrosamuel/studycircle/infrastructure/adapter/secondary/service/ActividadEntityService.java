package es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.model.Actividad;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.mapper.ActividadEntityMapper;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.entity.ActividadEntity;
import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.port.secondary.IActividadRepository;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.repository.ActividadEntityJPARepository;

@Service
public class ActividadEntityService implements IActividadRepository {

	@Autowired
	private ActividadEntityJPARepository repository;

	@Autowired
	private ActividadEntityMapper mapper;
	
	@Override
	public Actividad findById(Integer id) {
		 Optional<ActividadEntity> optActividad = repository.findById(id);
	        return optActividad.map(m -> mapper.toDomain(m)).orElse(null);
	}

	@Override
	public Actividad create(Actividad actividad) {
        return mapper.toDomain(repository.save(mapper.toEntityPost(actividad)));

	}

	@Override
	public Actividad update(Actividad actividad) {
        return mapper.toDomain(repository.save(mapper.toEntityPut(actividad)));
	}

	@Override
	public boolean delete(Integer id) {
		repository.deleteById(id);
        return findById(id) != null;
	}

	@Override
	public List<Actividad> findAll() {
        return repository.findAll().stream().map(m -> mapper.toDomain(m)).toList();
	}

	/*
	@Override
	public List<Actividad> findByCourse(String name) {
        return repository.findByCourse(name).stream().map(m -> mapper.toDomain(m)).toList(); 
	}
	*/
}
