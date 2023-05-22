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
import es.iespuertodelacruz.alejandrosamuel.studycircle.data.rest.dto.MateriaDTO;

public class MateriaAdapter extends RecyclerView.Adapter<MateriaAdapter.MateriaViewHolder> {
    private List<MateriaDTO> listaMaterias;
    private Context context;

    public MateriaAdapter(List<MateriaDTO> listaMaterias, Context context) {
        this.listaMaterias = listaMaterias;
        this.context = context;
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

    @Override
    public void onBindViewHolder(@NonNull MateriaViewHolder holder, int position) {
        MateriaDTO materia = listaMaterias.get(position);
        holder.textView.setText(materia.getNombre());

        if (materia.isSelected()) {
            holder.cardView.setCardBackgroundColor(Color.BLUE);
            holder.textView.setTextColor(Color.WHITE);
        } else {
            holder.cardView.setCardBackgroundColor(Color.WHITE);
            holder.textView.setTextColor(Color.BLACK);
        }

        holder.cardView.setOnClickListener(v -> {
            materia.setSelected(!materia.isSelected());
            notifyDataSetChanged();
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
            cardView = itemView.findViewById(R.id.cardView);
        }
    }
}
