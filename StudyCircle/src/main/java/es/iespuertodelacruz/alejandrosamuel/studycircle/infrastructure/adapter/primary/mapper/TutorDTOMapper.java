package es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.primary.mapper;

import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.model.MateriaTutor;
import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.model.Tutor;
import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.model.Usuario;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.primary.dto.MateriaTutorDTO;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.primary.dto.TutorDTO;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.primary.dto.UsuarioDTO;

public class TutorDTOMapper {

    public Tutor toDomain(TutorDTO in) {
        if(in == null)
            return null;

        Tutor tutor = new Tutor();
        tutor.setMateriasTutor(in.getMateriasTutor().stream().map(this::toDomain).toList());
        tutor.setId(in.getId());
        tutor.setUsuario(toDomain(in.getUsuario()));
        return tutor;
    }

    public TutorDTO toDTO(Tutor in) {
        if(in == null)
            return null;

        TutorDTO tutor = new TutorDTO();
        tutor.setMateriasTutor(in.getMateriasTutor().stream().map(this::toDTO).toList());
        tutor.setId(in.getId());
        tutor.setUsuario(toDTO(in.getUsuario()));
        return tutor;
    }

    public UsuarioDTO toDTO(Usuario in) {
        UsuarioDTO usuario = new UsuarioDTO();
        usuario.setId(in.getId());
        return usuario;
    }

    public Usuario toDomain(UsuarioDTO in) {
        Usuario usuario = new Usuario();
        usuario.setId(in.getId());
        return usuario;
    }

    public MateriaTutor toDomain(MateriaTutorDTO in) {
        MateriaTutor materiaTutor = new MateriaTutor();
        materiaTutor.setId(in.getId());
        return materiaTutor;
    }

    public MateriaTutorDTO toDTO(MateriaTutor in) {
        MateriaTutorDTO materiaTutor = new MateriaTutorDTO();
        materiaTutor.setId(in.getId());
        return materiaTutor;
    }
}
