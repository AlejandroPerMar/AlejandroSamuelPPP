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
import es.iespuertodelacruz.alejandrosamuel.studycircle.data.enums.RespuestasAnuncio;
import es.iespuertodelacruz.alejandrosamuel.studycircle.data.rest.RESTService;
import es.iespuertodelacruz.alejandrosamuel.studycircle.data.rest.RetrofitClient;
import es.iespuertodelacruz.alejandrosamuel.studycircle.data.rest.dto.AnuncioDTO;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AnunciosRepository {

    RESTService restAuthService;
    RESTService restNoAuthService;
    private final DatabaseStudyCircle database;

    public AnunciosRepository(Application application) {
        database = DatabaseStudyCircle.getDatabase(application);
    }

    public LiveData<Object> findAnuncioById(Integer idAnuncio, String token) {
        restAuthService = RetrofitClient.getInstance(token).getAuthRestService();
        MutableLiveData<Object> mutableAnuncio = new MutableLiveData<>();
        Call<ResponseBody> callFindAnuncio = restAuthService.findAnuncioById(idAnuncio);
        callFindAnuncio.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(@NonNull Call<ResponseBody> call,
                                   Response<ResponseBody> response) {
                ResponseBody body = response.body();
                if(response.isSuccessful()) {
                    Gson gson = new Gson();
                    AnuncioDTO anuncioDTO = gson.fromJson(body.charStream(), AnuncioDTO.class);
                    mutableAnuncio.setValue(anuncioDTO);
                }else {
                    String respuesta;
                    try {
                        respuesta = Objects.requireNonNull(response.errorBody()).string();
                        RespuestasAnuncio respuestasAnuncio = RespuestasAnuncio.valueOf(respuesta);
                        switch (respuestasAnuncio) {
                            case NON_EXISTING_ANNOUNCEMENT:
                                mutableAnuncio.setValue(RespuestasAnuncio.NON_EXISTING_ANNOUNCEMENT);
                                break;
                            default:
                        }
                    } catch (Exception ignored) {}

                    if(response.code() == 403) {
                        mutableAnuncio.setValue(null);
                    }
                }
            }
            @Override
            public void onFailure(@NonNull Call<ResponseBody> call, Throwable t) {
                Log.e(TAG, "Error en la llamada a la API: " + t.getMessage());
                t.printStackTrace();
            }
        });
        return mutableAnuncio;
    }



    public LiveData<Object> findAnuncios(String token) {
        restAuthService = RetrofitClient.getInstance(token).getAuthRestService();
        MutableLiveData<Object> mutableAnuncios = new MutableLiveData<>();
        Call<ResponseBody> callFindAnuncios = restAuthService.findAllAnuncios();
        callFindAnuncios.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(@NonNull Call<ResponseBody> call,
                                   Response<ResponseBody> response) {
                ResponseBody body = response.body();
                if(response.isSuccessful()) {
                    Gson gson = new Gson();
                    Type listType = new TypeToken<List<AnuncioDTO>>() {}.getType();
                    List<AnuncioDTO> anunciosDTO = new Gson().fromJson(body.charStream(), listType);
                    mutableAnuncios.setValue(anunciosDTO);
                }else {
                    String respuesta;
                    try {
                        respuesta = Objects.requireNonNull(response.errorBody()).string();
                        RespuestasAnuncio respuestasAnuncio = RespuestasAnuncio.valueOf(respuesta);
                        switch (respuestasAnuncio) {
                            case NON_EXISTING_ANNOUNCEMENTS:
                                mutableAnuncios.setValue(RespuestasAnuncio.NON_EXISTING_ANNOUNCEMENTS);
                                break;
                            default:
                        }
                    } catch (Exception ignored) {}

                    if(response.code() == 403) {
                        mutableAnuncios.setValue(null);
                    }
                }
            }
            @Override
            public void onFailure(@NonNull Call<ResponseBody> call, Throwable t) {
                Log.e(TAG, "Error en la llamada a la API: " + t.getMessage());
                t.printStackTrace();
            }
        });
        return mutableAnuncios;
    }

    public LiveData<Object> findAnunciosByUsuario(String token) {
        restAuthService = RetrofitClient.getInstance(token).getAuthRestService();
        MutableLiveData<Object> mutableAnuncios = new MutableLiveData<>();
        Call<ResponseBody> callFindAnuncios = restAuthService.findByUsuario();
        callFindAnuncios.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(@NonNull Call<ResponseBody> call,
                                   Response<ResponseBody> response) {
                ResponseBody body = response.body();
                if(response.isSuccessful()) {
                    Gson gson = new Gson();
                    Type listType = new TypeToken<List<AnuncioDTO>>() {}.getType();
                    List<AnuncioDTO> anunciosDTO = new Gson().fromJson(body.charStream(), listType);
                    mutableAnuncios.setValue(anunciosDTO);
                }else {
                    String respuesta;
                    try {
                        respuesta = Objects.requireNonNull(response.errorBody()).string();
                        RespuestasAnuncio respuestasAnuncio = RespuestasAnuncio.valueOf(respuesta);
                        switch (respuestasAnuncio) {
                            case NON_EXISTING_ANNOUNCEMENTS:
                                mutableAnuncios.setValue(RespuestasAnuncio.NON_EXISTING_ANNOUNCEMENTS);
                                break;
                            default:
                        }
                    } catch (Exception ignored) {}

                    if(response.code() == 403) {
                        mutableAnuncios.setValue(null);
                    }
                }
            }
            @Override
            public void onFailure(@NonNull Call<ResponseBody> call, Throwable t) {
                Log.e(TAG, "Error en la llamada a la API: " + t.getMessage());
                t.printStackTrace();
            }
        });
        return mutableAnuncios;
    }

    public LiveData<Object> crearAnuncio(AnuncioDTO anuncioDTO, String token) {
        restAuthService = RetrofitClient.getInstance(token).getAuthRestService();
        MutableLiveData<Object> mutableAnuncio = new MutableLiveData<>();
        Call<ResponseBody> callCrearAnuncio = restAuthService.createAnuncio(anuncioDTO);
        callCrearAnuncio.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(@NonNull Call<ResponseBody> call,
                                   Response<ResponseBody> response) {
                ResponseBody body = response.body();
                if(response.isSuccessful()) {
                    Gson gson = new Gson();
                    AnuncioDTO anuncioDTO = gson.fromJson(body.charStream(), AnuncioDTO.class);
                    mutableAnuncio.setValue(anuncioDTO);
                }else {
                    String respuesta;
                    try {
                        respuesta = Objects.requireNonNull(response.errorBody()).string();
                        RespuestasAnuncio respuestasAnuncio = RespuestasAnuncio.valueOf(respuesta);
                        switch (respuestasAnuncio) {
                            case INVALID_DTO_ANNOUNCEMENT:
                                mutableAnuncio.setValue(RespuestasAnuncio.INVALID_DTO_ANNOUNCEMENT);
                                break;
                            default:
                        }
                    } catch (Exception ignored) {}

                    if(response.code() == 403) {
                        mutableAnuncio.setValue(null);
                    }
                }
            }
            @Override
            public void onFailure(@NonNull Call<ResponseBody> call, Throwable t) {
                Log.e(TAG, "Error en la llamada a la API: " + t.getMessage());
                t.printStackTrace();
            }
        });
        return mutableAnuncio;
    }

    public LiveData<Object> deleteAnuncio(Integer idAnuncio, String token) {
        restAuthService = RetrofitClient.getInstance(token).getAuthRestService();
        MutableLiveData<Object> mutableDeleteAnuncio = new MutableLiveData<>();
        Call<ResponseBody> callDeleteAnuncio = restAuthService.deleteAnuncio(idAnuncio);
        callDeleteAnuncio.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(@NonNull Call<ResponseBody> call,
                                   Response<ResponseBody> response) {
                if(response.isSuccessful()) {
                    String anuncioEliminado = response.toString();
                    mutableDeleteAnuncio.setValue(RespuestasAnuncio.valueOf(anuncioEliminado));
                }else {
                    String respuesta;
                    RespuestasAnuncio respuestasAnuncio = null;
                    try {
                        respuesta = response.errorBody().string();
                        respuestasAnuncio = RespuestasAnuncio.valueOf(respuesta);
                    } catch (IOException | IllegalArgumentException ignored) {}
                    if(Objects.nonNull(respuestasAnuncio)) {
                        switch (respuestasAnuncio) {
                            case ANNOUNCEMENT_REMOVED:
                                mutableDeleteAnuncio.setValue(RespuestasAnuncio.ANNOUNCEMENT_REMOVED);
                                break;
                            case INVALID_ANNOUNCEMENT:
                                mutableDeleteAnuncio.setValue(RespuestasAnuncio.INVALID_ANNOUNCEMENT);
                                break;
                            case INVALID_ID:
                                mutableDeleteAnuncio.setValue(RespuestasAnuncio.INVALID_ID);
                                break;
                        }
                    }
                }
            }
            @Override
            public void onFailure(@NonNull Call<ResponseBody> call, Throwable t) {
                Log.e(TAG, "Error en la llamada a la API: " + t.getMessage());
                t.printStackTrace();
            }
        });
        return mutableDeleteAnuncio;
    }

}
