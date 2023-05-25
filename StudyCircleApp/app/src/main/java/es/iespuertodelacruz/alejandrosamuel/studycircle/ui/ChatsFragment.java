package es.iespuertodelacruz.alejandrosamuel.studycircle.ui;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RadioGroup;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.navigation.NavigationView;

import es.iespuertodelacruz.alejandrosamuel.studycircle.R;
import es.iespuertodelacruz.alejandrosamuel.studycircle.data.enums.RespuestasProfileConf;
import es.iespuertodelacruz.alejandrosamuel.studycircle.data.enums.UserProfiles;
import es.iespuertodelacruz.alejandrosamuel.studycircle.data.rest.dto.AlumnoDTO;
import es.iespuertodelacruz.alejandrosamuel.studycircle.data.rest.dto.TutorDTO;
import es.iespuertodelacruz.alejandrosamuel.studycircle.databinding.FragmentAlertsBinding;
import es.iespuertodelacruz.alejandrosamuel.studycircle.databinding.FragmentChatsBinding;
import es.iespuertodelacruz.alejandrosamuel.studycircle.viewmodel.MainActivityViewModel;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ChatsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ChatsFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private MainActivityViewModel viewModel;
    private FragmentChatsBinding binding;
    private MainActivity mainActivity;
    private RadioGroup switchProfile;
    private NavigationView navigationView;
    private BottomNavigationView bottomNavigationView;

    public ChatsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ChatsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ChatsFragment newInstance(String param1, String param2) {
        ChatsFragment fragment = new ChatsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(requireActivity()).get(MainActivityViewModel.class);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentChatsBinding.inflate(inflater, container, false);
        MainActivity mainActivity = (MainActivity) requireActivity();
        navigationView  = mainActivity.getNavigationView();
        bottomNavigationView = mainActivity.getBottomNav();
        switchProfile = mainActivity.getSwitchProfile();
        mainActivity.enableDrawer(true);
        mainActivity.setBottomNavVisibility(View.VISIBLE);
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                // Logic to handle item selection in the BottomNavigationView
                int itemId = item.getItemId();
                if(itemId == R.id.nav_home) {
                    Navigation.findNavController(container).navigate(R.id.action_chatsFragment_to_homeFragment);
                }else if(itemId == R.id.nav_notifications) {
                    Navigation.findNavController(container).navigate(R.id.action_chatsFragment_to_alertsFragment);
                }else if(itemId == R.id.nav_chats) {
                    Navigation.findNavController(container).navigate(R.id.action_refresh_chats_fragment);
                }else if(itemId == R.id.nav_calendar) {
                    Navigation.findNavController(container).navigate(R.id.action_chatsFragment_to_calendarFragment);
                }
                return true;
            }
        });

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                // Lógica para manejar la selección del item en el NavigationView
                int itemId = item.getItemId();
                mainActivity.enableDrawer(false);
                mainActivity.enableDrawer(true);
                if(itemId == R.id.nav_home) {
                    bottomNavigationView.setSelectedItemId(R.id.nav_home);
                }else if(itemId == R.id.nav_anouncements) {
                    Navigation.findNavController(container).navigate(R.id.action_chatsFragment_to_anunciosFragment);
                }else if(itemId == R.id.nav_search_users) {
                    Navigation.findNavController(container).navigate(R.id.action_chatsFragment_to_busquedaUsuariosFragment);
                }else if(itemId == R.id.nav_settings) {
                    Navigation.findNavController(container).navigate(R.id.action_chatsFragment_to_configuracionFragment);
                }else if(itemId == R.id.nav_logout) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(getContext(), R.style.CustomAlertDialogStyle);
                    builder.setTitle("Cerrar Sesión");
                    builder.setMessage("¿Desea cerrar la sesión?");
                    builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            viewModel.limpiarTokenSharedPreferences(getContext());
                            Navigation.findNavController(container).navigate(R.id.action_alertsFragment_to_loginFragment);
                        }
                    });
                    builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {}
                    });

                    AlertDialog dialog = builder.create();
                    dialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
                    dialog.getWindow().getDecorView().setPadding(50, 0, 50, 0);
                    dialog.show();
                    Button positiveButton = dialog.getButton(DialogInterface.BUTTON_POSITIVE);
                    Button negativeButton = dialog.getButton(DialogInterface.BUTTON_NEGATIVE);
                    positiveButton.setTextColor(ContextCompat.getColor(requireContext(), R.color.button_color_selector));
                    negativeButton.setTextColor(ContextCompat.getColor(requireContext(), R.color.button_color_selector));
                }
                return true;
            }
        });

        switchProfile.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if(checkedId == R.id.switchAlumno) {
                    LiveData<Object> alumno = viewModel.getAlumno(viewModel.recuperarTokenSharedPreferences(getContext()));
                    alumno.observe(getViewLifecycleOwner(), new Observer<Object>() {
                        @Override
                        public void onChanged(Object o) {
                            if(o instanceof AlumnoDTO) {
                                viewModel.guardarPerfilSeleccionadoSharedPreferences(getContext(), UserProfiles.STUDENT_PROFILE.name());
                                Navigation.findNavController(requireView()).navigate(R.id.action_refresh_chats_fragment);
                            }else if(o instanceof RespuestasProfileConf) {
                                Navigation.findNavController(requireView()).navigate(R.id.action_chatsFragment_to_alumnoConfFragment);
                            }
                        }
                    });
                }else if(checkedId == R.id.switchTutor) {
                    LiveData<Object> alumno = viewModel.getTutor(viewModel.recuperarTokenSharedPreferences(getContext()));
                    alumno.observe(getViewLifecycleOwner(), new Observer<Object>() {
                        @Override
                        public void onChanged(Object o) {
                            if(o instanceof TutorDTO) {
                                viewModel.guardarPerfilSeleccionadoSharedPreferences(getContext(), UserProfiles.TUTOR_PROFILE.name());
                                Navigation.findNavController(requireView()).navigate(R.id.action_refresh_chats_fragment);
                            }else if(o instanceof RespuestasProfileConf) {
                                Navigation.findNavController(requireView()).navigate(R.id.action_chatsFragment_to_tutorConfFragment);
                            }
                        }
                    });
                }
            }
        });

        return binding.getRoot();
    }
}