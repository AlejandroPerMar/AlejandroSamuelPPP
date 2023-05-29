package es.iespuertodelacruz.alejandrosamuel.studycircle.ui;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.Path;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import es.iespuertodelacruz.alejandrosamuel.studycircle.R;
import es.iespuertodelacruz.alejandrosamuel.studycircle.adapters.AnunciosAdapter;
import es.iespuertodelacruz.alejandrosamuel.studycircle.adapters.CustomArrayAdapter;
import es.iespuertodelacruz.alejandrosamuel.studycircle.adapters.MateriaAlumnoAdapter;
import es.iespuertodelacruz.alejandrosamuel.studycircle.adapters.SearchUsuariosAdapter;
import es.iespuertodelacruz.alejandrosamuel.studycircle.data.enums.MotivosAnuncio;
import es.iespuertodelacruz.alejandrosamuel.studycircle.data.enums.UserProfiles;
import es.iespuertodelacruz.alejandrosamuel.studycircle.data.rest.dto.AlumnoDTO;
import es.iespuertodelacruz.alejandrosamuel.studycircle.data.rest.dto.AnuncioDTO;
import es.iespuertodelacruz.alejandrosamuel.studycircle.data.rest.dto.EventoCalendarioDTO;
import es.iespuertodelacruz.alejandrosamuel.studycircle.data.rest.dto.MateriaDTO;
import es.iespuertodelacruz.alejandrosamuel.studycircle.data.rest.dto.NivelEstudiosDTO;
import es.iespuertodelacruz.alejandrosamuel.studycircle.databinding.FragmentAnunciosBinding;
import es.iespuertodelacruz.alejandrosamuel.studycircle.databinding.FragmentConfiguracionBinding;
import es.iespuertodelacruz.alejandrosamuel.studycircle.utils.ObjectsUtils;
import es.iespuertodelacruz.alejandrosamuel.studycircle.viewmodel.MainActivityViewModel;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AnunciosFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AnunciosFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private FragmentAnunciosBinding binding;
    private MainActivityViewModel viewModel;
    private ImageView btnAtras;
    private ProgressBar progressBar;
    private List<AnuncioDTO> anuncioDTOList;
    private ImageView btnCrearEvento;
    private RecyclerView recyclerView;

    private AnuncioDTO anuncioDTO;

    public AnunciosFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AnunciosFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AnunciosFragment newInstance(String param1, String param2) {
        AnunciosFragment fragment = new AnunciosFragment();
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
        binding = FragmentAnunciosBinding.inflate(inflater, container, false);
        ((MainActivity) requireActivity()).enableDrawer(false);
        ((MainActivity) requireActivity()).setBottomNavVisibility(View.GONE);
        View view = binding.getRoot();
        btnAtras = binding.btnAtras;
        progressBar = binding.progressBar;
        recyclerView = binding.recyclerView;
        btnCrearEvento = binding.btnCrearEvento;
        progressBar.setVisibility(View.INVISIBLE);

        LiveData<Object> allAnuncios = viewModel.findAllAnuncios(viewModel.recuperarTokenSharedPreferences(getContext()));
        allAnuncios.observe(getViewLifecycleOwner(), new Observer<Object>() {
            @Override
            public void onChanged(Object o) {
                if(o instanceof List) {
                    setAnuncioDTOList((List<AnuncioDTO>) o);
                    if(getAnuncioDTOList().isEmpty()) {
                        recyclerView.setVisibility(View.GONE);
                        TextView textView = new TextView(getContext());

                        textView.setText("No hay anuncios disponibles");

                        float scaledSizeInPixels = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 5, getResources().getDisplayMetrics());
                        textView.setTextSize(scaledSizeInPixels);

                        textView.setTextColor(Color.GRAY);

                        textView.setGravity(Gravity.CENTER);

                        textView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT));

                        LinearLayout layout = (LinearLayout) recyclerView.getParent();
                        layout.addView(textView);
                    }else {
                        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                        AnunciosAdapter anunciosAdapter = new AnunciosAdapter(getAnuncioDTOList());
                        recyclerView.setAdapter(anunciosAdapter);
                    }
                }
            }
        });

        btnAtras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(requireView()).popBackStack();
            }
        });

        btnCrearEvento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                anuncioDTO = new AnuncioDTO();
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext(), R.style.CustomAlertDialogStyle);
                LayoutInflater inflater = getLayoutInflater();
                View viewE = inflater.inflate(R.layout.dialog_new_anuncio, null);
                builder.setView(viewE);
                EditText titleEditText = viewE.findViewById(R.id.titleEditText);
                EditText descriptionEditText = viewE.findViewById(R.id.descriptionEditText);
                AutoCompleteTextView autoCompleteTextView = viewE.findViewById(R.id.autoCompleteTextView);
                autoCompleteTextView.setThreshold(1);
                if(viewModel.recuperarPerfilSeleccionadoSharedPreferences(getContext()).equals(UserProfiles.STUDENT_PROFILE.name())) {
                    TextView txtCabecera = viewE.findViewById(R.id.txtCabecera);
                    txtCabecera.setText("Búsqueda de tutor");
                    anuncioDTO.setMotivo(MotivosAnuncio.SEARCHING_TUTOR.getName());
                    LiveData<Object> materiasAlumno = viewModel.getAlumno(viewModel.recuperarTokenSharedPreferences(getContext()));
                    materiasAlumno.observe(getViewLifecycleOwner(), new Observer<Object>() {
                        @Override
                        public void onChanged(Object o) {
                            if(o instanceof AlumnoDTO) {
                                List<MateriaDTO> materias = ((AlumnoDTO) o).getMaterias();
                                List<String> nombresMateria = materias.stream().map(MateriaDTO::getNombre).collect(Collectors.toList());
                                CustomArrayAdapter adapter = new CustomArrayAdapter(getContext(), android.R.layout.simple_dropdown_item_1line, nombresMateria);
                                autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                    @Override
                                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                        closeKeyboard();
                                        String materiaSeleccionada = parent.getItemAtPosition(position).toString();
                                        MateriaDTO materiaDTO = materias.stream().filter(n -> n.getNombre().equals(materiaSeleccionada)).findFirst().get();
                                        anuncioDTO.setMateria(materiaDTO);
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
                            String description = descriptionEditText.getText().toString();
                            if(ObjectsUtils.notNullNorEmpty(title, description, anuncioDTO.getMateria())) {
                                anuncioDTO.setTitulo(title);
                                anuncioDTO.setDescripcion(description);

                                LiveData<Object> crearAnuncio = viewModel.crearAnuncio(anuncioDTO, viewModel.recuperarTokenSharedPreferences(getContext()));

                                crearAnuncio.observe(getViewLifecycleOwner(), new Observer<Object>() {
                                    @Override
                                    public void onChanged(Object o) {
                                        if(o instanceof AnuncioDTO) {
                                            AlertDialog.Builder builder = new AlertDialog.Builder(getContext(), R.style.CustomAlertDialogStyle);
                                            builder.setTitle("Anuncio creado");
                                            builder.setMessage("Se ha creado el anuncio correctamente");
                                            builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialog, int which) {
                                                    Navigation.findNavController(container).navigate(R.id.action_refresh_anuncios_fragment);

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
                }else {
                    autoCompleteTextView.setHint("Seleccione la materia relacionada al anuncio");
                    LiveData<Object> materiasByTutor = viewModel.findMateriasByTutor(viewModel.recuperarTokenSharedPreferences(getContext()));
                    materiasByTutor.observe(getViewLifecycleOwner(), new Observer<Object>() {
                        @Override
                        public void onChanged(Object o) {
                            if(o instanceof List) {
                                List<MateriaDTO> materias = ((List<MateriaDTO>)o);
                                List<String> nombresMateria = materias.stream().map(MateriaDTO::getNombre).collect(Collectors.toList());
                                CustomArrayAdapter adapter = new CustomArrayAdapter(getContext(), android.R.layout.simple_dropdown_item_1line, nombresMateria);
                                autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                    @Override
                                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                        closeKeyboard();
                                        String materiaSeleccionada = parent.getItemAtPosition(position).toString();
                                        MateriaDTO materiaDTO = materias.stream().filter(n -> n.getNombre().equals(materiaSeleccionada)).findFirst().get();
                                        anuncioDTO.setMateria(materiaDTO);
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
                            String description = descriptionEditText.getText().toString();
                            if(ObjectsUtils.notNullNorEmpty(title, description, anuncioDTO.getMateria())) {
                                anuncioDTO.setTitulo(title);
                                anuncioDTO.setDescripcion(description);
                                anuncioDTO.setMotivo(MotivosAnuncio.SEARCHING_STUDENTS.getName());

                                LiveData<Object> crearAnuncio = viewModel.crearAnuncio(anuncioDTO, viewModel.recuperarTokenSharedPreferences(getContext()));

                                crearAnuncio.observe(getViewLifecycleOwner(), new Observer<Object>() {
                                    @Override
                                    public void onChanged(Object o) {
                                        if(o instanceof AnuncioDTO) {
                                            Navigation.findNavController(container).navigate(R.id.action_refresh_anuncios_fragment);
                                            AlertDialog.Builder builder = new AlertDialog.Builder(getContext(), R.style.CustomAlertDialogStyle);
                                            builder.setTitle("Anuncio creado");
                                            builder.setMessage("Se ha creado el anuncio correctamente");
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

            }
        });

        return view;
    }

    public List<AnuncioDTO> getAnuncioDTOList() {
        return anuncioDTOList;
    }

    public void setAnuncioDTOList(List<AnuncioDTO> anuncioDTOList) {
        this.anuncioDTOList = anuncioDTOList;
    }

    public AnuncioDTO getAnuncioDTO() {
        return anuncioDTO;
    }

    public void setAnuncioDTO(AnuncioDTO anuncioDTO) {
        this.anuncioDTO = anuncioDTO;
    }
}