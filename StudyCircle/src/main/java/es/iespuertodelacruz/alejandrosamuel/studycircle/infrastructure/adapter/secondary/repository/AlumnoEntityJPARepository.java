package es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.entity.AlumnoEntity;

@Repository
public interface AlumnoEntityJPARepository extends JpaRepository<AlumnoEntity, Integer> {
	@Query("SELECT a FROM AlumnoEntity a WHERE a.user.id = :idUsuario")
	Optional<AlumnoEntity> findByIdUsuario(Integer idUsuario);
	
	@Query("SELECT a FROM AlumnoEntity a WHERE a.user.username = :username")
	Optional<AlumnoEntity> findByUsername(String username);
}
