package es.iespuertodelacruz.alejandrosamuel.studycircle.adapters;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import es.iespuertodelacruz.alejandrosamuel.studycircle.R;
import es.iespuertodelacruz.alejandrosamuel.studycircle.data.rest.dto.AlumnoDTO;
import es.iespuertodelacruz.alejandrosamuel.studycircle.data.rest.dto.MateriaDTO;

public class MateriaAlumnoAdapter extends RecyclerView.Adapter<MateriaAlumnoAdapter.MateriaViewHolder> {
    private List<MateriaDTO> listaMaterias;
    private Context context;
    private AlumnoDTO alumnoDTO;

    public MateriaAlumnoAdapter(List<MateriaDTO> listaMaterias, Context context, AlumnoDTO alumnoDTO) {
        this.listaMaterias = listaMaterias;
        this.context = context;
        this.alumnoDTO = alumnoDTO;
    }

    @NonNull
    @Override
    public MateriaViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_materia, parent, false);
        return new MateriaViewHolder(view);
    }

    public void actualizarMaterias(List<MateriaDTO> nuevasMaterias) {
        this.listaMaterias = nuevasMaterias;
        notifyDataSetChanged();
    }

    public void resetSelection() {
        listaMaterias.forEach(m -> m.setSelected(false));
    }

    @Override
    public void onBindViewHolder(@NonNull MateriaViewHolder holder, int position) {
        MateriaDTO materia = listaMaterias.get(position);
        holder.textView.setText(materia.getNombre());

        if (materia.isSelected()) {
            holder.cardView.setCardBackgroundColor(Color.parseColor("#1566e0"));
            holder.textView.setTextColor(Color.WHITE);
        } else {
            holder.cardView.setCardBackgroundColor(Color.parseColor("#ededed"));
            holder.textView.setTextColor(Color.BLACK);
        }

        holder.cardView.setOnClickListener(v -> {
            boolean wasSelected = materia.isSelected();
            materia.setSelected(!wasSelected);

            // Actualizar las materias del alumno aquí en lugar de en onBindViewHolder
            if (materia.isSelected()) {
                alumnoDTO.getMaterias().add(materia);
            } else if (wasSelected) { // solo intentar eliminar la materia si estaba seleccionada antes
                alumnoDTO.getMaterias().remove(materia);
            }
            notifyItemChanged(position);
        });
    }

    @Override
    public int getItemCount() {
        return listaMaterias.size();
    }

    class MateriaViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        CardView cardView;

        MateriaViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.textView);
            cardView = itemView.findViewById(R.id.cardViewMaterias);
        }
    }
}
