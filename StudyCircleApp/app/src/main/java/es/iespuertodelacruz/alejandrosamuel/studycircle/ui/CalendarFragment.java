package es.iespuertodelacruz.alejandrosamuel.studycircle.ui;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

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

import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.navigation.NavigationView;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;

import org.threeten.bp.Instant;
import org.threeten.bp.LocalDate;
import org.threeten.bp.ZoneId;
import org.threeten.bp.ZonedDateTime;

import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

import es.iespuertodelacruz.alejandrosamuel.studycircle.R;
import es.iespuertodelacruz.alejandrosamuel.studycircle.adapters.EventosAdapter;
import es.iespuertodelacruz.alejandrosamuel.studycircle.data.enums.RespuestasProfileConf;
import es.iespuertodelacruz.alejandrosamuel.studycircle.data.enums.UserProfiles;
import es.iespuertodelacruz.alejandrosamuel.studycircle.data.rest.dto.AlumnoDTO;
import es.iespuertodelacruz.alejandrosamuel.studycircle.data.rest.dto.EventoCalendarioDTO;
import es.iespuertodelacruz.alejandrosamuel.studycircle.data.rest.dto.TutorDTO;
import es.iespuertodelacruz.alejandrosamuel.studycircle.databinding.FragmentCalendarBinding;
import es.iespuertodelacruz.alejandrosamuel.studycircle.decorators.EventDecorator;
import es.iespuertodelacruz.alejandrosamuel.studycircle.utils.ObjectsUtils;
import es.iespuertodelacruz.alejandrosamuel.studycircle.viewmodel.MainActivityViewModel;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CalendarFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CalendarFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private MainActivityViewModel viewModel;
    private FragmentCalendarBinding binding;
    private MainActivity mainActivity;
    private RadioGroup switchProfile;
    private NavigationView navigationView;
    private BottomNavigationView bottomNavigationView;
    NavController navController;
    private ProgressBar progressBar;
    private ImageView btnExpand;
    private MaterialCalendarView calendarView;
    private RecyclerView eventsRecyclerView;
    private ImageView btnCrearEvento;
    private EventosAdapter eventosAdapter;

    List<EventoCalendarioDTO> eventos;

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

    public CalendarFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CalendarFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CalendarFragment newInstance(String param1, String param2) {
        CalendarFragment fragment = new CalendarFragment();
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

    private List<EventoCalendarioDTO> filtrarEventosPorFecha(List<EventoCalendarioDTO> eventos, CalendarDay fecha) {
        List<EventoCalendarioDTO> eventosDelDia = new ArrayList<>();
        LocalDate fechaSeleccionada = fecha.getDate();

        for (EventoCalendarioDTO evento : eventos) {
            System.out.println(evento.getFechaEvento());
            Instant instant = Instant.ofEpochMilli(evento.getFechaEvento().getTime());
            ZonedDateTime zdt = ZonedDateTime.ofInstant(instant, ZoneId.systemDefault());
            LocalDate localDate = zdt.toLocalDate();
            if (localDate.isEqual(fechaSeleccionada)) {
                eventosDelDia.add(evento);
            }
        }

        return eventosDelDia;
    }

    private void actualizarRecyclerView(List<EventoCalendarioDTO> eventos) {
        EventosAdapter adapter = new EventosAdapter(eventos);
        eventosAdapter = adapter;
        eventsRecyclerView.setAdapter(adapter);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentCalendarBinding.inflate(inflater, container, false);
        mainActivity = (MainActivity) requireActivity();
        progressBar = binding.progressBar;
        progressBar.setVisibility(View.INVISIBLE);
        btnExpand = binding.btnExpand;
        btnCrearEvento = binding.btnCrearEvento;
        navigationView  = mainActivity.getNavigationView();
        bottomNavigationView = mainActivity.getBottomNav();
        switchProfile = mainActivity.getSwitchProfile();
        mainActivity.enableDrawer(true);
        mainActivity.setBottomNavVisibility(View.VISIBLE);
        calendarView = binding.calendarView;
        eventosAdapter = new EventosAdapter(new ArrayList<>());
        eventsRecyclerView = binding.eventsRecyclerView;
        eventsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        String perfilSeleccionado = viewModel.recuperarPerfilSeleccionadoSharedPreferences(getContext());
        if(UserProfiles.TUTOR_PROFILE.name().equals(perfilSeleccionado)) {
            LiveData<Object> eventosByPerfilUsuarioTutor = viewModel.findEventosByPerfilUsuarioTutor(viewModel.recuperarTokenSharedPreferences(getContext()));
            eventosByPerfilUsuarioTutor.observe(getViewLifecycleOwner(), new Observer<Object>() {
                @Override
                public void onChanged(Object o) {
                    if(o instanceof List) {
                        setEventos((List<EventoCalendarioDTO>) o);
                        getEventos().forEach(ev -> {
                            Instant instant = Instant.ofEpochMilli(ev.getFechaEvento().getTime());
                            ZonedDateTime zdt = ZonedDateTime.ofInstant(instant, ZoneId.systemDefault());
                            LocalDate localDate = zdt.toLocalDate();
                            calendarView.addDecorator(new EventDecorator(localDate));
                        });

                        // Configura un listener para cuando se seleccione una fecha
                        calendarView.setOnDateChangedListener((widget, date, selected) -> {
                            // Filtra los eventos para solo mostrar los del día seleccionado
                            List<EventoCalendarioDTO> eventosDelDia = filtrarEventosPorFecha(eventos, date);

                            // Actualiza tu RecyclerView con los eventos del día
                            actualizarRecyclerView(eventosDelDia);
                        });
                    }
                }
            });
        }else if(UserProfiles.STUDENT_PROFILE.name().equals(perfilSeleccionado)) {
            LiveData<Object> eventosByPerfilUsuarioAlumno = viewModel.findEventosByPerfilUsuarioAlumno(viewModel.recuperarTokenSharedPreferences(getContext()));
            eventosByPerfilUsuarioAlumno.observe(getViewLifecycleOwner(), new Observer<Object>() {
                @Override
                public void onChanged(Object o) {
                    if(o instanceof List) {
                        setEventos((List<EventoCalendarioDTO>) o);
                        getEventos().forEach(ev -> {
                            Instant instant = Instant.ofEpochMilli(ev.getFechaEvento().getTime());
                            ZonedDateTime zdt = ZonedDateTime.ofInstant(instant, ZoneId.systemDefault());
                            LocalDate localDate = zdt.toLocalDate();
                            calendarView.addDecorator(new EventDecorator(localDate));
                        });

                        // Configura un listener para cuando se seleccione una fecha
                        calendarView.setOnDateChangedListener((widget, date, selected) -> {
                            // Filtra los eventos para solo mostrar los del día seleccionado
                            List<EventoCalendarioDTO> eventosDelDia = filtrarEventosPorFecha(eventos, date);

                            // Actualiza tu RecyclerView con los eventos del día
                            actualizarRecyclerView(eventosDelDia);
                        });
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
                    Navigation.findNavController(container).navigate(R.id.action_calendarFragment_to_anunciosFragment);
                }else if(itemId == R.id.nav_search_users) {
                    Navigation.findNavController(container).navigate(R.id.action_calendarFragment_to_busquedaUsuariosFragment);
                }else if(itemId == R.id.nav_settings) {
                    Navigation.findNavController(container).navigate(R.id.action_calendarFragment_to_configuracionFragment);
                }else if(itemId == R.id.nav_logout) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(getContext(), R.style.CustomAlertDialogStyle);
                    builder.setTitle("Cerrar Sesión");
                    builder.setMessage("¿Desea cerrar la sesión?");
                    builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            viewModel.limpiarTokenSharedPreferences(getContext());
                            viewModel.limpiarPerfilSeleccionadoSharedPreferences(getContext());
                            Navigation.findNavController(container).navigate(R.id.action_calendarFragment_to_loginFragment);
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
                                Navigation.findNavController(requireView()).navigate(R.id.action_refresh_calendar_fragment);
                            }else if(o instanceof RespuestasProfileConf) {
                                switchProfile.check(R.id.switchTutor);
                                Navigation.findNavController(requireView()).navigate(R.id.action_calendarFragment_to_alumnoConfFragment);
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
                                Navigation.findNavController(requireView()).navigate(R.id.action_refresh_calendar_fragment);
                            }else if(o instanceof RespuestasProfileConf) {
                                switchProfile.check(R.id.switchAlumno);
                                Navigation.findNavController(requireView()).navigate(R.id.action_calendarFragment_to_tutorConfFragment);
                            }
                        }
                    });
                }
            }
        });

        btnCrearEvento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext(), R.style.CustomAlertDialogStyle);
                LayoutInflater inflater = getLayoutInflater();
                View viewE = inflater.inflate(R.layout.dialog_new_event, null);

                builder.setView(viewE);

                EditText titleEditText = viewE.findViewById(R.id.titleEditText);
                EditText descriptionEditText = viewE.findViewById(R.id.descriptionEditText);
                DatePicker datePicker = viewE.findViewById(R.id.datePicker);

                builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        String title = titleEditText.getText().toString();
                        String description = descriptionEditText.getText().toString();
                        LocalDate date = LocalDate.of(datePicker.getYear(), datePicker.getMonth() + 1, datePicker.getDayOfMonth());
                        if((date.isEqual(LocalDate.now()) || date.isAfter(LocalDate.now())) && ObjectsUtils.notNullNorEmpty(title, description)) {
                            EventoCalendarioDTO eventoCalendarioDTO = new EventoCalendarioDTO();
                            eventoCalendarioDTO.setNombre(title);
                            eventoCalendarioDTO.setDescripcion(description);

                            ZoneId defaultZoneId = ZoneId.systemDefault();
                            Instant instant = date.atStartOfDay(defaultZoneId).toInstant();
                            long milliseconds = instant.toEpochMilli();
                            eventoCalendarioDTO.setFechaEvento(new BigInteger(String.valueOf(milliseconds)));
                            eventoCalendarioDTO.setPerfilUsuario(viewModel.recuperarPerfilSeleccionadoSharedPreferences(getContext()));
                            LiveData<Object> eventoCalendario = viewModel.createEventoCalendario(eventoCalendarioDTO, viewModel.recuperarTokenSharedPreferences(getContext()));

                            eventoCalendario.observe(getViewLifecycleOwner(), new Observer<Object>() {
                                @Override
                                public void onChanged(Object o) {
                                    Navigation.findNavController(container).navigate(R.id.action_refresh_calendar_fragment);
                                    AlertDialog.Builder builder = new AlertDialog.Builder(getContext(), R.style.CustomAlertDialogStyle);
                                    builder.setTitle("Evento creado");
                                    builder.setMessage("Se ha creado el evento correctamente");
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
                            });
                        }else {
                            AlertDialog.Builder builder = new AlertDialog.Builder(getContext(), R.style.CustomAlertDialogStyle);
                            builder.setTitle("Datos incorrectos");
                            builder.setMessage("La fecha no puede ser anterior a la actual y los campos no pueden estar vacíos");
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

                dialog.setOnShowListener(new DialogInterface.OnShowListener() {
                    @Override
                    public void onShow(DialogInterface dialogInterface) {
                        Button positiveButton = dialog.getButton(DialogInterface.BUTTON_POSITIVE);
                        Button negativeButton = dialog.getButton(DialogInterface.BUTTON_NEGATIVE);
                        positiveButton.setTextColor(ContextCompat.getColor(requireContext(), R.color.button_color_selector));
                        negativeButton.setTextColor(ContextCompat.getColor(requireContext(), R.color.button_color_selector));
                    }
                });

                dialog.show();
            }
        });

        btnExpand.setOnClickListener(btn -> {
            mainActivity.openDrawer();
        });

        return binding.getRoot();
    }

    public List<EventoCalendarioDTO> getEventos() {
        return eventos;
    }

    public void setEventos(List<EventoCalendarioDTO> eventos) {
        this.eventos = eventos;
    }
}