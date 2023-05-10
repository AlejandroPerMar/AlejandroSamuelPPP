package es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.mapper.AlertaActividadEntityMapper;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.repository.UsuarioEntityJPARepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.model.AlertaActividad;
import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.port.secondary.IAlertaRepository;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.entity.AlertaActividadEntity;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.repository.AlertaActividadEntityJPARepository;

@Service
public class AlertaEntityService implements IAlertaRepository {

	@Autowired
	private AlertaActividadEntityJPARepository repository;

	@Autowired
	private UsuarioEntityJPARepository usuarioRepository;

	@Autowired
	private AlertaActividadEntityMapper mapper;
	
	@Override
	public AlertaActividad findById(Integer id) {
		 Optional<AlertaActividadEntity> optActividad = repository.findById(id);
	        return optActividad.map(m -> mapper.toDomain(m)).orElse(null);
	}

	@Override
	public List<AlertaActividad> findByUsername(String username) {
		List<AlertaActividadEntity> alertasUsuario = repository.findByUsuario(username);
		return alertasUsuario.stream().map(mapper::toDomain).collect(Collectors.toList());
	}

	@Override
	public AlertaActividad create(AlertaActividad actividad) {
        return mapper.toDomain(repository.save(mapper.toEntityPost(actividad)));

	}

	@Override
	public AlertaActividad update(AlertaActividad actividad) {
        return mapper.toDomain(repository.save(mapper.toEntityPut(actividad)));
	}

	@Override
	public boolean delete(Integer id) {
		repository.deleteById(id);
        return findById(id) != null;
	}

	@Override
	public List<AlertaActividad> findAll() {
        return repository.findAll().stream().map(m -> mapper.toDomain(m)).toList();
	}

}