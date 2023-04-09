package es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.service;

import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.model.Materia;
import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.port.secondary.IMateriaRepository;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.repository.MateriaEntityJPARepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MateriaEntityService implements IMateriaRepository {

    @Autowired
    private MateriaEntityJPARepository repository;


    @Override
    public Materia findById(Integer id) {
        return null;
    }

    @Override
    public Materia findByNombre(String nombre) {
        return null;
    }

    @Override
    public Materia create(Materia nivelEstudios) {
        return null;
    }

    @Override
    public Materia update(Materia nivelEstudios) {
        return null;
    }

    @Override
    public boolean delete(Integer id) {
        return false;
    }

    @Override
    public List<Materia> findAll() {
        return null;
    }
}
