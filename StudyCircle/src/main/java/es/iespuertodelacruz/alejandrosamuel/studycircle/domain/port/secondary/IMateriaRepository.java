package es.iespuertodelacruz.alejandrosamuel.studycircle.domain.port.secondary;

import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.model.Materia;

import java.util.List;

public interface IMateriaRepository {
    public Materia findById(Integer id);

    public Materia findByNombre(String nombre);

    public Materia create(Materia nivelEstudios);

    public Materia update(Materia nivelEstudios);

    public boolean delete(Integer id);

    public List<Materia> findAll();
}
