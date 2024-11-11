package es.iespuertodelacruz.alejandrosamuel.studycircle.domain.port.secondary;

import java.util.List;

import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.model.Actividad;

public interface IActividadRepository {

	Actividad findById(Integer id);

    Actividad create(Actividad actividad);

    Actividad update(Actividad actividad);

    boolean delete(Integer id);

    List<Actividad> findAll();

    Integer getNumeroActividadesPendientes(Integer idAlumno);

    //List<Actividad> findByCourse(String name);

}