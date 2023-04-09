package es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.primary.mapper;

import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.model.Materia;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.primary.dto.MateriaDTO;

public class MateriaDTOMapper {

    public Materia toDomain(MateriaDTO in) {
        Materia materia = new Materia();

        materia.setId(in.getId());

        return materia;
    }

    public MateriaDTO toDTO(Materia in) {
        MateriaDTO materia = new MateriaDTO();

        materia.setId(in.getId());

        return materia;
    }

}
