package es.iespuertodelacruz.alejandrosamuel.studycircle.adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import java.util.Objects;

import es.iespuertodelacruz.alejandrosamuel.studycircle.R;
import es.iespuertodelacruz.alejandrosamuel.studycircle.data.rest.dto.NivelEstudiosDTO;

public class NivelEstudiosTutorAdapter extends RecyclerView.Adapter<NivelEstudiosTutorAdapter.ViewHolder> {
    private List<MateriasPorNivelEstudios> materiasPorNivelEstudiosList;
    private Context context;

    // Constructor, pasas la lista de objetos a mostrar en el RecyclerView
    public NivelEstudiosTutorAdapter(Context context, List<MateriasPorNivelEstudios> materiasPorNivelEstudiosList) {
        this.context = context;
        this.materiasPorNivelEstudiosList = materiasPorNivelEstudiosList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_materias_por_nivel_estudios, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        MateriasPorNivelEstudios materiasPorNivelEstudios = materiasPorNivelEstudiosList.get(position);
        if(Objects.nonNull(materiasPorNivelEstudios)) {
            holder.textView.setText(materiasPorNivelEstudios.getNombreNivelEstudios());
            holder.itemView.setOnClickListener(v -> {
                AlertDialog.Builder builder = new AlertDialog.Builder(context, R.style.CustomAlertDialogStyle);
                builder.setTitle("Eliminar nivel de estudio");
                builder.setMessage("¿Estás seguro de que quieres eliminar este Nivel de Estudios?");
                builder.setPositiveButton("Sí", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        int pos = holder.getBindingAdapterPosition();
                        if (pos != RecyclerView.NO_POSITION) {
                            materiasPorNivelEstudiosList.remove(pos);
                            notifyItemRemoved(pos);
                            notifyItemRangeChanged(pos, materiasPorNivelEstudiosList.size());
                        }
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {}
                });
                AlertDialog dialog = builder.create();
                dialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
                dialog.getWindow().getDecorView().setPadding(50, 0, 50, 0);
                dialog.show();
                Button positiveButton = dialog.getButton(DialogInterface.BUTTON_POSITIVE);
                Button negativeButton = dialog.getButton(DialogInterface.BUTTON_NEGATIVE);
                positiveButton.setTextColor(ContextCompat.getColor(context, R.color.button_color_selector));
                negativeButton.setTextColor(ContextCompat.getColor(context, R.color.button_color_selector));

            });
        }
    }

    public void agregarMateriasPorNivelEstudios(MateriasPorNivelEstudios materiasPorNivelEstudios) {
        materiasPorNivelEstudiosList.add(materiasPorNivelEstudios);
        notifyItemInserted(materiasPorNivelEstudiosList.size() - 1);
    }

    @Override
    public int getItemCount() {
        return materiasPorNivelEstudiosList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textView;

        public ViewHolder(View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.textView);
        }
    }
}
