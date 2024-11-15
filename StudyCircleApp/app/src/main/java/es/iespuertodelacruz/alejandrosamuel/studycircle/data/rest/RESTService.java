package es.iespuertodelacruz.alejandrosamuel.studycircle.data.rest;

import java.util.List;

import es.iespuertodelacruz.alejandrosamuel.studycircle.data.rest.dto.ActividadDTO;
import es.iespuertodelacruz.alejandrosamuel.studycircle.data.rest.dto.AlumnoDTO;
import es.iespuertodelacruz.alejandrosamuel.studycircle.data.rest.dto.AmistadDTO;
import es.iespuertodelacruz.alejandrosamuel.studycircle.data.rest.dto.AnuncioDTO;
import es.iespuertodelacruz.alejandrosamuel.studycircle.data.rest.dto.CursoDTO;
import es.iespuertodelacruz.alejandrosamuel.studycircle.data.rest.dto.EventoCalendarioDTO;
import es.iespuertodelacruz.alejandrosamuel.studycircle.data.rest.dto.MateriaDTO;
import es.iespuertodelacruz.alejandrosamuel.studycircle.data.rest.dto.TutorDTO;
import es.iespuertodelacruz.alejandrosamuel.studycircle.data.rest.dto.UsuarioLoginDTO;
import es.iespuertodelacruz.alejandrosamuel.studycircle.data.rest.dto.UsuarioRegisterDTO;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface RESTService {

    // Acciones de autenticación
    @POST("login")
    Call<String> login(@Body UsuarioLoginDTO usuarioLoginDTO);

    @POST("register")
    Call<ResponseBody> register(@Body UsuarioRegisterDTO usuarioRegisterDTO);

    @GET("v2/usuarios")
    Call<ResponseBody> getUsuario();

    @GET("v2/usuarios/perfilesUsuarios")
    Call<ResponseBody> getPerfilesUsuarios();

    @GET("v2/usuarios/resendconfirmationemail")
    Call<String> resendEmail();

    @GET("v2/usuarios/renewToken")
    Call<String> renewBearerToken();

    @GET("v2/usuarios/activeUser")
    Call<String> getEstadoUsuario();

    @PUT("v2/usuarios/cambiarUsername/{username}")
    Call<String> changeUsername(@Path("username") String username);

    @PUT("v2/usuarios/cambiarNombreCompleto/{nombreCompleto}")
    Call<ResponseBody> changeNombreCompleto(@Path("nombreCompleto") String nombreCompleto);

    //Configuración de perfiles de usuario
    @POST("v2/alumnos")
    Call<ResponseBody> createStudent(@Body AlumnoDTO alumnoDTO);

    @PUT("v2/alumnos")
    Call<ResponseBody> updateAlumno(@Body AlumnoDTO alumnoDTO);

    @GET("v2/alumnos")
    Call<ResponseBody> getAlumno();

    @POST("v2/tutores")
    Call<ResponseBody> createTutor(@Body List<MateriaDTO> materiasDTO);

    @PUT("v2/tutores")
    Call<ResponseBody> updateTutor(@Body TutorDTO tutorDTO);

    @GET("v2/tutores/numeroAlumnos/{idUsuario}")
    Call<ResponseBody> getNumeroAlumnosTutor(@Path("idUsuario") Integer idUsuario);

    @GET("v2/tutores")
    Call<ResponseBody> getTutor();

    //Acciones referentes a los cursos
    @POST("v2/cursos")
    Call<ResponseBody> createCurso(@Body CursoDTO cursoDTO);

    @GET("v2/cursos/alumno")
    Call<ResponseBody> findCursosAlumno();

    @GET("v2/cursos/tutor")
    Call<ResponseBody> findCursosTutor();

    @GET("v2/cursos/tutor/cantidadCursos/{idUsuario}")
    Call<ResponseBody> getCantidadCursosTutor(@Path("idUsuario") Integer idUsuario);

    @GET("v2/cursos/alumno/cantidadCursos/{idUsuario}")
    Call<ResponseBody> getCantidadCursosAlumno(@Path("idUsuario") Integer idUsuario);

    @POST("v2/cursos/inviteStudent")
    Call<ResponseBody> invitarAlumnoToCurso(@Query("idUser") Integer idUser, @Query("idCourse") Integer idCourse);

    @DELETE("v2/cursos/refuseInvitacion/{idInvitacion}")
    Call<ResponseBody> rechazarInvitacion(@Path("idInvitacion") Integer idInvitacion);

    @PUT("v2/cursos/aceptarInvitacionCursoAlumno")
    Call<ResponseBody> aceptarInvitacionCursoAlumno(@Query("idAlertaCursoAlumno") Integer idAlertaCursoAlumno);

    @PUT("v2/cursos/eliminarAlumno")
    Call<ResponseBody> removeAlumnoFromCurso(@Query("idCurso") Integer idCurso, @Query("idAlumno") Integer idAlumno);

    @PUT("v2/cursos/changeTituloCurso")
    Call<ResponseBody> changeTituloCurso(@Query("idCurso") Integer idCurso, @Query("titulo") String titulo);

    //Acciones referentes a las materias
    @GET("v2/materias/tutor")
    Call<ResponseBody> findMateriasByTutor();

    @GET("v2/materias/nivelEstudios")
    Call<ResponseBody> findMateriasByNivelEstudiosId(@Query("idNivelEstudios") Integer idNivelEstudios);

    @GET("v2/materias/{id}")
    Call<ResponseBody> findMateriaById(@Path("id") Integer id);

    //Acciones referentes a las amistades
    @POST("v2/amistades")
    Call<ResponseBody> solicitarAmistad(@Body AmistadDTO amistadDTO);

    @GET("v2/amistades/{id}")
    Call<ResponseBody> findAmistadById(@Path("id") Integer idAmistad);

    @GET("v2/amistades/usuario")
    Call<ResponseBody> findAmistadesByUsuario();

    @GET("v2/amistades")
    Call<ResponseBody> getAmistades();

    @PUT("v2/amistades/accept")
    Call<ResponseBody> aceptarAmistad(@Query("idUsuarioAmistad") Integer idUsuarioAmistad);

    @DELETE("v2/amistades/remove")
    Call<ResponseBody> eliminarAmistad(@Query("idUsuarioAmistad") Integer idUsuarioAmistad);

    @GET("v2/amistades/amistadConUsuario/{idUsuario}")
    Call<ResponseBody> getEstadoAmistad(@Path("idUsuario") Integer idUsuario);

    @GET("v2/amistades/conAlumno")
    Call<ResponseBody> getAmistadesConAlumno();

    //Acciones referentes a los anuncios
    @GET("v2/anuncios/{id}")
    Call<ResponseBody> findAnuncioById(@Path("id") Integer idAnuncio);

    @GET("v2/anuncios")
    Call<ResponseBody> findAllAnuncios();

    @GET("v2/anuncios/usuario")
    Call<ResponseBody> findByUsuario();

    @DELETE("v2/anuncios/{idAnuncio}")
    Call<ResponseBody> deleteAnuncio(@Path("idAnuncio") Integer idAnuncio);

    @POST("v2/anuncios")
    Call<ResponseBody> createAnuncio(@Body AnuncioDTO anuncioDTO);

    //Acciones referentes a los eventos de calendario
    @POST("v2/eventosCalendario")
    Call<ResponseBody> createEventoCalendario(@Body EventoCalendarioDTO eventoCalendarioDTO);

    @GET("v2/eventosCalendario/perfilTutor")
    Call<ResponseBody> findByPerfilUsuarioTutor();

    @GET("v2/eventosCalendario/perfilAlumno")
    Call<ResponseBody> findByPerfilUsuarioAlumno();

    //Acciones referentes a las actividades
    @GET("v2/actividades/{id}")
    Call<ResponseBody> findByActividadId(@Path("id") Integer id);

    @POST("v2/actividades")
    Call<ResponseBody> createActividad(@Body ActividadDTO actividadDTO);

    @PUT("v2/actividades")
    Call<ResponseBody> updateActividad(@Body ActividadDTO actividadDTO);

    @DELETE("v2/actividades/{id}")
    Call<ResponseBody> removeActividad(@Path("id") Integer id);

    @GET("v2/actividades/numeroActividadesPendientes/{idUsuario}")
    Call<ResponseBody> getNumeroActividadesPendientes(@Path("idUsuario") Integer idUsuario);

    //Acciones referentes a las alertas
    @GET("v2/alertas/actividades")
    Call<ResponseBody> findAlertasActividadByUsuario();

    @GET("v2/alertas/amistades")
    Call<ResponseBody> findAlertasAmistadByUsuario();

    @GET("v2/alertas/invitacionesCursos")
    Call<ResponseBody> findAlertasCursoAlumnodByUsuario();

    //Acciones niveles de estudios
    @GET("v2/estudios")
    Call<ResponseBody> findAllNivelesEstudios();

}
