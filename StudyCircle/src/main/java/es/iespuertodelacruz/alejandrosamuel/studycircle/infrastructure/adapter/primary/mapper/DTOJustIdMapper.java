package es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.primary.mapper;

import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.model.*;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.primary.dto.*;

import java.util.Objects;

public class DTOJustIdMapper {

    public Actividad toDomain(ActividadDTO in) {
        if(Objects.isNull(in))
            return null;

        Actividad actividad = new Actividad();
        actividad.setId(in.getId());
        return actividad;
    }

    public Alerta toDomain(AlertaDTO in) {
        if(Objects.isNull(in))
            return null;

        Alerta alerta = new Alerta();
        alerta.setId(in.getId());
        return alerta;
    }

    public Alumno toDomain(AlumnoDTO in) {
        if(Objects.isNull(in))
            return null;

        Alumno alumno = new Alumno();
        alumno.setId(in.getId());
        return alumno;
    }

    public Amistad toDomain(AmistadDTO in) {
        if(Objects.isNull(in))
            return null;

        Amistad amistad = new Amistad();
        amistad.setId(in.getId());
        return amistad;
    }

    public Anuncio toDomain(AnuncioDTO in) {
        if(Objects.isNull(in))
            return null;

        Anuncio anuncio = new Anuncio();
        anuncio.setId(in.getId());
        return anuncio;
    }

    public Curso toDomain(CursoDTO in) {
        if(Objects.isNull(in))
            return null;

        Curso curso = new Curso();
        curso.setId(in.getId());
        return curso;
    }

    public EventoCalendario toDomain(EventoCalendarioDTO in) {
        if(Objects.isNull(in))
            return null;

        EventoCalendario eventoCalendario = new EventoCalendario();
        eventoCalendario.setId(in.getId());
        return eventoCalendario;
    }

    public Materia toDomain(MateriaDTO in) {
        if(Objects.isNull(in))
            return null;

        Materia materia = new Materia();
        materia.setId(in.getId());
        return materia;
    }

    public MateriaTutor toDomain(MateriaTutorDTO in) {
        if(Objects.isNull(in))
            return null;

        MateriaTutor materiaTutor = new MateriaTutor();
        materiaTutor.setId(in.getId());
        return materiaTutor;
    }

    public NivelEstudios toDomain(NivelEstudiosDTO in) {
        if(Objects.isNull(in))
            return null;

        NivelEstudios nivelEstudios = new NivelEstudios();
        nivelEstudios.setId(in.getId());
        return nivelEstudios;
    }

    public Tutor toDomain(TutorDTO in) {
        if(Objects.isNull(in))
            return null;

        Tutor tutor = new Tutor();
        tutor.setId(in.getId());
        return tutor;
    }

    public Usuario toDomain(UsuarioDTO in) {
        if(Objects.isNull(in))
            return null;

        Usuario usuario = new Usuario();
        usuario.setId(in.getId());
        return usuario;
    }

    public ActividadDTO toDTO(Actividad in) {
        if(Objects.isNull(in))
            return null;

        ActividadDTO actividad = new ActividadDTO();
        actividad.setId(in.getId());
        return actividad;
    }

    public AlertaDTO toDTO(Alerta in) {
        if(Objects.isNull(in))
            return null;

        AlertaDTO alerta = new AlertaDTO();
        alerta.setId(in.getId());
        return alerta;
    }

    public AlumnoDTO toDTO(Alumno in) {
        if(Objects.isNull(in))
            return null;

        AlumnoDTO alumno = new AlumnoDTO();
        alumno.setId(in.getId());
        return alumno;
    }

    public AmistadDTO toDTO(Amistad in) {
        if(Objects.isNull(in))
            return null;

        AmistadDTO amistad = new AmistadDTO();
        amistad.setId(in.getId());
        return amistad;
    }

    public AnuncioDTO toDTO(Anuncio in) {
        if(Objects.isNull(in))
            return null;

        AnuncioDTO anuncio = new AnuncioDTO();
        anuncio.setId(in.getId());
        return anuncio;
    }

    public CursoDTO toDTO(Curso in) {
        if(Objects.isNull(in))
            return null;

        CursoDTO curso = new CursoDTO();
        curso.setId(in.getId());
        return curso;
    }

    public EventoCalendarioDTO toDTO(EventoCalendario in) {
        if(Objects.isNull(in))
            return null;

        EventoCalendarioDTO eventoCalendario = new EventoCalendarioDTO();
        eventoCalendario.setId(in.getId());
        return eventoCalendario;
    }

    public MateriaDTO toDTO(Materia in) {
        if(Objects.isNull(in))
            return null;

        MateriaDTO materia = new MateriaDTO();
        materia.setId(in.getId());
        return materia;
    }

    public MateriaTutorDTO toDTO(MateriaTutor in) {
        if(Objects.isNull(in))
            return null;

        MateriaTutorDTO materiaTutor = new MateriaTutorDTO();
        materiaTutor.setId(in.getId());
        return materiaTutor;
    }

    public NivelEstudiosDTO toDTO(NivelEstudios in) {
        if(Objects.isNull(in))
            return null;

        NivelEstudiosDTO nivelEstudios = new NivelEstudiosDTO();
        nivelEstudios.setId(in.getId());
        return nivelEstudios;
    }

    public TutorDTO toDTO(Tutor in) {
        if(Objects.isNull(in))
            return null;

        TutorDTO tutor = new TutorDTO();
        tutor.setId(in.getId());
        return tutor;
    }

    public UsuarioDTO toDTO(Usuario in) {
        if(Objects.isNull(in))
            return null;

        UsuarioDTO usuario = new UsuarioDTO();
        usuario.setId(in.getId());
        return usuario;
    }
}
