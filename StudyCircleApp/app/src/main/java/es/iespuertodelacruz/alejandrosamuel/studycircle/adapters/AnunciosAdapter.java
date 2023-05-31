package es.iespuertodelacruz.alejandrosamuel.studycircle.adapters;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.UnderlineSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import es.iespuertodelacruz.alejandrosamuel.studycircle.R;
import es.iespuertodelacruz.alejandrosamuel.studycircle.data.enums.RespuestasAnuncio;
import es.iespuertodelacruz.alejandrosamuel.studycircle.data.rest.dto.AnuncioDTO;
import es.iespuertodelacruz.alejandrosamuel.studycircle.data.rest.dto.MateriaDTO;
import es.iespuertodelacruz.alejandrosamuel.studycircle.data.rest.dto.UsuarioDTO;
import es.iespuertodelacruz.alejandrosamuel.studycircle.viewmodel.MainActivityViewModel;

public class AnunciosAdapter extends RecyclerView.Adapter<AnunciosAdapter.ViewHolder> {

    private List<AnuncioDTO> anunciosDTO;
    private MainActivityViewModel mainActivityViewModel;
    private ViewGroup viewGroup;
    private UsuarioDTO usuarioDTO;
    private LifecycleOwner lifecycleOwner;

    public AnunciosAdapter(List<AnuncioDTO> anunciosDTO,
                           MainActivityViewModel mainActivityViewModel,
                           ViewGroup viewGroup,
                           UsuarioDTO usuarioDTO,
                           LifecycleOwner viewLifecycleOwner) {
        this.anunciosDTO = anunciosDTO;
        this.mainActivityViewModel = mainActivityViewModel;
        this.viewGroup = viewGroup;
        this.usuarioDTO = usuarioDTO;
        this.lifecycleOwner = viewLifecycleOwner;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_anuncios, parent, false);
        return new ViewHolder(view);
    }

    public void actualizarAnuncios(List<AnuncioDTO> nuevosAnuncios) {
        this.anunciosDTO = nuevosAnuncios;
        notifyDataSetChanged();
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
            ImageView btnEliminarAnuncio = viewE.findViewById(R.id.btnEliminarAnuncio);

            titleTextView.setText(objeto.getTitulo());
            descriptionTextView.setText(objeto.getDescripcion());
            materiaTextView.setText(objeto.getMateria().getNombre());
            usuarioTextView.setText(objeto.getUsuario().getUsername());
            SpannableString spannableString = new SpannableString(usuarioTextView.getText());
            spannableString.setSpan(new UnderlineSpan(), 0, usuarioTextView.getText().length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            usuarioTextView.setText(spannableString);
            builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int id) {}
            });

            AlertDialog dialog = builder.create();
            if(objeto.getUsuario().getId().equals(usuarioDTO.getId())) {
                btnEliminarAnuncio.setVisibility(View.VISIBLE);
                btnEliminarAnuncio.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext(), R.style.CustomAlertDialogStyle);
                        builder.setTitle("Eliminar Anuncio");
                        builder.setMessage("¿Desea eliminar el anuncio?");
                        builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog2, int which) {
                                LiveData<Object> objectLiveData = mainActivityViewModel.deleteAnuncio(objeto.getId(), mainActivityViewModel.recuperarTokenSharedPreferences(viewGroup.getContext()));
                                objectLiveData.observe(lifecycleOwner, new Observer<Object>() {
                                    @Override
                                    public void onChanged(Object o) {
                                        if(o instanceof RespuestasAnuncio) {
                                            dialog2.dismiss();
                                            if(((RespuestasAnuncio) o).getName().equals(RespuestasAnuncio.ANNOUNCEMENT_REMOVED.getName())) {
                                                dialog.dismiss();
                                                Toast.makeText(viewGroup.getContext(), "Anuncio eliminado correctamente", Toast.LENGTH_LONG).show();
                                                Navigation.findNavController(viewGroup).navigate(R.id.action_refresh_anuncios_fragment);
                                            }else {
                                                Toast.makeText(viewGroup.getContext(), "No se ha podido eliminar el anuncio", Toast.LENGTH_LONG).show();
                                            }
                                        }else {
                                            Toast.makeText(viewGroup.getContext(), "No se ha podido eliminar el anuncio", Toast.LENGTH_LONG).show();
                                        }
                                    }
                                });
                            }
                        });
                        builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {}
                        });

                        AlertDialog dialog = builder.create();
                        dialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
                        dialog.getWindow().getDecorView().setPadding(50, 0, 50, 0);
                        dialog.show();
                        Button positiveButton = dialog.getButton(DialogInterface.BUTTON_POSITIVE);
                        Button negativeButton = dialog.getButton(DialogInterface.BUTTON_NEGATIVE);
                        positiveButton.setTextColor(ContextCompat.getColor(view.getContext(), R.color.button_color_selector));
                        negativeButton.setTextColor(ContextCompat.getColor(view.getContext(), R.color.button_color_selector));

                    }
                });
            }
            dialog.getWindow().setLayout(WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.WRAP_CONTENT);
            dialog.getWindow().getDecorView().setPadding(50, 0, 50, 0);
            dialog.show();
            Button positiveButton = dialog.getButton(DialogInterface.BUTTON_POSITIVE);
            positiveButton.setTextColor(ContextCompat.getColor(holder.itemView.getContext(), R.color.button_color_selector));


            usuarioTextView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dialog.dismiss();
                    if(objeto.getUsuario().getId().equals(usuarioDTO.getId())) {
                        mainActivityViewModel.setSelectedUsuarioDTO(objeto.getUsuario());
                        Navigation.findNavController(viewGroup).navigate(R.id.action_anunciosFragment_to_configuracionFragment);
                    }else {
                        mainActivityViewModel.setSelectedUsuarioDTO(objeto.getUsuario());
                        mainActivityViewModel.setFromAnunciosToVisualizarPerfil(true);
                        Navigation.findNavController(viewGroup).navigate(R.id.action_anunciosFragment_to_visualizarPerfilFragment);
                    }
                }
            });
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
