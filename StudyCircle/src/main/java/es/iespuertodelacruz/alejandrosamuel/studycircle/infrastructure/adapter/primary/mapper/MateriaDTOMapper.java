package es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.primary.mapper;

import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.model.Materia;
import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.model.NivelEstudios;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.primary.dto.MateriaDTO;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.primary.dto.NivelEstudiosDTO;

public class MateriaDTOMapper {

    public Materia toDomain(MateriaDTO in) {
        Materia materia = new Materia();

        materia.setId(in.getId());

        return materia;
    }

    public Materia toDomainPost(MateriaDTO in) {
        Materia materia = new Materia();

        materia.setNombre(in.getNombre());
        materia.setNivelEstudios(toDomain(in.getNivelEstudios()));

        return materia;
    }

    public MateriaDTO toDTO(Materia in) {
        MateriaDTO materia = new MateriaDTO();

        materia.setId(in.getId());
        materia.setNombre(in.getNombre());
        materia.setNivelEstudios(toDTO(in.getNivelEstudios()));

        return materia;
    }

    public NivelEstudios toDomain(NivelEstudiosDTO in) {
        NivelEstudios nivelEstudios = new NivelEstudios();
        nivelEstudios.setId(in.getId());
        return nivelEstudios;
    }

    public NivelEstudiosDTO toDTO(NivelEstudios in) {
        NivelEstudiosDTO nivelEstudios = new NivelEstudiosDTO();
        nivelEstudios.setId(in.getId());
        return nivelEstudios;
    }
}
