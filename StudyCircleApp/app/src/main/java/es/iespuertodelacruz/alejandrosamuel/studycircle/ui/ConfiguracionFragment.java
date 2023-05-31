package es.iespuertodelacruz.alejandrosamuel.studycircle.ui;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import es.iespuertodelacruz.alejandrosamuel.studycircle.R;
import es.iespuertodelacruz.alejandrosamuel.studycircle.data.enums.EstadosAmistad;
import es.iespuertodelacruz.alejandrosamuel.studycircle.data.rest.dto.AlumnoDTO;
import es.iespuertodelacruz.alejandrosamuel.studycircle.data.rest.dto.AmistadDTO;
import es.iespuertodelacruz.alejandrosamuel.studycircle.data.rest.dto.CursoDTO;
import es.iespuertodelacruz.alejandrosamuel.studycircle.data.rest.dto.TutorDTO;
import es.iespuertodelacruz.alejandrosamuel.studycircle.data.rest.dto.UsuarioDTO;
import es.iespuertodelacruz.alejandrosamuel.studycircle.databinding.FragmentConfiguracionBinding;
import es.iespuertodelacruz.alejandrosamuel.studycircle.viewmodel.MainActivityViewModel;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ConfiguracionFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ConfiguracionFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private FragmentConfiguracionBinding binding;
    private MainActivityViewModel viewModel;
    private ImageView btnAtras;
    private ProgressBar progressBar;
    private EditText txtUsername;
    private EditText txtNombreCompleto;
    private TextView txtEmail;
    private TextView btnFollowers;
    private LinearLayout perfilAlumno;
    private LinearLayout perfilTutor;
    private ImageView btnCambiarUsername;
    private ImageView btnCambiarNombreCompleto;


    public ConfiguracionFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ConfiguracionFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ConfiguracionFragment newInstance(String param1, String param2) {
        ConfiguracionFragment fragment = new ConfiguracionFragment();
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
        binding = FragmentConfiguracionBinding.inflate(inflater, container, false);
        ((MainActivity) requireActivity()).enableDrawer(false);
        ((MainActivity) requireActivity()).setBottomNavVisibility(View.GONE);
        View view = binding.getRoot();
        btnAtras = binding.btnAtras;
        progressBar = binding.progressBar;
        progressBar.setVisibility(View.INVISIBLE);
        txtUsername = binding.txtUsername;
        txtNombreCompleto = binding.txtNombreCompleto;
        txtEmail = binding.txtEmail;
        btnFollowers = binding.btnFollowers;
        btnCambiarUsername = binding.btnCambiarUsername;
        btnCambiarUsername.setVisibility(View.INVISIBLE);
        btnCambiarNombreCompleto = binding.btnCambiarNombreCompleto;
        btnCambiarNombreCompleto.setVisibility(View.INVISIBLE);
        perfilAlumno = binding.perfilAlumno;
        perfilTutor = binding.perfilTutor;

        LiveData<Object> usuario = viewModel.getUsuario(viewModel.recuperarTokenSharedPreferences(getContext()));

        usuario.observe(getViewLifecycleOwner(), new Observer<Object>() {
            @Override
            public void onChanged(Object o) {
                if(o instanceof UsuarioDTO) {
                    txtUsername.setText(((UsuarioDTO) o).getUsername());
                    btnCambiarUsername.setVisibility(View.VISIBLE);
                    btnCambiarUsername.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            if(!((UsuarioDTO) o).getUsername().contentEquals(txtUsername.getText())) {
                                LiveData<Object> objectLiveData = viewModel.changeUsername(String.valueOf(txtUsername.getText()), viewModel.recuperarTokenSharedPreferences(getContext()));
                                objectLiveData.observe(getViewLifecycleOwner(), new Observer<Object>() {
                                    @Override
                                    public void onChanged(Object o) {
                                        if(o instanceof String) {
                                            Toast.makeText(getContext(), "Username actualizado correctamente", Toast.LENGTH_LONG).show();
                                            viewModel.guardarTokenSharedPreferences(getContext(), (String) o);
                                            Navigation.findNavController(container).navigate(R.id.action_refresh_configuracion_fragment);
                                        }else {
                                            Toast.makeText(getContext(), "No se ha podido actualizar el username", Toast.LENGTH_LONG).show();
                                        }
                                    }
                                });
                            }else {
                                AlertDialog.Builder builder = new AlertDialog.Builder(getContext(), R.style.CustomAlertDialogStyle);
                                builder.setTitle("Cambiar username");
                                builder.setMessage("Debe introducir un nuevo username antes de intentar modificarlo");
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
                    txtEmail.setText(((UsuarioDTO) o).getEmail());
                    txtNombreCompleto.setText(((UsuarioDTO) o).getNombreCompleto());
                    btnCambiarNombreCompleto.setVisibility(View.VISIBLE);
                    btnCambiarNombreCompleto.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            if(!((UsuarioDTO) o).getNombreCompleto().contentEquals(txtNombreCompleto.getText())) {
                                LiveData<Object> objectLiveData = viewModel.changeNombreCompleto(String.valueOf(txtNombreCompleto.getText()), viewModel.recuperarTokenSharedPreferences(getContext()));
                                objectLiveData.observe(getViewLifecycleOwner(), new Observer<Object>() {
                                    @Override
                                    public void onChanged(Object o) {
                                        if(o instanceof UsuarioDTO) {
                                            Toast.makeText(getContext(), "Nombre actualizado correctamente", Toast.LENGTH_LONG).show();
                                            Navigation.findNavController(container).navigate(R.id.action_refresh_configuracion_fragment);
                                        }else {
                                            Toast.makeText(getContext(), "No se ha podido actualizar el nombre", Toast.LENGTH_LONG).show();
                                        }
                                    }
                                });
                            }else {
                                AlertDialog.Builder builder = new AlertDialog.Builder(getContext(), R.style.CustomAlertDialogStyle);
                                builder.setTitle("Cambiar nombre");
                                builder.setMessage("Debe introducir un nuevo nombre antes de intentar modificarlo");
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
                    LiveData<Object> amistadesByUsuario = viewModel.getAmistades(viewModel.recuperarTokenSharedPreferences(getContext()));

                    amistadesByUsuario.observe(getViewLifecycleOwner(), new Observer<Object>() {
                        @Override
                        public void onChanged(Object o) {
                            if(o instanceof List) {
                                int size = (int) ((List<AmistadDTO>) o).stream().filter(a -> a.getEstado().equals(EstadosAmistad.FRIENDSHIP_ACCEPTED.name())).count();
                                String countAmistades = getString(R.string.friends_count, String.valueOf(size));
                                btnFollowers.setText(countAmistades);
                            }
                        }
                    });

                    LiveData<Object> alumno = viewModel.getAlumno(viewModel.recuperarTokenSharedPreferences(getContext()));
                    alumno.observe(getViewLifecycleOwner(), new Observer<Object>() {
                        @Override
                        public void onChanged(Object o) {
                            TextView txtNumCursos = binding.perfilAlumno.findViewById(R.id.txtNumCursosAlumno);
                            TextView txtNumActividadesPendientes = binding.perfilAlumno.findViewById(R.id.txtNumActividadesPendientes);
                            txtNumCursos.setVisibility(View.VISIBLE);
                            txtNumActividadesPendientes.setVisibility(View.VISIBLE);
                            if(o instanceof AlumnoDTO) {
                                LiveData<Object> cursosAlumno = viewModel.findCursosAlumno(viewModel.recuperarTokenSharedPreferences(getContext()));
                                cursosAlumno.observe(getViewLifecycleOwner(), new Observer<Object>() {
                                    @Override
                                    public void onChanged(Object o) {
                                        if(o instanceof List) {
                                            int size = ((List<CursoDTO>) o).size();
                                            String numCursos = getString(R.string._0_cursos, String.valueOf(size));
                                            txtNumCursos.setText(numCursos);
                                        }
                                    }
                                });

                                LiveData<Object> numActividadesPendientes = viewModel.getNumActividadesPendientesAlumno(viewModel.getUsuarioDTO().getId(), viewModel.recuperarTokenSharedPreferences(getContext()));
                                numActividadesPendientes.observe(getViewLifecycleOwner(), new Observer<Object>() {
                                    @Override
                                    public void onChanged(Object o) {
                                        if(o instanceof Integer) {
                                            if(Objects.nonNull(o)) {
                                                String numActividades = getString(R.string._0_actividades_pendientes, String.valueOf((Integer) o));
                                                txtNumActividadesPendientes.setText(numActividades);
                                            }else {
                                                txtNumActividadesPendientes.setText(getString(R.string._0_actividades_pendientes, String.valueOf(0)));
                                            }
                                        }
                                    }
                                });
                            }else {
                                txtNumCursos.setVisibility(View.GONE);
                                txtNumActividadesPendientes.setVisibility(View.GONE);
                                TextView textView = new TextView(getContext());

                                textView.setText("Configure su perfil de alumno");

                                float scaledSizeInPixels = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 5, getResources().getDisplayMetrics());
                                textView.setTextSize(scaledSizeInPixels);

                                textView.setTextColor(Color.GRAY);

                                textView.setGravity(Gravity.CENTER);

                                textView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));

                                LinearLayout layout = (LinearLayout) txtNumActividadesPendientes.getParent();
                                layout.addView(textView);

                                textView.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        Navigation.findNavController(container).navigate(R.id.action_configuracionFragment_to_alumnoConfFragment);
                                    }
                                });
                            }
                        }
                    });

                    LiveData<Object> tutor = viewModel.getTutor(viewModel.recuperarTokenSharedPreferences(getContext()));
                    tutor.observe(getViewLifecycleOwner(), new Observer<Object>() {
                        @Override
                        public void onChanged(Object o) {
                            TextView txtNumCursos = binding.perfilTutor.findViewById(R.id.txtNumCursosTutor);
                            TextView txtNumAlumnos = binding.perfilTutor.findViewById(R.id.txtNumAlumnos);
                            txtNumAlumnos.setVisibility(View.VISIBLE);
                            txtNumCursos.setVisibility(View.VISIBLE);
                            if(o instanceof TutorDTO) {
                                LiveData<Object> cursosTutor = viewModel.findCursosTutor(viewModel.recuperarTokenSharedPreferences(getContext()));
                                cursosTutor.observe(getViewLifecycleOwner(), new Observer<Object>() {
                                    @Override
                                    public void onChanged(Object o) {
                                        if(o instanceof List) {
                                            int size = ((List<CursoDTO>) o).size();
                                            String numCursos = getString(R.string._0_cursos, String.valueOf(size));
                                            txtNumCursos.setText(numCursos);
                                        }
                                    }
                                });

                                LiveData<Object> numAlumnosForTutor = viewModel.getNumAlumnosForTutor(viewModel.getUsuarioDTO().getId(), viewModel.recuperarTokenSharedPreferences(getContext()));
                                numAlumnosForTutor.observe(getViewLifecycleOwner(), new Observer<Object>() {
                                    @Override
                                    public void onChanged(Object o) {
                                        if(o instanceof Integer) {
                                            if(Objects.nonNull(o)) {
                                                String numCursos = getString(R.string._0_cursos, String.valueOf((Integer) o));
                                                txtNumAlumnos.setText(numCursos);
                                            }else {
                                                txtNumAlumnos.setText(getString(R.string._0_cursos, String.valueOf(0)));
                                            }
                                        }
                                    }
                                });
                            }else {
                                txtNumAlumnos.setVisibility(View.GONE);
                                txtNumCursos.setVisibility(View.GONE);
                                TextView textView = new TextView(getContext());

                                textView.setText("Configure su perfil de tutor");

                                float scaledSizeInPixels = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 5, getResources().getDisplayMetrics());
                                textView.setTextSize(scaledSizeInPixels);

                                textView.setTextColor(Color.GRAY);

                                textView.setGravity(Gravity.CENTER);

                                textView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));

                                LinearLayout layout = (LinearLayout) txtNumAlumnos.getParent();
                                layout.addView(textView);
                                textView.setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        Navigation.findNavController(container).navigate(R.id.action_configuracionFragment_to_tutorConfFragment);
                                    }
                                });
                            }
                        }
                    });
                }
            }
        });



        btnFollowers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewModel.setVisualizarAmistades(true);
                Navigation.findNavController(container).navigate(R.id.action_configuracionFragment_to_busquedaUsuariosFragment);
            }
        });

        btnAtras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(container).navigate(R.id.action_configuracionFragment_to_homeFragment);
            }
        });

        return view;
    }
}