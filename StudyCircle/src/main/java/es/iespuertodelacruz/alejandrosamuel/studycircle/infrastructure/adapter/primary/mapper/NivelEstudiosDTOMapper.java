package es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.primary.mapper;

import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.model.Materia;
import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.model.NivelEstudios;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.primary.dto.MateriaDTO;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.primary.dto.NivelEstudiosDTO;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Objects;

public class NivelEstudiosDTOMapper {

    @Autowired
    private DTOJustIdMapper dtoJustIdMapper;

    public NivelEstudios toDomain(NivelEstudiosDTO in) {
        if(Objects.isNull(in)) return null;

        NivelEstudios nivelEstudios = new NivelEstudios();
        nivelEstudios.setNombre(in.getNombre());
        if(Objects.nonNull(in.getMaterias()))
            nivelEstudios.setMaterias(in.getMaterias().stream().map(dtoJustIdMapper::toDomain).toList());
        return nivelEstudios;
    }

    public NivelEstudiosDTO toDTO(NivelEstudios in) {
        if(Objects.isNull(in)) return null;

        NivelEstudiosDTO nivelEstudios = new NivelEstudiosDTO();
        nivelEstudios.setId(in.getId());
        nivelEstudios.setFechaCreacion(in.getFechaCreacion());
        nivelEstudios.setNombre(in.getNombre());
        if(Objects.nonNull(in.getMaterias()))
            nivelEstudios.setMaterias(in.getMaterias().stream().map(this::toDTO).toList());

        return nivelEstudios;
    }

    public MateriaDTO toDTO(Materia in) {
        if(Objects.isNull(in)) return null;

        MateriaDTO materia = new MateriaDTO();
        materia.setId(in.getId());
        materia.setNombre(in.getNombre());
        materia.setNivelEstudios(dtoJustIdMapper.toDTO(in.getNivelEstudios()));
        return materia;
    }

}
