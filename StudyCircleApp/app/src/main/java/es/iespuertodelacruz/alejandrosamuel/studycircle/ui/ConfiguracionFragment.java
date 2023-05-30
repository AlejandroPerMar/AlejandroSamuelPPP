package es.iespuertodelacruz.alejandrosamuel.studycircle.ui;

import android.graphics.Color;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import java.util.List;
import java.util.Objects;

import es.iespuertodelacruz.alejandrosamuel.studycircle.R;
import es.iespuertodelacruz.alejandrosamuel.studycircle.data.rest.dto.AlumnoDTO;
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
    private TextView txtUsername;
    private TextView txtNombreCompleto;
    private TextView txtEmail;
    private TextView btnFollowers;
    private LinearLayout perfilAlumno;
    private LinearLayout perfilTutor;


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
        perfilAlumno = binding.perfilAlumno;
        perfilTutor = binding.perfilTutor;

        LiveData<Object> usuario = viewModel.getUsuario(viewModel.recuperarTokenSharedPreferences(getContext()));

        usuario.observe(getViewLifecycleOwner(), new Observer<Object>() {
            @Override
            public void onChanged(Object o) {
                if(o instanceof UsuarioDTO) {
                    txtUsername.setText(((UsuarioDTO) o).getUsername());
                    txtEmail.setText(((UsuarioDTO) o).getEmail());
                    txtNombreCompleto.setText(((UsuarioDTO) o).getNombreCompleto());
                    LiveData<Object> amistadesByUsuario = viewModel.findAmistadesByUsuario(viewModel.recuperarTokenSharedPreferences(getContext()));

                    amistadesByUsuario.observe(getViewLifecycleOwner(), new Observer<Object>() {
                        @Override
                        public void onChanged(Object o) {
                            if(o instanceof List) {
                                int size = ((List<?>) o).size();
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
                                                String numCursos = getString(R.string._0_actividades_pendientes, String.valueOf((Integer) o));
                                                txtNumActividadesPendientes.setText(numCursos);
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

            }
        });

        btnAtras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(container).popBackStack();
            }
        });

        return view;
    }
}