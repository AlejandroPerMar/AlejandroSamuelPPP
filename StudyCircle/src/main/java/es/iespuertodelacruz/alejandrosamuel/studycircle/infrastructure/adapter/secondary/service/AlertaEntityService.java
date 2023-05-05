package es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.repository.UsuarioEntityJPARepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.model.Alerta;
import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.port.secondary.IAlertaRepository;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.entity.AlertaEntity;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.mapper.AlertaEntityMapper;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.repository.AlertaEntityJPARepository;

@Service
public class AlertaEntityService implements IAlertaRepository {

	@Autowired
	private AlertaEntityJPARepository repository;

	@Autowired
	private UsuarioEntityJPARepository usuarioRepository;

	@Autowired
	private AlertaEntityMapper mapper;
	
	@Override
	public Alerta findById(Integer id) {
		 Optional<AlertaEntity> optActividad = repository.findById(id);
	        return optActividad.map(m -> mapper.toDomain(m)).orElse(null);
	}

	@Override
	public List<Alerta> findByUsername(String username) {
		List<AlertaEntity> alertasUsuario = repository.findByUsuario(username);
		return alertasUsuario.stream().map(mapper::toDomain).collect(Collectors.toList());
	}

	@Override
	public  Alerta findByType(String tipo) {
        Optional<AlertaEntity> optActividad = repository.findByType(tipo);
        return optActividad.map(m -> mapper.toDomain(m)).orElse(null);
	}

	@Override
	public  Alerta create( Alerta actividad) {
        return mapper.toDomain(repository.save(mapper.toEntityPost(actividad)));

	}

	@Override
	public  Alerta update( Alerta actividad) {
        return mapper.toDomain(repository.save(mapper.toEntityPut(actividad)));
	}

	@Override
	public boolean delete(Integer id) {
		repository.deleteById(id);
        return findById(id) != null;
	}

	@Override
	public List<Alerta> findAll() {
        return repository.findAll().stream().map(m -> mapper.toDomain(m)).toList();
	}

}