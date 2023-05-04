package es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.repository;

import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.entity.CursoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CursoEntityJPARepository extends JpaRepository<CursoEntity, Integer> {

    @Query("SELECT c FROM CursoEntity c WHERE c.materiaTutor.tutor.id = :id")
    List<CursoEntity> findByIdTutor(@Param("id") Integer id);

    @Query("SELECT c FROM CursoEntity c JOIN c.alumnos ac WHERE ac.id = :id")
    List<CursoEntity> findByAlumno(@Param("id") Integer id);
}
