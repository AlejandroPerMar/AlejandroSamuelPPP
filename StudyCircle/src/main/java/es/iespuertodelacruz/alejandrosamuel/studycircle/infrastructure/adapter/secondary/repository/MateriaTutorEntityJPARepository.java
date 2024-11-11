package es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.repository;

import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.entity.MateriaTutorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface MateriaTutorEntityJPARepository extends JpaRepository<MateriaTutorEntity, Integer> {

    @Query("SELECT mt FROM MateriaTutorEntity mt WHERE mt.materia.id= :idMateria AND mt.tutor.id = :idTutor")
    Optional<MateriaTutorEntity> findByMateriaTutor(@Param("idMateria") Integer idMateria, @Param("idTutor") Integer idTutor);
}
