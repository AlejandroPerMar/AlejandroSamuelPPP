package es.iespuertodelacruz.alejandrosamuel.studycircle.ui;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import es.iespuertodelacruz.alejandrosamuel.studycircle.databinding.ActivityMainBinding;
import es.iespuertodelacruz.alejandrosamuel.studycircle.viewmodel.MainActivityViewModel;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    private SharedPreferences sharedPreferences;
    MainActivityViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        viewModel = new ViewModelProvider(this).get(MainActivityViewModel.class);
        setContentView(binding.getRoot());
    }
}