package es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.service;

import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.model.Curso;
import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.port.secondary.ICursoRepository;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.entity.CursoEntity;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.mapper.CursoEntityMapper;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.repository.CursoEntityJPARepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;

@Service
public class CursoEntityService implements ICursoRepository {

    @Autowired
    private CursoEntityJPARepository repository;

    @Autowired
    private CursoEntityMapper mapper;

    @Override
    public Curso findById(Integer id) {
        return mapper.toDomain(repository.findById(id).orElse(null));
    }

    @Override
    public List<Curso> findByIdTutor(Integer id) {
        return repository.findByIdTutor(id).stream().map(mapper::toDomain).toList();
    }

    @Override
    public List<Curso> findByAlumno(Integer id) {
        return repository.findByAlumno(id).stream().map(mapper::toDomain).toList();
    }

    @Override
    @Transactional
    public Curso create(Curso curso) {
        CursoEntity cursoEntity = new CursoEntity();
        cursoEntity.setTitulo(curso.getTitulo());
        cursoEntity.setFechaCreacion(new BigInteger(String.valueOf(new Date().getTime())));
        cursoEntity.setMateriaTutor(mapper.toEntity(curso.getMateriaTutor()));
        cursoEntity.setAlumnos(curso.getAlumnos().stream().map(mapper::toEntity).toList());
        CursoEntity finalCursoEntity = repository.save(cursoEntity);

        return mapper.toDomain(finalCursoEntity);
    }

    @Override
    public Curso update(Curso curso) {
        return null;
    }

    @Override
    public boolean delete(Integer id) {
        return false;
    }
}
