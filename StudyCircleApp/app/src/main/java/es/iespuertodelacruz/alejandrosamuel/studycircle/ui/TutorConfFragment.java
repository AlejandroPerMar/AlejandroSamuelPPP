package es.iespuertodelacruz.alejandrosamuel.studycircle.ui;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import es.iespuertodelacruz.alejandrosamuel.studycircle.R;
import es.iespuertodelacruz.alejandrosamuel.studycircle.adapters.CustomArrayAdapter;
import es.iespuertodelacruz.alejandrosamuel.studycircle.adapters.MateriaTutorAdapter;
import es.iespuertodelacruz.alejandrosamuel.studycircle.adapters.MateriasPorNivelEstudios;
import es.iespuertodelacruz.alejandrosamuel.studycircle.adapters.NivelEstudiosTutorAdapter;
import es.iespuertodelacruz.alejandrosamuel.studycircle.data.enums.RespuestasProfileConf;
import es.iespuertodelacruz.alejandrosamuel.studycircle.data.enums.RespuestasRegister;
import es.iespuertodelacruz.alejandrosamuel.studycircle.data.enums.UserProfiles;
import es.iespuertodelacruz.alejandrosamuel.studycircle.data.rest.dto.MateriaDTO;
import es.iespuertodelacruz.alejandrosamuel.studycircle.data.rest.dto.NivelEstudiosDTO;
import es.iespuertodelacruz.alejandrosamuel.studycircle.data.rest.dto.TutorDTO;
import es.iespuertodelacruz.alejandrosamuel.studycircle.databinding.FragmentTutorConfBinding;
import es.iespuertodelacruz.alejandrosamuel.studycircle.utils.TextViewUtils;
import es.iespuertodelacruz.alejandrosamuel.studycircle.viewmodel.MainActivityViewModel;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TutorConfFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TutorConfFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private FragmentTutorConfBinding binding;
    private MainActivityViewModel viewModel;
    private List<NivelEstudiosDTO> nivelesEstudio;
    private List<MateriasPorNivelEstudios> materiasPorNivelEstudiosList;
    private AutoCompleteTextView autoCompleteTextView;
    private ImageView btnAtras;
    private RecyclerView recyclerViewMaterias;
    private RecyclerView recyclerViewNivelesEstudio;
    private TextView btnAceptar;
    private TextView btnAgregar;
    private LinearLayout tutorConfFragment;
    private ProgressBar progressBar;

    public TutorConfFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TutorConfFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static TutorConfFragment newInstance(String param1, String param2) {
        TutorConfFragment fragment = new TutorConfFragment();
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
        binding = FragmentTutorConfBinding.inflate(inflater, container, false);
        tutorConfFragment = binding.tutorConfFragment;
        materiasPorNivelEstudiosList = new ArrayList<>();
        progressBar = binding.progressBar;
        btnAtras = binding.btnAtras;
        btnAceptar = binding.btnAceptar;
        btnAgregar = binding.btnAgregar;
        recyclerViewMaterias = binding.recyclerViewMaterias;
        recyclerViewNivelesEstudio = binding.recyclerViewNivelesEstudio;
        autoCompleteTextView = binding.autoCompleteTextView;
        autoCompleteTextView.setThreshold(1);
        recyclerViewNivelesEstudio.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        recyclerViewNivelesEstudio.setAdapter(new NivelEstudiosTutorAdapter(getContext(), materiasPorNivelEstudiosList));
        recyclerViewMaterias.setLayoutManager(new GridLayoutManager(getContext(), 3));
            recyclerViewMaterias.setAdapter(new MateriaTutorAdapter(getContext(), new ArrayList<>()));
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

        autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                closeKeyboard();
                String nivelEstudioSeleccionado = parent.getItemAtPosition(position).toString();
                Optional<MateriasPorNivelEstudios> matNiv = materiasPorNivelEstudiosList.stream().filter(mn -> mn.getNombreNivelEstudios().equals(nivelEstudioSeleccionado)).findFirst();
                if(!matNiv.isPresent()) {
                    MateriasPorNivelEstudios materiasPorNivelEstudios = new MateriasPorNivelEstudios();
                    materiasPorNivelEstudios.setMateriasSeleccionadas(new ArrayList<>());
                    materiasPorNivelEstudios.setNombreNivelEstudios(nivelEstudioSeleccionado);
                    NivelEstudiosDTO nivelEstudiosDTO = getNivelesEstudio().stream().filter(n -> n.getNombre().equals(nivelEstudioSeleccionado)).findFirst().get();
                    List<MateriaDTO> materiasFiltradas = nivelEstudiosDTO.getMaterias();
                    MateriaTutorAdapter materiaTutorAdapter = (MateriaTutorAdapter) recyclerViewMaterias.getAdapter();
                    materiaTutorAdapter.setMateriasPorNivelEstudios(materiasPorNivelEstudios);
                    if(Objects.nonNull(materiaTutorAdapter)){
                        materiaTutorAdapter.resetSelection(); // Restablecer selección
                        materiaTutorAdapter.actualizarMaterias(new ArrayList<>(materiasFiltradas));
                    }
                }else {
                    autoCompleteTextView.setText("");
                    MateriaTutorAdapter materiaTutorAdapter = (MateriaTutorAdapter) recyclerViewMaterias.getAdapter();
                    materiaTutorAdapter.removeSelectionWithUpdate(); // Restablecer selección
                    AlertDialog.Builder builder = new AlertDialog.Builder(getContext(), R.style.CustomAlertDialogStyle);
                    builder.setTitle("Nivel de Estudios ya existente");
                    builder.setMessage("El Nivel de Estudios seleccionado ya se encuentra en la lista, si desea volver a configurarlo debe eliminarlo primero");
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

                }

            }
        });

        btnAgregar.setOnClickListener(v -> {
            MateriaTutorAdapter materiaTutorAdapter = (MateriaTutorAdapter) recyclerViewMaterias.getAdapter();
            MateriasPorNivelEstudios materiasPorNivelEstudios = materiaTutorAdapter.getMateriasPorNivelEstudios();
            if(Objects.nonNull(materiasPorNivelEstudios)) {
                if(materiasPorNivelEstudios.getMateriasSeleccionadas().isEmpty()) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(getContext(), R.style.CustomAlertDialogStyle);
                    builder.setTitle("Configuración de Nivel de Estudios");
                    builder.setMessage("Debe incluir mínimo una materia para impartir");
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
                }else {
                    NivelEstudiosTutorAdapter adapter = (NivelEstudiosTutorAdapter) recyclerViewNivelesEstudio.getAdapter();
                    adapter.agregarMateriasPorNivelEstudios(materiasPorNivelEstudios);
                    autoCompleteTextView.setText("");
                    materiaTutorAdapter.resetSelection();
                    materiaTutorAdapter.removeSelectionWithUpdate();
                }
            }else {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext(), R.style.CustomAlertDialogStyle);
                builder.setTitle("Nivel de Estudios no cofigurado");
                builder.setMessage("Configure al menos un Nivel de Estudios para agregar");
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
            }
        });

        btnAceptar.setOnClickListener(btn -> {
            List<MateriaDTO> materiasSeleccionadas = new ArrayList<>();
            getMateriasPorNivelEstudiosList().forEach(mn -> materiasSeleccionadas.addAll(mn.getMateriasSeleccionadas()));
            if(!materiasSeleccionadas.isEmpty()) {
                LiveData<Object> tutorProfile = viewModel.createTutorProfile(materiasSeleccionadas, viewModel.recuperarTokenSharedPreferences(getContext()));
                tutorProfile.observe(getViewLifecycleOwner(), new Observer<Object>() {
                    @Override
                    public void onChanged(Object o) {
                        if(o instanceof TutorDTO) {
                            viewModel.guardarPerfilSeleccionadoSharedPreferences(getContext(), UserProfiles.TUTOR_PROFILE.name());
                            Toast.makeText(getContext(), "Perfil de tutor creado con éxito", Toast.LENGTH_LONG).show();
                            Navigation.findNavController(container).navigate(R.id.action_tutorConfFragment_to_homeFragment);
                        }else if(o instanceof RespuestasProfileConf){
                            TextView txtErrorMessage = TextViewUtils.getTextViewLinearLayoutErrorMessage(requireContext(), RespuestasProfileConf.TUTOR_PROFILE_NOT_CREATED.getDescripcion());
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
                builder.setTitle("Configurar perfil de Tutor");
                builder.setMessage("Debe configurar los Niveles de Estudios que puede impartir antes de continuar");
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
            }
        });

        btnAtras.setOnClickListener(btn -> {
            Navigation.findNavController(requireView()).popBackStack();
        });

        return binding.getRoot();
    }

    private void closeKeyboard() {
        InputMethodManager manager = (InputMethodManager) requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        manager.hideSoftInputFromWindow(getView().getWindowToken(), 0);
    }

    public List<NivelEstudiosDTO> getNivelesEstudio() {
        return nivelesEstudio;
    }

    public void setNivelesEstudio(List<NivelEstudiosDTO> nivelesEstudio) {
        this.nivelesEstudio = nivelesEstudio;
    }

    public List<MateriasPorNivelEstudios> getMateriasPorNivelEstudiosList() {
        return materiasPorNivelEstudiosList;
    }

    public void setMateriasPorNivelEstudiosList(List<MateriasPorNivelEstudios> materiasPorNivelEstudiosList) {
        this.materiasPorNivelEstudiosList = materiasPorNivelEstudiosList;
    }
}