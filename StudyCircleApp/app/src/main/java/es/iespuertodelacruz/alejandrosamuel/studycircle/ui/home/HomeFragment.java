package es.iespuertodelacruz.alejandrosamuel.studycircle.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.NavHost;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import es.iespuertodelacruz.alejandrosamuel.studycircle.R;

import es.iespuertodelacruz.alejandrosamuel.studycircle.databinding.FragmentHomeBinding;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private  NavController navController;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentHomeBinding.inflate(inflater, container, false);

        BottomNavigationView navView = binding.navStart;
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
               R.id.navigation_start, R.id.navigation_dashboard, R.id.navigation_notifications)
                .build();

      //  NavHostFragment navHostFragment = (NavHostFragment) requireActivity().getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment_activity_home);
//        navController = navHostFragment.getNavController();

        //NavController navController = Navigation.findNavController(this.requireActivity(), R.id.nav_host_fragment_activity_home); // Problem #2 .findNavController
     //  NavigationUI.setupActionBarWithNavController((AppCompatActivity) this.requireActivity(), navController, appBarConfiguration);
      // NavigationUI.setupWithNavController(navView, navController);

        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}