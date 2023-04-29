package es.iespuertodelacruz.alejandrosamuel.studycircle.repository;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;


import java.io.IOException;

import es.iespuertodelacruz.alejandrosamuel.studycircle.data.db.DatabaseStudyCircle;
import es.iespuertodelacruz.alejandrosamuel.studycircle.data.enums.EstadosUsuario;
import es.iespuertodelacruz.alejandrosamuel.studycircle.data.enums.RespuestasRegister;
import es.iespuertodelacruz.alejandrosamuel.studycircle.data.rest.RESTService;
import es.iespuertodelacruz.alejandrosamuel.studycircle.data.rest.RetrofitClient;
import es.iespuertodelacruz.alejandrosamuel.studycircle.data.rest.dto.UsuarioDTO;
import es.iespuertodelacruz.alejandrosamuel.studycircle.data.rest.dto.UsuarioLoginDTO;
import es.iespuertodelacruz.alejandrosamuel.studycircle.data.rest.dto.UsuarioRegisterDTO;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AuthRepository {
    RESTService restAuthService;
    RESTService restNoAuthService;
    private final DatabaseStudyCircle database;

    public AuthRepository(Application application) {
        database = DatabaseStudyCircle.getDatabase(application);
        restNoAuthService = RetrofitClient.getInstance().getNoAuthRestService();
    }

    public LiveData<String> getAuthToken(UsuarioLoginDTO usuarioLoginDTO) {
        MutableLiveData<String> mutableToken = new MutableLiveData<>();
        Call<String> callToken = restNoAuthService.login(usuarioLoginDTO);
        callToken.enqueue(new Callback<String>() {
            @Override
            public void onResponse(@NonNull Call<String> call,
                                   Response<String> response) {
                if(response.isSuccessful()) {
                    String tokenResponse = "Bearer " + response.body();
                    System.out.println(tokenResponse);
                    mutableToken.setValue(tokenResponse);
                }
            }
            @Override
            public void onFailure(@NonNull Call<String> call, Throwable t) {
                System.out.println("Error en la llamada");
                System.out.println(t.getMessage());
            }
        });
        return mutableToken;
    }

    public LiveData<Object> registerUsuario(UsuarioRegisterDTO usuarioRegisterDTO) {
        MutableLiveData<Object> mutableToken = new MutableLiveData<>();
        Call<Object> register = restNoAuthService.register(usuarioRegisterDTO);
        register.enqueue(new Callback<Object>() {
            @Override
            public void onResponse(@NonNull Call<Object> call,
                                   Response<Object> response) {
                if(response.isSuccessful()) {
                    Object responseObject = response.body();
                    if (String.class.isAssignableFrom(responseObject.getClass())) {
                        System.out.println("ffff");
                        switch (RespuestasRegister.valueOf((String) response.body())) {
                            case INVALID_NAME:
                                break;
                            case INVALID_EMAIL:
                                break;
                            case NOT_AVAILABLE_USERNAME:
                                break;
                            case NOT_MINIMUN_REQUIREMENTS_PASSWORD:
                                break;
                            default:
                        }
                    } else {
                        UsuarioDTO usuarioDTO = (UsuarioDTO) response.body();
                        System.out.println(usuarioDTO);
                        mutableToken.setValue(usuarioDTO);
                    }
                }
            }
            @Override
            public void onFailure(@NonNull Call<Object> call, Throwable t) {
                System.out.println("xd");
            }
        });
        return mutableToken;
    }

    public LiveData<String> resendEmail(String token) {
        restAuthService = RetrofitClient.getInstance(token).getAuthRestService();
        MutableLiveData<String> mutableToken = new MutableLiveData<>();
        Call<ResponseBody> responseResendEmail = restAuthService.resendEmail();
        responseResendEmail.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(@NonNull Call<ResponseBody> call,
                                   Response<ResponseBody> response) {
                if(response.isSuccessful()) {
                    String respuesta = null;
                    try {
                        respuesta = response.body().string();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    System.out.println("ffff");
                    switch (EstadosUsuario.valueOf(respuesta)) {
                        case STATUS_INACTIVE:
                            break;
                        case STATUS_BAN:
                            break;
                        case STATUS_ACTIVE:
                            break;
                        default:
                            System.out.print("reenviaddodododo");
                    }
                }
            }
            @Override
            public void onFailure(@NonNull Call<ResponseBody> call, Throwable t) {
                System.out.println("xd");
            }
        });
        System.out.println(responseResendEmail.isExecuted());
        System.out.println(mutableToken.getValue());
        return mutableToken;
    }

}
