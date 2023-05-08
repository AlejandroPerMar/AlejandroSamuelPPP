package es.iespuertodelacruz.alejandrosamuel.studycircle.data.rest;

import java.util.List;

import es.iespuertodelacruz.alejandrosamuel.studycircle.data.rest.dto.AlumnoDTO;
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

    @GET("v2/cursos/tutor")
    Call<ResponseBody> findCursosTutor();

    @PUT("v2/cursos/agregarAlumno")
    Call<ResponseBody> addAlumnoToCurso(@Query("idCurso") Integer idCurso, @Query("idAlumno") Integer idAlumno);

    @PUT("v2/cursos/eliminarAlumno")
    Call<ResponseBody> removeAlumnoToCurso(@Query("idCurso") Integer idCurso, @Query("idAlumno") Integer idAlumno);
}
