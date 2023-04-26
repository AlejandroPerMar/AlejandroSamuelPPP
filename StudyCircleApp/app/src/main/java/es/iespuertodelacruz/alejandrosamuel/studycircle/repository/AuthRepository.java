package es.iespuertodelacruz.alejandrosamuel.studycircle.repository;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;


import es.iespuertodelacruz.alejandrosamuel.studycircle.data.db.DatabaseStudyCircle;
import es.iespuertodelacruz.alejandrosamuel.studycircle.data.rest.RESTService;
import es.iespuertodelacruz.alejandrosamuel.studycircle.data.rest.RetrofitClient;
import es.iespuertodelacruz.alejandrosamuel.studycircle.data.rest.dto.UsuarioLoginDTO;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AuthRepository {
    RESTService restService;
    private final DatabaseStudyCircle database;

    public AuthRepository(Application application){
        database = DatabaseStudyCircle.getDatabase(application);
        restService = RetrofitClient.getInstance().getRestService();
    }

    public LiveData<String> getAuthToken(UsuarioLoginDTO usuarioLoginDTO) {
        MutableLiveData<String> mutableToken = new MutableLiveData<>();
        Call<String> callToken = restService.login(usuarioLoginDTO);
        callToken.enqueue(new Callback<String>() {
            @Override
            public void onResponse(@NonNull Call<String> call,
                                   Response<String> response) {
                if(response.isSuccessful()) {
                    String tokenResponse = "Bearer " + response.body();
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

}
