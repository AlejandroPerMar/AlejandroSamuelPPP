package es.iespuertodelacruz.alejandrosamuel.studycircle.domain.port.primary;

import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.model.NivelEstudios;

import java.util.List;

public interface INivelEstudiosService {

    public NivelEstudios findById(Integer id);

    public NivelEstudios findByNombre(String nombre);

    public NivelEstudios create(NivelEstudios nivelEstudios);

    public NivelEstudios update(NivelEstudios nivelEstudios);

    public List<NivelEstudios> findAll();

}
