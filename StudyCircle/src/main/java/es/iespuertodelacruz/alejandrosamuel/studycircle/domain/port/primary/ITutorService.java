package es.iespuertodelacruz.alejandrosamuel.studycircle.domain.port.primary;

import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.model.Tutor;

import java.util.List;

public interface ITutorService {

    Tutor create(Tutor tutor);

    Tutor findById(Integer id);

    Tutor findByUsuario();

    List<Tutor> findAll();

}
