package es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.repository;

import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.entity.ActividadEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ActividadEntityJPARepository extends JpaRepository<ActividadEntity, Integer> {

    @Query("SELECT a FROM ActividadEntity a JOIN FETCH a.curso c WHERE a.id = :id")
    Optional<ActividadEntity> findByIdWithCurso(@Param("id") Integer id);


    /*
    @Query("SELECT a FROM ActividadEntity a WHERE a.course.name = :name")
    public List<ActividadEntity> findByCourse(@Param("name") String name);
    */

}
