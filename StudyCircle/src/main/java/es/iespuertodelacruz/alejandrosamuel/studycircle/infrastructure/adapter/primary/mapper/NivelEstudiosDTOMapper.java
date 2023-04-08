package es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.primary.mapper;

import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.model.Materia;
import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.model.NivelEstudios;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.primary.dto.MateriaDTO;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.primary.dto.NivelEstudiosDTO;

import java.util.stream.Collectors;

public class NivelEstudiosDTOMapper {

    public NivelEstudios toDomain(NivelEstudiosDTO in) {
        NivelEstudios nivelEstudios = new NivelEstudios();

        nivelEstudios.setId(in.getId());
        nivelEstudios.setFechaCreacion(in.getFechaCreacion());
        nivelEstudios.setNombre(in.getNombre());
        if(in.getMaterias() != null)
            nivelEstudios.setMaterias(in.getMaterias().stream().map(this::toDomain).collect(Collectors.toList()));

        return nivelEstudios;
    }

    public NivelEstudiosDTO toDTO(NivelEstudios in) {
        NivelEstudiosDTO nivelEstudios = new NivelEstudiosDTO();

        nivelEstudios.setId(in.getId());
        nivelEstudios.setFechaCreacion(in.getFechaCreacion());
        nivelEstudios.setNombre(in.getNombre());
        if(in.getMaterias() != null)
            nivelEstudios.setMaterias(in.getMaterias().stream().map(this::toDTO).collect(Collectors.toList()));

        return nivelEstudios;
    }

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
