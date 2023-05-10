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
    public List<AlertaActividadEntity> findByUsuario(@Param("username") String usuarioEntity);

}