package es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface UsuarioEntityJPARepository extends JpaRepository<UsuarioEntity, Integer> {
	
	@Query("SELECT u from UsuarioEntity u where u.nombre=:nombre")
	public UsuarioEntity findByName(@Param("nombre") String nombre);
}
