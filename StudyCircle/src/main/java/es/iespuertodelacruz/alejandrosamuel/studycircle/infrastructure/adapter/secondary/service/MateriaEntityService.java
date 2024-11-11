package es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.service;

import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.model.Materia;
import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.port.secondary.IMateriaRepository;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.entity.MateriaEntity;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.mapper.MateriaEntityMapper;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.repository.MateriaEntityJPARepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class MateriaEntityService implements IMateriaRepository {

    @Autowired
    private MateriaEntityJPARepository repository;

    @Autowired
    private MateriaEntityMapper mapper;


    @Override
    public Materia findById(Integer id) {
        Optional<MateriaEntity> optMateria = repository.findById(id);
        return optMateria.map(mapper::toDomain).orElse(null);
    }

    @Override
    public Materia findByNombre(String nombre) {
        Optional<MateriaEntity> optMateria = repository.findByNombre(nombre);
        return optMateria.map(mapper::toDomain).orElse(null);
    }

    @Override
    public List<Materia> findByNivelEstudiosId(Integer id) {
        return repository.findByNivelEstudiosId(id).stream().map(mapper::toDomain).toList();
    }

    @Override
    public List<Materia> findByNivelEstudiosNombre(String nombre) {
        return repository.findByNivelEstudiosNombre(nombre).stream().map(mapper::toDomain).toList();
    }

    @Override
    public Materia create(Materia materia) {
        return mapper.toDomain(repository.save(mapper.toEntityPost(materia)));
    }

    @Override
    public Materia update(Materia materia) {
        return mapper.toDomain(repository.save(mapper.toEntityPut(materia)));
    }

    @Override
    public boolean delete(Integer id) {
        repository.deleteById(id);
        return Objects.nonNull(findById(id));
    }

    @Override
    public List<Materia> findAll() {
        return repository.findAll().stream().map(mapper::toDomain).toList();
    }

    @Override
    public List<Materia> findByTutor(Integer idTutor) {
        return repository.findByTutor(idTutor).stream().map(mapper::toDomain).toList();
    }
}
