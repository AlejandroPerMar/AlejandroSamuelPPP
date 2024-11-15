package es.iespuertodelacruz.alejandrosamuel.studycircle.repository;

import static android.content.ContentValues.TAG;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

import es.iespuertodelacruz.alejandrosamuel.studycircle.data.db.DatabaseStudyCircle;
import es.iespuertodelacruz.alejandrosamuel.studycircle.data.enums.RespuestasAuthToken;
import es.iespuertodelacruz.alejandrosamuel.studycircle.data.enums.RespuestasProfileConf;
import es.iespuertodelacruz.alejandrosamuel.studycircle.data.enums.RespuestasRegister;
import es.iespuertodelacruz.alejandrosamuel.studycircle.data.rest.RESTService;
import es.iespuertodelacruz.alejandrosamuel.studycircle.data.rest.RetrofitClient;
import es.iespuertodelacruz.alejandrosamuel.studycircle.data.rest.dto.AlumnoDTO;
import es.iespuertodelacruz.alejandrosamuel.studycircle.data.rest.dto.MateriaDTO;
import es.iespuertodelacruz.alejandrosamuel.studycircle.data.rest.dto.TutorDTO;
import es.iespuertodelacruz.alejandrosamuel.studycircle.data.rest.dto.UsuarioDTO;
import es.iespuertodelacruz.alejandrosamuel.studycircle.data.rest.dto.UsuarioLoginDTO;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProfilesConfRepository {

    RESTService restAuthService;
    RESTService restNoAuthService;
    private final DatabaseStudyCircle database;

    public ProfilesConfRepository(Application application) {
        database = DatabaseStudyCircle.getDatabase(application);
    }

    public LiveData<Object> createStudentProfile(AlumnoDTO alumno, String token) {
        restAuthService = RetrofitClient.getInstance(token).getAuthRestService();
        MutableLiveData<Object> mutableAlumno = new MutableLiveData<>();
        Call<ResponseBody> callCreateAlumno = restAuthService.createStudent(alumno);
        callCreateAlumno.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(@NonNull Call<ResponseBody> call,
                                   Response<ResponseBody> response) {
                ResponseBody body = response.body();
                if(response.isSuccessful()) {
                    Gson gson = new Gson();
                    AlumnoDTO alumnoDTO = gson.fromJson(body.charStream(), AlumnoDTO.class);
                    mutableAlumno.setValue(alumnoDTO);
                }else {
                    String respuesta;
                    RespuestasProfileConf respuestasProfileConf = null;
                    try {
                        respuesta = response.errorBody().string();
                        respuestasProfileConf = RespuestasProfileConf.valueOf(respuesta);
                    } catch (IOException | IllegalArgumentException ignored) {}
                    if(Objects.nonNull(respuestasProfileConf)) {
                        switch (Objects.requireNonNull(respuestasProfileConf)) {
                            case STUDENT_PROFILE_ALREADY_CREATED:
                                mutableAlumno.setValue(RespuestasProfileConf.STUDENT_PROFILE_ALREADY_CREATED);
                                break;
                            case STUDENT_PROFILE_NOT_CREATED:
                                mutableAlumno.setValue(RespuestasProfileConf.STUDENT_PROFILE_NOT_CREATED);
                                break;
                            default:
                        }
                    }

                    if(response.code() == 403) {
                        mutableAlumno.setValue(null);
                    }
                }
            }
            @Override
            public void onFailure(@NonNull Call<ResponseBody> call, Throwable t) {
                Log.e(TAG, "Error en la llamada a la API: " + t.getMessage());
                t.printStackTrace();
            }
        });
        return mutableAlumno;
    }

    public LiveData<Object> updateAlumno(AlumnoDTO alumno, String token) {
        restAuthService = RetrofitClient.getInstance(token).getAuthRestService();
        MutableLiveData<Object> mutableAlumno = new MutableLiveData<>();
        Call<ResponseBody> callUpdateAlumno = restAuthService.updateAlumno(alumno);
        callUpdateAlumno.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(@NonNull Call<ResponseBody> call,
                                   Response<ResponseBody> response) {
                ResponseBody body = response.body();
                if(response.isSuccessful()) {
                    Gson gson = new Gson();
                    AlumnoDTO alumnoDTO = gson.fromJson(body.charStream(), AlumnoDTO.class);
                    mutableAlumno.setValue(alumnoDTO);
                }else {
                    String respuesta;
                    RespuestasProfileConf respuestasProfileConf = null;
                    try {
                        respuesta = response.errorBody().string();
                        respuestasProfileConf = RespuestasProfileConf.valueOf(respuesta);
                    } catch (IOException | IllegalArgumentException ignored) {}
                    if(Objects.nonNull(respuestasProfileConf)) {
                        if (Objects.requireNonNull(respuestasProfileConf) == RespuestasProfileConf.STUDENT_PROFILE_NOT_FOUND) {
                            mutableAlumno.setValue(RespuestasProfileConf.STUDENT_PROFILE_NOT_FOUND);
                        }
                    }

                    if(response.code() == 403) {
                        mutableAlumno.setValue(null);
                    }
                }
            }
            @Override
            public void onFailure(@NonNull Call<ResponseBody> call, Throwable t) {
                Log.e(TAG, "Error en la llamada a la API: " + t.getMessage());
                t.printStackTrace();
            }
        });
        return mutableAlumno;
    }

    public LiveData<Object> getAlumno(String token) {
        restAuthService = RetrofitClient.getInstance(token).getAuthRestService();
        MutableLiveData<Object> mutableAlumno = new MutableLiveData<>();
        Call<ResponseBody> callGetAlumno = restAuthService.getAlumno();
        callGetAlumno.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(@NonNull Call<ResponseBody> call,
                                   Response<ResponseBody> response) {
                ResponseBody body = response.body();
                if(response.isSuccessful()) {
                    Gson gson = new Gson();
                    AlumnoDTO alumnoDTO = gson.fromJson(body.charStream(), AlumnoDTO.class);
                    mutableAlumno.setValue(alumnoDTO);
                }else {
                    String respuesta;
                    RespuestasProfileConf respuestasProfileConf = null;
                    try {
                        respuesta = response.errorBody().string();
                        respuestasProfileConf = RespuestasProfileConf.valueOf(respuesta);
                    } catch (IOException | IllegalArgumentException ignored) {}
                    if(Objects.nonNull(respuestasProfileConf)) {
                        if (Objects.requireNonNull(respuestasProfileConf) == RespuestasProfileConf.STUDENT_PROFILE_NOT_FOUND) {
                            mutableAlumno.setValue(RespuestasProfileConf.STUDENT_PROFILE_NOT_FOUND);
                        }
                    }
                    if(response.code() == 403) {
                        mutableAlumno.setValue(null);
                    }
                }
            }
            @Override
            public void onFailure(@NonNull Call<ResponseBody> call, Throwable t) {
                Log.e(TAG, "Error en la llamada a la API: " + t.getMessage());
                t.printStackTrace();
            }
        });
        return mutableAlumno;
    }

    public LiveData<Object> createTutorProfile(List<MateriaDTO> materias, String token) {
        restAuthService = RetrofitClient.getInstance(token).getAuthRestService();
        MutableLiveData<Object> mutableTutor = new MutableLiveData<>();
        Call<ResponseBody> callCreateTutor = restAuthService.createTutor(materias);
        callCreateTutor.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(@NonNull Call<ResponseBody> call,
                                   @NonNull Response<ResponseBody> response) {
                ResponseBody body = response.body();
                if(response.isSuccessful()) {
                    Gson gson = new Gson();
                    TutorDTO tutorDTO = gson.fromJson(body.charStream(), TutorDTO.class);
                    mutableTutor.setValue(tutorDTO);
                }else {
                    String respuesta;
                    RespuestasProfileConf respuestasProfileConf = null;
                    try {
                        respuesta = response.errorBody().string();
                        respuestasProfileConf = RespuestasProfileConf.valueOf(respuesta);
                    } catch (IOException | IllegalArgumentException ignored) {}
                    if(Objects.nonNull(respuestasProfileConf)) {
                        switch (Objects.requireNonNull(respuestasProfileConf)) {
                            case STUDENT_PROFILE_ALREADY_CREATED:
                                mutableTutor.setValue(RespuestasProfileConf.TUTOR_PROFILE_ALREADY_CREATED);
                                break;
                            case STUDENT_PROFILE_NOT_CREATED:
                                mutableTutor.setValue(RespuestasProfileConf.TUTOR_PROFILE_NOT_CREATED);
                                break;
                            default:
                        }
                    }
                    if(response.code() == 403) {
                        mutableTutor.setValue(null);
                    }
                }
            }
            @Override
            public void onFailure(@NonNull Call<ResponseBody> call, @NonNull Throwable t) {
                Log.e(TAG, "Error en la llamada a la API: " + t.getMessage());
                t.printStackTrace();
            }
        });
        return mutableTutor;
    }

    public LiveData<Object> updateTutor(TutorDTO tutorDTO, String token) {
        restAuthService = RetrofitClient.getInstance(token).getAuthRestService();
        MutableLiveData<Object> mutableTutor = new MutableLiveData<>();
        Call<ResponseBody> callUpdateTutor = restAuthService.updateTutor(tutorDTO);
        callUpdateTutor.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(@NonNull Call<ResponseBody> call,
                                   Response<ResponseBody> response) {
                ResponseBody body = response.body();
                if(response.isSuccessful()) {
                    Gson gson = new Gson();
                    TutorDTO tutorDTO = gson.fromJson(body.charStream(), TutorDTO.class);
                    mutableTutor.setValue(tutorDTO);
                }else {
                    String respuesta;
                    RespuestasProfileConf respuestasProfileConf = null;
                    try {
                        respuesta = response.errorBody().string();
                        respuestasProfileConf = RespuestasProfileConf.valueOf(respuesta);
                    } catch (IOException | IllegalArgumentException ignored) {}

                    if(Objects.nonNull(respuestasProfileConf)) {
                        switch (Objects.requireNonNull(respuestasProfileConf)) {
                            case TUTOR_PROFILE_NOT_FOUND:
                                mutableTutor.setValue(RespuestasProfileConf.TUTOR_PROFILE_NOT_FOUND);
                                break;
                            case TUTOR_PROFILE_NOT_UPDATED:
                                mutableTutor.setValue(RespuestasProfileConf.TUTOR_PROFILE_NOT_UPDATED);
                                break;
                            default:
                        }
                    }

                    if(response.code() == 403) {
                        mutableTutor.setValue(null);
                    }
                }
            }
            @Override
            public void onFailure(@NonNull Call<ResponseBody> call, Throwable t) {
                Log.e(TAG, "Error en la llamada a la API: " + t.getMessage());
                t.printStackTrace();
            }
        });
        return mutableTutor;
    }

    public LiveData<Object> getTutor(String token) {
        restAuthService = RetrofitClient.getInstance(token).getAuthRestService();
        MutableLiveData<Object> mutableTutor = new MutableLiveData<>();
        Call<ResponseBody> callGetTutor = restAuthService.getTutor();
        callGetTutor.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(@NonNull Call<ResponseBody> call,
                                   Response<ResponseBody> response) {
                ResponseBody body = response.body();
                if(response.isSuccessful()) {
                    Gson gson = new Gson();
                    TutorDTO tutorDTO = gson.fromJson(body.charStream(), TutorDTO.class);
                    mutableTutor.setValue(tutorDTO);
                }else {
                    String respuesta;
                    RespuestasProfileConf respuestasProfileConf = null;
                    try {
                        respuesta = response.errorBody().string();
                        respuestasProfileConf = RespuestasProfileConf.valueOf(respuesta);
                    } catch (IOException | IllegalArgumentException ignored) {
                    }
                    if(Objects.nonNull(respuestasProfileConf)) {
                        if (Objects.requireNonNull(respuestasProfileConf) == RespuestasProfileConf.TUTOR_PROFILE_NOT_FOUND) {
                            mutableTutor.setValue(RespuestasProfileConf.TUTOR_PROFILE_NOT_FOUND);
                        }
                    }

                    if(response.code() == 403) {
                        mutableTutor.setValue(null);
                    }
                }
            }
            @Override
            public void onFailure(@NonNull Call<ResponseBody> call, Throwable t) {
                Log.e(TAG, "Error en la llamada a la API: " + t.getMessage());
                t.printStackTrace();
            }
        });
        return mutableTutor;
    }

    public LiveData<Object> getNumeroAlumnosTutor(Integer id, String token) {
        restAuthService = RetrofitClient.getInstance(token).getAuthRestService();
        MutableLiveData<Object> mutableNumAlumnosTutor = new MutableLiveData<>();
        Call<ResponseBody> callGetNumeroAlumnosTutor = restAuthService.getNumeroAlumnosTutor(id);
        callGetNumeroAlumnosTutor.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(@NonNull Call<ResponseBody> call,
                                   Response<ResponseBody> response) {
                ResponseBody body = response.body();
                if(response.isSuccessful()) {
                    Integer numAlumnos = null;
                    try {
                        numAlumnos = Integer.valueOf(body.string());
                    } catch (IOException ignored) {}
                    mutableNumAlumnosTutor.setValue(numAlumnos);
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
                            mutableNumAlumnosTutor.setValue(RespuestasProfileConf.TUTOR_PROFILE_NOT_CREATED);
                        }
                    }

                    if(response.code() == 403) {
                        mutableNumAlumnosTutor.setValue(null);
                    }
                }
            }
            @Override
            public void onFailure(@NonNull Call<ResponseBody> call, Throwable t) {
                Log.e(TAG, "Error en la llamada a la API: " + t.getMessage());
                t.printStackTrace();
            }
        });
        return mutableNumAlumnosTutor;
    }
}
