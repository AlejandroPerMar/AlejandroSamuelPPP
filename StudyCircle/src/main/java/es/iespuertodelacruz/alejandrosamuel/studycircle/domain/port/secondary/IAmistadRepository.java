package es.iespuertodelacruz.alejandrosamuel.studycircle.domain.port.secondary;

import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.model.Amistad;

public interface IAmistadRepository {

    Amistad create(Amistad amistad);
    Amistad accept(Amistad amistad);
    Amistad remove(Amistad amistad);
    Amistad findByUsuarioId(Integer idUsuario);

}
