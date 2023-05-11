package es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.service;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.entity.AlertaActividadEntity;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.repository.AlertaActividadEntityJPARepository;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.enums.EstadosAlerta;
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

	@Autowired
	private AlertaActividadEntityJPARepository alertaRepository;
	
	@Override
	public Actividad findById(Integer id) {
		 ActividadEntity actividad = repository.findById(id).orElse(null);
		 return (Objects.nonNull(actividad)) ? mapper.toDomain(actividad) : null;
	}

	@Override
	public Actividad create(Actividad actividad) {
		ActividadEntity actividadEntity = repository.save(mapper.toEntityPost(actividad));
		ActividadEntity finalActividadEntity = repository.findByIdWithCurso(actividadEntity.getId()).orElse(null);
		Objects.requireNonNull(finalActividadEntity).getCurso().getAlumnos().forEach(a -> {
			AlertaActividadEntity alertaActividadEntity = new AlertaActividadEntity();
			alertaActividadEntity.setFechaCreacion(new BigInteger(String.valueOf(new Date().getTime())));
			alertaActividadEntity.setActividad(actividadEntity);
			alertaActividadEntity.setEstado(EstadosAlerta.NEW_ALERT.name());
			alertaActividadEntity.setUsuario(a.getUsuario());
			alertaRepository.save(alertaActividadEntity);
		});
		return mapper.toDomain(actividadEntity);

	}

	@Override
	public Actividad update(Actividad actividad) {
        return mapper.toDomain(repository.save(mapper.toEntityPut(actividad)));
	}

	@Override
	public boolean delete(Integer id) {
		repository.deleteById(id);
        return Objects.nonNull(findById(id));
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
