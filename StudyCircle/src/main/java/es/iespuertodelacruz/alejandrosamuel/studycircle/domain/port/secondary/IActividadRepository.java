package es.iespuertodelacruz.alejandrosamuel.studycircle.domain.port.secondary;

import java.util.List;

import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.model.Actividad;

public interface IActividadRepository {

	Actividad findById(Integer id);
    
    Actividad findByNombre(String nombre);

    Actividad create(Actividad nivelEstudios);

    Actividad update(Actividad nivelEstudios);

    boolean delete(Integer id);

    List<Actividad> findAll();
    
    List<Actividad> findByCourse(String name);

}