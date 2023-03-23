package es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.service;

import java.math.BigInteger;
import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.model.Alumno;
import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.port.secondary.IAlumnoRepository;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.entity.AlumnoEntity;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.mapper.AlumnoPutEntityMapper;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.repository.AlumnoEntityJPARepository;

@Service
public class AlumnoEntityService implements IAlumnoRepository {
	
	@Autowired
	private AlumnoEntityJPARepository repository;

	@Override
	public Alumno create(Alumno alumno) {
		AlumnoPutEntityMapper mapper = new AlumnoPutEntityMapper();
		alumno.setFechaCreacion(new BigInteger(new Date().getTime() + ""));
		AlumnoEntity savedEntity = repository.save(mapper.toEntity(alumno));
		return mapper.toDomain(savedEntity);
	}

	@Override
	public Alumno update(Alumno alumno) {
		AlumnoPutEntityMapper mapper = new AlumnoPutEntityMapper();
		AlumnoEntity updatedEntity = repository.save(mapper.toEntity(alumno));
		return mapper.toDomain(updatedEntity);
	}

	@Override
	public Alumno findAlumnoById(Integer id) {
		AlumnoPutEntityMapper mapper = new AlumnoPutEntityMapper();
		Optional<AlumnoEntity> foundEntity = repository.findById(id);
		if(foundEntity.isEmpty())
			return null;
		
		return mapper.toDomain(foundEntity.get());
	}

	@Override
	public Alumno findAlumnoByIdUsuario(Integer id) {
		AlumnoPutEntityMapper mapper = new AlumnoPutEntityMapper();
		Optional<AlumnoEntity> foundEntity = repository.findByIdUsuario(id);
		if(foundEntity.isEmpty())
			return null;
		
		return mapper.toDomain(foundEntity.get());
	}

	@Override
	public Alumno findAlumnoByUsername(String username) {
		AlumnoPutEntityMapper mapper = new AlumnoPutEntityMapper();
		Optional<AlumnoEntity> foundEntity = repository.findByUsername(username);
		if(foundEntity.isEmpty())
			return null;
		
		return mapper.toDomain(foundEntity.get());
	}

}
