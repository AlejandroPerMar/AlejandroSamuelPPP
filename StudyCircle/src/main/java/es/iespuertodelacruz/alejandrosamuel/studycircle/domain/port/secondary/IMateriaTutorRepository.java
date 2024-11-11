package es.iespuertodelacruz.alejandrosamuel.studycircle.domain.port.secondary;

import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.model.MateriaTutor;

public interface IMateriaTutorRepository {

    MateriaTutor findByMateriaTutor(Integer idMateria, Integer idTutor);

}
