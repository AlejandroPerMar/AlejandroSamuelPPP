package es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.mapper;

import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.model.*;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.entity.*;

import java.util.Objects;

public class EntityJustIdMapper {
    public Actividad toDomain(ActividadEntity in) {
        if(Objects.isNull(in))
            return null;

        Actividad actividad = new Actividad();
        actividad.setId(in.getId());
        return actividad;
    }

    public AlertaActividad toDomain(AlertaActividadEntity in) {
        if(Objects.isNull(in))
            return null;

        AlertaActividad alerta = new AlertaActividad();
        alerta.setId(in.getId());
        return alerta;
    }

    public AlertaAmistad toDomain(AlertaAmistadEntity in) {
        if(Objects.isNull(in))
            return null;

        AlertaAmistad alerta = new AlertaAmistad();
        alerta.setId(in.getId());
        return alerta;
    }

    public Alumno toDomain(AlumnoEntity in) {
        if(Objects.isNull(in))
            return null;

        Alumno alumno = new Alumno();
        alumno.setId(in.getId());
        return alumno;
    }

    public Amistad toDomain(AmistadEntity in) {
        if(Objects.isNull(in))
            return null;

        Amistad amistad = new Amistad();
        amistad.setId(in.getId());
        return amistad;
    }

    public Anuncio toDomain(AnuncioEntity in) {
        if(Objects.isNull(in))
            return null;

        Anuncio anuncio = new Anuncio();
        anuncio.setId(in.getId());
        return anuncio;
    }

    public Curso toDomain(CursoEntity in) {
        if(Objects.isNull(in))
            return null;

        Curso curso = new Curso();
        curso.setId(in.getId());
        return curso;
    }

    public EventoCalendario toDomain(EventoCalendarioEntity in) {
        if(Objects.isNull(in))
            return null;

        EventoCalendario eventoCalendario = new EventoCalendario();
        eventoCalendario.setId(in.getId());
        return eventoCalendario;
    }

    public Materia toDomain(MateriaEntity in) {
        if(Objects.isNull(in))
            return null;

        Materia materia = new Materia();
        materia.setId(in.getId());
        return materia;
    }

    public MateriaTutor toDomain(MateriaTutorEntity in) {
        if(Objects.isNull(in))
            return null;

        MateriaTutor materiaTutor = new MateriaTutor();
        materiaTutor.setId(in.getId());
        return materiaTutor;
    }

    public NivelEstudios toDomain(NivelEstudiosEntity in) {
        if(Objects.isNull(in))
            return null;

        NivelEstudios nivelEstudios = new NivelEstudios();
        nivelEstudios.setId(in.getId());
        return nivelEstudios;
    }

    public Tutor toDomain(TutorEntity in) {
        if(Objects.isNull(in))
            return null;

        Tutor tutor = new Tutor();
        tutor.setId(in.getId());
        return tutor;
    }

    public Usuario toDomain(UsuarioEntity in) {
        if(Objects.isNull(in))
            return null;

        Usuario usuario = new Usuario();
        usuario.setId(in.getId());
        return usuario;
    }

    public ActividadEntity toEntity(Actividad in) {
        if(Objects.isNull(in))
            return null;

        ActividadEntity actividad = new ActividadEntity();
        actividad.setId(in.getId());
        return actividad;
    }

    public AlertaActividadEntity toEntity(AlertaActividad in) {
        if(Objects.isNull(in))
            return null;

        AlertaActividadEntity alerta = new AlertaActividadEntity();
        alerta.setId(in.getId());
        return alerta;
    }

    public AlertaActividadEntity toDomain(AlertaAmistad in) {
        if(Objects.isNull(in))
            return null;

        AlertaActividadEntity alerta = new AlertaActividadEntity();
        alerta.setId(in.getId());
        return alerta;
    }

    public AlumnoEntity toEntity(Alumno in) {
        if(Objects.isNull(in))
            return null;

        AlumnoEntity alumno = new AlumnoEntity();
        alumno.setId(in.getId());
        return alumno;
    }

    public AmistadEntity toEntity(Amistad in) {
        if(Objects.isNull(in))
            return null;

        AmistadEntity amistad = new AmistadEntity();
        amistad.setId(in.getId());
        return amistad;
    }

    public AnuncioEntity toEntity(Anuncio in) {
        if(Objects.isNull(in))
            return null;

        AnuncioEntity anuncio = new AnuncioEntity();
        anuncio.setId(in.getId());
        return anuncio;
    }

    public CursoEntity toEntity(Curso in) {
        if(Objects.isNull(in))
            return null;

        CursoEntity curso = new CursoEntity();
        curso.setId(in.getId());
        return curso;
    }

    public EventoCalendarioEntity toEntity(EventoCalendario in) {
        if(Objects.isNull(in))
            return null;

        EventoCalendarioEntity eventoCalendario = new EventoCalendarioEntity();
        eventoCalendario.setId(in.getId());
        return eventoCalendario;
    }

    public MateriaEntity toEntity(Materia in) {
        if(Objects.isNull(in))
            return null;

        MateriaEntity materia = new MateriaEntity();
        materia.setId(in.getId());
        return materia;
    }

    public MateriaTutorEntity toEntity(MateriaTutor in) {
        if(Objects.isNull(in))
            return null;

        MateriaTutorEntity materiaTutor = new MateriaTutorEntity();
        materiaTutor.setId(in.getId());
        return materiaTutor;
    }

    public NivelEstudiosEntity toEntity(NivelEstudios in) {
        if(Objects.isNull(in))
            return null;

        NivelEstudiosEntity nivelEstudios = new NivelEstudiosEntity();
        nivelEstudios.setId(in.getId());
        return nivelEstudios;
    }

    public TutorEntity toEntity(Tutor in) {
        if(Objects.isNull(in))
            return null;

        TutorEntity tutor = new TutorEntity();
        tutor.setId(in.getId());
        return tutor;
    }

    public UsuarioEntity toEntity(Usuario in) {
        if(Objects.isNull(in))
            return null;

        UsuarioEntity usuario = new UsuarioEntity();
        usuario.setId(in.getId());
        return usuario;
    }
}
