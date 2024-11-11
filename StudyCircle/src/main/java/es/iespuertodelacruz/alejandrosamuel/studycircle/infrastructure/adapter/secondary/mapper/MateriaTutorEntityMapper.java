package es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.mapper;

import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.model.MateriaTutor;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.entity.MateriaTutorEntity;
import org.springframework.beans.factory.annotation.Autowired;

public class MateriaTutorEntityMapper {

    @Autowired
    private MateriaEntityMapper materiaEntityMapper;

    @Autowired
    private TutorEntityMapper tutorEntityMapper;

    public MateriaTutor toDomain(MateriaTutorEntity in) {
        MateriaTutor materiaTutor = new MateriaTutor();
        materiaTutor.setId(in.getId());
        materiaTutor.setMateria(materiaEntityMapper.toDomain(in.getMateria()));
        materiaTutor.setTutor(tutorEntityMapper.toDomain(in.getTutor()));
        return materiaTutor;
    }
}
