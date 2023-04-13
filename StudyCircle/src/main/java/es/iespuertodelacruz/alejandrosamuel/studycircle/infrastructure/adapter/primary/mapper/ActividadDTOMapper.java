package es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.primary.mapper;

import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.model.Actividad;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.primary.dto.ActividadDTO;

public class ActividadDTOMapper {
	
    public Actividad toDomain(ActividadDTO in) {
    	Actividad materia = new Actividad();

        materia.setId(in.getId());

        return materia;
    }

    public Actividad toDomainPost(ActividadDTO in) {
    	Actividad materia = new Actividad();

    	 materia.setId(in.getId());
         materia.setNombre(in.getNombre());
         materia.setDescripcion(in.getDescripcion());
         materia.setEstado(in.getEstado());

        return materia;
    }

    public ActividadDTO toDTO(Actividad in) {
    	ActividadDTO materia = new ActividadDTO();

        materia.setId(in.getId());
        materia.setNombre(in.getNombre());
        materia.setDescripcion(in.getDescripcion());
        materia.setEstado(in.getEstado());

        return materia;
    }

    /*
    public NivelEstudios toDomain(NivelEstudiosDTO in) {
        NivelEstudios nivelEstudios = new NivelEstudios();
        nivelEstudios.setId(in.getId());
        return nivelEstudios;
    }

    public NivelEstudiosDTO toDTO(NivelEstudios in) {
        NivelEstudiosDTO nivelEstudios = new NivelEstudiosDTO();
        nivelEstudios.setId(in.getId());
        return nivelEstudios;
    }
    */
}
