package es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.repository;

import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.entity.ActividadEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;

@Repository
public interface ActividadEntityJPARepository extends JpaRepository<ActividadEntity, Integer> {

    @Query("SELECT COUNT(a) FROM CursoEntity c JOIN c.alumnos al JOIN c.actividades a WHERE al.id = :idAlumno AND CAST(a.fechaActividad AS long) > :fechaActual")
    Integer getNumeroActividadesPendientes(@Param("idAlumno") Integer idAlumno, @Param("fechaActual") Long fechaActual);
}
