package es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.mapper;

import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.model.Tutor;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.entity.TutorEntity;

public class TutorEntityMapper {
    public TutorEntity toEntityPost(Tutor in) {
        TutorEntity tutor = new TutorEntity();

        return tutor;
    }

    public Tutor toDomain(TutorEntity in) {
        return null;
    }

    public TutorEntity toEntityPut(Tutor in) {
        return null;
    }
}
