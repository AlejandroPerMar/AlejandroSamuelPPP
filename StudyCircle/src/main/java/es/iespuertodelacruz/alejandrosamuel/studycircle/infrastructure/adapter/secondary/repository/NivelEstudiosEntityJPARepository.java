package es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.repository;

import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.entity.NivelEstudiosEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface NivelEstudiosEntityJPARepository extends JpaRepository<NivelEstudiosEntity, Integer> {

    @Query("SELECT n FROM NivelEstudiosEntity n WHERE n.nombre = :nombre")
    Optional<NivelEstudiosEntity> findByNombre(@Param("nombre") String nombre);

}
