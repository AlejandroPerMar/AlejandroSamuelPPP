package es.iespuertodelacruz.alejandrosamuel.studycircle.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import es.iespuertodelacruz.alejandrosamuel.studycircle.R;
import es.iespuertodelacruz.alejandrosamuel.studycircle.data.rest.dto.UsuarioDTO;
import es.iespuertodelacruz.alejandrosamuel.studycircle.viewmodel.MainActivityViewModel;

public class SearchUsuariosAdapter extends RecyclerView.Adapter<SearchUsuariosAdapter.ViewHolder> {

    private List<UsuarioDTO> usuariosDTO;
    private List<UsuarioDTO> usuariosFiltradosDTO;
    private MainActivityViewModel viewModel;
    ViewGroup container;

    public SearchUsuariosAdapter(List<UsuarioDTO> usuariosDTO, MainActivityViewModel mainActivityViewModel, ViewGroup container) {
        this.usuariosDTO = usuariosDTO;
        this.usuariosFiltradosDTO = new ArrayList<>(usuariosDTO);
        this.viewModel = mainActivityViewModel;
        this.container = container;
    }

    public void actualizarDatos(List<UsuarioDTO> nuevosDatos) {
        this.usuariosDTO.clear();
        this.usuariosDTO.addAll(nuevosDatos);
        this.usuariosFiltradosDTO.clear();
        this.usuariosFiltradosDTO.addAll(nuevosDatos);
        notifyDataSetChanged();
    }

    public void filtrar(String query) {
        usuariosFiltradosDTO.clear();
        if (query.isEmpty()) {
            usuariosFiltradosDTO.addAll(usuariosDTO);
        } else {
            usuariosDTO.forEach(u -> {
                if(u.getUsername().toLowerCase().contains(query.toLowerCase())) {
                    usuariosFiltradosDTO.add(u);
                }
            });
        }
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_usuarios, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        UsuarioDTO objeto = usuariosFiltradosDTO.get(position);
        holder.textView.setText(objeto.getUsername());

        holder.cardView.setOnClickListener(v -> {
            viewModel.setSelectedUsuarioDTO(objeto);
            Navigation.findNavController(container).navigate(R.id.action_busquedaUsuariosFragment_to_visualizarPerfilFragment);
        });
    }

    @Override
    public int getItemCount() {
        return usuariosFiltradosDTO != null ? usuariosFiltradosDTO.size() : 0;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        CardView cardView;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.txtUsuarioNombre);
            cardView = itemView.findViewById(R.id.cardViewUsuarios);
        }
    }
}
