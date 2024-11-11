package es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.repository;

import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.entity.AlertaAmistadEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AlertaAmistadEntityJPARepository extends JpaRepository<AlertaAmistadEntity, Integer> {

    @Query("SELECT a FROM AlertaAmistadEntity a WHERE a.usuario.username = :username")
    List<AlertaAmistadEntity> findByUsuario(@Param("username") String usuarioEntity);

    @Query("SELECT a FROM AlertaAmistadEntity a WHERE a.usuario.username = :username")
    List<AlertaAmistadEntity> findAlertasAmistadByUsuario(@Param("username") String usuarioEntity);

    @Query("SELECT al FROM AlertaAmistadEntity al WHERE al.amistad.id = :idAmistad")
    List<AlertaAmistadEntity> findAlertasAmistadByAmistad(@Param("idAmistad") Integer idAmistad);
}