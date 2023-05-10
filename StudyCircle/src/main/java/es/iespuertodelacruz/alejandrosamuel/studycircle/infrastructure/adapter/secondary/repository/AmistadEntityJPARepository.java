package es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.repository;


import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.entity.AmistadEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface AmistadEntityJPARepository extends JpaRepository<AmistadEntity, Integer> {
    @Query("SELECT AmistadEntity a WHERE (a.persona1.id = :id1 OR a.persona1.id = :id2) AND" +
            "(a.persona2.id = :id1 OR a.persona2.id = :id2)")
    public Optional<AmistadEntity> findAmistadByIds(@Param("id1") Integer id1, @Param("id2") Integer id2);
}
