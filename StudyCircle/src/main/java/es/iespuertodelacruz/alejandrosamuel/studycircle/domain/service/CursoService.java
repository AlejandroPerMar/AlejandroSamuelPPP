package es.iespuertodelacruz.alejandrosamuel.studycircle.domain.service;

import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.model.Alumno;
import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.model.Curso;
import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.port.primary.ICursoService;
import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.port.secondary.ICursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CursoService implements ICursoService {

    @Autowired
    private ICursoRepository repository;

    @Override
    public Curso findById(Integer id) {
        return repository.findById(id);
    }

    @Override
    public List<Curso> findByIdTutor(Integer id) {
        return repository.findByIdTutor(id);
    }

    @Override
    public List<Curso> findByAlumno(Integer id) {
        return repository.findByAlumno(id);
    }

    @Override
    public Curso create(Curso curso) {
        return repository.create(curso);
    }

    @Override
    public Curso changeTituloCurso(Integer idCurso, String titulo) {
        return repository.changeTituloCurso(idCurso, titulo);
    }

    @Override
    public Curso update(Curso curso) {
        return repository.update(curso);
    }

    @Override
    public boolean delete(Integer id) {
        return repository.delete(id);
    }

    @Override
    public void removeAlumnoFromCurso(Curso curso, Alumno alumno) {
        repository.removeAlumnoFromCurso(curso, alumno);
    }

    @Override
    public void addAlumnoToCurso(Curso curso, Alumno alumno) {
        repository.addAlumnoToCurso(curso, alumno);
    }
}
