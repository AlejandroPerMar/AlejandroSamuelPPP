package es.iespuertodelacruz.alejandrosamuel.studycircle.domain.port.primary;

import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.model.Anuncio;

import java.util.List;

public interface IAnuncioService {

    Anuncio findById(Integer id);

    List<Anuncio> findAll();

    List<Anuncio> findByIdUsuario(Integer id);

    Anuncio create(Anuncio anuncio);

    void  delete(Integer idAnuncio);

}
