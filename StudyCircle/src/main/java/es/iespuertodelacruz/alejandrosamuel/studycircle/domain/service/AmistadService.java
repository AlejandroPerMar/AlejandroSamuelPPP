package es.iespuertodelacruz.alejandrosamuel.studycircle.domain.service;

import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.model.Amistad;
import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.model.Usuario;
import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.port.primary.IAmistadService;
import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.port.secondary.IAmistadRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AmistadService implements IAmistadService {

    @Autowired
    private IAmistadRepository repository;

    @Override
    public Amistad create(Amistad amistad) {
        return repository.create(amistad);
    }

    @Override
    public Amistad accept(Integer idAmistad) {
        return repository.accept(idAmistad);
    }

    @Override
    public Amistad findById(Integer id) {
        return repository.findById(id);
    }

    @Override
    public void remove(Integer idAmistad) {
        repository.remove(idAmistad);
    }

    @Override
    public Amistad findByUsuarioId(Integer idUsuario) {
        return repository.findByUsuarioId(idUsuario);
    }

    @Override
    public Amistad findAmistadByIds(Integer id1, Integer id2) {
        return repository.findAmistadByIds(id1, id2);
    }

    @Override
    public List<Usuario> findAmistadesByIdUsuario(Integer id) {
        return repository.findAmistadesById(id);
    }

    @Override
    public List<Amistad> findAmistades(Integer id) {
        return repository.findAmistades(id);
    }
}
