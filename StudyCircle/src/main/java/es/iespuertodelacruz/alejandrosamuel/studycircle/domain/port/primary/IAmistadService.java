package es.iespuertodelacruz.alejandrosamuel.studycircle.domain.port.primary;

import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.model.Amistad;
import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.model.Usuario;

import java.util.List;

public interface IAmistadService {

    Amistad create(Amistad amistad);

    Amistad accept(Integer idAmistad);

    Amistad findById(Integer id);

    void remove(Integer idAmistad);

    Amistad findByUsuarioId(Integer idUsuario);

    Amistad findAmistadByIds(Integer id1, Integer id2);

    List<Usuario> findAmistadesByIdUsuario(Integer id);

    List<Amistad> findAmistades(Integer id);
}
