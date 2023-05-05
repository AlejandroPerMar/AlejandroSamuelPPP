package es.iespuertodelacruz.alejandrosamuel.studycircle.domain.port.primary;

import java.util.List;

import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.model.Alerta;

public interface IAlertaService {

    Alerta findById(Integer id);
    
    Alerta findByType(String nombre);

    List<Alerta> findByUsername(String username);

    Alerta create(Alerta alerta);

    Alerta update(Alerta alerta);

    boolean delete(Integer id);

    List<Alerta> findAll();
	
}
