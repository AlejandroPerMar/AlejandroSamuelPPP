package es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.entity.AlertaEntity;

import java.util.Optional;

@Repository
public interface AlertaEntityJPARepository extends JpaRepository<AlertaEntity, Integer> {

    @Query("SELECT a FROM AlertaEntity a WHERE a.type = :type")
    public Optional<AlertaEntity> findByType(@Param("tipo") String tipo);

}