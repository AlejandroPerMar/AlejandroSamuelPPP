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

import es.iespuertodelacruz.alejandrosamuel.studycircle.data.db.DatabaseStudyCircle;
import es.iespuertodelacruz.alejandrosamuel.studycircle.data.enums.RespuestasEventoCalendario;
import es.iespuertodelacruz.alejandrosamuel.studycircle.data.rest.RESTService;
import es.iespuertodelacruz.alejandrosamuel.studycircle.data.rest.RetrofitClient;
import es.iespuertodelacruz.alejandrosamuel.studycircle.data.rest.dto.EventoCalendarioDTO;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EventosCalendarioRepository {

    RESTService restAuthService;
    RESTService restNoAuthService;
    private final DatabaseStudyCircle database;

    public EventosCalendarioRepository(Application application) {
        database = DatabaseStudyCircle.getDatabase(application);
    }

    public LiveData<Object> createEventoCalendario(EventoCalendarioDTO eventoCalendarioDTO, String token) {
        restAuthService = RetrofitClient.getInstance(token).getAuthRestService();
        MutableLiveData<Object> mutableEventoCalendario = new MutableLiveData<>();
        Call<ResponseBody> callCreateEventoCalendario = restAuthService.createEventoCalendario(eventoCalendarioDTO);
        callCreateEventoCalendario.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(@NonNull Call<ResponseBody> call,
                                   Response<ResponseBody> response) {
                ResponseBody body = response.body();
                if(response.isSuccessful()) {
                    Gson gson = new Gson();
                    EventoCalendarioDTO eventoCalendarioDTO = gson.fromJson(body.charStream(), EventoCalendarioDTO.class);
                    mutableEventoCalendario.setValue(eventoCalendarioDTO);
                }else {
                    String respuesta;
                    try {
                        respuesta = response.errorBody().string();
                    } catch (IOException e) {
                        respuesta = null;
                    }
                    switch (RespuestasEventoCalendario.valueOf(respuesta)) {
                        case INVALID_USER_PROFILE:
                            mutableEventoCalendario.setValue(RespuestasEventoCalendario.INVALID_USER_PROFILE);
                            break;
                        case CALENDAR_EVENT_DTO_NOT_VALID:
                            mutableEventoCalendario.setValue(RespuestasEventoCalendario.CALENDAR_EVENT_DTO_NOT_VALID);
                            break;
                        default:
                    }

                    if(response.code() == 403) {
                        mutableEventoCalendario.setValue(null);
                    }
                }
            }
            @Override
            public void onFailure(@NonNull Call<ResponseBody> call, Throwable t) {
                Log.e(TAG, "Error en la llamada a la API: " + t.getMessage());
                t.printStackTrace();
            }
        });
        return mutableEventoCalendario;
    }

    public LiveData<Object> findByPerfilUsuarioTutor(Integer idUsuario, String token) {
        restAuthService = RetrofitClient.getInstance(token).getAuthRestService();
        MutableLiveData<Object> mutableEventosCalendario = new MutableLiveData<>();
        Call<ResponseBody> callFindByPerfilUsuarioTutor = restAuthService.findByPerfilUsuarioTutor();
        callFindByPerfilUsuarioTutor.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(@NonNull Call<ResponseBody> call,
                                   Response<ResponseBody> response) {
                ResponseBody body = response.body();
                if(response.isSuccessful()) {
                    Gson gson = new Gson();
                    Type listType = new TypeToken<List<EventoCalendarioDTO>>() {}.getType();
                    List<EventoCalendarioDTO> eventosCalendarioDTO = new Gson().fromJson(body.charStream(), listType);
                    mutableEventosCalendario.setValue(eventosCalendarioDTO);
                }else {

                    if(response.code() == 403) {
                        mutableEventosCalendario.setValue(null);
                    }
                }
            }
            @Override
            public void onFailure(@NonNull Call<ResponseBody> call, Throwable t) {
                Log.e(TAG, "Error en la llamada a la API: " + t.getMessage());
                t.printStackTrace();
            }
        });
        return mutableEventosCalendario;
    }

    public LiveData<Object> findByPerfilUsuarioAlumno(Integer idUsuario, String token) {
        restAuthService = RetrofitClient.getInstance(token).getAuthRestService();
        MutableLiveData<Object> mutableEventosCalendario = new MutableLiveData<>();
        Call<ResponseBody> callFindByPerfilUsuarioAlumno = restAuthService.findByPerfilUsuarioAlumno();
        callFindByPerfilUsuarioAlumno.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(@NonNull Call<ResponseBody> call,
                                   Response<ResponseBody> response) {
                ResponseBody body = response.body();
                if(response.isSuccessful()) {
                    Gson gson = new Gson();
                    Type listType = new TypeToken<List<EventoCalendarioDTO>>() {}.getType();
                    List<EventoCalendarioDTO> eventosCalendarioDTO = new Gson().fromJson(body.charStream(), listType);
                    mutableEventosCalendario.setValue(eventosCalendarioDTO);
                }else {

                    if(response.code() == 403) {
                        mutableEventosCalendario.setValue(null);
                    }
                }
            }
            @Override
            public void onFailure(@NonNull Call<ResponseBody> call, Throwable t) {
                Log.e(TAG, "Error en la llamada a la API: " + t.getMessage());
                t.printStackTrace();
            }
        });
        return mutableEventosCalendario;
    }
}
