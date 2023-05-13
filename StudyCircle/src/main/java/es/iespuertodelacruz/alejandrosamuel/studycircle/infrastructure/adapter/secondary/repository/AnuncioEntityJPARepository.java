package es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.repository;

import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.entity.AnuncioEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnuncioEntityJPARepository extends JpaRepository<AnuncioEntity, Integer> {
}
