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
import es.iespuertodelacruz.alejandrosamuel.studycircle.data.enums.RespuestasCursos;
import es.iespuertodelacruz.alejandrosamuel.studycircle.data.enums.RespuestasProfileConf;
import es.iespuertodelacruz.alejandrosamuel.studycircle.data.rest.RESTService;
import es.iespuertodelacruz.alejandrosamuel.studycircle.data.rest.RetrofitClient;
import es.iespuertodelacruz.alejandrosamuel.studycircle.data.rest.dto.CursoDTO;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CursosRepository {

    RESTService restAuthService;
    RESTService restNoAuthService;
    private final DatabaseStudyCircle database;

    public CursosRepository(Application application) {
        database = DatabaseStudyCircle.getDatabase(application);
    }

    public LiveData<Object> createCurso(CursoDTO cursoDTO, String token) {
        restAuthService = RetrofitClient.getInstance(token).getAuthRestService();
        MutableLiveData<Object> mutableCurso = new MutableLiveData<>();
        Call<ResponseBody> callCreateCurso = restAuthService.createCurso(cursoDTO);
        callCreateCurso.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(@NonNull Call<ResponseBody> call,
                                   Response<ResponseBody> response) {
                ResponseBody body = response.body();
                if(response.isSuccessful()) {
                    Gson gson = new Gson();
                    CursoDTO cursoDTO = gson.fromJson(body.charStream(), CursoDTO.class);
                    mutableCurso.setValue(cursoDTO);
                }else {
                    String respuesta;
                    try {
                        respuesta = response.errorBody().string();
                    } catch (IOException e) {
                        respuesta = null;
                    }
                    switch (RespuestasCursos.valueOf(respuesta)) {
                        case COURSE_DTO_NOT_VALID:
                            mutableCurso.setValue(RespuestasCursos.COURSE_DTO_NOT_VALID);
                            break;
                        case NON_AUTHENTICATED_OWNER:
                            mutableCurso.setValue(RespuestasCursos.NON_AUTHENTICATED_OWNER);
                            break;
                        default:
                    }

                    if(response.code() == 403) {
                        mutableCurso.setValue(null);
                    }
                }
            }
            @Override
            public void onFailure(@NonNull Call<ResponseBody> call, Throwable t) {
                Log.e(TAG, "Error en la llamada a la API: " + t.getMessage());
                t.printStackTrace();
            }
        });
        return mutableCurso;
    }

    public LiveData<Object> invitarAlumnoToCurso(Integer idUser, Integer idCurso, String token) {
        restAuthService = RetrofitClient.getInstance(token).getAuthRestService();
        MutableLiveData<Object> mutableCurso = new MutableLiveData<>();
        Call<ResponseBody> callAddAlumno = restAuthService.invitarAlumnoToCurso(idUser, idCurso);
        callAddAlumno.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(@NonNull Call<ResponseBody> call,
                                   Response<ResponseBody> response) {
                ResponseBody body = response.body();
                if(response.isSuccessful()) {
                    mutableCurso.setValue(RespuestasCursos.STUDENT_INVITED);
                }else {
                    String respuesta;
                    try {
                        respuesta = response.errorBody().string();
                    } catch (IOException e) {
                        respuesta = null;
                    }
                    switch (RespuestasCursos.valueOf(respuesta)) {
                        case STUDENT_OR_COURSE_NOT_FOUND:
                            mutableCurso.setValue(RespuestasCursos.STUDENT_OR_COURSE_NOT_FOUND);
                            break;
                        case STUDENT_NOT_FOUND_IN_CONTACTS:
                            mutableCurso.setValue(RespuestasCursos.STUDENT_NOT_FOUND_IN_CONTACTS);
                            break;
                        case NON_AUTHENTICATED_OWNER:
                            mutableCurso.setValue(RespuestasCursos.NON_AUTHENTICATED_OWNER);
                            break;
                        case USER_ALREADY_IN_COURSE_OR_INVITED:
                            mutableCurso.setValue(RespuestasCursos.USER_ALREADY_IN_COURSE_OR_INVITED);
                            break;
                        default:
                    }

                    if(response.code() == 403) {
                        mutableCurso.setValue(null);
                    }
                }
            }
            @Override
            public void onFailure(@NonNull Call<ResponseBody> call, Throwable t) {
                Log.e(TAG, "Error en la llamada a la API: " + t.getMessage());
                t.printStackTrace();
            }
        });
        return mutableCurso;
    }

    public LiveData<Object> rechazarInvitacion(Integer idInvitacion, String token) {
        restAuthService = RetrofitClient.getInstance(token).getAuthRestService();
        MutableLiveData<Object> mutableInvitacion = new MutableLiveData<>();
        Call<ResponseBody> callRechazarInvitacion = restAuthService.rechazarInvitacion(idInvitacion);
        callRechazarInvitacion.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(@NonNull Call<ResponseBody> call,
                                   Response<ResponseBody> response) {
                ResponseBody body = response.body();
                if(response.isSuccessful()) {
                    mutableInvitacion.setValue(RespuestasCursos.INVITATION_REMOVED);
                }else {
                    String respuesta;
                    try {
                        respuesta = response.errorBody().string();
                    } catch (IOException e) {
                        respuesta = null;
                    }
                    switch (RespuestasCursos.valueOf(respuesta)) {
                        case STUDENT_OR_INVITATION_NOT_FOUND:
                            mutableInvitacion.setValue(RespuestasCursos.STUDENT_OR_INVITATION_NOT_FOUND);
                            break;
                        default:
                    }

                    if(response.code() == 403) {
                        mutableInvitacion.setValue(null);
                    }
                }
            }
            @Override
            public void onFailure(@NonNull Call<ResponseBody> call, Throwable t) {
                Log.e(TAG, "Error en la llamada a la API: " + t.getMessage());
                t.printStackTrace();
            }
        });
        return mutableInvitacion;
    }


    public LiveData<Object> aceptarInvitacionCurso(Integer idAlertaCursoAlumno, String token) {
        restAuthService = RetrofitClient.getInstance(token).getAuthRestService();
        MutableLiveData<Object> mutableCurso = new MutableLiveData<>();
        Call<ResponseBody> callAceptarInvitacion = restAuthService.aceptarInvitacionCursoAlumno(idAlertaCursoAlumno);
        callAceptarInvitacion.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(@NonNull Call<ResponseBody> call,
                                   Response<ResponseBody> response) {
                ResponseBody body = response.body();
                if(response.isSuccessful()) {
                    Gson gson = new Gson();
                    CursoDTO cursoDTO = gson.fromJson(body.charStream(), CursoDTO.class);
                    mutableCurso.setValue(cursoDTO);
                }else {
                    String respuesta;
                    try {
                        respuesta = response.errorBody().string();
                    } catch (IOException e) {
                        respuesta = null;
                    }
                    switch (RespuestasCursos.valueOf(respuesta)) {
                        case ALERT_NOT_FOUND:
                            mutableCurso.setValue(RespuestasCursos.ALERT_NOT_FOUND);
                            break;
                        case STUDENT_OR_COURSE_NOT_FOUND:
                            mutableCurso.setValue(RespuestasCursos.STUDENT_OR_COURSE_NOT_FOUND);
                            break;
                        default:
                    }

                    if(response.code() == 403) {
                        mutableCurso.setValue(null);
                    }
                }
            }
            @Override
            public void onFailure(@NonNull Call<ResponseBody> call, Throwable t) {
                Log.e(TAG, "Error en la llamada a la API: " + t.getMessage());
                t.printStackTrace();
            }
        });
        return mutableCurso;
    }

    public LiveData<Object> removeAlumnoFromCurso(Integer idCurso, Integer idAlumno, String token) {
        restAuthService = RetrofitClient.getInstance(token).getAuthRestService();
        MutableLiveData<Object> mutableCurso = new MutableLiveData<>();
        Call<ResponseBody> callRemoveAlumno = restAuthService.removeAlumnoFromCurso(idCurso, idAlumno);
        callRemoveAlumno.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(@NonNull Call<ResponseBody> call,
                                   Response<ResponseBody> response) {
                ResponseBody body = response.body();
                if(response.isSuccessful()) {
                    Gson gson = new Gson();
                    CursoDTO cursoDTO = gson.fromJson(body.charStream(), CursoDTO.class);
                    mutableCurso.setValue(cursoDTO);
                }else {
                    String respuesta;
                    try {
                        respuesta = response.errorBody().string();
                    } catch (IOException e) {
                        respuesta = null;
                    }
                    switch (RespuestasCursos.valueOf(respuesta)) {
                        case TUTOR_PROFILE_NOT_CREATED:
                            mutableCurso.setValue(RespuestasCursos.TUTOR_PROFILE_NOT_CREATED);
                            break;
                        case INVALID_PARAMETERS:
                            mutableCurso.setValue(RespuestasCursos.INVALID_PARAMETERS);
                            break;
                        case NON_EXISTING_COURSE_OR_STUDENT:
                            mutableCurso.setValue(RespuestasCursos.NON_EXISTING_COURSE_OR_STUDENT);
                            break;
                        case NON_AUTHENTICATED_OWNER:
                            mutableCurso.setValue(RespuestasCursos.NON_AUTHENTICATED_OWNER);
                            break;
                        default:
                    }

                    if(response.code() == 403) {
                        mutableCurso.setValue(null);
                    }
                }
            }
            @Override
            public void onFailure(@NonNull Call<ResponseBody> call, Throwable t) {
                Log.e(TAG, "Error en la llamada a la API: " + t.getMessage());
                t.printStackTrace();
            }
        });
        return mutableCurso;
    }

    public LiveData<Object> findCursosAlumno(String token) {
        restAuthService = RetrofitClient.getInstance(token).getAuthRestService();
        MutableLiveData<Object> mutableCursos = new MutableLiveData<>();
        Call<ResponseBody> callFindCursosAlumno = restAuthService.findCursosAlumno();
        callFindCursosAlumno.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(@NonNull Call<ResponseBody> call,
                                   Response<ResponseBody> response) {
                ResponseBody body = response.body();
                if(response.isSuccessful()) {
                    Gson gson = new Gson();
                    Type listType = new TypeToken<List<CursoDTO>>() {}.getType();
                    List<CursoDTO> cursosDTO = new Gson().fromJson(body.charStream(), listType);
                    mutableCursos.setValue(cursosDTO);
                }else {
                    String respuesta;
                    RespuestasCursos respuestasCursos = null;
                    try {
                        respuesta = response.errorBody().string();
                        respuestasCursos = RespuestasCursos.valueOf(respuesta);
                    } catch (IOException | IllegalArgumentException ignored) {}
                    if(Objects.nonNull(respuestasCursos)) {
                        if (Objects.requireNonNull(respuestasCursos) == RespuestasCursos.TUTOR_PROFILE_NOT_CREATED) {
                            mutableCursos.setValue(RespuestasCursos.STUDENT_PROFILE_NOT_CREATED);
                        }
                    }

                    if(response.code() == 403) {
                        mutableCursos.setValue(null);
                    }
                }
            }
            @Override
            public void onFailure(@NonNull Call<ResponseBody> call, Throwable t) {
                Log.e(TAG, "Error en la llamada a la API: " + t.getMessage());
                t.printStackTrace();
            }
        });
        return mutableCursos;
    }

    public LiveData<Object> findCursosTutor(String token) {
        restAuthService = RetrofitClient.getInstance(token).getAuthRestService();
        MutableLiveData<Object> mutableCursos = new MutableLiveData<>();
        Call<ResponseBody> callFindCursosTutor = restAuthService.findCursosTutor();
        callFindCursosTutor.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(@NonNull Call<ResponseBody> call,
                                   Response<ResponseBody> response) {
                ResponseBody body = response.body();
                if(response.isSuccessful()) {
                    Gson gson = new Gson();
                    Type listType = new TypeToken<List<CursoDTO>>() {}.getType();
                    List<CursoDTO> cursosDTO = new Gson().fromJson(body.charStream(), listType);
                    mutableCursos.setValue(cursosDTO);
                }else {
                    String respuesta;
                    RespuestasCursos respuestasCursos = null;
                    try {
                        respuesta = response.errorBody().string();
                        respuestasCursos = RespuestasCursos.valueOf(respuesta);
                    } catch (IOException | IllegalArgumentException ignored) {}
                    if(Objects.nonNull(respuestasCursos)) {
                        if (Objects.requireNonNull(respuestasCursos) == RespuestasCursos.TUTOR_PROFILE_NOT_CREATED) {
                            mutableCursos.setValue(RespuestasCursos.TUTOR_PROFILE_NOT_CREATED);
                        }
                    }
                    if(response.code() == 403) {
                        mutableCursos.setValue(null);
                    }
                }
            }
            @Override
            public void onFailure(@NonNull Call<ResponseBody> call, Throwable t) {
                Log.e(TAG, "Error en la llamada a la API: " + t.getMessage());
                t.printStackTrace();
            }
        });
        return mutableCursos;
    }

    public LiveData<Object> getCantidadCursosTutor(Integer id, String token) {
        restAuthService = RetrofitClient.getInstance(token).getAuthRestService();
        MutableLiveData<Object> mutableNumCursosTutor = new MutableLiveData<>();
        Call<ResponseBody> callGetCantidadCursosTutor = restAuthService.getCantidadCursosTutor(id);
        callGetCantidadCursosTutor.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(@NonNull Call<ResponseBody> call,
                                   Response<ResponseBody> response) {
                ResponseBody body = response.body();
                if(response.isSuccessful()) {
                    Integer numCursos = null;
                    try {
                        numCursos = Integer.valueOf(body.string());
                    } catch (IOException ignored) {}
                    mutableNumCursosTutor.setValue(numCursos);
                }else {
                    String respuesta;
                    RespuestasProfileConf respuestasProfileConf = null;
                    try {
                        respuesta = response.errorBody().string();
                        respuestasProfileConf = RespuestasProfileConf.valueOf(respuesta);
                    } catch (IOException | IllegalArgumentException ignored) {
                    }
                    if(Objects.nonNull(respuestasProfileConf)) {
                        if (Objects.requireNonNull(respuestasProfileConf) == RespuestasProfileConf.TUTOR_PROFILE_NOT_CREATED) {
                            mutableNumCursosTutor.setValue(RespuestasProfileConf.TUTOR_PROFILE_NOT_CREATED);
                        }
                    }

                    if(response.code() == 403) {
                        mutableNumCursosTutor.setValue(null);
                    }
                }
            }
            @Override
            public void onFailure(@NonNull Call<ResponseBody> call, Throwable t) {
                Log.e(TAG, "Error en la llamada a la API: " + t.getMessage());
                t.printStackTrace();
            }
        });
        return mutableNumCursosTutor;
    }

    public LiveData<Object> getCantidadCursosAlumno(Integer id, String token) {
        restAuthService = RetrofitClient.getInstance(token).getAuthRestService();
        MutableLiveData<Object> mutableNumCursosAlumno = new MutableLiveData<>();
        Call<ResponseBody> callGetCantidadCursosAlumno = restAuthService.getCantidadCursosAlumno(id);
        callGetCantidadCursosAlumno.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(@NonNull Call<ResponseBody> call,
                                   Response<ResponseBody> response) {
                ResponseBody body = response.body();
                if(response.isSuccessful()) {
                    Integer numCursos = null;
                    try {
                        numCursos = Integer.valueOf(body.string());
                    } catch (IOException ignored) {}
                    mutableNumCursosAlumno.setValue(numCursos);
                }else {
                    String respuesta;
                    RespuestasProfileConf respuestasProfileConf = null;
                    try {
                        respuesta = response.errorBody().string();
                        respuestasProfileConf = RespuestasProfileConf.valueOf(respuesta);
                    } catch (IOException | IllegalArgumentException ignored) {
                    }
                    if(Objects.nonNull(respuestasProfileConf)) {
                        if (Objects.requireNonNull(respuestasProfileConf) == RespuestasProfileConf.STUDENT_PROFILE_NOT_CREATED) {
                            mutableNumCursosAlumno.setValue(RespuestasProfileConf.STUDENT_PROFILE_NOT_CREATED);
                        }
                    }

                    if(response.code() == 403) {
                        mutableNumCursosAlumno.setValue(null);
                    }
                }
            }
            @Override
            public void onFailure(@NonNull Call<ResponseBody> call, Throwable t) {
                Log.e(TAG, "Error en la llamada a la API: " + t.getMessage());
                t.printStackTrace();
            }
        });
        return mutableNumCursosAlumno;
    }

    public LiveData<Object> changeTituloCurso(Integer idCurso, String titulo, String token) {
        restAuthService = RetrofitClient.getInstance(token).getAuthRestService();
        MutableLiveData<Object> mutableCurso = new MutableLiveData<>();
        Call<ResponseBody> callChangeTituloCurso = restAuthService.changeTituloCurso(idCurso, titulo);
        callChangeTituloCurso.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(@NonNull Call<ResponseBody> call,
                                   Response<ResponseBody> response) {
                ResponseBody body = response.body();
                if(response.isSuccessful()) {
                    Gson gson = new Gson();
                    CursoDTO cursoDTO = gson.fromJson(body.charStream(), CursoDTO.class);
                    mutableCurso.setValue(cursoDTO);
                }else {
                    String respuesta;
                    try {
                        respuesta = Objects.requireNonNull(response.errorBody()).string();
                        RespuestasCursos respuestasCursos = RespuestasCursos.valueOf(respuesta);
                        switch (respuestasCursos) {
                            case TUTOR_PROFILE_NOT_CREATED:
                                mutableCurso.setValue(RespuestasCursos.TUTOR_PROFILE_NOT_CREATED);
                                break;
                            case INVALID_PARAMETERS:
                                mutableCurso.setValue(RespuestasCursos.INVALID_PARAMETERS);
                                break;
                            case NON_EXISTING_COURSE_OR_STUDENT:
                                mutableCurso.setValue(RespuestasCursos.NON_EXISTING_COURSE);
                                break;
                            case NON_AUTHENTICATED_OWNER:
                                mutableCurso.setValue(RespuestasCursos.NON_AUTHENTICATED_OWNER);
                                break;
                            default:
                        }
                    } catch (Exception ignored) {}

                    if(response.code() == 403) {
                        mutableCurso.setValue(null);
                    }
                }
            }
            @Override
            public void onFailure(@NonNull Call<ResponseBody> call, Throwable t) {
                Log.e(TAG, "Error en la llamada a la API: " + t.getMessage());
                t.printStackTrace();
            }
        });
        return mutableCurso;
    }

}
