package es.iespuertodelacruz.alejandrosamuel.studycircle.adapters;

import android.app.AlertDialog;
import android.content.DialogInterface;
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

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

import es.iespuertodelacruz.alejandrosamuel.studycircle.R;
import es.iespuertodelacruz.alejandrosamuel.studycircle.data.enums.RespuestasActividad;
import es.iespuertodelacruz.alejandrosamuel.studycircle.data.rest.dto.ActividadDTO;
import es.iespuertodelacruz.alejandrosamuel.studycircle.viewmodel.MainActivityViewModel;

public class ActividadesAdapter  extends RecyclerView.Adapter<ActividadesAdapter.ViewHolder> {

    private List<ActividadDTO> actividadesDTO;
    private MainActivityViewModel mainActivityViewModel;
    private ViewGroup viewGroup;
    private LifecycleOwner lifecycleOwner;
    private boolean isTutor;
    private int position;

    public ActividadesAdapter(List<ActividadDTO> actividadesDTO,
                              MainActivityViewModel mainActivityViewModel,
                              ViewGroup viewGroup,
                              LifecycleOwner viewLifecycleOwner, boolean isTutor) {
        this.actividadesDTO = actividadesDTO;
        this.mainActivityViewModel = mainActivityViewModel;
        this.viewGroup = viewGroup;
        this.lifecycleOwner = viewLifecycleOwner;
        this.isTutor = isTutor;
    }

    @NonNull
    @Override
    public ActividadesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_actividad, parent, false);
        return new ActividadesAdapter.ViewHolder(view);
    }

    public void actualizarActividades(List<ActividadDTO> nuevasActividades) {
        this.actividadesDTO = nuevasActividades;
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(@NonNull ActividadesAdapter.ViewHolder holder, int position) {
        setPosition(position);
        ActividadDTO objeto = actividadesDTO.get(position);
        holder.txtActividad.setText(objeto.getNombre());
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder builder = new AlertDialog.Builder(holder.itemView.getContext(), R.style.CustomAlertDialogStyle);
                LayoutInflater inflater = LayoutInflater.from(holder.itemView.getContext());
                View viewE = inflater.inflate(R.layout.dialog_show_actividad, null);

                builder.setView(viewE);

                TextView titleTextView = viewE.findViewById(R.id.titleTextView);
                TextView descriptionTextView = viewE.findViewById(R.id.descriptionTextView);
                ImageView btnEliminarActividad = viewE.findViewById(R.id.btnEliminarActividad);
                TextView dateTextView = viewE.findViewById(R.id.dateTextView);

                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
                String formattedDate = sdf.format(objeto.getFechaActividad());
                dateTextView.setText(formattedDate);
                titleTextView.setText(objeto.getNombre());
                descriptionTextView.setText(objeto.getDescripcion());
                builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {}
                });

                AlertDialog dialog = builder.create();
                if(isTutor) {
                    btnEliminarActividad.setVisibility(View.VISIBLE);
                    btnEliminarActividad.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext(), R.style.CustomAlertDialogStyle);
                            builder.setTitle("Eliminar Actividad");
                            builder.setMessage("¿Desea eliminar la actividad?");
                            builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog2, int which) {
                                        LiveData<Object> objectLiveData = mainActivityViewModel.eliminarActividadById(objeto.getId(), mainActivityViewModel.recuperarTokenSharedPreferences(viewGroup.getContext()));
                                    objectLiveData.observe(lifecycleOwner, new Observer<Object>() {
                                        @Override
                                        public void onChanged(Object o) {
                                            if(o instanceof RespuestasActividad) {
                                                dialog2.dismiss();
                                                if(((RespuestasActividad) o).getName().equals(RespuestasActividad.ACTIVITY_REMOVED.getName())) {
                                                    dialog.dismiss();
                                                    Toast.makeText(viewGroup.getContext(), "Actividad eliminada correctamente", Toast.LENGTH_LONG).show();
                                                    Navigation.findNavController(viewGroup).navigate(R.id.action_refresh_cursoFragment);
                                                }else {
                                                    Toast.makeText(viewGroup.getContext(), "No se ha podido eliminar la actividad", Toast.LENGTH_LONG).show();
                                                }
                                            }else {
                                                Toast.makeText(viewGroup.getContext(), "No se ha podido eliminar la actividad", Toast.LENGTH_LONG).show();
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
                notifyItemChanged(getPosition());
            }
        });
    }

    @Override
    public int getItemCount() {
        return actividadesDTO != null ? actividadesDTO.size() : 0;
    }

    public boolean isTutor() {
        return isTutor;
    }

    public void setTutor(boolean tutor) {
        isTutor = tutor;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtActividad;
        CardView cardView;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtActividad = itemView.findViewById(R.id.txtActividad);
            cardView = itemView.findViewById(R.id.cardViewActividades);
        }
    }
}
