package es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.primary.mapper;

import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.model.NivelEstudios;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.primary.dto.NivelEstudiosDTO;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.stream.Collectors;

public class NivelEstudiosDTOMapper {

    @Autowired
    private DTOJustIdMapper dtoJustIdMapper;

    public NivelEstudios toDomain(NivelEstudiosDTO in) {
        NivelEstudios nivelEstudios = new NivelEstudios();

        nivelEstudios.setId(in.getId());
        nivelEstudios.setFechaCreacion(in.getFechaCreacion());
        nivelEstudios.setNombre(in.getNombre());
        if(in.getMaterias() != null)
            nivelEstudios.setMaterias(in.getMaterias().stream().map(dtoJustIdMapper::toDomain).collect(Collectors.toList()));

        return nivelEstudios;
    }

    public NivelEstudiosDTO toDTO(NivelEstudios in) {
        NivelEstudiosDTO nivelEstudios = new NivelEstudiosDTO();

        nivelEstudios.setId(in.getId());
        nivelEstudios.setFechaCreacion(in.getFechaCreacion());
        nivelEstudios.setNombre(in.getNombre());
        if(in.getMaterias() != null)
            nivelEstudios.setMaterias(in.getMaterias().stream().map(dtoJustIdMapper::toDTO).collect(Collectors.toList()));

        return nivelEstudios;
    }

}
