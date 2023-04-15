package es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.primary.mapper;

import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.model.Tutor;
import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.model.Usuario;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.primary.dto.TutorDTO;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.primary.dto.UsuarioDTO;

public class TutorDTOMapper {

    public TutorDTO toDTOGet(Tutor tutor) {
        return null;
    }

    public UsuarioDTO toDTO(Usuario in) {
        UsuarioDTO usuario = new UsuarioDTO();

        usuario.setId(in.getId());
        return usuario;
    }

    public Tutor toDomain(TutorDTO in) {
        return null;
    }
}
