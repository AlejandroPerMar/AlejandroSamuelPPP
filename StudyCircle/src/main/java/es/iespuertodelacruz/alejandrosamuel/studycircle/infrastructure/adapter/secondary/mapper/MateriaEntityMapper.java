package es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.mapper;

import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.model.Materia;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.entity.MateriaEntity;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigInteger;
import java.util.Date;

public class MateriaEntityMapper {

    @Autowired
    private EntityJustIdMapper entityJustIdMapper;

    public Materia toDomain(MateriaEntity in) {
        Materia materia = new Materia();

        materia.setId(in.getId());
        materia.setNombre(in.getNombre());
        materia.setNivelEstudios(entityJustIdMapper.toDomain(in.getNivelEstudios()));

        return materia;
    }

    public MateriaEntity toEntityPost(Materia in) {
        MateriaEntity materia = new MateriaEntity();

        materia.setFechaCreacion(new BigInteger(String.valueOf(new Date().getTime())));
        materia.setNombre(in.getNombre());
        materia.setNivelEstudios(entityJustIdMapper.toEntity(in.getNivelEstudios()));

        return materia;
    }

    public MateriaEntity toEntityPut(Materia in) {
        MateriaEntity materia = new MateriaEntity();

        materia.setNombre(in.getNombre());
        materia.setNivelEstudios(entityJustIdMapper.toEntity(in.getNivelEstudios()));

        return materia;
    }
}
