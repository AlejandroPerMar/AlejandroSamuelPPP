package es.iespuertodelacruz.alejandrosamuel.studycircle.data.rest;

import java.util.List;

import es.iespuertodelacruz.alejandrosamuel.studycircle.data.rest.dto.AlumnoDTO;
import es.iespuertodelacruz.alejandrosamuel.studycircle.data.rest.dto.AmistadDTO;
import es.iespuertodelacruz.alejandrosamuel.studycircle.data.rest.dto.CursoDTO;
import es.iespuertodelacruz.alejandrosamuel.studycircle.data.rest.dto.MateriaDTO;
import es.iespuertodelacruz.alejandrosamuel.studycircle.data.rest.dto.UsuarioLoginDTO;
import es.iespuertodelacruz.alejandrosamuel.studycircle.data.rest.dto.UsuarioRegisterDTO;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.*;

public interface RESTService {
    @POST("login")
    Call<String> login(@Body UsuarioLoginDTO usuarioLoginDTO);

    @POST("register")
    Call<ResponseBody> register(@Body UsuarioRegisterDTO usuarioRegisterDTO);

    @GET("v2/usuarios/resendconfirmationemail")
    Call<String> resendEmail();

    @POST("v2/alumnos")
    Call<ResponseBody> createStudent(@Body AlumnoDTO alumnoDTO);

    @POST("v2/tutores")
    Call<ResponseBody> createTutor(@Body List<MateriaDTO> materiasDTO);

    @GET("v2/cursos/alumno")
    Call<ResponseBody> findCursosAlumno();

    @GET("v2/cursos/tutor")
    Call<ResponseBody> findCursosTutor();

    @PUT("v2/cursos/agregarAlumno")
    Call<ResponseBody> addAlumnoToCurso(@Query("idCurso") Integer idCurso, @Query("idAlumno") Integer idAlumno);

    @PUT("v2/cursos/eliminarAlumno")
    Call<ResponseBody> removeAlumnoFromCurso(@Query("idCurso") Integer idCurso, @Query("idAlumno") Integer idAlumno);

    @PUT("v2/cursos/changeTituloCurso")
    Call<ResponseBody> changeTituloCurso(@Query("idCurso") Integer idCurso, @Query("titulo") String titulo);

    @GET("v2/materias/tutor")
    Call<ResponseBody> findMateriasByTutor();

    @POST("v2/cursos")
    Call<ResponseBody> createCurso(@Body CursoDTO cursoDTO);

    @POST("v2/amistades")
    Call<ResponseBody> solicitarAmistad(@Body AmistadDTO amistadDTO);

    @GET("v2/amistades")
    Call<ResponseBody> findAmistadById(@Query("idAmistad") Integer idAmistad);

    @GET("v2/amistades/usuario")
    Call<ResponseBody> findAmistadesByUsuario();

    @GET("v2/amistades/usuario")
    Call<ResponseBody> aceptarAmistad(@Query("idUsuarioAmistad") Integer idUsuarioAmistad);
}
