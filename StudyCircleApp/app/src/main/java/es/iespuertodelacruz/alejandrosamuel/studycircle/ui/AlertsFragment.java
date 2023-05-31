package es.iespuertodelacruz.alejandrosamuel.studycircle.ui;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RadioGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.navigation.NavigationView;

import java.util.List;
import java.util.stream.Collectors;

import es.iespuertodelacruz.alejandrosamuel.studycircle.R;
import es.iespuertodelacruz.alejandrosamuel.studycircle.adapters.AlumnosAdapter;
import es.iespuertodelacruz.alejandrosamuel.studycircle.adapters.InvitacionesAdapter;
import es.iespuertodelacruz.alejandrosamuel.studycircle.adapters.SolicitudesAdapter;
import es.iespuertodelacruz.alejandrosamuel.studycircle.data.enums.EstadosAmistad;
import es.iespuertodelacruz.alejandrosamuel.studycircle.data.enums.RespuestasProfileConf;
import es.iespuertodelacruz.alejandrosamuel.studycircle.data.enums.UserProfiles;
import es.iespuertodelacruz.alejandrosamuel.studycircle.data.rest.dto.AlertaAmistadDTO;
import es.iespuertodelacruz.alejandrosamuel.studycircle.data.rest.dto.AlertaCursoAlumnoDTO;
import es.iespuertodelacruz.alejandrosamuel.studycircle.data.rest.dto.AlumnoDTO;
import es.iespuertodelacruz.alejandrosamuel.studycircle.data.rest.dto.TutorDTO;
import es.iespuertodelacruz.alejandrosamuel.studycircle.databinding.FragmentAlertsBinding;
import es.iespuertodelacruz.alejandrosamuel.studycircle.viewmodel.MainActivityViewModel;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AlertsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AlertsFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private MainActivityViewModel viewModel;
    private FragmentAlertsBinding binding;
    private MainActivity mainActivity;
    private RadioGroup switchProfile;
    private NavigationView navigationView;
    private BottomNavigationView bottomNavigationView;
    NavController navController;
    private ImageView btnExpand;
    private RecyclerView solicitudesAmistadRecyclerView;
    private RecyclerView solicitudesCursosRecyclerView;
    private TextView txtInvitacionesCurso;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController = NavHostFragment.findNavController(this);

        // En tu onViewCreated...

        // Define tu OnItemSelectedListener
        NavigationBarView.OnItemSelectedListener onItemSelectedListener = new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() != bottomNavigationView.getSelectedItemId()) {
                    if(item.getItemId() == R.id.nav_home) {
                        navController.navigate(R.id.homeFragment);
                    }else if(item.getItemId() == R.id.nav_notifications) {
                        navController.navigate(R.id.alertsFragment);
                    }else if(item.getItemId() == R.id.nav_chats) {
                        navController.navigate(R.id.chatsFragment);
                    }else if(item.getItemId() == R.id.nav_calendar) {
                        navController.navigate(R.id.calendarFragment);
                    }
                }
                return true;
            }
        };

        // Establece el OnItemSelectedListener
        bottomNavigationView.setOnItemSelectedListener(onItemSelectedListener);

        // Añade el OnDestinationChangedListener
        navController.addOnDestinationChangedListener(new NavController.OnDestinationChangedListener() {
            @Override
            public void onDestinationChanged(@NonNull NavController controller, @NonNull NavDestination destination, @Nullable Bundle arguments) {
                // Deshabilita el listener temporalmente
                bottomNavigationView.setOnItemSelectedListener(null);

                Menu menu = bottomNavigationView.getMenu();
                MenuItem menuItem = null;

                if(destination.getId() == R.id.homeFragment) {
                    menuItem = menu.findItem(R.id.nav_home);
                }else if(destination.getId() == R.id.alertsFragment) {
                    menuItem = menu.findItem(R.id.nav_notifications);
                }else if(destination.getId() == R.id.chatsFragment) {
                    menuItem = menu.findItem(R.id.nav_chats);
                }else if(destination.getId() == R.id.calendarFragment) {
                    menuItem = menu.findItem(R.id.nav_calendar);
                }

                if (menuItem != null) {
                    menuItem.setChecked(true);
                }

                // Reestablece el listener
                bottomNavigationView.setOnItemSelectedListener(onItemSelectedListener);
            }
        });
    }

    public AlertsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AlertsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AlertsFragment newInstance(String param1, String param2) {
        AlertsFragment fragment = new AlertsFragment();
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
        binding = FragmentAlertsBinding.inflate(inflater, container, false);
        mainActivity = (MainActivity) requireActivity();
        solicitudesCursosRecyclerView = binding.solicitudesCursosRecyclerView;
        solicitudesAmistadRecyclerView = binding.solicitudesAmistadRecyclerView;
        btnExpand = binding.btnExpand;
        txtInvitacionesCurso = binding.txtInvitacionesCurso;
        navigationView  = mainActivity.getNavigationView();
        bottomNavigationView = mainActivity.getBottomNav();
        switchProfile = mainActivity.getSwitchProfile();
        mainActivity.enableDrawer(true);
        mainActivity.setBottomNavVisibility(View.VISIBLE);

        String perfil = viewModel.recuperarPerfilSeleccionadoSharedPreferences(getContext());
        LiveData<Object> alertasAmistadByUsuario = viewModel.findAlertasAmistadByUsuario(viewModel.recuperarTokenSharedPreferences(getContext()));
        alertasAmistadByUsuario.observe(getViewLifecycleOwner(), new Observer<Object>() {
            @Override
            public void onChanged(Object o) {
                if(o instanceof List) {
                    List<AlertaAmistadDTO> alertasAmistad = ((List<AlertaAmistadDTO>) o).stream().filter(a -> !a.getAmistad().getEstado().equals(EstadosAmistad.FRIENDSHIP_ACCEPTED.name())).collect(Collectors.toList());
                    if(alertasAmistad.isEmpty()) {
                        solicitudesAmistadRecyclerView.setVisibility(View.GONE);
                        TextView textView = new TextView(getContext());
                        textView.setText("No hay nuevas solicitudes");

                        float scaledSizeInPixels = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 5, getResources().getDisplayMetrics());
                        textView.setTextSize(scaledSizeInPixels);

                        textView.setTextColor(Color.GRAY);
                        textView.setGravity(Gravity.CENTER_HORIZONTAL);

                        LinearLayout.LayoutParams textViewParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
                        textViewParams.weight = 1; // Establece el weight deseado

                        LinearLayout.LayoutParams containerParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
                        containerParams.setMargins(90, 200, 90, 200); // Ajusta el valor del margen superior según sea necesario

                        LinearLayout c = new LinearLayout(getContext());
                        c.setLayoutParams(containerParams);
                        c.addView(textView, textViewParams);

                        LinearLayout layout = (LinearLayout) solicitudesAmistadRecyclerView.getParent();
                        layout.setOrientation(LinearLayout.VERTICAL);
                        layout.setGravity(Gravity.CENTER_VERTICAL);
                        layout.addView(c, layout.indexOfChild(solicitudesAmistadRecyclerView)); // Índice 1 para insertar en la segunda posición
                    }else {
                        solicitudesAmistadRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                        solicitudesAmistadRecyclerView.setVisibility(View.VISIBLE);
                        SolicitudesAdapter adapter = new SolicitudesAdapter(alertasAmistad, viewModel, container, getViewLifecycleOwner());
                        solicitudesAmistadRecyclerView.setAdapter(adapter);
                    }
                }
            }
        });
        if(perfil.equals(UserProfiles.TUTOR_PROFILE.name())) {
            solicitudesCursosRecyclerView.setVisibility(View.GONE);
            txtInvitacionesCurso.setVisibility(View.GONE);
        }else if(perfil.equals(UserProfiles.STUDENT_PROFILE.name())) {
            LiveData<Object> alertasCursoAlumnodByUsuario = viewModel.findAlertasCursoAlumnodByUsuario(viewModel.recuperarTokenSharedPreferences(getContext()));
            alertasCursoAlumnodByUsuario.observe(getViewLifecycleOwner(), new Observer<Object>() {
                @Override
                public void onChanged(Object o) {
                    if(o instanceof List) {
                        if(((List<?>) o).isEmpty()) {
                            solicitudesCursosRecyclerView.setVisibility(View.GONE);
                            TextView textView = new TextView(getContext());
                            textView.setText("No hay nuevas invitaciones");

                            float scaledSizeInPixels = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 5, getResources().getDisplayMetrics());
                            textView.setTextSize(scaledSizeInPixels);

                            textView.setTextColor(Color.GRAY);
                            textView.setGravity(Gravity.CENTER_HORIZONTAL);

                            LinearLayout.LayoutParams textViewParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
                            textViewParams.weight = 1; // Establece el weight deseado

                            LinearLayout.LayoutParams containerParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
                            containerParams.setMargins(90, 200, 90, 200); // Ajusta el valor del margen superior según sea necesario

                            LinearLayout c = new LinearLayout(getContext());
                            c.setLayoutParams(containerParams);
                            c.addView(textView, textViewParams);

                            LinearLayout layout = (LinearLayout) solicitudesCursosRecyclerView.getParent();
                            layout.setOrientation(LinearLayout.VERTICAL);
                            layout.setGravity(Gravity.CENTER_VERTICAL);
                            layout.addView(c, layout.indexOfChild(solicitudesCursosRecyclerView)); // Índice 1 para insertar en la segunda posición
                        }else {
                            solicitudesCursosRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                            solicitudesCursosRecyclerView.setVisibility(View.VISIBLE);
                            InvitacionesAdapter adapter = new InvitacionesAdapter((List<AlertaCursoAlumnoDTO>) o, viewModel, container, getViewLifecycleOwner());
                            solicitudesCursosRecyclerView.setAdapter(adapter);
                        }
                    }
                }
            });
        }




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
                    Navigation.findNavController(container).navigate(R.id.action_alertsFragment_to_anunciosFragment);
                }else if(itemId == R.id.nav_search_users) {
                    Navigation.findNavController(container).navigate(R.id.action_alertsFragment_to_busquedaUsuariosFragment);
                }else if(itemId == R.id.nav_settings) {
                    Navigation.findNavController(container).navigate(R.id.action_alertsFragment_to_configuracionFragment);
                }else if(itemId == R.id.nav_logout) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(getContext(), R.style.CustomAlertDialogStyle);
                    builder.setTitle("Cerrar Sesión");
                    builder.setMessage("¿Desea cerrar la sesión?");
                    builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            viewModel.limpiarTokenSharedPreferences(getContext());
                            viewModel.limpiarPerfilSeleccionadoSharedPreferences(getContext());
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
                mainActivity.closeDrawer();
                if(checkedId == R.id.switchAlumno) {
                    LiveData<Object> alumno = viewModel.getAlumno(viewModel.recuperarTokenSharedPreferences(getContext()));
                    alumno.observe(getViewLifecycleOwner(), new Observer<Object>() {
                        @Override
                        public void onChanged(Object o) {
                            if(o instanceof AlumnoDTO) {
                                viewModel.guardarPerfilSeleccionadoSharedPreferences(getContext(), UserProfiles.STUDENT_PROFILE.name());
                                Navigation.findNavController(requireView()).navigate(R.id.action_refresh_alerts_fragment);
                            }else if(o instanceof RespuestasProfileConf) {
                                switchProfile.check(R.id.switchTutor);
                                Navigation.findNavController(requireView()).navigate(R.id.action_alertsFragment_to_alumnoConfFragment);
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
                                Navigation.findNavController(requireView()).navigate(R.id.action_refresh_alerts_fragment);
                            }else if(o instanceof RespuestasProfileConf) {
                                switchProfile.check(R.id.switchAlumno);
                                Navigation.findNavController(requireView()).navigate(R.id.action_alertsFragment_to_tutorConfFragment);
                            }
                        }
                    });
                }
            }
        });

        btnExpand.setOnClickListener(btn -> {
            mainActivity.openDrawer();
        });

        return binding.getRoot();
    }
}