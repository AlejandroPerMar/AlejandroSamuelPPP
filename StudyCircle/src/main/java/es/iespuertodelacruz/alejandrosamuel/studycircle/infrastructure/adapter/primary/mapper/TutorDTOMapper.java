package es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.primary.mapper;

import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.model.Tutor;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.primary.dto.TutorDTO;
import org.springframework.beans.factory.annotation.Autowired;

public class TutorDTOMapper {

    @Autowired
    private DTOJustIdMapper dtoJustIdMapper;

    public Tutor toDomain(TutorDTO in) {
        if(in == null)
            return null;

        Tutor tutor = new Tutor();
        tutor.setMateriasTutor(in.getMateriasTutor().stream().map(dtoJustIdMapper::toDomain).toList());
        tutor.setId(in.getId());
        tutor.setUsuario(dtoJustIdMapper.toDomain(in.getUsuario()));
        return tutor;
    }

    public TutorDTO toDTO(Tutor in) {
        if(in == null)
            return null;

        TutorDTO tutor = new TutorDTO();
        tutor.setMateriasTutor(in.getMateriasTutor().stream().map(dtoJustIdMapper::toDTO).toList());
        tutor.setId(in.getId());
        tutor.setUsuario(dtoJustIdMapper.toDTO(in.getUsuario()));
        return tutor;
    }
}
