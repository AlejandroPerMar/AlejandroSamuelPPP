package es.iespuertodelacruz.alejandrosamuel.studycircle.domain.port.primary;

import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.model.NivelEstudios;

import java.util.List;

public interface INivelEstudiosService {

    NivelEstudios findById(Integer id);

    NivelEstudios findByNombre(String nombre);

    NivelEstudios create(NivelEstudios nivelEstudios);

    NivelEstudios update(NivelEstudios nivelEstudios);

    boolean delete(Integer id);

    List<NivelEstudios> findAll();

}
