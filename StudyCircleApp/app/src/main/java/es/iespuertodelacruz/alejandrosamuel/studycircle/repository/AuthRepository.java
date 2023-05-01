package es.iespuertodelacruz.alejandrosamuel.studycircle.repository;

import static android.content.ContentValues.TAG;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;


import com.google.gson.Gson;

import java.io.IOException;
import java.util.Objects;

import es.iespuertodelacruz.alejandrosamuel.studycircle.data.db.DatabaseStudyCircle;
import es.iespuertodelacruz.alejandrosamuel.studycircle.data.enums.EstadosUsuario;
import es.iespuertodelacruz.alejandrosamuel.studycircle.data.enums.RespuestasRegister;
import es.iespuertodelacruz.alejandrosamuel.studycircle.data.rest.RESTService;
import es.iespuertodelacruz.alejandrosamuel.studycircle.data.rest.RetrofitClient;
import es.iespuertodelacruz.alejandrosamuel.studycircle.data.rest.dto.UsuarioDTO;
import es.iespuertodelacruz.alejandrosamuel.studycircle.data.rest.dto.UsuarioLoginDTO;
import es.iespuertodelacruz.alejandrosamuel.studycircle.data.rest.dto.UsuarioRegisterDTO;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AuthRepository {
    RESTService restAuthService;
    RESTService restNoAuthService;
    private final DatabaseStudyCircle database;

    public AuthRepository(Application application) {
        database = DatabaseStudyCircle.getDatabase(application);
        restNoAuthService = RetrofitClient.getInstance().getNoAuthRestService();
    }

    public LiveData<String> getAuthToken(UsuarioLoginDTO usuarioLoginDTO) {
        MutableLiveData<String> mutableToken = new MutableLiveData<>();
        Call<String> callToken = restNoAuthService.login(usuarioLoginDTO);
        callToken.enqueue(new Callback<String>() {
            @Override
            public void onResponse(@NonNull Call<String> call,
                                   Response<String> response) {
                if(response.isSuccessful()) {
                    String tokenResponse = "Bearer " + response.body();
                    System.out.println(tokenResponse);
                    mutableToken.setValue(tokenResponse);
                }
            }
            @Override
            public void onFailure(@NonNull Call<String> call, Throwable t) {
                System.out.println("Error en la llamada");
                System.out.println(t.getMessage());
            }
        });
        return mutableToken;
    }

    public LiveData<Object> registerUsuario(UsuarioRegisterDTO usuarioRegisterDTO) {
        MutableLiveData<Object> mutableRespuesta = new MutableLiveData<>();
        Call<ResponseBody> register = restNoAuthService.register(usuarioRegisterDTO);
        register.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(@NonNull Call<ResponseBody> call,
                                   Response<ResponseBody> response) {
                ResponseBody body = response.body();
                if(response.isSuccessful()) {
                    Gson gson = new Gson();
                    UsuarioDTO usuarioDTO = gson.fromJson(body.charStream(), UsuarioDTO.class);
                    mutableRespuesta.setValue(usuarioDTO);
                }else {
                    String respuesta;
                    try {
                        respuesta = response.errorBody().string();
                    } catch (IOException e) {
                        respuesta = null;
                    }
                    switch (RespuestasRegister.valueOf(respuesta)) {
                        case INVALID_NAME:
                            System.out.println(RespuestasRegister.INVALID_NAME.getDescripcion());
                            break;
                        case INVALID_EMAIL:
                            System.out.println(RespuestasRegister.INVALID_EMAIL.getDescripcion());
                            break;
                        case NOT_AVAILABLE_USERNAME:
                            System.out.println(RespuestasRegister.NOT_AVAILABLE_USERNAME.getDescripcion());
                            break;
                        case NOT_MINIMUN_REQUIREMENTS_PASSWORD:
                            System.out.println(RespuestasRegister.NOT_MINIMUN_REQUIREMENTS_PASSWORD.getDescripcion());
                            break;
                        default:
                    }
                }
            }
            @Override
            public void onFailure(@NonNull Call<ResponseBody> call, Throwable t) {
                Log.e(TAG, "Error en la llamada a la API: " + t.getMessage());
                t.printStackTrace();
            }
        });
        return mutableRespuesta;
    }

    public LiveData<String> resendEmail(String token) {
        restAuthService = RetrofitClient.getInstance(token).getAuthRestService();
        MutableLiveData<String> mutableToken = new MutableLiveData<>();
        Call<String> responseResendEmail = restAuthService.resendEmail();
        responseResendEmail.enqueue(new Callback<String>() {
            @Override
            public void onResponse(@NonNull Call<String> call,
                                   @NonNull Response<String> response) {
                String respuesta;
                respuesta = response.body();
                if(response.isSuccessful()) {
                    respuesta = Objects.nonNull(respuesta) ? "token de verificación: " + respuesta : "No respuesta";
                    System.out.println(respuesta);
                }else {
                    try {
                        respuesta = response.errorBody().string();
                        System.out.println(respuesta);
                        switch (EstadosUsuario.valueOf(respuesta)) {
                            case STATUS_INACTIVE:
                                System.out.println(EstadosUsuario.STATUS_INACTIVE.getDescripcion());
                                break;
                            case STATUS_BAN:
                                System.out.println(EstadosUsuario.STATUS_BAN.getDescripcion());
                                break;
                            case STATUS_ACTIVE:
                                System.out.println(EstadosUsuario.STATUS_ACTIVE.getDescripcion());
                                break;
                            default:
                                System.out.println("Error desconocido");
                        }
                    } catch (IOException e) {
                        respuesta = null;
                    }
                }
            }
            @Override
            public void onFailure(@NonNull Call<String> call, Throwable t) {
                Log.e(TAG, "Error en la llamada a la API: " + t.getMessage());
                t.printStackTrace();
            }
        });
        return mutableToken;
    }

}
