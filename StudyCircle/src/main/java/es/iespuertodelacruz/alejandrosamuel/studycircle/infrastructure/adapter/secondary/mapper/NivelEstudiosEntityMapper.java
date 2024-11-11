package es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.mapper;

import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.model.Materia;
import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.model.NivelEstudios;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.entity.MateriaEntity;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.entity.NivelEstudiosEntity;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.Objects;
import java.util.stream.Collectors;

public class NivelEstudiosEntityMapper {

    @Autowired
    private EntityJustIdMapper entityJustIdMapper;

    public NivelEstudiosEntity toEntityPost(NivelEstudios in) {
        NivelEstudiosEntity nivelEstudios = new NivelEstudiosEntity();

        nivelEstudios.setFechaCreacion(new BigInteger(String.valueOf(new Date().getTime())));
        nivelEstudios.setNombre(in.getNombre());
        if(Objects.nonNull(in.getMaterias()))
            nivelEstudios.setMaterias(in.getMaterias().stream().map(entityJustIdMapper::toEntity).collect(Collectors.toList()));
        else
            nivelEstudios.setMaterias(new ArrayList<>());

        return nivelEstudios;
    }

    public NivelEstudiosEntity toEntityPut(NivelEstudios in) {
        NivelEstudiosEntity nivelEstudios = new NivelEstudiosEntity();

        nivelEstudios.setNombre(in.getNombre());
        if(Objects.nonNull(in.getMaterias()))
            nivelEstudios.setMaterias(in.getMaterias().stream().map(entityJustIdMapper::toEntity).collect(Collectors.toList()));
        else
            nivelEstudios.setMaterias(new ArrayList<>());

        return nivelEstudios;
    }

    public NivelEstudios toDomain(NivelEstudiosEntity in) {
        NivelEstudios nivelEstudios = new NivelEstudios();

        nivelEstudios.setId(in.getId());
        nivelEstudios.setNombre(in.getNombre());
        if(Objects.nonNull(in.getMaterias()))
            nivelEstudios.setMaterias(in.getMaterias().stream().map(this::toDomain).collect(Collectors.toList()));
        else
            nivelEstudios.setMaterias(new ArrayList<>());

        return nivelEstudios;
    }

    public Materia toDomain(MateriaEntity in) {
        if(Objects.isNull(in)) return null;

        Materia materia = new Materia();
        materia.setId(in.getId());
        materia.setNombre(in.getNombre());
        materia.setNivelEstudios(entityJustIdMapper.toDomain(in.getNivelEstudios()));
        return materia;
    }

}
