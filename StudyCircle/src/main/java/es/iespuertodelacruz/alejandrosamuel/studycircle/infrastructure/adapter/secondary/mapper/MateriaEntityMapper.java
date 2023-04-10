package es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.mapper;

import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.model.Materia;
import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.model.NivelEstudios;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.entity.MateriaEntity;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.entity.NivelEstudiosEntity;

import java.math.BigInteger;
import java.util.Date;

public class MateriaEntityMapper {
    public Materia toDomain(MateriaEntity in) {
        Materia materia = new Materia();

        materia.setId(in.getId());
        materia.setNombre(in.getNombre());
        materia.setNivelEstudios(toDomain(in.getNivelEstudios()));

        return materia;
    }

    public MateriaEntity toEntityPost(Materia in) {
        MateriaEntity materia = new MateriaEntity();

        materia.setFechaCreacion(new BigInteger(new Date().getTime() + ""));
        materia.setNombre(in.getNombre());
        materia.setNivelEstudios(toEntity(in.getNivelEstudios()));

        return materia;
    }

    public MateriaEntity toEntityPut(Materia in) {
        MateriaEntity materia = new MateriaEntity();

        materia.setNombre(in.getNombre());
        materia.setNivelEstudios(toEntity(in.getNivelEstudios()));

        return materia;
    }

    public MateriaEntity toEntity(Materia in) {
        MateriaEntity materia = new MateriaEntity();
        return materia;
    }

    public NivelEstudiosEntity toEntity(NivelEstudios in) {
        NivelEstudiosEntity nivelEstudios = new NivelEstudiosEntity();
        nivelEstudios.setId(in.getId());
        return nivelEstudios;
    }

    public NivelEstudios toDomain(NivelEstudiosEntity in) {
        NivelEstudios nivelEstudios = new NivelEstudios();
        nivelEstudios.setId(in.getId());
        return nivelEstudios;
    }
}
