package es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.service;

import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.model.Anuncio;
import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.port.secondary.IAnuncioRepository;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.mapper.AnuncioEntityMapper;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.repository.AnuncioEntityJPARepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
        return mapper.toDomain(repository.save(mapper.toEntity(anuncio)));
    }
}
