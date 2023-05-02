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
        Call<ResponseBody> callAlumno = restAuthService.createStudent(alumno);
        callAlumno.enqueue(new Callback<ResponseBody>() {
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
                    try {
                        respuesta = response.errorBody().string();
                    } catch (IOException e) {
                        respuesta = null;
                    }
                    switch (RespuestasProfileConf.valueOf(respuesta)) {
                        case STUDENT_PROFILE_ALREADY_CREATED:
                            mutableAlumno.setValue(RespuestasProfileConf.STUDENT_PROFILE_ALREADY_CREATED.getDescripcion());
                            break;
                        case STUDENT_PROFILE_NOT_CREATED:
                            mutableAlumno.setValue(RespuestasProfileConf.STUDENT_PROFILE_NOT_CREATED.getDescripcion());
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
        return mutableAlumno;
    }

    public LiveData<Object> createTutorProfile(List<MateriaDTO> materias, String token) {
        restAuthService = RetrofitClient.getInstance(token).getAuthRestService();
        MutableLiveData<Object> mutableTutor = new MutableLiveData<>();
        Call<ResponseBody> callTutor = restAuthService.createTutor(materias);
        callTutor.enqueue(new Callback<ResponseBody>() {
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
                    try {
                        respuesta = response.errorBody().string();
                    } catch (IOException e) {
                        respuesta = null;
                    }
                    switch (RespuestasProfileConf.valueOf(respuesta)) {
                        case STUDENT_PROFILE_ALREADY_CREATED:
                            mutableTutor.setValue(RespuestasProfileConf.TUTOR_PROFILE_ALREADY_CREATED.getDescripcion());
                            break;
                        case STUDENT_PROFILE_NOT_CREATED:
                            mutableTutor.setValue(RespuestasProfileConf.TUTOR_PROFILE_NOT_CREATED.getDescripcion());
                            break;
                        default:
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
}
