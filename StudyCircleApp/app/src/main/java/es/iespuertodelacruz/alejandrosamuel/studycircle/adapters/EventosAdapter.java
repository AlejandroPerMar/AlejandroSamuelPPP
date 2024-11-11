package es.iespuertodelacruz.alejandrosamuel.studycircle.adapters;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.RecyclerView;

import org.threeten.bp.Instant;
import org.threeten.bp.LocalDate;
import org.threeten.bp.ZoneId;
import org.w3c.dom.Text;

import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

import es.iespuertodelacruz.alejandrosamuel.studycircle.R;
import es.iespuertodelacruz.alejandrosamuel.studycircle.data.rest.dto.EventoCalendarioDTO;
import es.iespuertodelacruz.alejandrosamuel.studycircle.utils.ObjectsUtils;
import es.iespuertodelacruz.alejandrosamuel.studycircle.viewmodel.MainActivityViewModel;

public class EventosAdapter extends RecyclerView.Adapter<EventosAdapter.EventoViewHolder> {

    private List<EventoCalendarioDTO> eventos;

    public EventosAdapter(List<EventoCalendarioDTO> eventos) {
        this.eventos = eventos;
    }

    public EventoCalendarioDTO getEventoAtPosition(int position) {
        return eventos.get(position);
    }


    @NonNull
    @Override
    public EventoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_evento, parent, false);
        return new EventoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EventoViewHolder holder, int position) {
        EventoCalendarioDTO evento = eventos.get(position);
        holder.tituloTextView.setText(evento.getNombre());
        holder.descripcionTextView.setText(evento.getDescripcion());
        long diferenciaMillis =  evento.getFechaEvento().getTime() - new Date().getTime();
        if(diferenciaMillis < 0) {
            holder.txtDiasRestantes.setText(String.valueOf("FINALIZADA"));
        }else {
            long diferenciaDias = diferenciaMillis / (24 * 60 * 60 * 1000);
            holder.txtDiasRestantes.setText(String.valueOf(diferenciaDias + 1 + " días"));
        }

        holder.linearLayout.setOnClickListener(v -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(holder.itemView.getContext(), R.style.CustomAlertDialogStyle);
            LayoutInflater inflater = LayoutInflater.from(holder.itemView.getContext());
            View viewE = inflater.inflate(R.layout.dialog_show_event, null);

            builder.setView(viewE);

            TextView titleTextView = viewE.findViewById(R.id.titleTextView);
            TextView descriptionTextView = viewE.findViewById(R.id.descriptionTextView);
            TextView dateTextView = viewE.findViewById(R.id.dateTextView);

            titleTextView.setText(evento.getNombre());
            descriptionTextView.setText(evento.getDescripcion());

            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
            String formattedDate = sdf.format(evento.getFechaEvento());
            dateTextView.setText(formattedDate);

            builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int id) {}
            });

            AlertDialog dialog = builder.create();

            // Aquí ajustas el tamaño y los márgenes del diálogo, así como los colores de los botones.
            dialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
            dialog.getWindow().getDecorView().setPadding(50, 0, 50, 0);

            dialog.setOnShowListener(new DialogInterface.OnShowListener() {
                @Override
                public void onShow(DialogInterface dialogInterface) {
                    Button positiveButton = dialog.getButton(DialogInterface.BUTTON_POSITIVE);
                    Button negativeButton = dialog.getButton(DialogInterface.BUTTON_NEGATIVE);
                    positiveButton.setTextColor(ContextCompat.getColor(holder.itemView.getContext(), R.color.button_color_selector));
                }
            });

            dialog.show();
            notifyItemChanged(position);
        });
    }

    @Override
    public int getItemCount() {
        return eventos.size();
    }

    public class EventoViewHolder extends RecyclerView.ViewHolder {

        TextView tituloTextView;
        TextView descripcionTextView;

        TextView txtDiasRestantes;
        LinearLayout linearLayout;

        public EventoViewHolder(@NonNull View itemView) {
            super(itemView);
            tituloTextView = itemView.findViewById(R.id.tituloTextView);
            descripcionTextView = itemView.findViewById(R.id.descripcionTextView);
            txtDiasRestantes = itemView.findViewById(R.id.txtDiasRestantes);
            linearLayout = itemView.findViewById(R.id.eventoPopUp);
        }
    }
}
