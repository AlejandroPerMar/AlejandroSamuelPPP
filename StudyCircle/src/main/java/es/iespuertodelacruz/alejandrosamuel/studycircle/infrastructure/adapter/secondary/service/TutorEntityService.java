package es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.service;

import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.model.Materia;
import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.model.MateriaTutor;
import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.model.Tutor;
import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.model.Usuario;
import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.port.secondary.ITutorRepository;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.entity.MateriaTutorEntity;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.entity.TutorEntity;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.mapper.TutorEntityMapper;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.repository.MateriaTutorEntityJPARepository;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.repository.TutorEntityJPARepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TutorEntityService implements ITutorRepository {

    @Autowired
    private TutorEntityJPARepository repository;

    @Autowired
    private MateriaTutorEntityJPARepository materiaRepository;

    @Autowired
    private TutorEntityMapper mapper;

    @Override
    public Tutor create(Usuario usuario, List<Materia> materias) {
        Tutor tutor = new Tutor();
        List<MateriaTutor> materiasTutor = new ArrayList<>();
        materias.forEach(m -> {
            MateriaTutorEntity materiaTutor = new MateriaTutorEntity();
            materiaTutor.setTutor(mapper.toEntity(tutor));
            materiaTutor.setMateria(mapper.toEntity(m));
            materiasTutor.add(mapper.toDomain(materiaRepository.save(materiaTutor)));
        });
        tutor.setUsuario(usuario);
        tutor.setMateriasTutor(materiasTutor);
        TutorEntity savedEntity = repository.save(mapper.toEntityPost(tutor));
        return mapper.toDomain(savedEntity);
    }

    @Override
    @Transactional
    public Tutor update(Tutor tutor, List<Materia> materias) {
        List<MateriaTutor> materiasTutor = tutor.getMateriasTutor();
        List<MateriaTutor> materiasTutorNuevas = new ArrayList<>();
        if(materiasTutor != null) {
            List<Materia> materiasActuales = materiasTutor.stream().map(MateriaTutor::getMateria).toList();
            materias.forEach(m -> {
                if(materiasActuales.contains(m)) {
                    // Agregamos a la lista materiasTutorNuevas la materiaTutor existente en materiasTutor que coincida con la materia sobre la que iteramos
                    materiasTutorNuevas.add(materiasTutor.stream().filter(mt -> mt.getMateria().equals(m)).findFirst().get());
                }else {
                    //Creamos una nueva entidad MateriaTutor y la guardamos en bbdd para agregarla a la  nueva lista del tutor
                    MateriaTutorEntity materiaTutor = new MateriaTutorEntity();
                    materiaTutor.setTutor(mapper.toEntity(tutor));
                    materiaTutor.setMateria(mapper.toEntity(m));
                    materiasTutorNuevas.add(mapper.toDomain(materiaRepository.save(materiaTutor)));
                }
            });
        }
        tutor.setMateriasTutor(materiasTutorNuevas);
        TutorEntity updatedEntity = repository.save(mapper.toEntityPut(tutor));
        return mapper.toDomain(updatedEntity);
    }

    @Override
    public Tutor findTutorById(Integer id) {
        Optional<TutorEntity> optTutor = repository.findById(id);
        return optTutor.map(a -> mapper.toDomain(a)).orElse(null);
    }

    @Override
    public Tutor findTutorByIdUsuario(Integer id) {
        Optional<TutorEntity> optTutor = repository.findByIdUsuario(id);
        return optTutor.map(a -> mapper.toDomain(a)).orElse(null);
    }

    @Override
    public Tutor findTutorByUsername(String username) {
        Optional<TutorEntity> optTutor = repository.findByUsername(username);
        return optTutor.map(a -> mapper.toDomain(a)).orElse(null);
    }
}
