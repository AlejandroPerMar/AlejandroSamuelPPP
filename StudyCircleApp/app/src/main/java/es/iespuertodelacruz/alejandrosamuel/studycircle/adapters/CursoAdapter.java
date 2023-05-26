package es.iespuertodelacruz.alejandrosamuel.studycircle.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import es.iespuertodelacruz.alejandrosamuel.studycircle.R;
import es.iespuertodelacruz.alejandrosamuel.studycircle.data.rest.dto.CursoDTO;

public class CursoAdapter extends RecyclerView.Adapter<CursoAdapter.CursoViewHolder> {
    private List<CursoDTO> listaCursos;
    private Context context;

    public CursoAdapter(List<CursoDTO> listaCursos, Context context) {
        this.listaCursos = listaCursos;
        this.context = context;
    }

    @NonNull
    @Override
    public CursoAdapter.CursoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_materia, parent, false);
        return new CursoAdapter.CursoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CursoAdapter.CursoViewHolder holder, int position) {
        CursoDTO cursoDTO = listaCursos.get(position);
        holder.textView.setText(cursoDTO.getTitulo());

        holder.cardView.setOnClickListener(v -> {

            notifyItemChanged(position);
        });
    }

    @Override
    public int getItemCount() {
        return listaCursos.size();
    }

    class CursoViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        CardView cardView;

        CursoViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.txtAlumno);
            cardView = itemView.findViewById(R.id.cardViewAlumnos);
        }
    }
}
