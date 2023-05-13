package es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.service;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.entity.AlertaActividadEntity;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.entity.AlumnoEntity;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.repository.AlertaActividadEntityJPARepository;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.repository.CursoEntityJPARepository;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.enums.EstadosAlerta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.model.Actividad;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.mapper.ActividadEntityMapper;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.entity.ActividadEntity;
import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.port.secondary.IActividadRepository;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.repository.ActividadEntityJPARepository;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ActividadEntityService implements IActividadRepository {

	@Autowired
	private ActividadEntityJPARepository repository;

	@Autowired
	private CursoEntityJPARepository cursoRepository;

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
	@Transactional
	public Actividad create(Actividad actividad) {
		ActividadEntity actividadEntity = repository.save(mapper.toEntityPost(actividad));
		List<AlumnoEntity> alumnosByCurso = cursoRepository.findAlumnosByCurso(actividadEntity.getCurso().getId());
		alumnosByCurso.forEach(a -> {
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
		List<AlertaActividadEntity> alertasActividades = alertaRepository.findByActividad(id);
		alertaRepository.deleteAll(alertasActividades);
		repository.deleteById(id);
        return Objects.isNull(findById(id));
	}

	@Override
	public List<Actividad> findAll() {
        return repository.findAll().stream().map(m -> mapper.toDomain(m)).toList();
	}

}
