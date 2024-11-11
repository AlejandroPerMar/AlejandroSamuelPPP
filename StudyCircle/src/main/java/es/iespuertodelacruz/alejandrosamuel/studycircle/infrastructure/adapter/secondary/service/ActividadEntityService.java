package es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.service;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.entity.AlertaActividadEntity;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.entity.AlumnoEntity;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.entity.EventoCalendarioEntity;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.repository.AlertaActividadEntityJPARepository;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.repository.CursoEntityJPARepository;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.repository.EventoCalendarioEntityJPARepository;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.enums.EstadosAlerta;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.enums.PerfilUsuario;
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

	@Autowired
	private EventoCalendarioEntityJPARepository eventoCalendarioRepository;
	
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
		alumnosByCurso.forEach(a -> {
			EventoCalendarioEntity eventoCalendarioEntity = new EventoCalendarioEntity();
			eventoCalendarioEntity.setFechaEvento(actividadEntity.getFechaActividad());
			eventoCalendarioEntity.setDescripcion(actividad.getDescripcion());
			eventoCalendarioEntity.setNombre(actividad.getNombre());
			eventoCalendarioEntity.setPerfilUsuario(PerfilUsuario.STUDENT_PROFILE.getPerfilUsuario());
			eventoCalendarioEntity.setFechaCreacion(new BigInteger(String.valueOf(new Date().getTime())));
			eventoCalendarioEntity.setActividad(actividadEntity);
			eventoCalendarioEntity.setUsuario(a.getUsuario());
			eventoCalendarioRepository.save(eventoCalendarioEntity);
		});
		return mapper.toDomain(actividadEntity);

	}

	@Override
	@Transactional
	public Actividad update(Actividad actividad) {
		ActividadEntity actividadEntity = repository.findById(actividad.getId()).orElse(null);
		if(Objects.isNull(actividadEntity)) return null;
		actividadEntity.setNombre(actividad.getNombre());
		actividadEntity.setDescripcion(actividad.getDescripcion());
		actividadEntity.setFechaActividad(actividad.getFechaActividad());
		ActividadEntity finalActividadEntity = repository.save(actividadEntity);
		List<AlertaActividadEntity> alertasActividad = alertaRepository.findByActividad(actividadEntity.getId());
		alertaRepository.deleteAll(alertasActividad);
		List<AlumnoEntity> alumnosByCurso = cursoRepository.findAlumnosByCurso(actividadEntity.getCurso().getId());
		alumnosByCurso.forEach(a -> {
			AlertaActividadEntity alertaActividadEntity = new AlertaActividadEntity();
			alertaActividadEntity.setFechaCreacion(new BigInteger(String.valueOf(new Date().getTime())));
			alertaActividadEntity.setActividad(finalActividadEntity);
			alertaActividadEntity.setEstado(EstadosAlerta.NEW_ALERT.name());
			alertaActividadEntity.setUsuario(a.getUsuario());
			alertaRepository.save(alertaActividadEntity);
		});
		List<EventoCalendarioEntity> eventosCalendarioEntity = eventoCalendarioRepository.findByActividad(actividadEntity.getId());
		eventoCalendarioRepository.deleteAll(eventosCalendarioEntity);
		alumnosByCurso.forEach(a -> {
			EventoCalendarioEntity eventoCalendarioEntity = new EventoCalendarioEntity();
			eventoCalendarioEntity.setFechaEvento(actividadEntity.getFechaActividad());
			eventoCalendarioEntity.setDescripcion(actividad.getDescripcion());
			eventoCalendarioEntity.setNombre(actividad.getNombre());
			eventoCalendarioEntity.setPerfilUsuario(PerfilUsuario.STUDENT_PROFILE.getPerfilUsuario());
			eventoCalendarioEntity.setFechaCreacion(new BigInteger(String.valueOf(new Date().getTime())));
			eventoCalendarioEntity.setActividad(finalActividadEntity);
			eventoCalendarioEntity.setUsuario(a.getUsuario());
			eventoCalendarioRepository.save(eventoCalendarioEntity);
		});
		return mapper.toDomain(finalActividadEntity);
	}

	@Override
	@Transactional
	public boolean delete(Integer id) {
		List<AlertaActividadEntity> alertasActividades = alertaRepository.findByActividad(id);
		alertaRepository.deleteAll(alertasActividades);
		List<EventoCalendarioEntity> eventosCalendarioEntity = eventoCalendarioRepository.findByActividad(id);
		eventoCalendarioRepository.deleteAll(eventosCalendarioEntity);
		repository.deleteById(id);
        return Objects.isNull(findById(id));
	}

	@Override
	public List<Actividad> findAll() {
        return repository.findAll().stream().map(m -> mapper.toDomain(m)).toList();
	}

	@Override
	public Integer getNumeroActividadesPendientes(Integer idAlumno) {
		return repository.getNumeroActividadesPendientes(idAlumno, new Date().getTime());
	}

}
