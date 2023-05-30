package es.iespuertodelacruz.alejandrosamuel.studycircle.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;

import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import es.iespuertodelacruz.alejandrosamuel.studycircle.R;
import es.iespuertodelacruz.alejandrosamuel.studycircle.adapters.SearchUsuariosAdapter;
import es.iespuertodelacruz.alejandrosamuel.studycircle.data.rest.dto.UsuarioDTO;
import es.iespuertodelacruz.alejandrosamuel.studycircle.databinding.FragmentBusquedaUsuariosBinding;
import es.iespuertodelacruz.alejandrosamuel.studycircle.viewmodel.MainActivityViewModel;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BusquedaUsuariosFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BusquedaUsuariosFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private FragmentBusquedaUsuariosBinding binding;
    private MainActivityViewModel viewModel;
    private ImageView btnAtras;
    private ProgressBar progressBar;
    private RecyclerView recyclerView;
    private SearchUsuariosAdapter searchUsuariosAdapter;
    private SearchView searchView;

    List<UsuarioDTO> usuariosDTO;

    public BusquedaUsuariosFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BusquedaUsuariosFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static BusquedaUsuariosFragment newInstance(String param1, String param2) {
        BusquedaUsuariosFragment fragment = new BusquedaUsuariosFragment();
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
        binding = FragmentBusquedaUsuariosBinding.inflate(inflater, container, false);
        ((MainActivity) requireActivity()).enableDrawer(false);
        ((MainActivity) requireActivity()).setBottomNavVisibility(View.GONE);
        btnAtras = binding.btnAtras;
        progressBar = binding.progressBar;
        searchView = binding.searchView;
        recyclerView = binding.recyclerView;
        progressBar.setVisibility(View.INVISIBLE);
        View view = binding.getRoot();
        usuariosDTO = new ArrayList<>();
        LiveData<Object> perfilesUsuarios = viewModel.getPerfilesUsuarios(viewModel.recuperarTokenSharedPreferences(getContext()));

        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        perfilesUsuarios.observe(getViewLifecycleOwner(), new Observer<Object>() {
            @Override
            public void onChanged(Object o) {
                if(o instanceof List) {
                    setUsuariosDTO((List<UsuarioDTO>) o);
                    searchUsuariosAdapter = new SearchUsuariosAdapter(usuariosDTO, viewModel, container);
                    recyclerView.setAdapter(searchUsuariosAdapter);
                }
            }
        });
        btnAtras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(container).navigate(R.id.action_busquedaUsuariosFragment_to_homeFragment);
            }
        });

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // Este método se llama cuando el usuario presiona el botón de búsqueda en el teclado
                // Podrías usar este método si necesitas buscar cuando el usuario presiona el botón de búsqueda,
                // pero en tu caso, como estás buscando a medida que el usuario escribe, no necesitas usar este método.
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                search(newText);
                return true;
            }
        });

        return view;
    }

    private void search(String query) {
        searchUsuariosAdapter.filtrar(query);
    }

    public List<UsuarioDTO> getUsuariosDTO() {
        return usuariosDTO;
    }

    public void setUsuariosDTO(List<UsuarioDTO> usuariosDTO) {
        this.usuariosDTO = usuariosDTO;
    }
}