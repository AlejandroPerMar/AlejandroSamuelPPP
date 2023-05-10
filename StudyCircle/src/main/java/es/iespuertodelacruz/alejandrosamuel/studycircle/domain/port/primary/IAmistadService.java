package es.iespuertodelacruz.alejandrosamuel.studycircle.domain.port.primary;

import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.model.Amistad;
import org.springframework.stereotype.Service;

@Service
public interface IAmistadService {

    Amistad create(Amistad amistad);

    Amistad accept(Amistad amistad);

    Amistad findById(Integer id);

    Amistad remove(Amistad amistad);

    Amistad findByUsuarioId(Integer idUsuario);

    Amistad findAmistadByIds(Integer id1, Integer id2);
}
