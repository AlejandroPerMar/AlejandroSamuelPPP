package es.iespuertodelacruz.alejandrosamuel.studycircle.repository;

import static android.content.ContentValues.TAG;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.gson.Gson;

import java.io.IOException;

import es.iespuertodelacruz.alejandrosamuel.studycircle.data.db.DatabaseStudyCircle;
import es.iespuertodelacruz.alejandrosamuel.studycircle.data.enums.RespuestasCursos;
import es.iespuertodelacruz.alejandrosamuel.studycircle.data.enums.RespuestasProfileConf;
import es.iespuertodelacruz.alejandrosamuel.studycircle.data.rest.RESTService;
import es.iespuertodelacruz.alejandrosamuel.studycircle.data.rest.RetrofitClient;
import es.iespuertodelacruz.alejandrosamuel.studycircle.data.rest.dto.AlumnoDTO;
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

    public LiveData<Object> addAlumnoToCurso(Integer idCurso, Integer idAlumno, String token) {
        restAuthService = RetrofitClient.getInstance(token).getAuthRestService();
        MutableLiveData<Object> mutableCurso = new MutableLiveData<>();
        Call<ResponseBody> callAddAlumno = restAuthService.addAlumnoToCurso(idCurso, idAlumno);
        callAddAlumno.enqueue(new Callback<ResponseBody>() {
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
                            mutableCurso.setValue(RespuestasCursos.TUTOR_PROFILE_NOT_CREATED.getDescripcion());
                            break;
                        case INVALID_PARAMETERS:
                            mutableCurso.setValue(RespuestasCursos.INVALID_PARAMETERS.getDescripcion());
                            break;
                        case NON_EXISTING_COURSE_OR_STUDENT:
                            mutableCurso.setValue(RespuestasCursos.NON_EXISTING_COURSE_OR_STUDENT.getDescripcion());
                            break;
                        case NON_AUTHENTICATED_OWNER:
                            mutableCurso.setValue(RespuestasCursos.NON_AUTHENTICATED_OWNER.getDescripcion());
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
        return mutableCurso;
    }

    public LiveData<Object> removeAlumnoToCurso(Integer idCurso, Integer idAlumno, String token) {
        restAuthService = RetrofitClient.getInstance(token).getAuthRestService();
        MutableLiveData<Object> mutableCurso = new MutableLiveData<>();
        Call<ResponseBody> callRemoveAlumno = restAuthService.removeAlumnoToCurso(idCurso, idAlumno);
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
                            mutableCurso.setValue(RespuestasCursos.TUTOR_PROFILE_NOT_CREATED.getDescripcion());
                            break;
                        case INVALID_PARAMETERS:
                            mutableCurso.setValue(RespuestasCursos.INVALID_PARAMETERS.getDescripcion());
                            break;
                        case NON_EXISTING_COURSE_OR_STUDENT:
                            mutableCurso.setValue(RespuestasCursos.NON_EXISTING_COURSE_OR_STUDENT.getDescripcion());
                            break;
                        case NON_AUTHENTICATED_OWNER:
                            mutableCurso.setValue(RespuestasCursos.NON_AUTHENTICATED_OWNER.getDescripcion());
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
        return mutableCurso;
    }

}
