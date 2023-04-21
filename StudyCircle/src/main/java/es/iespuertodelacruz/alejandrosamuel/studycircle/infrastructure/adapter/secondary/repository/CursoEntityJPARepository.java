package es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.repository;

import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.entity.CursoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CursoEntityJPARepository extends JpaRepository<CursoEntity, Integer> {

}
