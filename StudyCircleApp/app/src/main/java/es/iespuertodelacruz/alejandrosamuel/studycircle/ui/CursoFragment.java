package es.iespuertodelacruz.alejandrosamuel.studycircle.ui;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import org.threeten.bp.Instant;
import org.threeten.bp.LocalDate;
import org.threeten.bp.ZoneId;

import java.math.BigInteger;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import es.iespuertodelacruz.alejandrosamuel.studycircle.R;
import es.iespuertodelacruz.alejandrosamuel.studycircle.adapters.ActividadesAdapter;
import es.iespuertodelacruz.alejandrosamuel.studycircle.adapters.AlumnosAdapter;
import es.iespuertodelacruz.alejandrosamuel.studycircle.adapters.CustomArrayAdapter;
import es.iespuertodelacruz.alejandrosamuel.studycircle.data.enums.RespuestasCursos;
import es.iespuertodelacruz.alejandrosamuel.studycircle.data.enums.UserProfiles;
import es.iespuertodelacruz.alejandrosamuel.studycircle.data.rest.dto.ActividadDTO;
import es.iespuertodelacruz.alejandrosamuel.studycircle.data.rest.dto.AlumnoDTO;
import es.iespuertodelacruz.alejandrosamuel.studycircle.data.rest.dto.CursoDTO;
import es.iespuertodelacruz.alejandrosamuel.studycircle.data.rest.dto.MateriaDTO;
import es.iespuertodelacruz.alejandrosamuel.studycircle.data.rest.dto.MateriaTutorDTO;
import es.iespuertodelacruz.alejandrosamuel.studycircle.data.rest.dto.UsuarioDTO;
import es.iespuertodelacruz.alejandrosamuel.studycircle.databinding.FragmentCursoBinding;
import es.iespuertodelacruz.alejandrosamuel.studycircle.utils.ObjectsUtils;
import es.iespuertodelacruz.alejandrosamuel.studycircle.viewmodel.MainActivityViewModel;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CursoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CursoFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private FragmentCursoBinding binding;
    private MainActivityViewModel viewModel;
    private MainActivity mainActivity;
    private ProgressBar progressBar;
    private CursoDTO selectedCursoDTO;
    private TextView txtTutor2;
    private TextView txtMateria2;
    private LinearLayout lnrTutor;
    private EditText dtTituloCurso;
    private LinearLayout lnrAlumnos;
    private ImageView btnEditarTitulo;
    private RecyclerView recyclerAlumnos;
    private  RecyclerView recyclerActividades;
    private ImageView btnCrearActividad;
    private ImageView btnInvitarAlumno;
    private ImageView btnAtras;
    private UsuarioDTO usuarioDTOToInvitar;

    public CursoFragment() {
        // Required empty public constructor
    }


    private void closeKeyboard() {
        InputMethodManager manager = (InputMethodManager) requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        manager.hideSoftInputFromWindow(getView().getWindowToken(), 0);
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CursoFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CursoFragment newInstance(String param1, String param2) {
        CursoFragment fragment = new CursoFragment();
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
        binding = FragmentCursoBinding.inflate(inflater, container, false);
        mainActivity = (MainActivity) requireActivity();
        mainActivity.enableDrawer(false);
        progressBar = binding.progressBar;
        progressBar.setVisibility(View.INVISIBLE);
        txtMateria2 = binding.txtMateria2;
        lnrTutor = binding.lnrTutor;
        btnEditarTitulo = binding.btnEditarTitulo;
        lnrAlumnos = binding.lnrAlumnos;
        btnAtras = binding.btnAtras;
        dtTituloCurso = binding.dtTituloCurso;
        btnCrearActividad = binding.btnCrearActividad;
        btnInvitarAlumno = binding.btnInvitarAlumno;
        txtTutor2 = binding.txtTutor2;
        recyclerAlumnos = binding.recyclerAlumnos;
        recyclerActividades = binding.recyclerActividades;
        recyclerActividades.setLayoutManager(new LinearLayoutManager(getContext()));
        mainActivity.setBottomNavVisibility(View.GONE);
        selectedCursoDTO = viewModel.getSelectedCursoDTO();
        View view = binding.getRoot();
        String perfilSeleccionado = viewModel.recuperarPerfilSeleccionadoSharedPreferences(getContext());
        String nombreMateria = selectedCursoDTO.getMateriaTutor().getMateria().getNombre();
        if(perfilSeleccionado.equals(UserProfiles.STUDENT_PROFILE.name())) {
            recyclerAlumnos.setVisibility(View.GONE);
            btnCrearActividad.setVisibility(View.GONE);
            btnEditarTitulo.setVisibility(View.GONE);
            dtTituloCurso.setEnabled(false);
            btnEditarTitulo.setVisibility(View.GONE);
            lnrAlumnos.setVisibility(View.GONE);
            String nombreTutor = selectedCursoDTO.getMateriaTutor().getTutor().getUsuario().getUsername();
            txtTutor2.setText(nombreTutor);
            LiveData<Object> cursosAlumno = viewModel.findCursosAlumno(viewModel.recuperarTokenSharedPreferences(getContext()));
            cursosAlumno.observe(getViewLifecycleOwner(), new Observer<Object>() {
                @Override
                public void onChanged(Object o) {
                    if(o instanceof List) {
                        CursoDTO cursoDTO = ((List<CursoDTO>) o).stream().filter(c -> c.getId().equals(selectedCursoDTO.getId())).findFirst().orElse(null);
                        if(Objects.nonNull(cursoDTO)) {
                            viewModel.setSelectedCursoDTO(cursoDTO);
                            selectedCursoDTO = viewModel.getSelectedCursoDTO();
                        }
                    }
                    txtMateria2.setText(nombreMateria);
                    dtTituloCurso.setText(selectedCursoDTO.getTitulo());
                    dtTituloCurso.setBackground(null);
                    lnrTutor.setVisibility(View.VISIBLE);
                    List<ActividadDTO> actividades = viewModel.getSelectedCursoDTO().getActividades();
                    if(actividades.isEmpty()) {
                        recyclerActividades.setVisibility(View.GONE);
                        TextView textView = new TextView(getContext());
                        textView.setText("No hay actividades creadas");

                        float scaledSizeInPixels = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 5, getResources().getDisplayMetrics());
                        textView.setTextSize(scaledSizeInPixels);

                        textView.setTextColor(Color.GRAY);
                        textView.setGravity(Gravity.CENTER_HORIZONTAL);

                        LinearLayout.LayoutParams textViewParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                        textViewParams.weight = 1; // Establece el weight deseado

                        LinearLayout.LayoutParams containerParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                        containerParams.setMargins(90, 200, 90, 200); // Ajusta el valor del margen superior según sea necesario

                        LinearLayout c = new LinearLayout(getContext());
                        c.setLayoutParams(containerParams);
                        c.addView(textView, textViewParams);

                        LinearLayout layout = (LinearLayout) recyclerActividades.getParent();
                        layout.addView(c, layout.indexOfChild(recyclerActividades) + 1); // Índice 1 para insertar en la segunda posición
                    }else {
                        recyclerActividades.setVisibility(View.VISIBLE);
                        ActividadesAdapter adapter = new ActividadesAdapter(actividades, viewModel, container, getViewLifecycleOwner(), false);
                        recyclerActividades.setAdapter(adapter);
                    }
                }
            });

        } else if (perfilSeleccionado.equals(UserProfiles.TUTOR_PROFILE.name())) {
            lnrTutor.setVisibility(View.GONE);
            LiveData<Object> cursosTutor = viewModel.findCursosTutor(viewModel.recuperarTokenSharedPreferences(getContext()));
            cursosTutor.observe(getViewLifecycleOwner(), new Observer<Object>() {
                @Override
                public void onChanged(Object o) {
                    if(o instanceof List) {
                        CursoDTO cursoDTO = ((List<CursoDTO>) o).stream().filter(c -> c.getId().equals(selectedCursoDTO.getId())).findFirst().orElse(null);
                        if(Objects.nonNull(cursoDTO)) {
                            viewModel.setSelectedCursoDTO(cursoDTO);
                            selectedCursoDTO = viewModel.getSelectedCursoDTO();
                        }
                    }
                    txtMateria2.setText(nombreMateria);
                    dtTituloCurso.setText(viewModel.getSelectedCursoDTO().getTitulo());
                    btnEditarTitulo.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            if(!selectedCursoDTO.getTitulo().contentEquals(dtTituloCurso.getText())) {
                                LiveData<Object> objectLiveData = viewModel.changeTituloCurso(selectedCursoDTO.getId(), String.valueOf(dtTituloCurso.getText()), viewModel.recuperarTokenSharedPreferences(getContext()));
                                objectLiveData.observe(getViewLifecycleOwner(), new Observer<Object>() {
                                    @Override
                                    public void onChanged(Object o) {
                                        if(o instanceof CursoDTO) {
                                            Toast.makeText(getContext(), "Título actualizado correctamente", Toast.LENGTH_LONG).show();
                                            Navigation.findNavController(container).navigate(R.id.action_refresh_cursoFragment);
                                        }else {
                                            Toast.makeText(getContext(), "No se ha podido actualizar el nombre", Toast.LENGTH_LONG).show();
                                        }
                                    }
                                });
                            }else {
                                AlertDialog.Builder builder = new AlertDialog.Builder(getContext(), R.style.CustomAlertDialogStyle);
                                builder.setTitle("Cambiar título");
                                builder.setMessage("Debe introducir un nuevo título antes de intentar modificarlo");
                                builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                    }
                                });

                                AlertDialog dialog = builder.create();
                                dialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
                                dialog.getWindow().getDecorView().setPadding(50, 0, 50, 0);
                                dialog.show();
                                Button positiveButton = dialog.getButton(DialogInterface.BUTTON_POSITIVE);
                                positiveButton.setTextColor(ContextCompat.getColor(requireContext(), R.color.button_color_selector));
                            }
                        }
                    });
                    List<AlumnoDTO> alumnos = selectedCursoDTO.getAlumnos();
                    recyclerAlumnos.setLayoutManager(new LinearLayoutManager(getContext()));
                    if(alumnos.isEmpty()) {
                        recyclerAlumnos.setVisibility(View.GONE);
                        TextView textView = new TextView(getContext());
                        textView.setText("No hay alumnos suscritos");

                        float scaledSizeInPixels = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 5, getResources().getDisplayMetrics());
                        textView.setTextSize(scaledSizeInPixels);

                        textView.setTextColor(Color.GRAY);
                        textView.setGravity(Gravity.CENTER_HORIZONTAL);

                        LinearLayout.LayoutParams textViewParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 350);
                        textViewParams.weight = 1; // Establece el weight deseado

                        LinearLayout.LayoutParams containerParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 350);
                        containerParams.setMargins(90, 200, 90, 200); // Ajusta el valor del margen superior según sea necesario

                        LinearLayout c = new LinearLayout(getContext());
                        c.setLayoutParams(containerParams);
                        c.addView(textView, textViewParams);

                        LinearLayout layout = (LinearLayout) recyclerAlumnos.getParent();
                        layout.setOrientation(LinearLayout.VERTICAL);
                        layout.setGravity(Gravity.CENTER_VERTICAL);
                        layout.addView(c, 1); // Índice 1 para insertar en la segunda posición
                    }else {
                        recyclerAlumnos.setVisibility(View.VISIBLE);
                        AlumnosAdapter adapter = new AlumnosAdapter(alumnos, viewModel, container, getViewLifecycleOwner());
                        recyclerAlumnos.setAdapter(adapter);
                    }
                    List<ActividadDTO> actividades = viewModel.getSelectedCursoDTO().getActividades();
                    if(actividades.isEmpty()) {
                        recyclerActividades.setVisibility(View.GONE);
                        TextView textView = new TextView(getContext());
                        textView.setText("No hay actividades creadas");

                        float scaledSizeInPixels = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 5, getResources().getDisplayMetrics());
                        textView.setTextSize(scaledSizeInPixels);

                        textView.setTextColor(Color.GRAY);
                        textView.setGravity(Gravity.CENTER_HORIZONTAL);

                        LinearLayout.LayoutParams textViewParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                        textViewParams.weight = 1; // Establece el weight deseado

                        LinearLayout.LayoutParams containerParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                        containerParams.setMargins(90, 200, 90, 200); // Ajusta el valor del margen superior según sea necesario

                        LinearLayout c = new LinearLayout(getContext());
                        c.setLayoutParams(containerParams);
                        c.addView(textView, textViewParams);

                        LinearLayout layout = (LinearLayout) recyclerActividades.getParent();
                        layout.addView(c, layout.indexOfChild(recyclerActividades) + 1); // Índice 1 para insertar en la segunda posición
                    }else {
                        ActividadesAdapter adapter = new ActividadesAdapter(actividades, viewModel, container, getViewLifecycleOwner(), true);
                        recyclerActividades.setAdapter(adapter);
                    }
                }
            });

            btnInvitarAlumno.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(getContext(), R.style.CustomAlertDialogStyle);
                    LayoutInflater inflater = getLayoutInflater();
                    View viewE = inflater.inflate(R.layout.dialog_new_alumno, null);

                    builder.setView(viewE);

                    AutoCompleteTextView autoCompleteTextView = viewE.findViewById(R.id.autoCompleteTextView);
                    autoCompleteTextView.setThreshold(1);
                    LiveData<Object> usuariosAlumno = viewModel.getAmistadesAlumno(viewModel.recuperarTokenSharedPreferences(getContext()));
                    usuariosAlumno.observe(getViewLifecycleOwner(), new Observer<Object>() {
                        @Override
                        public void onChanged(Object o) {
                            if(o instanceof List) {
                                List<UsuarioDTO> usuarios = ((List<UsuarioDTO>) o);
                                List<String> nombresUsuarios = usuarios.stream().map(UsuarioDTO::getUsername).collect(Collectors.toList());
                                CustomArrayAdapter adapter = new CustomArrayAdapter(getContext(), android.R.layout.simple_dropdown_item_1line, nombresUsuarios);
                                autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                    @Override
                                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                        closeKeyboard();
                                        String usuarioSeleccionado = parent.getItemAtPosition(position).toString();
                                        UsuarioDTO usuarioDTO = usuarios.stream().filter(n -> n.getUsername().equals(usuarioSeleccionado)).findFirst().get();
                                        usuarioDTOToInvitar = usuarioDTO;
                                    }
                                });
                                autoCompleteTextView.setAdapter(adapter);
                            }
                        }
                    });
                    builder.setPositiveButton("Invitar", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int id) {
                            if(Objects.nonNull(usuarioDTOToInvitar)) {
                                LiveData<Object> objectLiveData = viewModel.invitarAlumnoToCurso(usuarioDTOToInvitar.getId(), selectedCursoDTO.getId(), viewModel.recuperarTokenSharedPreferences(getContext()));
                                objectLiveData.observe(getViewLifecycleOwner(), new Observer<Object>() {
                                    @Override
                                    public void onChanged(Object o) {
                                        if(o instanceof RespuestasCursos) {
                                            if(((RespuestasCursos) o).getName().equals(RespuestasCursos.STUDENT_INVITED.getName())) {
                                                Toast.makeText(getContext(), "Usuario invitado correctamente", Toast.LENGTH_LONG).show();
                                            }else if(((RespuestasCursos) o).getName().equals(RespuestasCursos.USER_ALREADY_IN_COURSE_OR_INVITED.getName())){
                                                Toast.makeText(getContext(), "Usuario ya se encuentra en el curso o ya ha sido invitado", Toast.LENGTH_LONG).show();
                                            }
                                        }
                                    }
                                });
                            }else {
                                dialog.cancel();
                                AlertDialog.Builder builder = new AlertDialog.Builder(getContext(), R.style.CustomAlertDialogStyle);
                                builder.setTitle("Invitar usuario");
                                builder.setMessage("Debe seleccionar una amistad a la que invitar");
                                builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                    }
                                });

                                AlertDialog dialog2 = builder.create();
                                dialog2.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
                                dialog2.getWindow().getDecorView().setPadding(50, 0, 50, 0);
                                dialog2.show();
                                Button positiveButton = dialog2.getButton(DialogInterface.BUTTON_POSITIVE);
                                positiveButton.setTextColor(ContextCompat.getColor(requireContext(), R.color.button_color_selector));
                            }
                        }
                    });

                    builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            usuarioDTOToInvitar = null;
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
        }

        btnAtras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(container).navigate(R.id.action_cursoFragment_to_homeFragment);
            }
        });

        btnCrearActividad.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext(), R.style.CustomAlertDialogStyle);
                LayoutInflater inflater = getLayoutInflater();
                View viewE = inflater.inflate(R.layout.dialog_new_actividad, null);

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
                            ActividadDTO actividadDTO = new ActividadDTO();
                            actividadDTO.setNombre(title);
                            actividadDTO.setDescripcion(description);
                            actividadDTO.setCurso(selectedCursoDTO);
                            ZoneId defaultZoneId = ZoneId.systemDefault();
                            Instant instant = date.atStartOfDay(defaultZoneId).toInstant();
                            long milliseconds = instant.toEpochMilli();
                            actividadDTO.setFechaActividad(new BigInteger(String.valueOf(milliseconds)));
                            LiveData<Object> eventoCalendario = viewModel.crearActividad(actividadDTO, viewModel.recuperarTokenSharedPreferences(getContext()));

                            eventoCalendario.observe(getViewLifecycleOwner(), new Observer<Object>() {
                                @Override
                                public void onChanged(Object o) {
                                    if(o instanceof ActividadDTO) {
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
                                        Navigation.findNavController(container).navigate(R.id.action_refresh_cursoFragment);
                                    }else {
                                        Toast.makeText(container.getContext(), "No se ha podido crear la actividad", Toast.LENGTH_LONG).show();
                                    }
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

        return view;
    }
}