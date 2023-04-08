package es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.mapper;

import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.model.Materia;
import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.model.NivelEstudios;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.entity.MateriaEntity;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.entity.NivelEstudiosEntity;

import java.math.BigInteger;
import java.util.Date;
import java.util.stream.Collectors;

public class NivelEstudiosPostEntityMapper {

    public NivelEstudiosEntity toEntity(NivelEstudios in) {
        NivelEstudiosEntity nivelEstudios = new NivelEstudiosEntity();

        nivelEstudios.setFechaCreacion(new BigInteger(new Date().getTime() + ""));
        nivelEstudios.setNombre(in.getNombre());
        if(in.getMaterias() != null)
            nivelEstudios.setMaterias(in.getMaterias().stream().map(this::toEntity).collect(Collectors.toList()));

        return nivelEstudios;
    }

    public NivelEstudios toDomain(NivelEstudiosEntity in) {
        NivelEstudios nivelEstudios = new NivelEstudios();

        nivelEstudios.setId(in.getId());
        nivelEstudios.setNombre(in.getNombre());
        if(in.getMaterias() != null)
            nivelEstudios.setMaterias(in.getMaterias().stream().map(this::toDomain).collect(Collectors.toList()));

        return nivelEstudios;
    }

    public MateriaEntity toEntity(Materia in) {
        MateriaEntity materia = new MateriaEntity();

        materia.setId(in.getId());

        return materia;
    }

    public Materia toDomain(MateriaEntity in) {
        Materia materia = new Materia();

        materia.setId(in.getId());

        return materia;
    }
}
