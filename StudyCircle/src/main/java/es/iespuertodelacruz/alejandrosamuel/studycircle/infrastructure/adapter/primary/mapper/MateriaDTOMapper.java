package es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.primary.mapper;

import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.model.Materia;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.primary.dto.MateriaDTO;
import org.springframework.beans.factory.annotation.Autowired;

public class MateriaDTOMapper {

    @Autowired
    private DTOJustIdMapper dtoJustIdMapper;

    @Autowired
    private NivelEstudiosDTOMapper nivelEstudiosMapper;

    public Materia toDomainPost(MateriaDTO in) {
        Materia materia = new Materia();

        materia.setNombre(in.getNombre());
        materia.setNivelEstudios(dtoJustIdMapper.toDomain(in.getNivelEstudios()));

        return materia;
    }

    public MateriaDTO toDTO(Materia in) {
        MateriaDTO materia = new MateriaDTO();

        materia.setId(in.getId());
        materia.setNombre(in.getNombre());
        materia.setNivelEstudios(nivelEstudiosMapper.toDTO(in.getNivelEstudios()));

        return materia;
    }

}
