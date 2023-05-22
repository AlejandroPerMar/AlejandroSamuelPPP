package es.iespuertodelacruz.alejandrosamuel.studycircle.ui;

import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.work.Constraints;
import androidx.work.ExistingPeriodicWorkPolicy;
import androidx.work.NetworkType;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;

import com.google.android.material.bottomnavigation.BottomNavigationMenuView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationView;

import java.util.concurrent.TimeUnit;

import es.iespuertodelacruz.alejandrosamuel.studycircle.R;
import es.iespuertodelacruz.alejandrosamuel.studycircle.databinding.ActivityMainBinding;
import es.iespuertodelacruz.alejandrosamuel.studycircle.utils.TokenRenewalWorker;
import es.iespuertodelacruz.alejandrosamuel.studycircle.viewmodel.MainActivityViewModel;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    private SharedPreferences sharedPreferences;
    private MainActivityViewModel viewModel;
    private DrawerLayout drawerLayout;
    private BottomNavigationView bottomNav;
    private RadioGroup switchProfile;
    private NavigationView navigationView;

    public void enableDrawer(boolean enable){
        int lockMode = enable ? DrawerLayout.LOCK_MODE_UNLOCKED : DrawerLayout.LOCK_MODE_LOCKED_CLOSED;
        drawerLayout.setDrawerLockMode(lockMode);
    }

    public void checkTutorProfile() {
        switchProfile.check(R.id.switchTutor);
    }

    public void checkAlumnoProfile() {
        switchProfile.check(R.id.switchAlumno);
    }



    public void setBottomNavVisibility(int visibility) {
        bottomNav.setVisibility(visibility);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        viewModel = new ViewModelProvider(this).get(MainActivityViewModel.class);
        setContentView(binding.getRoot());
        drawerLayout = binding.drawerLayout;
        bottomNav = findViewById(R.id.bottom_nav);
        navigationView = drawerLayout.findViewById(R.id.nav_view);
        View headerView = navigationView.getHeaderView(0);
        switchProfile = headerView.findViewById(R.id.toggle);

        // Esto hace que el primer elemento (Home en este caso) se seleccione por defecto al iniciar
        bottomNav.setSelectedItemId(R.id.navigation_home);
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

    public MainActivityViewModel getViewModel() {
        return viewModel;
    }

    public DrawerLayout getDrawerLayout() {
        return drawerLayout;
    }

    public RadioGroup getSwitchProfile() {
        return switchProfile;
    }

    public void setSwitchProfile(RadioGroup switchProfile) {
        this.switchProfile = switchProfile;
    }
}