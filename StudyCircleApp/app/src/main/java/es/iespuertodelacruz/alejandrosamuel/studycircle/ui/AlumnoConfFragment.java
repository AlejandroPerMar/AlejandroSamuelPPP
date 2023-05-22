package es.iespuertodelacruz.alejandrosamuel.studycircle.ui;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import es.iespuertodelacruz.alejandrosamuel.studycircle.R;
import es.iespuertodelacruz.alejandrosamuel.studycircle.adapters.MateriaAdapter;
import es.iespuertodelacruz.alejandrosamuel.studycircle.data.rest.dto.MateriaDTO;
import es.iespuertodelacruz.alejandrosamuel.studycircle.data.rest.dto.NivelEstudiosDTO;
import es.iespuertodelacruz.alejandrosamuel.studycircle.databinding.FragmentAlumnoConfBinding;
import es.iespuertodelacruz.alejandrosamuel.studycircle.databinding.FragmentTutorConfBinding;
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
        btnAtras = binding.btnAtras;
        btnAceptar = binding.btnAceptar;
        recyclerView = binding.recyclerView;
        autoCompleteTextView = binding.autoCompleteTextView;
        autoCompleteTextView.setThreshold(1);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 3));
        recyclerView.setAdapter(new MateriaAdapter(new ArrayList<>(), getContext()));
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
                String nivelEstudioSeleccionado = parent.getItemAtPosition(position).toString();

                List<MateriaDTO> materiasFiltradas = getNivelesEstudio().stream().filter(n -> n.getNombre().equals(nivelEstudioSeleccionado)).findFirst().get().getMaterias();
                MateriaAdapter materiaAdapter = (MateriaAdapter) recyclerView.getAdapter();
                if(Objects.nonNull(materiaAdapter)){
                    materiaAdapter.actualizarMaterias(materiasFiltradas);
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

    class CustomArrayAdapter extends ArrayAdapter<String> {

        private List<String> items;
        private List<String> itemsAll;

        public CustomArrayAdapter(Context context, int resource, List<String> items) {
            super(context, resource, items);
            this.items = items;
            this.itemsAll = new ArrayList<>(items);
        }

        @NonNull
        @Override
        public Filter getFilter() {
            return new Filter() {
                @Override
                protected FilterResults performFiltering(CharSequence constraint) {
                    FilterResults filterResults = new FilterResults();
                    if(constraint != null) {
                        List<String> suggestions = new ArrayList<>();
                        for(String item : itemsAll) {
                            if(item.toLowerCase().contains(constraint.toString().toLowerCase())) {
                                suggestions.add(item);
                            }
                        }

                        filterResults.values = suggestions;
                        filterResults.count = suggestions.size();
                    }
                    return filterResults;
                }

                @Override
                protected void publishResults(CharSequence constraint, FilterResults results) {
                    items.clear();
                    if(results != null && results.count > 0) {
                        items.addAll((List<String>) results.values);
                    }
                    notifyDataSetChanged();
                }
            };
        }
    }
}