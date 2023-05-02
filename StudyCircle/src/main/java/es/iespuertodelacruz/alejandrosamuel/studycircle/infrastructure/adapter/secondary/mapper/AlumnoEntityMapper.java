package es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.mapper;

import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.model.Alumno;
import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.model.Materia;
import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.model.NivelEstudios;
import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.model.Usuario;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.entity.AlumnoEntity;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.entity.MateriaEntity;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.entity.NivelEstudiosEntity;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.entity.UsuarioEntity;

import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class AlumnoEntityMapper {

    public Alumno toDomain(AlumnoEntity in) {
        Alumno alumno = new Alumno();
        alumno.setId(in.getId());
		alumno.setNivelEstudios(toDomain(in.getNivelEstudios()));
		List<Materia> materias = in.getMaterias()
				.stream()
				.map(m->toDomain(m))
				.collect(Collectors.toList());
		alumno.setMaterias(materias);
        alumno.setUsuario(toDomain(in.getUsuario()));
        return alumno;
    }

    private Usuario toDomain(UsuarioEntity in) {
        if(Objects.isNull(in))
            return null;
        Usuario usuario = new Usuario();
        usuario.setId(in.getId());
        return usuario;
    }

    public AlumnoEntity toEntityPost(Alumno in) {
        AlumnoEntity alumno = new AlumnoEntity();
        alumno.setFechaCreacion(in.getFechaCreacion());
        alumno.setNivelEstudios(toEntity(in.getNivelEstudios()));
        if(in.getMaterias() == null)
            alumno.setMaterias(null);
        else
            alumno.setMaterias(in.getMaterias().stream().map(this::toEntity).toList());
        alumno.setUsuario(toEntity(in.getUsuario()));
        return alumno;
    }

    private UsuarioEntity toEntity(Usuario in) {
        if(Objects.isNull(in))
            return null;
        UsuarioEntity usuario = new UsuarioEntity();
        usuario.setId(in.getId());
        return usuario;
    }

    public AlumnoEntity toEntityPut(Alumno in) {
        AlumnoEntity alumno = new AlumnoEntity();
        alumno.setId(in.getId());
		alumno.setNivelEstudios(toEntity(in.getNivelEstudios()));
        if(in.getMaterias() == null)
            alumno.setMaterias(null);
        else
		    alumno.setMaterias(in.getMaterias().stream().map(m->toEntity(m)).toList());
        return alumno;
    }

    private MateriaEntity toEntity(Materia in) {
        MateriaEntity materia = new MateriaEntity();
        materia.setId(in.getId());
        return materia;
    }

    private Materia toDomain(MateriaEntity in) {
        Materia materia = new Materia();
        materia.setId(in.getId());
        return materia;
    }

    private NivelEstudiosEntity toEntity(NivelEstudios in) {
        NivelEstudiosEntity nivelEstudios = new NivelEstudiosEntity();
        nivelEstudios.setId(in.getId());
        return nivelEstudios;
    }

    private NivelEstudios toDomain(NivelEstudiosEntity in) {
        NivelEstudios nivelEstudios = new NivelEstudios();
        nivelEstudios.setId(in.getId());
        return nivelEstudios;
    }
}
