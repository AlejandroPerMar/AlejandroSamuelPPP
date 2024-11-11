package es.iespuertodelacruz.alejandrosamuel.studycircle.repository;

import static android.content.ContentValues.TAG;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;
import java.util.Objects;

import es.iespuertodelacruz.alejandrosamuel.studycircle.data.db.DatabaseStudyCircle;
import es.iespuertodelacruz.alejandrosamuel.studycircle.data.enums.RespuestasActividad;
import es.iespuertodelacruz.alejandrosamuel.studycircle.data.enums.RespuestasCursos;
import es.iespuertodelacruz.alejandrosamuel.studycircle.data.enums.RespuestasMateria;
import es.iespuertodelacruz.alejandrosamuel.studycircle.data.rest.RESTService;
import es.iespuertodelacruz.alejandrosamuel.studycircle.data.rest.RetrofitClient;
import es.iespuertodelacruz.alejandrosamuel.studycircle.data.rest.dto.EventoCalendarioDTO;
import es.iespuertodelacruz.alejandrosamuel.studycircle.data.rest.dto.MateriaDTO;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MateriasRepository {

    RESTService restAuthService;
    RESTService restNoAuthService;
    private final DatabaseStudyCircle database;

    public MateriasRepository(Application application) {
        database = DatabaseStudyCircle.getDatabase(application);
    }

    public LiveData<Object> findMateriasByTutor(String token) {
        restAuthService = RetrofitClient.getInstance(token).getAuthRestService();
        MutableLiveData<Object> mutableMaterias = new MutableLiveData<>();
        Call<ResponseBody> callMateriasTutor = restAuthService.findMateriasByTutor();
        callMateriasTutor.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(@NonNull Call<ResponseBody> call,
                                   @NonNull Response<ResponseBody> response) {
                ResponseBody body = response.body();
                if(response.isSuccessful()) {
                    Type listType = new TypeToken<List<MateriaDTO>>() {}.getType();
                    List<MateriaDTO> materiasDTO = new Gson().fromJson(Objects.requireNonNull(body).charStream(), listType);
                    mutableMaterias.setValue(materiasDTO);
                }else {
                    String respuesta;
                    try {
                        respuesta = Objects.requireNonNull(response.errorBody()).string();
                        if (RespuestasCursos.valueOf(respuesta) == RespuestasCursos.TUTOR_PROFILE_NOT_CREATED) {
                            mutableMaterias.setValue(RespuestasCursos.TUTOR_PROFILE_NOT_CREATED);
                        }
                    } catch (Exception ignored) {}

                    if(response.code() == 403) {
                        mutableMaterias.setValue(null);
                    }
                }
            }
            @Override
            public void onFailure(@NonNull Call<ResponseBody> call, Throwable t) {
                Log.e(TAG, "Error en la llamada a la API: " + t.getMessage());
                t.printStackTrace();
            }
        });
        return mutableMaterias;
    }

    public LiveData<Object> findByNivelEstudiosId(Integer idNivelEstudios, String token) {
        restAuthService = RetrofitClient.getInstance(token).getAuthRestService();
        MutableLiveData<Object> mutableMaterias = new MutableLiveData<>();
        Call<ResponseBody> callMaterias = restAuthService.findMateriasByNivelEstudiosId(idNivelEstudios);
        callMaterias.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(@NonNull Call<ResponseBody> call,
                                   @NonNull Response<ResponseBody> response) {
                ResponseBody body = response.body();
                if(response.isSuccessful()) {
                    Type listType = new TypeToken<List<MateriaDTO>>() {}.getType();
                    List<MateriaDTO> materiasDTO = new Gson().fromJson(Objects.requireNonNull(body).charStream(), listType);
                    mutableMaterias.setValue(materiasDTO);
                }else {

                    if(response.code() == 403) {
                        mutableMaterias.setValue(null);
                    }
                }
            }
            @Override
            public void onFailure(@NonNull Call<ResponseBody> call, Throwable t) {
                Log.e(TAG, "Error en la llamada a la API: " + t.getMessage());
                t.printStackTrace();
            }
        });
        return mutableMaterias;
    }

    public LiveData<Object> findById(Integer idMateria, String token) {
        restAuthService = RetrofitClient.getInstance(token).getAuthRestService();
        MutableLiveData<Object> mutableMateria = new MutableLiveData<>();
        Call<ResponseBody> callFindMateriaById = restAuthService.findMateriaById(idMateria);
        callFindMateriaById.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(@NonNull Call<ResponseBody> call,
                                   @NonNull Response<ResponseBody> response) {
                ResponseBody body = response.body();
                if(response.isSuccessful()) {
                    Gson gson = new Gson();
                    MateriaDTO materiaDTO = gson.fromJson(body.charStream(), MateriaDTO.class);
                    mutableMateria.setValue(materiaDTO);
                }else {
                    String respuesta;
                    try {
                        respuesta = Objects.requireNonNull(response.errorBody()).string();
                        RespuestasMateria respuestasMateria = RespuestasMateria.valueOf(respuesta);
                        switch (respuestasMateria) {
                            case SUBJECT_NOT_FOUND:
                                mutableMateria.setValue(RespuestasMateria.SUBJECT_NOT_FOUND);
                                break;
                            default:
                        }
                    } catch (Exception ignored) {}

                    if(response.code() == 403) {
                        mutableMateria.setValue(null);
                    }
                }
            }
            @Override
            public void onFailure(@NonNull Call<ResponseBody> call, Throwable t) {
                Log.e(TAG, "Error en la llamada a la API: " + t.getMessage());
                t.printStackTrace();
            }
        });
        return mutableMateria;
    }

}
