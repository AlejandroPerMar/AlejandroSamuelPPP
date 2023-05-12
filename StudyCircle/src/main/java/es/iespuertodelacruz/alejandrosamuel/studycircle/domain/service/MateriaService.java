package es.iespuertodelacruz.alejandrosamuel.studycircle.domain.service;

import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.model.Materia;
import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.port.primary.IMateriaService;
import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.port.secondary.IMateriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MateriaService implements IMateriaService {

    @Autowired
    public IMateriaRepository repository;

    @Override
    public Materia findById(Integer id) {
        return repository.findById(id);
    }

    @Override
    public Materia findByNombre(String nombre) {
        return repository.findByNombre(nombre);
    }

    @Override
    public List<Materia> findByNivelEstudiosId(Integer id) {
        return repository.findByNivelEstudiosId(id);
    }

    @Override
    public List<Materia> findByNivelEstudiosNombre(String nombre) {
        return repository.findByNivelEstudiosNombre(nombre);
    }

    @Override
    public Materia create(Materia materia) {
        return repository.create(materia);
    }

    @Override
    public Materia update(Materia materia) {
        return repository.update(materia);
    }

    @Override
    public boolean delete(Integer id) {
        return repository.delete(id);
    }

    @Override
    public List<Materia> findAll() {
        return repository.findAll();
    }

    @Override
    public List<Materia> findByTutor(Integer idTutor) {
        return repository.findByTutor(idTutor);
    }
}
