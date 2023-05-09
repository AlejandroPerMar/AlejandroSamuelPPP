package es.iespuertodelacruz.alejandrosamuel.studycircle.domain.port.primary;

import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.model.Materia;
import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.model.Tutor;

import java.util.List;

public interface IMateriaService {
    Materia findById(Integer id);

    Materia findByNombre(String nombre);

    List<Materia> findByNivelEstudiosId(Integer id);

    List<Materia> findByNivelEstudiosNombre(String nombre);

    Materia create(Materia nivelEstudios);

    Materia update(Materia nivelEstudios);

    boolean delete(Integer id);

    List<Materia> findAll();

    List<Materia> findByTutor(Tutor tutor);
}
