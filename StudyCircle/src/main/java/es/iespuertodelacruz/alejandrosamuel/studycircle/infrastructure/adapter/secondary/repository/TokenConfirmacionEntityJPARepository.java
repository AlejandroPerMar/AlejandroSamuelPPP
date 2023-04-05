package es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.repository;

import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.entity.TokenConfirmacionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.Optional;

@Repository
public interface TokenConfirmacionEntityJPARepository extends JpaRepository<TokenConfirmacionEntity, Integer> {

    @Query("SELECT t FROM TokenConfirmacionEntity t WHERE t.token = :token")
    public Optional<TokenConfirmacionEntity> findByToken(@Param("token") String token);

    @Modifying
    @Query("UPDATE TokenConfirmacionEntity t SET t.fechaConfirmacion = :fechaConfirmacion WHERE t.token = :token")
    public Integer updateConfirmado(@Param("token") String token, @Param("fechaConfirmacion") Timestamp fechaConfirmacion);
}
