package es.iespuertodelacruz.alejandrosamuel.studycircle.domain.service;

import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.model.MateriaTutor;
import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.port.primary.IMateriaTutorService;
import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.port.secondary.IMateriaTutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MateriaTutorService implements IMateriaTutorService {

    @Autowired
    private IMateriaTutorRepository repository;

    @Override
    public MateriaTutor findByMateriaTutor(Integer idMateria, Integer idTutor) {
        return repository.findByMateriaTutor(idMateria, idTutor);
    }
}
