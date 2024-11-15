package es.iespuertodelacruz.alejandrosamuel.studycircle.domain.port.secondary;

import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.model.NivelEstudios;

import java.util.List;

public interface INivelEstudiosRepository {

    public NivelEstudios findById(Integer id);

    public NivelEstudios findByNombre(String nombre);

    public NivelEstudios create(NivelEstudios nivelEstudios);

    public NivelEstudios update(NivelEstudios nivelEstudios);

    public boolean delete(Integer id);

    public List<NivelEstudios> findAll();
}
