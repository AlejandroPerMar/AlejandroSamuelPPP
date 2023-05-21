package es.iespuertodelacruz.alejandrosamuel.studycircle.ui.home.start.alumno;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import es.iespuertodelacruz.alejandrosamuel.studycircle.R;

public class AlumnoAdapter extends RecyclerView.Adapter<AlumnoAdapter.AlumnoViewHolder> {

    private Context context;
    private List<Alumno> alumnos;
    private List<Alumno> alumnosSeleccionados;

    public AlumnoAdapter(Context context, List<Alumno> alumnos) {
        this.context = context;
        this.alumnos = alumnos;
        this.alumnosSeleccionados = new ArrayList<>();
    }

    public List<Alumno> getAlumnosSeleccionados() {
        return alumnosSeleccionados;
    }

    public void setAlumnos(List<Alumno> alumnos) {
        this.alumnos = alumnos;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public AlumnoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_alumno, parent, false);
        return new AlumnoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AlumnoViewHolder holder, int position) {
        Alumno alumno = alumnos.get(position);
        holder.nombreTextView.setText(alumno.getNombre());

        if (alumnosSeleccionados.contains(alumno)) {
            holder.itemView.setBackgroundColor(ContextCompat.getColor(context, R.color.colorItemSelected));
        } else {
            holder.itemView.setBackgroundColor(Color.TRANSPARENT);
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (alumnosSeleccionados.contains(alumno)) {
                    alumnosSeleccionados.remove(alumno);
                    v.setBackgroundColor(Color.TRANSPARENT);
                } else {
                    alumnosSeleccionados.add(alumno);
                    v.setBackgroundColor(ContextCompat.getColor(context, R.color.colorItemSelected));
                }
            }
        });


    }



    @Override
    public int getItemCount() {
        return alumnos.size();
    }

    class AlumnoViewHolder extends RecyclerView.ViewHolder {
        TextView nombreTextView;

        AlumnoViewHolder(@NonNull View itemView) {
            super(itemView);
            nombreTextView = itemView.findViewById(R.id.nombreTextView);
        }
    }
}