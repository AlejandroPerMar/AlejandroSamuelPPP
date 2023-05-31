package es.iespuertodelacruz.alejandrosamuel.studycircle.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import es.iespuertodelacruz.alejandrosamuel.studycircle.R;
import es.iespuertodelacruz.alejandrosamuel.studycircle.data.rest.dto.CursoDTO;
import es.iespuertodelacruz.alejandrosamuel.studycircle.viewmodel.MainActivityViewModel;

public class CursoAdapter extends RecyclerView.Adapter<CursoAdapter.CursoViewHolder> {
    private List<CursoDTO> listaCursos;
    private Context context;
    private MainActivityViewModel viewModel;
    private ViewGroup container;

    public CursoAdapter(List<CursoDTO> listaCursos, Context context, MainActivityViewModel viewModel, ViewGroup container) {
        this.listaCursos = listaCursos;
        this.context = context;
        this.viewModel = viewModel;
        this.container = container;
    }

    @NonNull
    @Override
    public CursoAdapter.CursoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_curso, parent, false);
        return new CursoAdapter.CursoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CursoAdapter.CursoViewHolder holder, int position) {
        CursoDTO cursoDTO = listaCursos.get(position);
        holder.txtCursoTitulo.setText(cursoDTO.getTitulo());
        String nombreTutor = context.getString(R.string.tutor_, cursoDTO.getMateriaTutor().getTutor().getUsuario().getUsername());
        holder.txtTutor.setText(nombreTutor);
        String nombreMateria = context.getString(R.string.materia_, cursoDTO.getMateriaTutor().getMateria().getNombre());
        holder.txtMateria.setText(nombreMateria);

        holder.cardView.setOnClickListener(v -> {
            viewModel.setSelectedCursoDTO(cursoDTO);
            Navigation.findNavController(container).navigate(R.id.action_homeFragment_to_cursoFragment);
            notifyItemChanged(position);
        });
    }

    @Override
    public int getItemCount() {
        return listaCursos.size();
    }

    class CursoViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        TextView txtMateria;
        TextView txtTutor;
        TextView txtCursoTitulo;

        CursoViewHolder(@NonNull View itemView) {
            super(itemView);
            txtMateria = itemView.findViewById(R.id.txtMateria);
            txtTutor = itemView.findViewById(R.id.txtTutor);
            txtCursoTitulo = itemView.findViewById(R.id.txtCursoTitulo);
            cardView = itemView.findViewById(R.id.cardViewCursos);
        }
    }
}
