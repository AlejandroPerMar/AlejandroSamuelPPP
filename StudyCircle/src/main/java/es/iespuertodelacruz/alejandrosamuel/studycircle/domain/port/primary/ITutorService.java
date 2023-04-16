package es.iespuertodelacruz.alejandrosamuel.studycircle.domain.port.primary;

import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.model.Materia;
import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.model.Tutor;
import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.model.Usuario;

import java.util.List;

public interface ITutorService {

    Tutor create(Usuario usuario, List<Materia> materias);

    Tutor update(Tutor tutor, List<Materia> materias);

    Tutor findTutorById(Integer id);

    Tutor findTutorByIdUsuario(Integer id);

    Tutor findTutorByUsername(String username);

}
