package es.iespuertodelacruz.alejandrosamuel.studycircle.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import es.iespuertodelacruz.alejandrosamuel.studycircle.data.rest.dto.UsuarioLoginDTO;
import es.iespuertodelacruz.alejandrosamuel.studycircle.data.rest.dto.UsuarioRegisterDTO;
import es.iespuertodelacruz.alejandrosamuel.studycircle.databinding.ActivityMainBinding;
import es.iespuertodelacruz.alejandrosamuel.studycircle.R;
import es.iespuertodelacruz.alejandrosamuel.studycircle.repository.AuthRepository;
import es.iespuertodelacruz.alejandrosamuel.studycircle.viewmodel.MainActivityViewModel;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private AuthRepository authRepository;
    MainActivityViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        viewModel = new ViewModelProvider(this).get(MainActivityViewModel.class);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        /**UsuarioRegisterDTO usuarioRegisterDTO = new UsuarioRegisterDTO();
        usuarioRegisterDTO.setNombre("samuel");
        usuarioRegisterDTO.setUsername("sammm");
        usuarioRegisterDTO.setEmail("codonak923@in2reach.com");
        usuarioRegisterDTO.setClave("Samuel1@");
        viewModel.register(usuarioRegisterDTO);*/
        UsuarioLoginDTO usuarioLoginDTO = new UsuarioLoginDTO();
        usuarioLoginDTO.setUsername("sammm");
        usuarioLoginDTO.setClave("Samuel1@");
        LiveData<String> liveDataToken = viewModel.getLiveDataToken(usuarioLoginDTO);
        viewModel.resendEmail();
    }
}