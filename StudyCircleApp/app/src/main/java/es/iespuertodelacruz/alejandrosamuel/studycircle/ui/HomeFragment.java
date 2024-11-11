package es.iespuertodelacruz.alejandrosamuel.studycircle.ui;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

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
import java.util.Objects;
import java.util.stream.Collectors;

import es.iespuertodelacruz.alejandrosamuel.studycircle.R;
import es.iespuertodelacruz.alejandrosamuel.studycircle.adapters.CursoAdapter;
import es.iespuertodelacruz.alejandrosamuel.studycircle.adapters.CustomArrayAdapter;
import es.iespuertodelacruz.alejandrosamuel.studycircle.data.enums.RespuestasProfileConf;
import es.iespuertodelacruz.alejandrosamuel.studycircle.data.enums.UserProfiles;
import es.iespuertodelacruz.alejandrosamuel.studycircle.data.rest.dto.AlumnoDTO;
import es.iespuertodelacruz.alejandrosamuel.studycircle.data.rest.dto.CursoDTO;
import es.iespuertodelacruz.alejandrosamuel.studycircle.data.rest.dto.MateriaDTO;
import es.iespuertodelacruz.alejandrosamuel.studycircle.data.rest.dto.MateriaTutorDTO;
import es.iespuertodelacruz.alejandrosamuel.studycircle.data.rest.dto.TutorDTO;
import es.iespuertodelacruz.alejandrosamuel.studycircle.data.rest.dto.UsuarioDTO;
import es.iespuertodelacruz.alejandrosamuel.studycircle.databinding.FragmentHomeBinding;
import es.iespuertodelacruz.alejandrosamuel.studycircle.utils.ObjectsUtils;
import es.iespuertodelacruz.alejandrosamuel.studycircle.viewmodel.MainActivityViewModel;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private FragmentHomeBinding binding;
    private MainActivityViewModel viewModel;
    private MainActivity mainActivity;
    private RadioGroup switchProfile;
    private NavigationView navigationView;
    private BottomNavigationView bottomNavigationView;
    private NavController navController;
    private ProgressBar progressBar;
    private ImageView btnExpand;
    private ImageView btnCrearCurso;
    private RecyclerView recyclerCursos;
    private boolean isProgrammaticChange;
    private CursoDTO cursoDTO;

    public HomeFragment() {
        // Required empty public constructor
    }


    private void closeKeyboard() {
        InputMethodManager manager = (InputMethodManager) requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        manager.hideSoftInputFromWindow(getView().getWindowToken(), 0);
    }

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

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HomeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HomeFragment newInstance(String param1, String param2) {
        HomeFragment fragment = new HomeFragment();
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
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        mainActivity = (MainActivity) requireActivity();
        progressBar = binding.progressBar;
        btnExpand = binding.btnExpand;
        progressBar.setVisibility(View.INVISIBLE);
        navigationView  = mainActivity.getNavigationView();
        bottomNavigationView = mainActivity.getBottomNav();
        switchProfile = mainActivity.getSwitchProfile();
        mainActivity.enableDrawer(true);
        btnCrearCurso = binding.btnCrearCurso;
        recyclerCursos = binding.recyclerCursos;
        mainActivity.setBottomNavVisibility(View.VISIBLE);
        isProgrammaticChange = false;
        LiveData<Object> usuario = viewModel.getUsuario(viewModel.recuperarTokenSharedPreferences(getContext()));
        usuario.observe(getViewLifecycleOwner(), new Observer<Object>() {
            @Override
            public void onChanged(Object o) {
                if(o instanceof UsuarioDTO) {
                    viewModel.setUsuarioDTO((UsuarioDTO) o);
                    TextView viewById = navigationView.getHeaderView(0).findViewById(R.id.nav_header_textView);
                    viewById.setText(viewModel.getUsuarioDTO().getUsername());
                }
            }
        });

        String perfilSeleccionado = viewModel.recuperarPerfilSeleccionadoSharedPreferences(getContext());

        Log.d("TAG", perfilSeleccionado != null ? perfilSeleccionado : "");
        if(Objects.nonNull(perfilSeleccionado)) {
            if(perfilSeleccionado.equals(UserProfiles.TUTOR_PROFILE.name())) {
                mainActivity.checkTutorProfile();
                LiveData<Object> cursosTutor = viewModel.findCursosTutor(viewModel.recuperarTokenSharedPreferences(getContext()));
                cursosTutor.observe(getViewLifecycleOwner(), new Observer<Object>() {
                    @Override
                    public void onChanged(Object o) {
                        if(o instanceof List) {
                            if(((List<CursoDTO>) o).isEmpty()) {
                                recyclerCursos.setVisibility(View.GONE);
                                TextView textView = new TextView(getContext());
                                textView.setText("No tienes ningún curso, ¡comienza a crear cursos y a tutorizar alumnos!");

                                float scaledSizeInPixels = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 5, getResources().getDisplayMetrics());
                                textView.setTextSize(scaledSizeInPixels);

                                textView.setTextColor(Color.GRAY);
                                textView.setGravity(Gravity.CENTER_HORIZONTAL);

                                LinearLayout.LayoutParams textViewParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);

                                LinearLayout.LayoutParams containerParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                                containerParams.setMargins(90, 700, 90, 0); // Ajusta el valor del margen superior según sea necesario

                                LinearLayout container = new LinearLayout(getContext());
                                container.setLayoutParams(containerParams);
                                container.addView(textView, textViewParams);

                                LinearLayout layout = (LinearLayout) recyclerCursos.getParent();
                                layout.addView(container);
                            }else {
                                recyclerCursos.setVisibility(View.VISIBLE);
                                recyclerCursos.setLayoutManager(new LinearLayoutManager(getContext()));
                                CursoAdapter cursoAdapter = new CursoAdapter((List<CursoDTO>)o, getContext(), viewModel, container);
                                recyclerCursos.setAdapter(cursoAdapter);
                            }
                        }
                    }
                });

                btnCrearCurso.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        cursoDTO = new CursoDTO();
                        AlertDialog.Builder builder = new AlertDialog.Builder(getContext(), R.style.CustomAlertDialogStyle);
                        LayoutInflater inflater = getLayoutInflater();
                        View viewE = inflater.inflate(R.layout.dialog_new_curso, null);
                        builder.setView(viewE);
                        EditText titleEditText = viewE.findViewById(R.id.titleEditText);
                        AutoCompleteTextView autoCompleteTextView = viewE.findViewById(R.id.autoCompleteTextView);
                        autoCompleteTextView.setThreshold(1);
                        LiveData<Object> materiasByTutor = viewModel.findMateriasByTutor(viewModel.recuperarTokenSharedPreferences(getContext()));
                        materiasByTutor.observe(getViewLifecycleOwner(), new Observer<Object>() {
                            @Override
                            public void onChanged(Object o) {
                                if(o instanceof List) {
                                    List<MateriaDTO> materias = ((List<MateriaDTO>) o);
                                    List<String> nombresMateria = materias.stream().map(MateriaDTO::getNombre).collect(Collectors.toList());
                                    CustomArrayAdapter adapter = new CustomArrayAdapter(getContext(), android.R.layout.simple_dropdown_item_1line, nombresMateria);
                                    autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                        @Override
                                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                            closeKeyboard();
                                            String materiaSeleccionada = parent.getItemAtPosition(position).toString();
                                            MateriaDTO materiaDTO = materias.stream().filter(n -> n.getNombre().equals(materiaSeleccionada)).findFirst().get();
                                            cursoDTO.setMateriaTutor(new MateriaTutorDTO());
                                            cursoDTO.getMateriaTutor().setMateria(materiaDTO);
                                        }
                                    });
                                    autoCompleteTextView.setAdapter(adapter);
                                }
                            }
                        });
                        builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int id) {
                                String title = titleEditText.getText().toString();
                                if(ObjectsUtils.notNullNorEmpty(title, cursoDTO.getMateriaTutor().getMateria())) {
                                    cursoDTO.setTitulo(title);

                                    LiveData<Object> crearAnuncio = viewModel.createCurso(cursoDTO, viewModel.recuperarTokenSharedPreferences(getContext()));

                                    crearAnuncio.observe(getViewLifecycleOwner(), new Observer<Object>() {
                                        @Override
                                        public void onChanged(Object o) {
                                            if(o instanceof CursoDTO) {
                                                AlertDialog.Builder builder = new AlertDialog.Builder(getContext(), R.style.CustomAlertDialogStyle);
                                                builder.setTitle("Curso creado");
                                                builder.setMessage("Se ha creado el curso correctamente");
                                                builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                                                    @Override
                                                    public void onClick(DialogInterface dialog, int which) {
                                                        Navigation.findNavController(container).navigate(R.id.action_refresh_home_fragment);

                                                    }
                                                });

                                                AlertDialog dialogFechaError = builder.create();
                                                dialogFechaError.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
                                                dialogFechaError.getWindow().getDecorView().setPadding(50, 0, 50, 0);
                                                dialogFechaError.show();
                                                Button positiveButton = dialogFechaError.getButton(DialogInterface.BUTTON_POSITIVE);
                                                positiveButton.setTextColor(ContextCompat.getColor(requireContext(), R.color.button_color_selector));
                                            }else {
                                                Toast.makeText(getContext(), "No se ha podido crear el anuncio", Toast.LENGTH_LONG).show();
                                            }

                                        }
                                    });
                                }else {
                                    AlertDialog.Builder builder = new AlertDialog.Builder(getContext(), R.style.CustomAlertDialogStyle);
                                    builder.setTitle("Datos incorrectos");
                                    builder.setMessage("Los campos no pueden estar vacíos y se de debe seleccionar una materia");
                                    builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                        }
                                    });

                                    AlertDialog dialogFechaError = builder.create();
                                    dialogFechaError.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
                                    dialogFechaError.getWindow().getDecorView().setPadding(50, 0, 50, 0);
                                    dialogFechaError.show();
                                    Button positiveButton = dialogFechaError.getButton(DialogInterface.BUTTON_POSITIVE);
                                    positiveButton.setTextColor(ContextCompat.getColor(requireContext(), R.color.button_color_selector));
                                }
                            }
                        });

                        builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });

                        AlertDialog dialog = builder.create();

                        // Aquí ajustas el tamaño y los márgenes del diálogo, así como los colores de los botones.
                        dialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
                        dialog.getWindow().getDecorView().setPadding(50, 0, 50, 0);

                        dialog.show();

                        Button positiveButton = dialog.getButton(DialogInterface.BUTTON_POSITIVE);
                        Button negativeButton = dialog.getButton(DialogInterface.BUTTON_NEGATIVE);
                        positiveButton.setTextColor(ContextCompat.getColor(requireContext(), R.color.button_color_selector));
                        negativeButton.setTextColor(ContextCompat.getColor(requireContext(), R.color.button_color_selector));
                        }
                });
            }else if(perfilSeleccionado.equals(UserProfiles.STUDENT_PROFILE.name())) {
                btnCrearCurso.setVisibility(View.GONE);
                LiveData<Object> cursosAlumno = viewModel.findCursosAlumno(viewModel.recuperarTokenSharedPreferences(getContext()));
                cursosAlumno.observe(getViewLifecycleOwner(), new Observer<Object>() {
                    @Override
                    public void onChanged(Object o) {
                        if(o instanceof List) {
                            if(((List<CursoDTO>) o).isEmpty()) {
                                recyclerCursos.setVisibility(View.GONE);
                                TextView textView = new TextView(getContext());
                                textView.setText("No estás suscrito a cursos, ¡comienza nuevas amistades o consulta el tablón de anuncios y encuentra lo que necesitas!");

                                float scaledSizeInPixels = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 5, getResources().getDisplayMetrics());
                                textView.setTextSize(scaledSizeInPixels);

                                textView.setTextColor(Color.GRAY);
                                textView.setGravity(Gravity.CENTER_HORIZONTAL);

                                LinearLayout.LayoutParams textViewParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);

                                LinearLayout.LayoutParams containerParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                                containerParams.setMargins(90, 700, 90, 0); // Ajusta el valor del margen superior según sea necesario

                                LinearLayout container = new LinearLayout(getContext());
                                container.setLayoutParams(containerParams);
                                container.addView(textView, textViewParams);

                                LinearLayout layout = (LinearLayout) recyclerCursos.getParent();
                                layout.addView(container);
                            }else {
                                recyclerCursos.setVisibility(View.VISIBLE);
                                recyclerCursos.setLayoutManager(new LinearLayoutManager(getContext()));
                                CursoAdapter cursoAdapter = new CursoAdapter((List<CursoDTO>)o, getContext(), viewModel, container);
                                recyclerCursos.setAdapter(cursoAdapter);
                            }
                        }
                    }
                });
                mainActivity.checkAlumnoProfile();
            }else {
                Navigation.findNavController(container).navigate(R.id.action_homeFragment_to_profilesConfFragment);
            }
        }else {
            LiveData<Object> alumno = viewModel.getAlumno(viewModel.recuperarTokenSharedPreferences(getContext()));
            alumno.observe(getViewLifecycleOwner(), new Observer<Object>() {
                @Override
                public void onChanged(Object o) {
                    if(o instanceof AlumnoDTO) {
                        viewModel.guardarPerfilSeleccionadoSharedPreferences(getContext(), UserProfiles.STUDENT_PROFILE.name());
                        mainActivity.checkAlumnoProfile();
                        Navigation.findNavController(container).navigate(R.id.action_refresh_home_fragment);
                    }else {
                        LiveData<Object> tutor = viewModel.getTutor(viewModel.recuperarTokenSharedPreferences(getContext()));
                        tutor.observe(getViewLifecycleOwner(), new Observer<Object>() {
                            @Override
                            public void onChanged(Object o) {
                                if(o instanceof TutorDTO) {
                                    viewModel.guardarPerfilSeleccionadoSharedPreferences(getContext(), UserProfiles.TUTOR_PROFILE.name());
                                    isProgrammaticChange = true;
                                    mainActivity.checkTutorProfile();
                                    Navigation.findNavController(container).navigate(R.id.action_refresh_home_fragment);
                                }else {
                                    Navigation.findNavController(container).navigate(R.id.action_homeFragment_to_profilesConfFragment);
                                }
                            }
                        });
                    }

                }
            });
        }

        LiveData<Object> alumno = viewModel.getAlumno(viewModel.recuperarTokenSharedPreferences(getContext()));

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                // Lógica para manejar la selección del item en el NavigationView
                int itemId = item.getItemId();
                mainActivity.enableDrawer(false);
                mainActivity.enableDrawer(true);
                if(itemId == R.id.nav_home) {
                    Navigation.findNavController(container).navigate(R.id.action_refresh_home_fragment);
                }else if(itemId == R.id.nav_anouncements) {
                    Navigation.findNavController(container).navigate(R.id.action_homeFragment_to_anunciosFragment);
                }else if(itemId == R.id.nav_search_users) {
                    Navigation.findNavController(container).navigate(R.id.action_homeFragment_to_busquedaUsuariosFragment);
                }else if(itemId == R.id.nav_settings) {
                    Navigation.findNavController(container).navigate(R.id.action_homeFragment_to_configuracionFragment);
                }else if(itemId == R.id.nav_logout) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(getContext(), R.style.CustomAlertDialogStyle);
                    builder.setTitle("Cerrar Sesión");
                    builder.setMessage("¿Desea cerrar la sesión?");
                    builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            viewModel.limpiarTokenSharedPreferences(getContext());
                            viewModel.limpiarPerfilSeleccionadoSharedPreferences(getContext());
                            Navigation.findNavController(container).navigate(R.id.action_homeFragment_to_loginFragment);
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
                if(checkedId == R.id.switchAlumno && !isProgrammaticChange) {
                    LiveData<Object> alumno = viewModel.getAlumno(viewModel.recuperarTokenSharedPreferences(getContext()));
                    alumno.observe(getViewLifecycleOwner(), new Observer<Object>() {
                        @Override
                        public void onChanged(Object o) {
                            if(o instanceof AlumnoDTO) {
                                viewModel.guardarPerfilSeleccionadoSharedPreferences(getContext(), UserProfiles.STUDENT_PROFILE.name());
                                Navigation.findNavController(requireView()).navigate(R.id.action_refresh_home_fragment);
                            }else if(o instanceof RespuestasProfileConf) {
                                switchProfile.check(R.id.switchTutor);
                                Navigation.findNavController(requireView()).navigate(R.id.action_homeFragment_to_alumnoConfFragment);
                            }
                        }
                    });
                }else if(checkedId == R.id.switchTutor && !isProgrammaticChange) {
                    LiveData<Object> tutor = viewModel.getTutor(viewModel.recuperarTokenSharedPreferences(getContext()));
                    tutor.observe(getViewLifecycleOwner(), new Observer<Object>() {
                        @Override
                        public void onChanged(Object o) {
                            if(o instanceof TutorDTO) {
                                viewModel.guardarPerfilSeleccionadoSharedPreferences(getContext(), UserProfiles.TUTOR_PROFILE.name());
                                Navigation.findNavController(requireView()).navigate(R.id.action_refresh_home_fragment);
                            }else if(o instanceof RespuestasProfileConf) {
                                switchProfile.check(R.id.switchAlumno);
                                Navigation.findNavController(requireView()).navigate(R.id.action_homeFragment_to_tutorConfFragment);
                            }
                        }
                    });
                }
                isProgrammaticChange = false;
            }
        });

        btnExpand.setOnClickListener(btn -> {
            mainActivity.openDrawer();
        });

        return binding.getRoot();
    }
}