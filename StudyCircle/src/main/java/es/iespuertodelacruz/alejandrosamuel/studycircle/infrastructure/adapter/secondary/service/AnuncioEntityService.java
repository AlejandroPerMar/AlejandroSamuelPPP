package es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.service;

import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.model.Anuncio;
import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.port.secondary.IAnuncioRepository;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.entity.AnuncioEntity;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.mapper.AnuncioEntityMapper;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.repository.AnuncioEntityJPARepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class AnuncioEntityService implements IAnuncioRepository {

    @Autowired
    private AnuncioEntityJPARepository repository;

    @Autowired
    private AnuncioEntityMapper mapper;

    @Override
    public Anuncio findById(Integer id) {
        return mapper.toDomain(repository.findById(id).orElse(null));
    }

    @Override
    public List<Anuncio> findAll() {
        return repository.findAll().stream().map(mapper::toDomain).toList();
    }

    @Override
    public Anuncio create(Anuncio anuncio) {
        return mapper.toDomainPost(repository.save(mapper.toEntity(anuncio)));
    }

    @Override
    public List<Anuncio> findByIdUsuario(Integer id) {
        return repository.findByIdUsuario(id).stream().map(mapper::toDomain).toList();
    }

    @Override
    public void delete(Integer idAnuncio) {
        AnuncioEntity anuncioEntity = repository.findById(idAnuncio).orElse(null);
        if(Objects.nonNull(anuncioEntity)) {
            repository.delete(anuncioEntity);
        }
    }
}
