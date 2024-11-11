package es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.repository;

import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.entity.MateriaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MateriaEntityJPARepository extends JpaRepository<MateriaEntity, Integer> {

    @Query("SELECT m FROM MateriaEntity m WHERE m.nombre = :nombre")
    Optional<MateriaEntity> findByNombre(@Param("nombre") String nombre);

    @Query("SELECT m FROM MateriaEntity m WHERE m.nivelEstudios.id = :id")
    List<MateriaEntity> findByNivelEstudiosId(@Param("id") Integer id);

    @Query("SELECT m FROM MateriaEntity m WHERE m.nivelEstudios.nombre = :nombre")
    List<MateriaEntity> findByNivelEstudiosNombre(@Param("nombre") String nombre);

    @Query("SELECT m FROM MateriaEntity m WHERE m IN (SELECT mt.materia FROM MateriaTutorEntity mt WHERE mt.tutor.id = :idTutor)")
    List<MateriaEntity> findByTutor(@Param("idTutor") Integer idTutor);
}
