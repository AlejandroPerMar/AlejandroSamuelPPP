package es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.repository;

import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.model.enums.EstadosUsuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.entity.UsuarioEntity;
import org.springframework.transaction.annotation.Transactional;


@Repository
public interface UsuarioEntityJPARepository extends JpaRepository<UsuarioEntity, Integer> {
	
	@Query("SELECT u FROM UsuarioEntity u WHERE u.username = :username")
	public UsuarioEntity findByUsername(@Param("username") String username);

	@Query("SELECT u FROM UsuarioEntity u WHERE u.email = :email")
	public UsuarioEntity findByEmail(@Param("email") String email);

	@Modifying
	@Query("UPDATE UsuarioEntity u SET u.estado = 'STATUS_ACTIVE' WHERE u.email = :email")
	public Integer confirmarUsuario(@Param("email") String email);
}
