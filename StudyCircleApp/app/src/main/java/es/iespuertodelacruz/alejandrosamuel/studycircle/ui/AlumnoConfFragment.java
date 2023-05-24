package es.iespuertodelacruz.alejandrosamuel.studycircle.ui;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import es.iespuertodelacruz.alejandrosamuel.studycircle.R;
import es.iespuertodelacruz.alejandrosamuel.studycircle.adapters.CustomArrayAdapter;
import es.iespuertodelacruz.alejandrosamuel.studycircle.adapters.MateriaAlumnoAdapter;
import es.iespuertodelacruz.alejandrosamuel.studycircle.data.enums.RespuestasProfileConf;
import es.iespuertodelacruz.alejandrosamuel.studycircle.data.enums.RespuestasRegister;
import es.iespuertodelacruz.alejandrosamuel.studycircle.data.enums.UserProfiles;
import es.iespuertodelacruz.alejandrosamuel.studycircle.data.rest.dto.AlumnoDTO;
import es.iespuertodelacruz.alejandrosamuel.studycircle.data.rest.dto.MateriaDTO;
import es.iespuertodelacruz.alejandrosamuel.studycircle.data.rest.dto.NivelEstudiosDTO;
import es.iespuertodelacruz.alejandrosamuel.studycircle.databinding.FragmentAlumnoConfBinding;
import es.iespuertodelacruz.alejandrosamuel.studycircle.utils.ObjectsUtils;
import es.iespuertodelacruz.alejandrosamuel.studycircle.utils.TextViewUtils;
import es.iespuertodelacruz.alejandrosamuel.studycircle.viewmodel.MainActivityViewModel;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AlumnoConfFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AlumnoConfFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private MainActivityViewModel viewModel;
    private FragmentAlumnoConfBinding binding;

    private List<NivelEstudiosDTO> nivelesEstudio;
    private AutoCompleteTextView autoCompleteTextView;
    private ImageView btnAtras;
    private RecyclerView recyclerView;
    private TextView btnAceptar;
    private AlumnoDTO alumnoDTO;
    private LinearLayout alumnoConfFragment;
    private ProgressBar progressBar;


    public AlumnoConfFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AlumnoConfFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AlumnoConfFragment newInstance(String param1, String param2) {
        AlumnoConfFragment fragment = new AlumnoConfFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    private void closeKeyboard() {
        InputMethodManager manager = (InputMethodManager) requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        manager.hideSoftInputFromWindow(getView().getWindowToken(), 0);
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
        binding = FragmentAlumnoConfBinding.inflate(inflater, container, false);
        alumnoDTO = new AlumnoDTO();
        alumnoConfFragment = binding.alumnoConfFragment;
        progressBar = binding.progressBar;
        progressBar.setVisibility(View.INVISIBLE);
        btnAtras = binding.btnAtras;
        btnAceptar = binding.btnAceptar;
        recyclerView = binding.recyclerView;
        autoCompleteTextView = binding.autoCompleteTextView;
        autoCompleteTextView.setThreshold(1);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 3));
        recyclerView.setAdapter(new MateriaAlumnoAdapter(new ArrayList<>(), getContext(), alumnoDTO));
        LiveData<Object> allNivelesEstudios = viewModel.findAllNivelesEstudios(viewModel.recuperarTokenSharedPreferences(getContext()));
        allNivelesEstudios.observe(getViewLifecycleOwner(), new Observer<Object>() {
            @Override
            public void onChanged(Object o) {
                if(o instanceof List) {
                    setNivelesEstudio((List<NivelEstudiosDTO>) o);
                    List<String> nombresNE = getNivelesEstudio().stream().map(NivelEstudiosDTO::getNombre).collect(Collectors.toList());
                    CustomArrayAdapter adapter = new CustomArrayAdapter(getContext(), android.R.layout.simple_dropdown_item_1line, nombresNE);

                    autoCompleteTextView.setAdapter(adapter);
                }
            }
        });
        btnAtras.setOnClickListener(btn -> {
            Navigation.findNavController(container).navigate(R.id.action_alumnoConfFragment_to_profilesConfFragment);
        });

        autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                closeKeyboard();
                String nivelEstudioSeleccionado = parent.getItemAtPosition(position).toString();
                NivelEstudiosDTO nivelEstudiosDTO = getNivelesEstudio().stream().filter(n -> n.getNombre().equals(nivelEstudioSeleccionado)).findFirst().get();
                alumnoDTO.setNivelEstudios(nivelEstudiosDTO);
                List<MateriaDTO> materiasFiltradas = nivelEstudiosDTO.getMaterias();
                MateriaAlumnoAdapter materiaAlumnoAdapter = (MateriaAlumnoAdapter) recyclerView.getAdapter();
                if(Objects.nonNull(materiaAlumnoAdapter)){
                    alumnoDTO.setMaterias(new ArrayList<>());
                    materiaAlumnoAdapter.resetSelection(); // Restablecer selección
                    materiaAlumnoAdapter.actualizarMaterias(materiasFiltradas);
                }
            }
        });

        btnAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alumnoConfFragment.setVisibility(View.INVISIBLE);
                progressBar.setVisibility(View.VISIBLE);
                if(ObjectsUtils.notNullNorEmpty(alumnoDTO, alumnoDTO.getMaterias(), alumnoDTO.getNivelEstudios())) {
                    LiveData<Object> studentProfile = viewModel.createStudentProfile(alumnoDTO, viewModel.recuperarTokenSharedPreferences(getContext()));
                    studentProfile.observe(getViewLifecycleOwner(), new Observer<Object>() {
                        @Override
                        public void onChanged(Object o) {
                            if(o instanceof AlumnoDTO) {
                                viewModel.guardarPerfilSeleccionadoSharedPreferences(getContext(), UserProfiles.STUDENT_PROFILE.name());
                                Toast.makeText(getContext(), "Perfil de alumno creado con éxito", Toast.LENGTH_LONG).show();
                                Navigation.findNavController(container).navigate(R.id.action_alumnoConfFragment_to_homeFragment);
                            }else if(o instanceof RespuestasProfileConf){
                                TextView txtErrorMessage = TextViewUtils.getTextViewLinearLayoutErrorMessage(requireContext(), RespuestasProfileConf.STUDENT_PROFILE_NOT_CREATED.getDescripcion());
                                LinearLayout linearLayout = binding.btnAceptarLayout;
                                int index = linearLayout.indexOfChild(btnAceptar);  // Obtener el índice del LinearLayout en el ViewGroup padre

                                linearLayout.addView(txtErrorMessage, index);
                            }else {
                                viewModel.limpiarTokenSharedPreferences(getContext());
                                Navigation.findNavController(container).navigate(R.id.action_alumnoConfFragment_to_loginFragment);
                            }
                        }
                    });
                }else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(getContext(), R.style.CustomAlertDialogStyle);
                    builder.setTitle("Nivel de Estudios no cofigurado");
                    builder.setMessage("Configure un Nivel de Estudios para agregar");
                    builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {}
                    });

                    AlertDialog dialog = builder.create();
                    dialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
                    dialog.getWindow().getDecorView().setPadding(50, 0, 50, 0);
                    dialog.show();
                    Button positiveButton = dialog.getButton(DialogInterface.BUTTON_POSITIVE);
                    positiveButton.setTextColor(ContextCompat.getColor(requireContext(), R.color.button_color_selector));
                    progressBar.setVisibility(View.INVISIBLE);
                    alumnoConfFragment.setVisibility(View.VISIBLE);
                }

            }
        });


        return binding.getRoot();
    }

    public void setNivelesEstudio(List<NivelEstudiosDTO> nivelesEstudio) {
        this.nivelesEstudio = nivelesEstudio;
    }

    public List<NivelEstudiosDTO> getNivelesEstudio() {
        return nivelesEstudio;
    }
}