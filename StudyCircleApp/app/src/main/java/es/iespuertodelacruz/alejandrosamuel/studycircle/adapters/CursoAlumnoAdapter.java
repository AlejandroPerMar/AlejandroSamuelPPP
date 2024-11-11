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

public class CursoAlumnoAdapter extends RecyclerView.Adapter<CursoAlumnoAdapter.CursoAlumnoViewHolder> {
    private List<AlumnoDTO> listaAlumnos;
    private Context context;

    public CursoAlumnoAdapter(List<AlumnoDTO> listaAlumnos, Context context) {
        this.listaAlumnos = listaAlumnos;
        this.context = context;
    }

    @NonNull
    @Override
    public CursoAlumnoAdapter.CursoAlumnoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_materia, parent, false);
        return new CursoAlumnoAdapter.CursoAlumnoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CursoAlumnoAdapter.CursoAlumnoViewHolder holder, int position) {
        AlumnoDTO alumnoDTO = listaAlumnos.get(position);
        holder.textView.setText(alumnoDTO.getUsuario().getUsername());

        holder.cardView.setOnClickListener(v -> {

            notifyItemChanged(position);
        });
    }

    @Override
    public int getItemCount() {
        return listaAlumnos.size();
    }

    class CursoAlumnoViewHolder extends RecyclerView.ViewHolder {
        TextView textView;
        CardView cardView;

        CursoAlumnoViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.txtAlumno);
            cardView = itemView.findViewById(R.id.cardViewAlumnos);
        }
    }
}
