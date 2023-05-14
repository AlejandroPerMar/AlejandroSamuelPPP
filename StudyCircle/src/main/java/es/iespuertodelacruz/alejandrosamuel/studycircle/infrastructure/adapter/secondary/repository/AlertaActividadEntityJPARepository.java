package es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.repository;

import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.entity.AlertaActividadEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AlertaActividadEntityJPARepository extends JpaRepository<AlertaActividadEntity, Integer> {

    @Query("SELECT a FROM AlertaActividadEntity a WHERE a.usuario.username = :username")
    List<AlertaActividadEntity> findAlertasActividadByUsuario(@Param("username") String usuarioEntity);

    @Query("SELECT a FROM AlertaActividadEntity a WHERE a.actividad.id = :id")
    List<AlertaActividadEntity> findByActividad(@Param("id") Integer id);

    @Query("SELECT a FROM AlertaActividadEntity a WHERE a.actividad.id = :idActividad AND a.usuario.id = :idUsuario")
    List<AlertaActividadEntity> findByActividadUsuario(@Param("idActividad") Integer idActividad, @Param("idUsuario") Integer idUsuario);
}