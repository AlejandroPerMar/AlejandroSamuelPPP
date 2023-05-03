package es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.repository;

import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.entity.TutorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface TutorEntityJPARepository extends JpaRepository<TutorEntity, Integer> {
    @Query("SELECT t FROM TutorEntity t WHERE t.usuario.id = :idUsuario")
    Optional<TutorEntity> findByIdUsuario(Integer idUsuario);

    @Query("SELECT t FROM TutorEntity t WHERE t.usuario.username = :username")
    Optional<TutorEntity> findByUsername(String username);
}
