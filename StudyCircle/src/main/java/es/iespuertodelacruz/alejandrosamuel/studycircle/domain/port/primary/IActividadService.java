package es.iespuertodelacruz.alejandrosamuel.studycircle.domain.port.primary;

import java.util.List;

import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.model.Actividad;

public interface IActividadService {

    Actividad findById(Integer id);
    
    Actividad findByNombre(String nombre);

    List<Actividad> findByCourse(Integer id);

    Actividad create(Actividad nivelEstudios);

    Actividad update(Actividad nivelEstudios);

    boolean delete(Integer id);

    List<Actividad> findAll();
	
}
