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
import es.iespuertodelacruz.alejandrosamuel.studycircle.data.enums.RespuestasAuthToken;
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
    }

    public LiveData<Object> getAuthToken(UsuarioLoginDTO usuarioLoginDTO) {
        restNoAuthService = RetrofitClient.getInstance().getNoAuthRestService();
        MutableLiveData<Object> mutableToken = new MutableLiveData<>();
        Call<String> callToken = restNoAuthService.login(usuarioLoginDTO);
        callToken.enqueue(new Callback<String>() {
            @Override
            public void onResponse(@NonNull Call<String> call,
                                   Response<String> response) {
                if(response.isSuccessful()) {
                    String tokenResponse = response.body();
                    mutableToken.setValue(tokenResponse);
                }else {
                    String respuesta;
                    try {
                        respuesta = response.errorBody().string();
                    } catch (IOException e) {
                        respuesta = null;
                    }
                    switch (RespuestasAuthToken.valueOf(respuesta)) {
                        case INVALID_USERNAME_OR_PASSWORD:
                            mutableToken.setValue(RespuestasAuthToken.INVALID_USERNAME_OR_PASSWORD);
                            break;
                        default:
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

    public LiveData<Object> registerUsuario(UsuarioRegisterDTO usuarioRegisterDTO) {
        restNoAuthService = RetrofitClient.getInstance().getNoAuthRestService();
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
                            mutableRespuesta.setValue(RespuestasRegister.INVALID_NAME);
                            break;
                        case INVALID_EMAIL:
                            mutableRespuesta.setValue(RespuestasRegister.INVALID_EMAIL);
                            break;
                        case NOT_AVAILABLE_USERNAME:
                            mutableRespuesta.setValue(RespuestasRegister.NOT_AVAILABLE_USERNAME);
                            break;
                        case NOT_MINIMUN_REQUIREMENTS_PASSWORD:
                            mutableRespuesta.setValue(RespuestasRegister.NOT_MINIMUN_REQUIREMENTS_PASSWORD);
                            break;
                        case NOT_AVAILABLE_EMAIL:
                            mutableRespuesta.setValue(RespuestasRegister.NOT_AVAILABLE_EMAIL);
                        default:
                    }

                    if(response.code() == 403) {
                        mutableRespuesta.setValue(null);
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

    public LiveData<Object> resendEmail(String token) {
        restAuthService = RetrofitClient.getInstance(token).getAuthRestService();
        MutableLiveData<Object> mutableTokenAuth = new MutableLiveData<>();
        Call<String> responseResendEmail = restAuthService.resendEmail();
        responseResendEmail.enqueue(new Callback<String>() {
            @Override
            public void onResponse(@NonNull Call<String> call,
                                   @NonNull Response<String> response) {
                String respuesta = response.body();
                if(response.isSuccessful()) {
                    mutableTokenAuth.setValue(respuesta);
                }else {
                    try {
                        respuesta = Objects.requireNonNull(response.errorBody()).string();
                        switch (EstadosUsuario.valueOf(respuesta)) {
                            case STATUS_INACTIVE:
                                mutableTokenAuth.setValue(EstadosUsuario.STATUS_INACTIVE);
                                break;
                            case STATUS_BAN:
                                mutableTokenAuth.setValue(EstadosUsuario.STATUS_BAN);
                                break;
                            case STATUS_ACTIVE:
                                mutableTokenAuth.setValue(EstadosUsuario.STATUS_ACTIVE);
                                break;
                            default:
                        }
                    } catch (IOException ignored) {}
                    if(response.code() == 403) {
                        mutableTokenAuth.setValue(null);
                    }
                }
            }
            @Override
            public void onFailure(@NonNull Call<String> call, Throwable t) {
                Log.e(TAG, "Error en la llamada a la API: " + t.getMessage());
                t.printStackTrace();
            }
        });
        return mutableTokenAuth;
    }

    public LiveData<Object> renewBearerToken(String token) {
        restAuthService = RetrofitClient.getInstance(token).getAuthRestService();
        MutableLiveData<Object> mutableTokenAuth = new MutableLiveData<>();
        Call<String> responseRenewBearerToken = restAuthService.renewBearerToken();
        responseRenewBearerToken.enqueue(new Callback<String>() {
            @Override
            public void onResponse(@NonNull Call<String> call,
                                   @NonNull Response<String> response) {
                String respuesta = response.body();
                if(response.isSuccessful()) {
                    mutableTokenAuth.setValue(respuesta);
                }else {
                    mutableTokenAuth.setValue(null);
                }
            }
            @Override
            public void onFailure(@NonNull Call<String> call, Throwable t) {
                Log.e(TAG, "Error en la llamada a la API: " + t.getMessage());
                t.printStackTrace();
            }
        });
        return mutableTokenAuth;
    }

    public LiveData<Object> getEstadoUsuario(String token) {
        restAuthService = RetrofitClient.getInstance(token).getAuthRestService();
        MutableLiveData<Object> mutableEstadoUsuario = new MutableLiveData<>();
        Call<String> responseGetEstadoUsuario = restAuthService.getEstadoUsuario();
        responseGetEstadoUsuario.enqueue(new Callback<String>() {
            @Override
            public void onResponse(@NonNull Call<String> call,
                                   @NonNull Response<String> response) {
                String respuesta = response.body();
                if(response.isSuccessful()) {
                    mutableEstadoUsuario.setValue(respuesta);
                }else {
                    mutableEstadoUsuario.setValue(null);
                }
            }
            @Override
            public void onFailure(@NonNull Call<String> call, Throwable t) {
                Log.e(TAG, "Error en la llamada a la API: " + t.getMessage());
                t.printStackTrace();
            }
        });
        return mutableEstadoUsuario;
    }

}
