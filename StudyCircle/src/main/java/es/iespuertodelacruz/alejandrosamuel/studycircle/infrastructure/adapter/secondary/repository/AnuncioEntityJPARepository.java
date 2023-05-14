package es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.repository;

import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.entity.AnuncioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AnuncioEntityJPARepository extends JpaRepository<AnuncioEntity, Integer> {

    @Query("SELECT a FROM AnuncioEntity a WHERE a.usuario.id = :id")
    List<AnuncioEntity> findByIdUsuario(@Param("id") Integer id);
}
