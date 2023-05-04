package es.iespuertodelacruz.alejandrosamuel.studycircle.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.model.Alumno;
import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.port.primary.IAlumnoService;
import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.port.secondary.IAlumnoRepository;

import javax.transaction.Transactional;

@Service
public class AlumnoService implements IAlumnoService {
	
	@Autowired
	private IAlumnoRepository repository;

	@Override
	@Transactional
	public Alumno create(Alumno alumno) {
		return repository.create(alumno);
	}

	@Override
	@Transactional
	public Alumno update(Alumno alumno) {
		return repository.update(alumno);
	}

	@Override
	public Alumno findAlumnoById(Integer id) {
		return repository.findAlumnoById(id);
	}

	@Override
	public Alumno findAlumnoByIdUsuario(Integer id) {
		return repository.findAlumnoByIdUsuario(id);
	}

	@Override
	public Alumno findAlumnoByUsername(String username) {
		return repository.findAlumnoByUsername(username);
	}

}
