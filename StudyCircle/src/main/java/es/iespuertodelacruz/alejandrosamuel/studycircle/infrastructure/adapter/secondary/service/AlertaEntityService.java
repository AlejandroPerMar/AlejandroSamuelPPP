package es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.service;

import java.util.List;
import java.util.stream.Collectors;

import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.model.AlertaAmistad;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.entity.AlertaAmistadEntity;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.mapper.AlertaActividadEntityMapper;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.mapper.AlertaAmistadEntityMapper;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.repository.AlertaAmistadEntityJPARepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.model.AlertaActividad;
import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.port.secondary.IAlertaRepository;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.entity.AlertaActividadEntity;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.repository.AlertaActividadEntityJPARepository;

@Service
public class AlertaEntityService implements IAlertaRepository {

	@Autowired
	private AlertaActividadEntityJPARepository alertaActividadRepository;

	@Autowired
	private AlertaAmistadEntityJPARepository alertaAmistadRepository;

	@Autowired
	private AlertaActividadEntityMapper alertaActividadMapper;

	@Autowired
	private AlertaAmistadEntityMapper alertaAmistadMapper;

	@Override
	public List<AlertaAmistad> findAlertasAmistadByUsername(String usernameUsuario) {
		List<AlertaAmistadEntity> alertasUsuario = alertaAmistadRepository.findAlertasAmistadByUsuario(usernameUsuario);
		return alertasUsuario.stream().map(alertaAmistadMapper::toDomain).collect(Collectors.toList());
	}

	@Override
	public List<AlertaActividad> findAlertasActividadByUsername(String username) {
		List<AlertaActividadEntity> alertasUsuario = alertaActividadRepository.findAlertasActividadByUsuario(username);
		return alertasUsuario.stream().map(alertaActividadMapper::toDomain).collect(Collectors.toList());
	}

}