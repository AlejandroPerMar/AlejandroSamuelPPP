package es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.mapper;

import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.model.Materia;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.entity.MateriaEntity;

public class MateriaEntityMapper {
    public Materia toDomain(MateriaEntity in) {
        Materia materia = new Materia();

        materia.setId(in.getId());

        return materia;
    }

    public MateriaEntity toEntity(Materia in) {
        MateriaEntity materia = new MateriaEntity();

        materia.setId(in.getId());

        return materia;
    }
}
