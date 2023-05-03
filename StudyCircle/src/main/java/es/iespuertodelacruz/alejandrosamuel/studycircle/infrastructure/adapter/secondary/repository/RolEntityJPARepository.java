package es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.entity.RolEntity;

@Repository
public interface RolEntityJPARepository extends JpaRepository<RolEntity, Integer> {
	@Query("SELECT r from RolEntity r where r.rol = :rol")
	RolEntity findByRol(@Param("rol") String rol);
}
