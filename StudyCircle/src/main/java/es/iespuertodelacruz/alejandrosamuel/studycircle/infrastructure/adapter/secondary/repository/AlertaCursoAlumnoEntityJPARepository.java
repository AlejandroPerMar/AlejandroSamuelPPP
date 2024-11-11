package es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.repository;

import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.entity.AlertaActividadEntity;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.entity.AlertaCursoAlumnoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AlertaCursoAlumnoEntityJPARepository extends JpaRepository<AlertaCursoAlumnoEntity, Integer> {
    @Query("SELECT a FROM AlertaCursoAlumnoEntity a WHERE a.usuario.username = :username")
    List<AlertaCursoAlumnoEntity> findAlertasCursoAlumnoByUsername(@Param("username") String username);
}
