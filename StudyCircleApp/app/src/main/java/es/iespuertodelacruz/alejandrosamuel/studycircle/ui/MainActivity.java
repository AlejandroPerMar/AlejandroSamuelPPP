package es.iespuertodelacruz.alejandrosamuel.studycircle.ui;

import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.lifecycle.ViewModelProvider;
import androidx.work.Constraints;
import androidx.work.ExistingPeriodicWorkPolicy;
import androidx.work.NetworkType;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;

import com.google.android.material.navigation.NavigationView;

import java.util.concurrent.TimeUnit;

import es.iespuertodelacruz.alejandrosamuel.studycircle.R;
import es.iespuertodelacruz.alejandrosamuel.studycircle.databinding.ActivityMainBinding;
import es.iespuertodelacruz.alejandrosamuel.studycircle.utils.TokenRenewalWorker;
import es.iespuertodelacruz.alejandrosamuel.studycircle.viewmodel.MainActivityViewModel;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    private SharedPreferences sharedPreferences;
    MainActivityViewModel viewModel;
    DrawerLayout drawerLayout;

    public void enableDrawer(boolean enable){
        int lockMode = enable ? DrawerLayout.LOCK_MODE_UNLOCKED : DrawerLayout.LOCK_MODE_LOCKED_CLOSED;
        drawerLayout.setDrawerLockMode(lockMode);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        viewModel = new ViewModelProvider(this).get(MainActivityViewModel.class);
        setContentView(binding.getRoot());
        drawerLayout = binding.drawerLayout;

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