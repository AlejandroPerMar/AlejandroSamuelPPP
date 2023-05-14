package es.iespuertodelacruz.alejandrosamuel.studycircle.domain.service;

import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.model.Anuncio;
import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.port.primary.IAnuncioService;
import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.port.secondary.IAnuncioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnuncioService implements IAnuncioService {

    @Autowired
    private IAnuncioRepository repository;

    @Override
    public Anuncio findById(Integer id) {
        return repository.findById(id);
    }

    @Override
    public List<Anuncio> findAll() {
        return repository.findAll();
    }

    @Override
    public List<Anuncio> findByIdUsuario(Integer id) {
        return repository.findByIdUsuario(id);
    }

    @Override
    public Anuncio create(Anuncio anuncio) {
        return repository.create(anuncio);
    }
}
