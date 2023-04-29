package es.iespuertodelacruz.alejandrosamuel.studycircle.data.rest;

import es.iespuertodelacruz.alejandrosamuel.studycircle.data.rest.dto.UsuarioLoginDTO;
import es.iespuertodelacruz.alejandrosamuel.studycircle.data.rest.dto.UsuarioRegisterDTO;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.*;

public interface RESTService {
    @POST("login")
    Call<String> login(@Body UsuarioLoginDTO usuarioLoginDTO);

    @POST("register")
    Call<Object> register(@Body UsuarioRegisterDTO usuarioRegisterDTO);

    @GET("v2/usuarios/resendconfirmationemail")
    Call<ResponseBody> resendEmail();
}
