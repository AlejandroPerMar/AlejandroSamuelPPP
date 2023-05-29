package es.iespuertodelacruz.alejandrosamuel.studycircle.adapters;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

import es.iespuertodelacruz.alejandrosamuel.studycircle.R;
import es.iespuertodelacruz.alejandrosamuel.studycircle.data.rest.dto.AnuncioDTO;
import es.iespuertodelacruz.alejandrosamuel.studycircle.data.rest.dto.UsuarioDTO;
import es.iespuertodelacruz.alejandrosamuel.studycircle.viewmodel.MainActivityViewModel;

public class AnunciosAdapter extends RecyclerView.Adapter<AnunciosAdapter.ViewHolder> {

    private List<AnuncioDTO> anunciosDTO;

    public AnunciosAdapter(List<AnuncioDTO> anunciosDTO) {
        this.anunciosDTO = anunciosDTO;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_anuncios, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        AnuncioDTO objeto = anunciosDTO.get(position);
        holder.txtTituloAnuncio.setText(objeto.getTitulo());
        holder.txtDescripcion.setText(objeto.getDescripcion());

        holder.cardView.setOnClickListener(v -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(holder.itemView.getContext(), R.style.CustomAlertDialogStyle);
            LayoutInflater inflater = LayoutInflater.from(holder.itemView.getContext());
            View viewE = inflater.inflate(R.layout.dialog_show_anuncio, null);

            builder.setView(viewE);

            TextView titleTextView = viewE.findViewById(R.id.titleTextView);
            TextView descriptionTextView = viewE.findViewById(R.id.descriptionTextView);
            TextView materiaTextView = viewE.findViewById(R.id.materiaTextView);
            TextView usuarioTextView = viewE.findViewById(R.id.usuarioTextView);

            titleTextView.setText(objeto.getTitulo());
            descriptionTextView.setText(objeto.getDescripcion());
            materiaTextView.setText(objeto.getMateria().getNombre());
            usuarioTextView.setText(objeto.getUsuario().getUsername());

            builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int id) {}
            });

            AlertDialog dialog = builder.create();

            dialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
            dialog.getWindow().getDecorView().setPadding(50, 0, 50, 0);

            dialog.setOnShowListener(new DialogInterface.OnShowListener() {
                @Override
                public void onShow(DialogInterface dialogInterface) {
                    Button positiveButton = dialog.getButton(DialogInterface.BUTTON_POSITIVE);
                    positiveButton.setTextColor(ContextCompat.getColor(holder.itemView.getContext(), R.color.button_color_selector));
                }
            });

            dialog.show();
            notifyItemChanged(position);
        });
    }

    @Override
    public int getItemCount() {
        return anunciosDTO != null ? anunciosDTO.size() : 0;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtTituloAnuncio;
        TextView txtDescripcion;
        CardView cardView;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtTituloAnuncio = itemView.findViewById(R.id.txtTituloAnuncio);
            txtDescripcion = itemView.findViewById(R.id.txtDescripcion);
            cardView = itemView.findViewById(R.id.cardViewAnuncios);
        }
    }
}
