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
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import java.util.List;
import java.util.Objects;

import es.iespuertodelacruz.alejandrosamuel.studycircle.R;
import es.iespuertodelacruz.alejandrosamuel.studycircle.data.enums.EstadosAmistad;
import es.iespuertodelacruz.alejandrosamuel.studycircle.data.enums.RespuestasAmistad;
import es.iespuertodelacruz.alejandrosamuel.studycircle.data.rest.dto.AmistadDTO;
import es.iespuertodelacruz.alejandrosamuel.studycircle.data.rest.dto.UsuarioDTO;
import es.iespuertodelacruz.alejandrosamuel.studycircle.databinding.FragmentVisualizarPerfilBinding;
import es.iespuertodelacruz.alejandrosamuel.studycircle.viewmodel.MainActivityViewModel;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link VisualizarPerfilFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class VisualizarPerfilFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private FragmentVisualizarPerfilBinding binding;
    private MainActivityViewModel viewModel;
    private ImageView btnAtras;
    private ProgressBar progressBar;
    private TextView txtUsername;
    private TextView txtNombreCompleto;
    private TextView btnAmistad;
    private LinearLayout perfilAlumno;
    private LinearLayout perfilTutor;

    public VisualizarPerfilFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment VisualizarPerfilFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static VisualizarPerfilFragment newInstance(String param1, String param2) {
        VisualizarPerfilFragment fragment = new VisualizarPerfilFragment();
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
        // Inflate the layout for this fragment
        binding = FragmentVisualizarPerfilBinding.inflate(inflater, container, false);
        ((MainActivity) requireActivity()).enableDrawer(false);
        ((MainActivity) requireActivity()).setBottomNavVisibility(View.GONE);
        View view = binding.getRoot();
        btnAtras = binding.btnAtras;
        progressBar = binding.progressBar;
        progressBar.setVisibility(View.INVISIBLE);
        txtUsername = binding.txtUsername;
        txtNombreCompleto = binding.txtNombreCompleto;
        btnAmistad = binding.btnAmistad;
        perfilAlumno = binding.perfilAlumno;
        perfilTutor = binding.perfilTutor;
        UsuarioDTO selectedUsuarioDTO = viewModel.getSelectedUsuarioDTO();
        txtUsername.setText(selectedUsuarioDTO.getUsername());
        txtNombreCompleto.setText(selectedUsuarioDTO.getNombreCompleto());

        LiveData<Object> amistadesByUsuario = viewModel.findAmistadesByUsuario(viewModel.recuperarTokenSharedPreferences(getContext()));
        amistadesByUsuario.observe(getViewLifecycleOwner(), new Observer<Object>() {
            @Override
            public void onChanged(Object o) {
                if (o instanceof List) {
                    UsuarioDTO usuarioDTO = ((List<UsuarioDTO>) o).stream().filter(u -> u.getId().equals(selectedUsuarioDTO.getId())).findFirst().orElse(null);
                    if (Objects.nonNull(usuarioDTO)) {
                        LiveData<Object> amistad = viewModel.getAmistadConUsuario(usuarioDTO.getId(), viewModel.recuperarTokenSharedPreferences(getContext()));
                        amistad.observe(getViewLifecycleOwner(), new Observer<Object>() {
                            @Override
                            public void onChanged(Object o) {
                                if (o instanceof AmistadDTO) {
                                    if (((AmistadDTO) o).getEstado().equals(EstadosAmistad.FRIENDSHIP_ACCEPTED.name())) {
                                        btnAmistad.setBackgroundResource(R.drawable.ic_eliminar_amistad);
                                        btnAmistad.setOnClickListener(new View.OnClickListener() {
                                            @Override
                                            public void onClick(View view) {
                                                AlertDialog.Builder builder = new AlertDialog.Builder(getContext(), R.style.CustomAlertDialogStyle);
                                                builder.setTitle("Eliminar Amistad");
                                                builder.setMessage("¿Desea eliminar su amistad con este usuario?");
                                                builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                                                    @Override
                                                    public void onClick(DialogInterface dialog, int which) {
                                                        LiveData<Object> objectLiveData = viewModel.eliminarAmistad(selectedUsuarioDTO.getId(), viewModel.recuperarTokenSharedPreferences(getContext()));
                                                        objectLiveData.observe(getViewLifecycleOwner(), new Observer<Object>() {
                                                            @Override
                                                            public void onChanged(Object o) {
                                                                if (o instanceof RespuestasAmistad) {
                                                                    if (o.equals(RespuestasAmistad.REMOVED_FRIENDSHIP)) {
                                                                        AlertDialog.Builder builder = new AlertDialog.Builder(getContext(), R.style.CustomAlertDialogStyle);
                                                                        builder.setTitle("Amistad eliminada");
                                                                        builder.setMessage("Se ha eliminado la amistad correctamente");
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
                                                            }
                                                        });
                                                        Navigation.findNavController(container).navigate(R.id.action_refresh_visualizar_perfil_fragment);
                                                    }
                                                });
                                                builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                                                    @Override
                                                    public void onClick(DialogInterface dialog, int which) {
                                                    }
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
                                        });
                                    } else if (((AmistadDTO) o).getEstado().equals(EstadosAmistad.FRIENDSHIP_REQUESTED.name())) {
                                        if (((AmistadDTO) o).getUsuario1().getId().equals(viewModel.getUsuarioDTO().getId())) {
                                            btnAmistad.setBackgroundResource(R.drawable.ic_pending);
                                            AlertDialog.Builder builder = new AlertDialog.Builder(getContext(), R.style.CustomAlertDialogStyle);
                                            builder.setTitle("Amistad pendiente");
                                            builder.setMessage("El usuario aún no ha aceptado su amistad");
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
                                        } else {
                                            btnAmistad.setBackgroundResource(R.drawable.ic_accept);
                                            btnAmistad.setOnClickListener(new View.OnClickListener() {
                                                @Override
                                                public void onClick(View view) {
                                                    LiveData<Object> objectLiveData = viewModel.aceptarAmistad(((AmistadDTO) o).getUsuario1().getId(), viewModel.recuperarTokenSharedPreferences(getContext()));
                                                    objectLiveData.observe(getViewLifecycleOwner(), new Observer<Object>() {
                                                        @Override
                                                        public void onChanged(Object o) {
                                                            if (o instanceof AmistadDTO) {
                                                                Navigation.findNavController(container).navigate(R.id.action_refresh_visualizar_perfil_fragment);
                                                            }
                                                        }
                                                    });
                                                }
                                            });
                                        }
                                    }
                                }
                            }
                        });
                    } else {
                        btnAmistad.setBackgroundResource(R.drawable.ic_solicitar_amistad);
                        btnAmistad.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                AmistadDTO amistadDTO = new AmistadDTO();
                                amistadDTO.setUsuario1(viewModel.getUsuarioDTO());
                                amistadDTO.setUsuario2(selectedUsuarioDTO);
                                LiveData<Object> objectLiveData = viewModel.solicitarAmistad(amistadDTO, viewModel.recuperarTokenSharedPreferences(getContext()));
                                objectLiveData.observe(getViewLifecycleOwner(), new Observer<Object>() {
                                    @Override
                                    public void onChanged(Object o) {
                                        if (o instanceof AmistadDTO) {
                                            btnAmistad.setBackgroundResource(R.drawable.ic_pending);
                                            AlertDialog.Builder builder = new AlertDialog.Builder(getContext(), R.style.CustomAlertDialogStyle);
                                            builder.setTitle("Solicitud enviada");
                                            builder.setMessage("Se ha enviado la solicitud de amistad correctamente");
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
                                            btnAmistad.setOnClickListener(null);
                                        }
                                    }
                                });
                            }
                        });
                    }
                }
            }
        });
        TextView txtNumCursosAlumno = binding.perfilAlumno.findViewById(R.id.txtNumCursosAlumno);
        TextView txtNumActividadesPendientes = binding.perfilAlumno.findViewById(R.id.txtNumActividadesPendientes);
        txtNumCursosAlumno.setVisibility(View.VISIBLE);
        txtNumActividadesPendientes.setVisibility(View.VISIBLE);
        LiveData<Object> cursosAlumno = viewModel.findCantidadCursosAlumno(selectedUsuarioDTO.getId(), viewModel.recuperarTokenSharedPreferences(getContext()));
        cursosAlumno.observe(getViewLifecycleOwner(), new Observer<Object>() {
            @Override
            public void onChanged(Object o) {
                if(o instanceof Integer) {
                    String numCursos = getString(R.string._0_cursos, String.valueOf((Integer)o));
                    txtNumCursosAlumno.setText(numCursos);
                    LiveData<Object> numActividadesPendientes = viewModel.getNumActividadesPendientesAlumno(selectedUsuarioDTO.getId(), viewModel.recuperarTokenSharedPreferences(getContext()));
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
                    txtNumCursosAlumno.setVisibility(View.GONE);
                    txtNumActividadesPendientes.setVisibility(View.GONE);
                    TextView textView = new TextView(getContext());

                    textView.setText("Perfil de alumno no configurado");

                    float scaledSizeInPixels = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 5, getResources().getDisplayMetrics());
                    textView.setTextSize(scaledSizeInPixels);

                    textView.setTextColor(Color.GRAY);

                    textView.setGravity(Gravity.CENTER);

                    textView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));

                    LinearLayout layout = (LinearLayout) txtNumActividadesPendientes.getParent();
                    layout.addView(textView);
                }
            }
        });

        TextView txtNumCursosTutor = binding.perfilTutor.findViewById(R.id.txtNumCursosTutor);
        TextView txtNumAlumnos = binding.perfilTutor.findViewById(R.id.txtNumAlumnos);
        txtNumAlumnos.setVisibility(View.VISIBLE);
        txtNumCursosTutor.setVisibility(View.VISIBLE);
        LiveData<Object> cursosTutor = viewModel.findCantidadCursosTutor(selectedUsuarioDTO.getId(), viewModel.recuperarTokenSharedPreferences(getContext()));
        cursosTutor.observe(getViewLifecycleOwner(), new Observer<Object>() {
            @Override
            public void onChanged(Object o) {
                if(o instanceof Integer) {
                    String numCursos = getString(R.string._0_cursos, String.valueOf((Integer)o));
                    txtNumCursosTutor.setText(numCursos);
                    LiveData<Object> numAlumnosForTutor = viewModel.getNumAlumnosForTutor(selectedUsuarioDTO.getId(), viewModel.recuperarTokenSharedPreferences(getContext()));
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
                    txtNumCursosTutor.setVisibility(View.GONE);
                    TextView textView = new TextView(getContext());

                    textView.setText("Perfil de tutor no configurado");

                    float scaledSizeInPixels = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 5, getResources().getDisplayMetrics());
                    textView.setTextSize(scaledSizeInPixels);

                    textView.setTextColor(Color.GRAY);

                    textView.setGravity(Gravity.CENTER);

                    textView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));

                    LinearLayout layout = (LinearLayout) txtNumAlumnos.getParent();
                    layout.addView(textView);
                }
            }
        });

        btnAtras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(viewModel.isFromAnunciosToVisualizarPerfil()) {
                    viewModel.setFromAnunciosToVisualizarPerfil(false);
                    Navigation.findNavController(container).navigate(R.id.action_visualizarPerfilFragment_to_anunciosFragment);
                }else {
                    Navigation.findNavController(container).navigate(R.id.action_visualizarPerfilFragment_to_busquedaUsuariosFragment);
                }
            }
        });

        return view;
    }
}