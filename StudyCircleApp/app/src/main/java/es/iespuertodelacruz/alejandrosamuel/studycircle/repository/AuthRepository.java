package es.iespuertodelacruz.alejandrosamuel.studycircle.repository;

import static android.content.ContentValues.TAG;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Objects;

import es.iespuertodelacruz.alejandrosamuel.studycircle.data.db.DatabaseStudyCircle;
import es.iespuertodelacruz.alejandrosamuel.studycircle.data.enums.EstadosUsuario;
import es.iespuertodelacruz.alejandrosamuel.studycircle.data.enums.RespuestasAnuncio;
import es.iespuertodelacruz.alejandrosamuel.studycircle.data.enums.RespuestasAuthToken;
import es.iespuertodelacruz.alejandrosamuel.studycircle.data.enums.RespuestasRegister;
import es.iespuertodelacruz.alejandrosamuel.studycircle.data.enums.RespuestasUsuario;
import es.iespuertodelacruz.alejandrosamuel.studycircle.data.rest.RESTService;
import es.iespuertodelacruz.alejandrosamuel.studycircle.data.rest.RetrofitClient;
import es.iespuertodelacruz.alejandrosamuel.studycircle.data.rest.dto.AnuncioDTO;
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

    public LiveData<Object> getPerfilesUsuarios(String token) {
        restAuthService = RetrofitClient.getInstance(token).getAuthRestService();
        MutableLiveData<Object> mutableUsuarios = new MutableLiveData<>();
        Call<ResponseBody> callFindUsuarios = restAuthService.getPerfilesUsuarios();
        callFindUsuarios.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(@NonNull Call<ResponseBody> call,
                                   Response<ResponseBody> response) {
                ResponseBody body = response.body();
                if(response.isSuccessful()) {
                    Gson gson = new Gson();
                    Type listType = new TypeToken<List<UsuarioDTO>>() {}.getType();
                    List<UsuarioDTO> usuariosDTO = new Gson().fromJson(body.charStream(), listType);
                    mutableUsuarios.setValue(usuariosDTO);
                }else {
                    if(response.code() == 403) {
                        mutableUsuarios.setValue(null);
                    }
                }
            }
            @Override
            public void onFailure(@NonNull Call<ResponseBody> call, Throwable t) {
                Log.e(TAG, "Error en la llamada a la API: " + t.getMessage());
                t.printStackTrace();
            }
        });
        return mutableUsuarios;
    }

    public LiveData<Object> getUsuario(String token) {
        restAuthService = RetrofitClient.getCleanNewInstance(token).getAuthRestService();
        MutableLiveData<Object> mutableUsuario = new MutableLiveData<>();
        Call<ResponseBody> callGetUsuario = restAuthService.getUsuario();
        callGetUsuario.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(@NonNull Call<ResponseBody> call,
                                   Response<ResponseBody> response) {
                ResponseBody body = response.body();
                if(response.isSuccessful()) {
                    Gson gson = new Gson();
                    UsuarioDTO usuarioDTO = gson.fromJson(body.charStream(), UsuarioDTO.class);
                    mutableUsuario.setValue(usuarioDTO);
                }else {
                    if(response.code() == 403) {
                        mutableUsuario.setValue(null);
                    }
                }
            }
            @Override
            public void onFailure(@NonNull Call<ResponseBody> call, Throwable t) {
                Log.e(TAG, "Error en la llamada a la API: " + t.getMessage());
                t.printStackTrace();
            }
        });
        return mutableUsuario;
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
                    RespuestasAuthToken respuestasAuthToken = null;
                    try {
                        respuesta = response.errorBody().string();
                        respuestasAuthToken = RespuestasAuthToken.valueOf(respuesta);
                    } catch (IOException | IllegalArgumentException ignored) {}
                    if(Objects.nonNull(respuestasAuthToken)) {
                        if (Objects.requireNonNull(respuestasAuthToken) == RespuestasAuthToken.INVALID_USERNAME_OR_PASSWORD) {
                            mutableToken.setValue(RespuestasAuthToken.INVALID_USERNAME_OR_PASSWORD);
                        }
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
                    RespuestasRegister respuestasRegister = null;
                    try {
                        respuesta = response.errorBody().string();
                        respuestasRegister = RespuestasRegister.valueOf(respuesta);
                    } catch (IOException | IllegalArgumentException ignored) {}
                    if(Objects.nonNull(respuestasRegister)) {
                        switch (Objects.requireNonNull(respuestasRegister)) {
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
                    } catch (Exception ignored) {}
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
                mutableEstadoUsuario.setValue(null);
                Log.e(TAG, "Error en la llamada a la API: " + t.getMessage());
                t.printStackTrace();
            }
        });
        return mutableEstadoUsuario;
    }

    public LiveData<Object> changeUsername(String username, String token) {
        restAuthService = RetrofitClient.getInstance(token).getAuthRestService();
        MutableLiveData<Object> mutableUsuario = new MutableLiveData<>();
        Call<String> responseChangeUsername = restAuthService.changeUsername(username);
        responseChangeUsername.enqueue(new Callback<String>() {
            @Override
            public void onResponse(@NonNull Call<String> call,
                                   @NonNull Response<String> response) {
                String body = response.body();
                if(response.isSuccessful()) {
                    mutableUsuario.setValue(body);
                }else {
                    String respuesta;
                    try {
                        respuesta = Objects.requireNonNull(response.errorBody()).string();
                        RespuestasUsuario respuestasUsuario = RespuestasUsuario.valueOf(respuesta);
                        switch (respuestasUsuario) {
                            case USER_OR_USERNAME_NOT_VALID:
                                mutableUsuario.setValue(RespuestasUsuario.USER_OR_USERNAME_NOT_VALID);
                                break;
                            default:
                        }
                    } catch (Exception ignored) {}

                    if(response.code() == 403) {
                        mutableUsuario.setValue(null);
                    }
                }
            }
            @Override
            public void onFailure(@NonNull Call<String> call, Throwable t) {
                mutableUsuario.setValue(null);
                Log.e(TAG, "Error en la llamada a la API: " + t.getMessage());
                t.printStackTrace();
            }
        });
        return mutableUsuario;
    }

    public LiveData<Object> changeNombreCompleto(String nombreCompleto, String token) {
        restAuthService = RetrofitClient.getInstance(token).getAuthRestService();
        MutableLiveData<Object> mutableUsuario = new MutableLiveData<>();
        Call<ResponseBody> responseChangeUsername = restAuthService.changeNombreCompleto(nombreCompleto);
        responseChangeUsername.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(@NonNull Call<ResponseBody> call,
                                   @NonNull Response<ResponseBody> response) {
                ResponseBody body = response.body();
                if(response.isSuccessful()) {
                    Gson gson = new Gson();
                    UsuarioDTO usuarioDTO = gson.fromJson(body.charStream(), UsuarioDTO.class);
                    mutableUsuario.setValue(usuarioDTO);
                }else {
                    String respuesta;
                    try {
                        respuesta = Objects.requireNonNull(response.errorBody()).string();
                        RespuestasUsuario respuestasUsuario = RespuestasUsuario.valueOf(respuesta);
                        switch (respuestasUsuario) {
                            case USER_OR_NOMBRE_COMPLETO_NOT_VALID:
                                mutableUsuario.setValue(RespuestasUsuario.USER_OR_NOMBRE_COMPLETO_NOT_VALID);
                                break;
                            default:
                        }
                    } catch (Exception ignored) {}

                    if(response.code() == 403) {
                        mutableUsuario.setValue(null);
                    }
                }
            }
            @Override
            public void onFailure(@NonNull Call<ResponseBody> call, Throwable t) {
                mutableUsuario.setValue(null);
                Log.e(TAG, "Error en la llamada a la API: " + t.getMessage());
                t.printStackTrace();
            }
        });
        return mutableUsuario;
    }

}
