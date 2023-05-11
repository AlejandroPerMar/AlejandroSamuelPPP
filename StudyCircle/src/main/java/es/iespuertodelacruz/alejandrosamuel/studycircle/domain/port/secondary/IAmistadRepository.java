package es.iespuertodelacruz.alejandrosamuel.studycircle.domain.port.secondary;

import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.model.Amistad;
import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.model.Usuario;

import java.util.List;

public interface IAmistadRepository {

    Amistad create(Amistad amistad);

    Amistad accept(Integer idAmistad, Integer idAlertaAmistad);

    Amistad findById(Integer id);

    Amistad remove(Amistad amistad);

    Amistad findByUsuarioId(Integer idUsuario);

    Amistad findAmistadByIds(Integer id1, Integer id2);

    List<Usuario> findAmistadesById(Integer id);

    Amistad reject(Integer idAmistad, Integer idAlertaAmistad);
}
