package es.iespuertodelacruz.alejandrosamuel.studycircle.domain.service;

import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.model.Materia;
import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.model.Tutor;
import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.model.Usuario;
import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.port.primary.ITutorService;
import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.port.secondary.ITutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TutorService implements ITutorService {

    @Autowired
    private ITutorRepository repository;

    @Override
    public Tutor create(Usuario usuario, List<Materia> materias) {
        return repository.create(usuario, materias);
    }

    @Override
    public Tutor update(Usuario usuario, List<Materia> materias) {
        return repository.update(usuario, materias);
    }

    @Override
    public Tutor findTutorById(Integer id) {
        return repository.findTutorById(id);
    }

    @Override
    public Tutor findTutorByIdUsuario(Integer id) {
        return repository.findTutorByIdUsuario(id);
    }

    @Override
    public Tutor findTutorByUsername(String username) {
        return repository.findTutorByUsername(username);
    }

    @Override
    public Integer countStudents(Tutor tutor) {
        return repository.countStudents(tutor);
    }
}
