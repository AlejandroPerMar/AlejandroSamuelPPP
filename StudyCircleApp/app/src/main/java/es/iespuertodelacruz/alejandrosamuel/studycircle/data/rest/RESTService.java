package es.iespuertodelacruz.alejandrosamuel.studycircle.data.rest;

import es.iespuertodelacruz.alejandrosamuel.studycircle.data.rest.dto.UsuarioLoginDTO;
import retrofit2.Call;
import retrofit2.http.*;

public interface RESTService {
    @POST("login")
    Call<String> login(@Body UsuarioLoginDTO usuarioLoginDTO);
}
