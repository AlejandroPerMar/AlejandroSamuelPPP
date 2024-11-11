package es.iespuertodelacruz.alejandrosamuel.studycircle.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import es.iespuertodelacruz.alejandrosamuel.studycircle.R;
import es.iespuertodelacruz.alejandrosamuel.studycircle.data.enums.RespuestasAmistad;
import es.iespuertodelacruz.alejandrosamuel.studycircle.data.enums.RespuestasCursos;
import es.iespuertodelacruz.alejandrosamuel.studycircle.data.rest.dto.AlertaAmistadDTO;
import es.iespuertodelacruz.alejandrosamuel.studycircle.data.rest.dto.AlertaCursoAlumnoDTO;
import es.iespuertodelacruz.alejandrosamuel.studycircle.data.rest.dto.AmistadDTO;
import es.iespuertodelacruz.alejandrosamuel.studycircle.data.rest.dto.CursoDTO;
import es.iespuertodelacruz.alejandrosamuel.studycircle.viewmodel.MainActivityViewModel;

public class SolicitudesAdapter extends RecyclerView.Adapter<SolicitudesAdapter.ViewHolder> {

    private List<AlertaAmistadDTO> alertasAmistadDTO;
    private MainActivityViewModel mainActivityViewModel;
    private ViewGroup viewGroup;
    private LifecycleOwner lifecycleOwner;

    public SolicitudesAdapter(List<AlertaAmistadDTO> alertasAmistadDTO,
                               MainActivityViewModel mainActivityViewModel,
                               ViewGroup viewGroup,
                               LifecycleOwner viewLifecycleOwner) {
        this.alertasAmistadDTO = alertasAmistadDTO;
        this.mainActivityViewModel = mainActivityViewModel;
        this.viewGroup = viewGroup;
        this.lifecycleOwner = viewLifecycleOwner;
    }

    @NonNull
    @Override
    public SolicitudesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_solicitud_amistad, parent, false);
        return new SolicitudesAdapter.ViewHolder(view);
    }

    public void actualizarAnuncios(List<AlertaAmistadDTO> nuevasAlertas) {
        this.alertasAmistadDTO = nuevasAlertas;
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(@NonNull SolicitudesAdapter.ViewHolder holder, int position) {
        AlertaAmistadDTO objeto = alertasAmistadDTO.get(position);
        holder.txtUsername.setText(objeto.getAmistad().getUsuario1().getUsername());
        holder.imgAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LiveData<Object> objectLiveData = mainActivityViewModel.aceptarAmistad(objeto.getAmistad().getUsuario1().getId(), mainActivityViewModel.recuperarTokenSharedPreferences(viewGroup.getContext()));
                objectLiveData.observe(lifecycleOwner, new Observer<Object>() {
                    @Override
                    public void onChanged(Object o) {
                        if(o instanceof AmistadDTO) {
                            Toast.makeText(viewGroup.getContext(), "Se ha aceptado la solicitud correctamente", Toast.LENGTH_LONG).show();
                            Navigation.findNavController(viewGroup).navigate(R.id.action_refresh_alerts_fragment);
                        }else {
                            Toast.makeText(viewGroup.getContext(), "Ha ocurrido un error, inténtelo más tarde", Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        });

        holder.imgRechazar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LiveData<Object> objectLiveData = mainActivityViewModel.eliminarAmistad(objeto.getAmistad().getUsuario1().getId(), mainActivityViewModel.recuperarTokenSharedPreferences(viewGroup.getContext()));
                objectLiveData.observe(lifecycleOwner, new Observer<Object>() {
                    @Override
                    public void onChanged(Object o) {
                        if(o instanceof RespuestasAmistad) {
                            if(((RespuestasAmistad) o).getName().equals(RespuestasAmistad.REMOVED_FRIENDSHIP.getName())) {
                                Toast.makeText(viewGroup.getContext(), "Se ha eliminado la solicitud de amistad correctamente", Toast.LENGTH_LONG).show();
                                Navigation.findNavController(viewGroup).navigate(R.id.action_refresh_alerts_fragment);
                            }
                        }else {
                            Toast.makeText(viewGroup.getContext(), "Ha ocurrido un error, inténtelo más tarde", Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        });
    }

    @Override
    public int getItemCount() {
        return alertasAmistadDTO != null ? alertasAmistadDTO.size() : 0;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtUsername;
        ImageView imgAceptar;
        CardView cardView;
        ImageView imgRechazar;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtUsername = itemView.findViewById(R.id.txtUsername);
            cardView = itemView.findViewById(R.id.cardViewSolicitudAmistad);
            imgAceptar = itemView.findViewById(R.id.imgAceptar);
            imgRechazar = itemView.findViewById(R.id.imgRechazar);
        }
    }
}
