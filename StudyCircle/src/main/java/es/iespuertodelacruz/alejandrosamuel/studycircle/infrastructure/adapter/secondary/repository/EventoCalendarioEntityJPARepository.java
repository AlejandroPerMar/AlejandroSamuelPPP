package es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.repository;

import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.entity.EventoCalendarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EventoCalendarioEntityJPARepository extends JpaRepository<EventoCalendarioEntity, Integer> {

    @Query("SELECT ev FROM EventoCalendarioEntity ev WHERE ev.usuario.id = :id AND ev.perfilUsuario = :name")
    List<EventoCalendarioEntity> findByPerfilUsuario(@Param("id") Integer idUsuario, @Param("name") String name);

    @Query("SELECT ev FROM EventoCalendarioEntity ev WHERE ev.actividad.id = :id")
    List<EventoCalendarioEntity> findByActividad(@Param("id") Integer idActividad);

    @Query("SELECT ev FROM EventoCalendarioEntity ev WHERE ev.actividad.curso.id = :idCurso AND ev.usuario.id = :idUsuario")
    List<EventoCalendarioEntity> findByUsuarioAndCurso(@Param("idUsuario") Integer id, @Param("idCurso") Integer id1);
}
