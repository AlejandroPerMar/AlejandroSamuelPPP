package es.iespuertodelacruz.alejandrosamuel.studycircle.domain.port.secondary;

import java.util.List;

import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.model.Alerta;

public interface IAlertaRepository {

    Alerta findById(Integer id);
    
    Alerta findByType(String nombre);

    Alerta create(Alerta alerta);

    Alerta update(Alerta alerta);

    boolean delete(Integer id);

    List<Alerta> findAll();
	
}
