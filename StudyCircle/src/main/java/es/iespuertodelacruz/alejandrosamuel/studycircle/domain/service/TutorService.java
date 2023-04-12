package es.iespuertodelacruz.alejandrosamuel.studycircle.domain.service;

import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.model.Tutor;
import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.port.primary.ITutorService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TutorService implements ITutorService {

    @Override
    public Tutor create(Tutor tutor) {
        return null;
    }

    @Override
    public Tutor findById(Integer id) {
        return null;
    }

    @Override
    public Tutor findByUsuario() {
        return null;
    }

    @Override
    public List<Tutor> findAll() {
        return null;
    }
}
