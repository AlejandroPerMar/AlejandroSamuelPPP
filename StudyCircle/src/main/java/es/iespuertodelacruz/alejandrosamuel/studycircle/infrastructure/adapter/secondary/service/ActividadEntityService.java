package es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.service;

import org.springframework.beans.factory.annotation.Autowired;

import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.port.secondary.IActividadRepository;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.repository.ActividadEntityJPARepository;

public class ActividadEntityService implements IActividadRepository {

	@Autowired
	private ActividadEntityJPARepository actividadRepository;
}
