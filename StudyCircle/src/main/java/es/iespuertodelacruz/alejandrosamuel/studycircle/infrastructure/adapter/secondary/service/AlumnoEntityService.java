package es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.model.Alumno;
import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.port.secondary.IAlumnoRepository;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.entity.AlumnoEntity;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.mapper.AlumnoEntityMapper;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.repository.AlumnoEntityJPARepository;

@Service
public class AlumnoEntityService implements IAlumnoRepository {
	
	@Autowired
	private AlumnoEntityJPARepository repository;

	@Override
	public Alumno create(Alumno alumno) {
		AlumnoEntityMapper mapper = new AlumnoEntityMapper();
		AlumnoEntity savedEntity = repository.save(mapper.toEntity(alumno));
		return mapper.toDomain(savedEntity);
	}

	@Override
	public Alumno update(Alumno alumno) {
		AlumnoEntityMapper mapper = new AlumnoEntityMapper();
		AlumnoEntity updatedEntity = repository.save(mapper.toEntity(alumno));
		return mapper.toDomain(updatedEntity);
	}

	@Override
	public Alumno findAlumnoById(Integer id) {
		AlumnoEntityMapper mapper = new AlumnoEntityMapper();
		Optional<AlumnoEntity> foundEntity = repository.findById(id);
		if(foundEntity.isEmpty())
			return null;
		
		return mapper.toDomain(foundEntity.get());
	}

	@Override
	public Alumno findAlumnoByIdUsuario(Integer id) {
		AlumnoEntityMapper mapper = new AlumnoEntityMapper();
		Optional<AlumnoEntity> foundEntity = repository.findByIdUsuario(id);
		if(foundEntity.isEmpty())
			return null;
		
		return mapper.toDomain(foundEntity.get());
	}

	@Override
	public Alumno findAlumnoByUsername(String username) {
		AlumnoEntityMapper mapper = new AlumnoEntityMapper();
		Optional<AlumnoEntity> foundEntity = repository.findByUsername(username);
		if(foundEntity.isEmpty())
			return null;
		
		return mapper.toDomain(foundEntity.get());
	}

}
