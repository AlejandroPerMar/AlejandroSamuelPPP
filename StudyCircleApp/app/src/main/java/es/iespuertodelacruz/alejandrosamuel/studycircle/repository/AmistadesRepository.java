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
import es.iespuertodelacruz.alejandrosamuel.studycircle.data.enums.RespuestasAmistad;
import es.iespuertodelacruz.alejandrosamuel.studycircle.data.rest.RESTService;
import es.iespuertodelacruz.alejandrosamuel.studycircle.data.rest.RetrofitClient;
import es.iespuertodelacruz.alejandrosamuel.studycircle.data.rest.dto.AmistadDTO;
import es.iespuertodelacruz.alejandrosamuel.studycircle.data.rest.dto.UsuarioDTO;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AmistadesRepository {

    RESTService restAuthService;
    RESTService restNoAuthService;
    private final DatabaseStudyCircle database;

    public AmistadesRepository(Application application) {
        database = DatabaseStudyCircle.getDatabase(application);
    }

    public LiveData<Object> solicitarAmistad(AmistadDTO amistadDTO, String token) {
        restAuthService = RetrofitClient.getInstance(token).getAuthRestService();
        MutableLiveData<Object> mutableAmistad = new MutableLiveData<>();
        Call<ResponseBody> callSolicitarAmistad = restAuthService.solicitarAmistad(amistadDTO);
        callSolicitarAmistad.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(@NonNull Call<ResponseBody> call,
                                   Response<ResponseBody> response) {
                ResponseBody body = response.body();
                if(response.isSuccessful()) {
                    Gson gson = new Gson();
                    AmistadDTO amistadDTO = gson.fromJson(body.charStream(), AmistadDTO.class);
                    mutableAmistad.setValue(amistadDTO);
                }else {
                    String respuesta;
                    try {
                        respuesta = Objects.requireNonNull(response.errorBody()).string();
                        RespuestasAmistad respuestasAmistad = RespuestasAmistad.valueOf(respuesta);
                        switch (respuestasAmistad) {
                            case NON_EXISTING_USER1_OR_USER2:
                                mutableAmistad.setValue(RespuestasAmistad.NON_EXISTING_USER1_OR_USER2);
                                break;
                            case ALREADY_EXISTING_FRIENDSHIP:
                                mutableAmistad.setValue(RespuestasAmistad.ALREADY_EXISTING_FRIENDSHIP);
                                break;
                            case FORBIDDEN_FRIENDSHIP_FOR_USER:
                                mutableAmistad.setValue(RespuestasAmistad.FORBIDDEN_FRIENDSHIP_FOR_USER);
                                break;
                            case INVALID_FRIENDSHIP_FORMAT:
                                mutableAmistad.setValue(RespuestasAmistad.INVALID_FRIENDSHIP_FORMAT);
                                break;
                            default:
                        }
                    } catch (Exception ignored) {}

                    if(response.code() == 403) {
                        mutableAmistad.setValue(null);
                    }
                }
            }
            @Override
            public void onFailure(@NonNull Call<ResponseBody> call, Throwable t) {
                Log.e(TAG, "Error en la llamada a la API: " + t.getMessage());
                t.printStackTrace();
            }
        });
        return mutableAmistad;
    }

    public LiveData<Object> findAmistadById(Integer idAmistad, String token) {
        restAuthService = RetrofitClient.getInstance(token).getAuthRestService();
        MutableLiveData<Object> mutableAmistad = new MutableLiveData<>();
        Call<ResponseBody> callFindAmistadById = restAuthService.findAmistadById(idAmistad);
        callFindAmistadById.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(@NonNull Call<ResponseBody> call,
                                   Response<ResponseBody> response) {
                ResponseBody body = response.body();
                if(response.isSuccessful()) {
                    Gson gson = new Gson();
                    AmistadDTO amistadDTO = gson.fromJson(body.charStream(), AmistadDTO.class);
                    mutableAmistad.setValue(amistadDTO);
                }else {
                    String respuesta;
                    try {
                        respuesta = Objects.requireNonNull(response.errorBody()).string();
                        RespuestasAmistad respuestasAmistad = RespuestasAmistad.valueOf(respuesta);
                        switch (respuestasAmistad) {
                            case FORBIDDEN_FRIENDSHIP_FOR_USER:
                                mutableAmistad.setValue(RespuestasAmistad.FORBIDDEN_FRIENDSHIP_FOR_USER);
                                break;
                            default:
                        }
                    } catch (Exception ignored) {}

                    if(response.code() == 403) {
                        mutableAmistad.setValue(null);
                    }
                }
            }
            @Override
            public void onFailure(@NonNull Call<ResponseBody> call, Throwable t) {
                Log.e(TAG, "Error en la llamada a la API: " + t.getMessage());
                t.printStackTrace();
            }
        });
        return mutableAmistad;
    }

    public LiveData<Object> findAmistadesByUsuario(String token) {
        restAuthService = RetrofitClient.getInstance(token).getAuthRestService();
        MutableLiveData<Object> mutableAmistades = new MutableLiveData<>();
        Call<ResponseBody> callFindAmistadesByUsuario = restAuthService.findAmistadesByUsuario();
        callFindAmistadesByUsuario.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(@NonNull Call<ResponseBody> call,
                                   Response<ResponseBody> response) {
                ResponseBody body = response.body();
                if(response.isSuccessful()) {
                    Gson gson = new Gson();
                    Type listType = new TypeToken<List<UsuarioDTO>>() {}.getType();
                    List<UsuarioDTO> amistadesDTO = new Gson().fromJson(body.charStream(), listType);
                    mutableAmistades.setValue(amistadesDTO);
                }else {

                    if(response.code() == 403) {
                        mutableAmistades.setValue(null);
                    }
                }
            }
            @Override
            public void onFailure(@NonNull Call<ResponseBody> call, Throwable t) {
                Log.e(TAG, "Error en la llamada a la API: " + t.getMessage());
                t.printStackTrace();
            }
        });
        return mutableAmistades;
    }

    public LiveData<Object> aceptarAmistad(Integer idUsuarioAmistad, String token) {
        restAuthService = RetrofitClient.getInstance(token).getAuthRestService();
        MutableLiveData<Object> mutableAmistad = new MutableLiveData<>();
        Call<ResponseBody> callAceptarAmistad = restAuthService.aceptarAmistad(idUsuarioAmistad);
        callAceptarAmistad.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(@NonNull Call<ResponseBody> call,
                                   Response<ResponseBody> response) {
                ResponseBody body = response.body();
                if(response.isSuccessful()) {
                    Gson gson = new Gson();
                    AmistadDTO amistadDTO = gson.fromJson(body.charStream(), AmistadDTO.class);
                    mutableAmistad.setValue(amistadDTO);
                }else {
                    String respuesta;
                    try {
                        respuesta = Objects.requireNonNull(response.errorBody()).string();
                        RespuestasAmistad respuestasAmistad = RespuestasAmistad.valueOf(respuesta);
                        switch (respuestasAmistad) {
                            case ALREADY_ACCEPTED_FRIENDSHIP:
                                mutableAmistad.setValue(RespuestasAmistad.ALREADY_ACCEPTED_FRIENDSHIP);
                                break;
                            case FORBIDDEN_FRIENDSHIP_FOR_USER:
                                mutableAmistad.setValue(RespuestasAmistad.FORBIDDEN_FRIENDSHIP_FOR_USER);
                                break;
                            default:
                        }
                    } catch (Exception ignored) {}

                    if(response.code() == 403) {
                        mutableAmistad.setValue(null);
                    }
                }
            }
            @Override
            public void onFailure(@NonNull Call<ResponseBody> call, Throwable t) {
                Log.e(TAG, "Error en la llamada a la API: " + t.getMessage());
                t.printStackTrace();
            }
        });
        return mutableAmistad;
    }

    public LiveData<Object> eliminarAmistad(Integer idUsuarioAmistad, String token) {
        restAuthService = RetrofitClient.getInstance(token).getAuthRestService();
        MutableLiveData<Object> mutableAmistades = new MutableLiveData<>();
        Call<ResponseBody> callFindAmistadesByUsuario = restAuthService.eliminarAmistad(idUsuarioAmistad);
        callFindAmistadesByUsuario.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(@NonNull Call<ResponseBody> call,
                                   Response<ResponseBody> response) {
                ResponseBody body = response.body();
                if(response.isSuccessful()) {
                    try {
                        RespuestasAmistad respuestasAmistad = RespuestasAmistad.valueOf(body.string());
                        mutableAmistades.setValue(respuestasAmistad);
                    } catch (IOException | IllegalArgumentException ignored) {}
                }else {
                    String respuesta;
                    try {
                        respuesta = Objects.requireNonNull(response.errorBody()).string();
                        RespuestasAmistad respuestasAmistad = RespuestasAmistad.valueOf(respuesta);
                        if (respuestasAmistad == RespuestasAmistad.FORBIDDEN_FRIENDSHIP_FOR_USER) {
                            mutableAmistades.setValue(RespuestasAmistad.FORBIDDEN_FRIENDSHIP_FOR_USER);
                        }
                    } catch (Exception ignored) {}

                    if(response.code() == 403) {
                        mutableAmistades.setValue(null);
                    }
                }
            }
            @Override
            public void onFailure(@NonNull Call<ResponseBody> call, Throwable t) {
                Log.e(TAG, "Error en la llamada a la API: " + t.getMessage());
                t.printStackTrace();
            }
        });
        return mutableAmistades;
    }

    public LiveData<Object> getAmistadConUsuario(Integer idUsuario, String token) {
        restAuthService = RetrofitClient.getInstance(token).getAuthRestService();
        MutableLiveData<Object> mutableAmistad = new MutableLiveData<>();
        Call<ResponseBody> callGetAmistad = restAuthService.getEstadoAmistad(idUsuario);
        callGetAmistad.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(@NonNull Call<ResponseBody> call,
                                   Response<ResponseBody> response) {
                ResponseBody body = response.body();
                if(response.isSuccessful()) {
                    Gson gson = new Gson();
                    AmistadDTO amistadDTO = gson.fromJson(body.charStream(), AmistadDTO.class);
                    mutableAmistad.setValue(amistadDTO);
                }else {
                    String respuesta;
                    try {
                        respuesta = Objects.requireNonNull(response.errorBody()).string();
                        RespuestasAmistad respuestasAmistad = RespuestasAmistad.valueOf(respuesta);
                        if (respuestasAmistad == RespuestasAmistad.NON_EXISTING_USER1_OR_USER2) {
                            mutableAmistad.setValue(RespuestasAmistad.NON_EXISTING_USER1_OR_USER2);
                        }
                        if (respuestasAmistad == RespuestasAmistad.FORBIDDEN_FRIENDSHIP_FOR_USER) {
                            mutableAmistad.setValue(RespuestasAmistad.FORBIDDEN_FRIENDSHIP_FOR_USER);
                        }
                    } catch (Exception ignored) {}

                    if(response.code() == 403) {
                        mutableAmistad.setValue(null);
                    }
                }
            }
            @Override
            public void onFailure(@NonNull Call<ResponseBody> call, Throwable t) {
                Log.e(TAG, "Error en la llamada a la API: " + t.getMessage());
                t.printStackTrace();
            }
        });
        return mutableAmistad;
    }
}
