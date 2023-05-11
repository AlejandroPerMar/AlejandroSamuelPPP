package es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.repository;


import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.entity.AmistadEntity;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.entity.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface AmistadEntityJPARepository extends JpaRepository<AmistadEntity, Integer> {
    @Query("SELECT a FROM AmistadEntity a WHERE (a.usuario1.id = :id1 OR a.usuario1.id = :id2) AND (a.usuario2.id = :id1 OR a.usuario2.id = :id2)")
    Optional<AmistadEntity> findAmistadByIds(@Param("id1") Integer id1, @Param("id2") Integer id2);

    @Query("SELECT COALESCE(" +
            "  CASE WHEN a.usuario1.id = :id THEN a.usuario2 ELSE a.usuario1 END," +
            "  CASE WHEN a.usuario2.id = :id THEN a.usuario1 ELSE a.usuario2 END" +
            ") " +
            "FROM AmistadEntity a " +
            "WHERE (a.usuario1.id = :id OR a.usuario2.id = :id) " +
            "AND (a.usuario1.id <> :id OR a.usuario2.id <> :id) " +
            "AND a.estado = :estadoAmistad")
    List<UsuarioEntity> findAmistadesById(@Param("id") Integer id, @Param("estadoAmistad") String estadoAmistad);
}
