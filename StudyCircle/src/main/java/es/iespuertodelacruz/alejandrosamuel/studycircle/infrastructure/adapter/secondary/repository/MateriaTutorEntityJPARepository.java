package es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.repository;

import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.entity.MateriaTutorEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MateriaTutorEntityJPARepository extends JpaRepository<MateriaTutorEntity, Integer> {
}
