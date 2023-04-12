package es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.service;

import java.math.BigInteger;
import java.util.Date;
import java.util.Optional;

import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.mapper.AlumnoEntityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.model.Alumno;
import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.port.secondary.IAlumnoRepository;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.entity.AlumnoEntity;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.repository.AlumnoEntityJPARepository;

@Service
public class AlumnoEntityService implements IAlumnoRepository {
	
	@Autowired
	private AlumnoEntityJPARepository repository;

	@Autowired
	private AlumnoEntityMapper mapper;

	@Override
	public Alumno create(Alumno alumno) {
		alumno.setFechaCreacion(new BigInteger(new Date().getTime() + ""));
		AlumnoEntity savedEntity = repository.save(mapper.toEntityPost(alumno));
		return mapper.toDomain(savedEntity);
	}

	@Override
	public Alumno update(Alumno alumno) {
		AlumnoEntity updatedEntity = repository.save(mapper.toEntityPut(alumno));
		return mapper.toDomain(updatedEntity);
	}

	@Override
	public Alumno findAlumnoById(Integer id) {
		Optional<AlumnoEntity> optAlumno = repository.findById(id);
		return optAlumno.map(a -> mapper.toDomain(a)).orElse(null);
	}

	@Override
	public Alumno findAlumnoByIdUsuario(Integer id) {
		Optional<AlumnoEntity> optAlumno = repository.findByIdUsuario(id);
		return optAlumno.map(a -> mapper.toDomain(a)).orElse(null);
	}

	@Override
	public Alumno findAlumnoByUsername(String username) {
		Optional<AlumnoEntity> optAlumno = repository.findByUsername(username);
		return optAlumno.map(a -> mapper.toDomain(a)).orElse(null);
	}

}
