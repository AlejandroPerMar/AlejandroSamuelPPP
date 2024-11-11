package es.iespuertodelacruz.alejandrosamuel.studycircle.adapters;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import es.iespuertodelacruz.alejandrosamuel.studycircle.R;
import es.iespuertodelacruz.alejandrosamuel.studycircle.data.rest.dto.MateriaDTO;

public class MateriaTutorAdapter extends RecyclerView.Adapter<MateriaTutorAdapter.MateriaViewHolder> {
    private List<MateriaDTO> listaMaterias;
    private MateriasPorNivelEstudios materiasPorNivelEstudios;
    private Context context;

    public MateriaTutorAdapter(Context context, List<MateriaDTO> listaMaterias) {
        this.context = context;
        this.listaMaterias = listaMaterias;
    }

    @Override
    public MateriaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_materia, parent, false);
        return new MateriaViewHolder(v);
    }

    public void resetSelection() {
        listaMaterias.forEach(m -> m.setSelected(false));
    }

    public void removeSelectionWithUpdate() {
        listaMaterias.clear();
        notifyDataSetChanged();
    }

    public void actualizarMaterias(List<MateriaDTO> nuevasMaterias) {
        this.listaMaterias = nuevasMaterias;
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(MateriaViewHolder holder, int position) {
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
                materiasPorNivelEstudios.getMateriasSeleccionadas().add(materia);
            } else if (wasSelected) { // solo intentar eliminar la materia si estaba seleccionada antes
                materiasPorNivelEstudios.getMateriasSeleccionadas().remove(materia);
            }
            notifyItemChanged(position);
        });
    }

    @Override
    public int getItemCount() {
        return listaMaterias.size();
    }

    public MateriasPorNivelEstudios getMateriasPorNivelEstudios() {
        return materiasPorNivelEstudios;
    }

    public void setMateriasPorNivelEstudios(MateriasPorNivelEstudios materiasPorNivelEstudios) {
        this.materiasPorNivelEstudios = materiasPorNivelEstudios;
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
