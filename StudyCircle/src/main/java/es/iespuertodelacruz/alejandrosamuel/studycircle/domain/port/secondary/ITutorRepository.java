package es.iespuertodelacruz.alejandrosamuel.studycircle.domain.port.secondary;

import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.model.Materia;
import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.model.Tutor;

import java.util.List;

public interface ITutorRepository {
    Tutor create(Tutor tutor, List<Materia> materias);

    Tutor update(Tutor tutor);

    Tutor findTutorById(Integer id);

    Tutor findTutorByIdUsuario(Integer id);

    Tutor findTutorByUsername(String username);
}
