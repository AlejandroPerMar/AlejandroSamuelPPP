package es.iespuertodelacruz.alejandrosamuel.studycircle.domain.port.secondary;

import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.model.Anuncio;

import java.util.List;

public interface IAnuncioRepository {

    Anuncio findById(Integer id);

    List<Anuncio> findAll();

    Anuncio create(Anuncio anuncio);

    List<Anuncio> findByIdUsuario(Integer id);
}
