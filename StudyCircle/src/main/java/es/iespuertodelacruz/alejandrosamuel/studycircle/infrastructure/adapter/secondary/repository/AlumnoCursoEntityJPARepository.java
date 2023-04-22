package es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.repository;

import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.entity.AlumnoCursoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlumnoCursoEntityJPARepository extends JpaRepository<AlumnoCursoEntity, Integer> {
}
