package es.iespuertodelacruz.alejandrosamuel.studycircle.ui;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.work.Constraints;
import androidx.work.ExistingPeriodicWorkPolicy;
import androidx.work.NetworkType;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;

import java.util.concurrent.TimeUnit;

import es.iespuertodelacruz.alejandrosamuel.studycircle.databinding.ActivityMainBinding;
import es.iespuertodelacruz.alejandrosamuel.studycircle.utils.TokenRenewalWorker;
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

        /*
        Constraints constraints = new Constraints.Builder()
                .setRequiredNetworkType(NetworkType.CONNECTED) // Requiere conexión de red
                .build();

        PeriodicWorkRequest renewalWorkRequest =
                new PeriodicWorkRequest.Builder(TokenRenewalWorker.class, 2, TimeUnit.MINUTES)
                        .setConstraints(constraints)
                        .build();

        WorkManager.getInstance(getApplicationContext()).enqueueUniquePeriodicWork(
                "tokenRenewal",
                ExistingPeriodicWorkPolicy.KEEP,
                renewalWorkRequest
        );
         */

    }
}