package es.iespuertodelacruz.alejandrosamuel.studycircle.domain.service;

import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.model.Materia;
import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.port.primary.IMateriaService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MateriaService implements IMateriaService {

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
