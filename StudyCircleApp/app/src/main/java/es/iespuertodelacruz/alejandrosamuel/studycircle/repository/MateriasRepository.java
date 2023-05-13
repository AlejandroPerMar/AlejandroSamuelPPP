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
import es.iespuertodelacruz.alejandrosamuel.studycircle.data.rest.RESTService;
import es.iespuertodelacruz.alejandrosamuel.studycircle.data.rest.RetrofitClient;
import es.iespuertodelacruz.alejandrosamuel.studycircle.data.rest.dto.CursoDTO;
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
        MutableLiveData<Object> mutableMateriasTutor = new MutableLiveData<>();
        Call<ResponseBody> callMateriasTutor = restAuthService.findMateriasByTutor();
        callMateriasTutor.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(@NonNull Call<ResponseBody> call,
                                   @NonNull Response<ResponseBody> response) {
                ResponseBody body = response.body();
                if(response.isSuccessful()) {
                    Type listType = new TypeToken<List<MateriaDTO>>() {}.getType();
                    List<MateriaDTO> materiasDTO = new Gson().fromJson(Objects.requireNonNull(body).charStream(), listType);
                    mutableMateriasTutor.setValue(materiasDTO);
                }else {
                    String respuesta;
                    try {
                        respuesta = Objects.requireNonNull(response.errorBody()).string();
                        if (RespuestasCursos.valueOf(respuesta) == RespuestasCursos.TUTOR_PROFILE_NOT_CREATED) {
                            mutableMateriasTutor.setValue(RespuestasCursos.TUTOR_PROFILE_NOT_CREATED);
                        }
                    } catch (Exception ignored) {}

                }
            }
            @Override
            public void onFailure(@NonNull Call<ResponseBody> call, Throwable t) {
                Log.e(TAG, "Error en la llamada a la API: " + t.getMessage());
                t.printStackTrace();
            }
        });
        return mutableMateriasTutor;
    }

}
