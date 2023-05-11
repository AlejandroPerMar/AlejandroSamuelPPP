package es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.repository;

import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.entity.AlertaActividadEntity;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.entity.AlertaAmistadEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AlertaActividadEntityJPARepository extends JpaRepository<AlertaActividadEntity, Integer> {

    @Query("SELECT a FROM AlertaActividadEntity a WHERE a.usuario.username = :username")
    List<AlertaActividadEntity> findAlertasActividadByUsuario(@Param("username") String usuarioEntity);

    @Query("SELECT a FROM AlertaAmistadEntity a WHERE a.usuario.username = :username")
    List<AlertaAmistadEntity> findAlertasAmistadByUsuario(@Param("username") String usuarioEntity);

}