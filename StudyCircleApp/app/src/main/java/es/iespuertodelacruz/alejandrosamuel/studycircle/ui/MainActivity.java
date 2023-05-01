package es.iespuertodelacruz.alejandrosamuel.studycircle.ui;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import es.iespuertodelacruz.alejandrosamuel.studycircle.R;
import es.iespuertodelacruz.alejandrosamuel.studycircle.data.rest.dto.UsuarioRegisterDTO;
import es.iespuertodelacruz.alejandrosamuel.studycircle.databinding.ActivityMainBinding;
import es.iespuertodelacruz.alejandrosamuel.studycircle.viewmodel.MainActivityViewModel;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
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
        usuarioRegisterDTO.setUsername("pruee4");
        usuarioRegisterDTO.setEmail("bisiboy499@jobbrett.com");
        usuarioRegisterDTO.setClave("Samuel1@");
        viewModel.register(usuarioRegisterDTO);
        //UsuarioLoginDTO usuarioLoginDTO = new UsuarioLoginDTO();
        //usuarioLoginDTO.setUsername("sammm");
        //usuarioLoginDTO.setClave("Samuel1@");
        //viewModel.resendEmail("eyJhbGciOiJIUzI1NiJ9.eyJhdXRob3JpdGllcyI6WyJST0xFX1VTRVIiXSwic3ViIjoic3RyIiwiZXhwIjoxNjgzMDE4NzY0fQ.pX6jM0HOY813W3HepET4ZxrrcsMpp-gTLc5Jrs72WN8");
    }
}