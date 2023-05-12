package es.iespuertodelacruz.alejandrosamuel.studycircle.domain.port.primary;

import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.model.MateriaTutor;

public interface IMateriaTutorService {

    MateriaTutor findByMateriaTutor(Integer idMateria, Integer idTutor);

}
