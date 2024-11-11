package es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.repository;

import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.entity.TokenConfirmacionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.Optional;

@Repository
public interface TokenConfirmacionEntityJPARepository extends JpaRepository<TokenConfirmacionEntity, Integer> {

    @Query("SELECT t FROM TokenConfirmacionEntity t WHERE t.token = :token")
    Optional<TokenConfirmacionEntity> findByToken(@Param("token") String token);

    @Modifying
    @Query("UPDATE TokenConfirmacionEntity t SET t.fechaConfirmacion = :fechaConfirmacion WHERE t.token = :token")
    Integer updateConfirmado(@Param("token") String token, @Param("fechaConfirmacion") BigInteger fechaConfirmacion);
}
