package es.iespuertodelacruz.alejandrosamuel.studycircle.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;


import es.iespuertodelacruz.alejandrosamuel.studycircle.data.rest.dto.UsuarioLoginDTO;
import es.iespuertodelacruz.alejandrosamuel.studycircle.repository.AuthRepository;

public class MainActivityViewModel extends AndroidViewModel {
    private final AuthRepository authRepository;

    public MainActivityViewModel(@NonNull Application application) {
        super(application);
        authRepository = new AuthRepository(application);
    }

    public LiveData<String> getLiveDataToken(UsuarioLoginDTO usuarioLoginDTO) {
        return authRepository.getAuthToken(usuarioLoginDTO);
    }
}
