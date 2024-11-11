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
import es.iespuertodelacruz.alejandrosamuel.studycircle.data.enums.RespuestasActividad;
import es.iespuertodelacruz.alejandrosamuel.studycircle.data.rest.RESTService;
import es.iespuertodelacruz.alejandrosamuel.studycircle.data.rest.RetrofitClient;
import es.iespuertodelacruz.alejandrosamuel.studycircle.data.rest.dto.ActividadDTO;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ActividadesRepository {

    RESTService restAuthService;
    RESTService restNoAuthService;
    private final DatabaseStudyCircle database;

    public ActividadesRepository(Application application) {
        database = DatabaseStudyCircle.getDatabase(application);
    }

    public LiveData<Object> findActividadById(Integer id, String token) {
        restAuthService = RetrofitClient.getInstance(token).getAuthRestService();
        MutableLiveData<Object> mutableActividad = new MutableLiveData<>();
        Call<ResponseBody> callFindByActividadId = restAuthService.findByActividadId(id);
        callFindByActividadId.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(@NonNull Call<ResponseBody> call,
                                   Response<ResponseBody> response) {
                ResponseBody body = response.body();
                if(response.isSuccessful()) {
                    Gson gson = new Gson();
                    ActividadDTO actividadDTO = gson.fromJson(body.charStream(), ActividadDTO.class);
                    mutableActividad.setValue(actividadDTO);
                }else {
                    String respuesta;
                    try {
                        respuesta = Objects.requireNonNull(response.errorBody()).string();
                        RespuestasActividad respuestasActividad = RespuestasActividad.valueOf(respuesta);
                        switch (respuestasActividad) {
                            case ACTIVITY_NOT_FOUND:
                                mutableActividad.setValue(RespuestasActividad.ACTIVITY_NOT_FOUND);
                                break;
                            case ACTIVITY_FORBIDDEN:
                                mutableActividad.setValue(RespuestasActividad.ACTIVITY_FORBIDDEN);
                                break;
                            default:
                        }
                    } catch (Exception ignored) {}

                    if(response.code() == 403) {
                        mutableActividad.setValue(null);
                    }
                }
            }
            @Override
            public void onFailure(@NonNull Call<ResponseBody> call, Throwable t) {
                Log.e(TAG, "Error en la llamada a la API: " + t.getMessage());
                t.printStackTrace();
            }
        });
        return mutableActividad;
    }

    public LiveData<Object> createActividad(ActividadDTO actividadDTO, String token) {
        restAuthService = RetrofitClient.getInstance(token).getAuthRestService();
        MutableLiveData<Object> mutableActividad = new MutableLiveData<>();
        Call<ResponseBody> callCreateActividad = restAuthService.createActividad(actividadDTO);
        callCreateActividad.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(@NonNull Call<ResponseBody> call,
                                   Response<ResponseBody> response) {
                ResponseBody body = response.body();
                if(response.isSuccessful()) {
                    Gson gson = new Gson();
                    ActividadDTO actividadDTO = gson.fromJson(body.charStream(), ActividadDTO.class);
                    mutableActividad.setValue(actividadDTO);
                }else {
                    String respuesta;
                    try {
                        respuesta = Objects.requireNonNull(response.errorBody()).string();
                        RespuestasActividad respuestasActividad = RespuestasActividad.valueOf(respuesta);
                        switch (respuestasActividad) {
                            case TUTOR_PROFILE_NOT_CREATED:
                                mutableActividad.setValue(RespuestasActividad.TUTOR_PROFILE_NOT_CREATED);
                                break;
                            case INVALID_ACTIVITY_FORMAT:
                                mutableActividad.setValue(RespuestasActividad.INVALID_ACTIVITY_FORMAT);
                                break;
                            case COURSE_ACTIVITY_NOT_VALID:
                                mutableActividad.setValue(RespuestasActividad.COURSE_ACTIVITY_NOT_VALID);
                                break;
                            default:
                        }
                    } catch (Exception ignored) {}

                    if(response.code() == 403) {
                        mutableActividad.setValue(null);
                    }
                }
            }
            @Override
            public void onFailure(@NonNull Call<ResponseBody> call, Throwable t) {
                Log.e(TAG, "Error en la llamada a la API: " + t.getMessage());
                t.printStackTrace();
            }
        });
        return mutableActividad;
    }

    public LiveData<Object> updateActividad(ActividadDTO actividadDTO, String token) {
        restAuthService = RetrofitClient.getInstance(token).getAuthRestService();
        MutableLiveData<Object> mutableActividad = new MutableLiveData<>();
        Call<ResponseBody> callUpdateActividad = restAuthService.updateActividad(actividadDTO);
        callUpdateActividad.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(@NonNull Call<ResponseBody> call,
                                   Response<ResponseBody> response) {
                ResponseBody body = response.body();
                if(response.isSuccessful()) {
                    Gson gson = new Gson();
                    ActividadDTO actividadDTO = gson.fromJson(body.charStream(), ActividadDTO.class);
                    mutableActividad.setValue(actividadDTO);
                }else {
                    String respuesta;
                    try {
                        respuesta = Objects.requireNonNull(response.errorBody()).string();
                        RespuestasActividad respuestasActividad = RespuestasActividad.valueOf(respuesta);
                        switch (respuestasActividad) {
                            case ACTIVITY_NOT_FOUND:
                                mutableActividad.setValue(RespuestasActividad.ACTIVITY_NOT_FOUND);
                                break;
                            default:
                        }
                    } catch (Exception ignored) {}

                    if(response.code() == 403) {
                        mutableActividad.setValue(null);
                    }
                }
            }
            @Override
            public void onFailure(@NonNull Call<ResponseBody> call, Throwable t) {
                Log.e(TAG, "Error en la llamada a la API: " + t.getMessage());
                t.printStackTrace();
            }
        });
        return mutableActividad;
    }

    public LiveData<Object> getNumeroActividadesPendientes(Integer idUsuario, String token) {
        restAuthService = RetrofitClient.getInstance(token).getAuthRestService();
        MutableLiveData<Object> mutableNumeroActividadesPendientes = new MutableLiveData<>();
        Call<ResponseBody> callGetNumeroActividadesPendientes = restAuthService.getNumeroActividadesPendientes(idUsuario);
        callGetNumeroActividadesPendientes.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(@NonNull Call<ResponseBody> call,
                                   Response<ResponseBody> response) {
                ResponseBody body = response.body();
                if(response.isSuccessful()) {

                    Integer numAlumnos = null;
                    try {
                        numAlumnos = Integer.valueOf(body.string());
                    } catch (IOException ignored) {}
                    mutableNumeroActividadesPendientes.setValue(numAlumnos);
                }else {
                    String respuesta;
                    try {
                        respuesta = Objects.requireNonNull(response.errorBody()).string();
                        RespuestasActividad respuestasActividad = RespuestasActividad.valueOf(respuesta);
                        switch (respuestasActividad) {
                            case STUDENT_PROFILE_NOT_CREATED:
                                mutableNumeroActividadesPendientes.setValue(RespuestasActividad.STUDENT_PROFILE_NOT_CREATED);
                                break;
                            case ACTIVITY_NOT_REMOVED:
                                mutableNumeroActividadesPendientes.setValue(RespuestasActividad.ACTIVITY_NOT_REMOVED);
                                break;
                            default:
                        }
                    } catch (Exception ignored) {}

                    if(response.code() == 403) {
                        mutableNumeroActividadesPendientes.setValue(null);
                    }
                }
            }
            @Override
            public void onFailure(@NonNull Call<ResponseBody> call, Throwable t) {
                Log.e(TAG, "Error en la llamada a la API: " + t.getMessage());
                t.printStackTrace();
            }
        });
        return mutableNumeroActividadesPendientes;
    }

    public LiveData<Object> deleteActividad(Integer idActividad, String token) {
        restAuthService = RetrofitClient.getInstance(token).getAuthRestService();
        MutableLiveData<Object> mutableActividad = new MutableLiveData<>();
        Call<ResponseBody> callRemoveActividad = restAuthService.removeActividad(idActividad);
        callRemoveActividad.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(@NonNull Call<ResponseBody> call,
                                   Response<ResponseBody> response) {
                ResponseBody body = response.body();
                if(response.isSuccessful()) {
                    try {
                        String respuesta = body.string();
                        RespuestasActividad respuestasActividad = RespuestasActividad.valueOf(respuesta);
                        mutableActividad.setValue(respuestasActividad);
                    } catch (IOException ignored) {}
                }else {
                    String respuesta;
                    try {
                        respuesta = Objects.requireNonNull(response.errorBody()).string();
                        RespuestasActividad respuestasActividad = RespuestasActividad.valueOf(respuesta);
                        switch (respuestasActividad) {
                            case ACTIVITY_NOT_FOUND:
                                mutableActividad.setValue(RespuestasActividad.ACTIVITY_NOT_FOUND);
                                break;
                            case ACTIVITY_NOT_REMOVED:
                                mutableActividad.setValue(RespuestasActividad.ACTIVITY_NOT_REMOVED);
                                break;
                            default:
                        }
                    } catch (Exception ignored) {}

                    if(response.code() == 403) {
                        mutableActividad.setValue(null);
                    }
                }
            }
            @Override
            public void onFailure(@NonNull Call<ResponseBody> call, Throwable t) {
                Log.e(TAG, "Error en la llamada a la API: " + t.getMessage());
                t.printStackTrace();
            }
        });
        return mutableActividad;
    }

}
