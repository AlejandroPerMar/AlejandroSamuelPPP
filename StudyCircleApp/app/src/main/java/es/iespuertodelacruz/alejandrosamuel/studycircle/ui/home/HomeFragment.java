package es.iespuertodelacruz.alejandrosamuel.studycircle.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import es.iespuertodelacruz.alejandrosamuel.studycircle.R;

import es.iespuertodelacruz.alejandrosamuel.studycircle.databinding.FragmentHomeBinding;

public class HomeFragment extends Fragment {

    private FragmentHomeViewModel mViewModel;
    private FragmentHomeBinding binding;
    private NavController navController;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        /*
        System.out.println(binding);

        BottomNavigationView navView = binding.navStart;
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
               R.id.navigation_start, R.id.navigation_dashboard, R.id.navigation_notifications)
                .build();

       NavHostFragment navHostFragment = (NavHostFragment) requireActivity().getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment_activity_home);
        navController = navHostFragment.getNavController();
         NavController navController = Navigation.findNavController(this.requireActivity(), R.id.nav_host_fragment_activity_home); // Problem #2 .findNavController
        NavigationUI.setupActionBarWithNavController((AppCompatActivity) this.requireActivity(), navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);

*/      binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        HomeViewModel homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);



        binding.btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View viewButton) {
                NavController navController = Navigation.findNavController(binding.getRoot());
                navController.navigate(R.id.action_nav_home_to_navigation_start);
            }
        });

        binding.btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View viewButton) {
                NavController navController = Navigation.findNavController(binding.getRoot());
                navController.navigate(R.id.action_nav_home_to_navigation_notifications);
            }
        });

        binding.btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View viewButton) {
                NavController navController = Navigation.findNavController(binding.getRoot());
                navController.navigate(R.id.action_nav_home_to_navigation_dashboard);
            }
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}