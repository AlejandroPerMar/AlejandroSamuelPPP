package es.iespuertodelacruz.alejandrosamuel.studycircle.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

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
        UsuarioRegisterDTO usuarioRegisterDTO = new UsuarioRegisterDTO();
        usuarioRegisterDTO.setNombre("samuel");
        usuarioRegisterDTO.setUsername("sammm");
        usuarioRegisterDTO.setEmail("cejero4564@larland.com");
        usuarioRegisterDTO.setClave("Samuel1@");
        viewModel.register(usuarioRegisterDTO);
    }
}