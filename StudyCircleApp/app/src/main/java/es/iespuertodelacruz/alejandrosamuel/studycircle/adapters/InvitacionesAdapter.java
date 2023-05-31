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
import es.iespuertodelacruz.alejandrosamuel.studycircle.data.enums.RespuestasCursos;
import es.iespuertodelacruz.alejandrosamuel.studycircle.data.rest.dto.AlertaCursoAlumnoDTO;
import es.iespuertodelacruz.alejandrosamuel.studycircle.data.rest.dto.CursoDTO;
import es.iespuertodelacruz.alejandrosamuel.studycircle.viewmodel.MainActivityViewModel;

public class InvitacionesAdapter extends RecyclerView.Adapter<InvitacionesAdapter.ViewHolder> {

    private List<AlertaCursoAlumnoDTO> alertasCursoAlumnoDTO;
    private MainActivityViewModel mainActivityViewModel;
    private ViewGroup viewGroup;
    private LifecycleOwner lifecycleOwner;

    public InvitacionesAdapter(List<AlertaCursoAlumnoDTO> alertasCursoAlumnoDTO,
                          MainActivityViewModel mainActivityViewModel,
                          ViewGroup viewGroup,
                          LifecycleOwner viewLifecycleOwner) {
        this.alertasCursoAlumnoDTO = alertasCursoAlumnoDTO;
        this.mainActivityViewModel = mainActivityViewModel;
        this.viewGroup = viewGroup;
        this.lifecycleOwner = viewLifecycleOwner;
    }

    @NonNull
    @Override
    public InvitacionesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_invitacion_curso, parent, false);
        return new InvitacionesAdapter.ViewHolder(view);
    }

    public void actualizarAlertas(List<AlertaCursoAlumnoDTO> nuevasAlertas) {
        this.alertasCursoAlumnoDTO = nuevasAlertas;
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(@NonNull InvitacionesAdapter.ViewHolder holder, int position) {
        AlertaCursoAlumnoDTO objeto = alertasCursoAlumnoDTO.get(position);
        holder.txtCursoTutor.setText(objeto.getCurso().getTitulo());
        holder.imgAceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LiveData<Object> objectLiveData = mainActivityViewModel.aceptarInvitacionCurso(objeto.getId(), mainActivityViewModel.recuperarTokenSharedPreferences(viewGroup.getContext()));
                objectLiveData.observe(lifecycleOwner, new Observer<Object>() {
                    @Override
                    public void onChanged(Object o) {
                        if(o instanceof CursoDTO) {
                            Toast.makeText(viewGroup.getContext(), "Se ha unido al curso correctamente", Toast.LENGTH_LONG).show();
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
                LiveData<Object> objectLiveData = mainActivityViewModel.rechazarInvitacion(objeto.getId(), mainActivityViewModel.recuperarTokenSharedPreferences(viewGroup.getContext()));
                objectLiveData.observe(lifecycleOwner, new Observer<Object>() {
                    @Override
                    public void onChanged(Object o) {
                        if(o instanceof RespuestasCursos) {
                            if(((RespuestasCursos) o).getName().equals(RespuestasCursos.INVITATION_REMOVED.getName())) {
                                Toast.makeText(viewGroup.getContext(), "Se ha eliminado la invitación al curso correctamente", Toast.LENGTH_LONG).show();
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
        return alertasCursoAlumnoDTO != null ? alertasCursoAlumnoDTO.size() : 0;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtCursoTutor;
        ImageView imgAceptar;
        CardView cardView;
        ImageView imgRechazar;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtCursoTutor = itemView.findViewById(R.id.txtCursoTutor);
            cardView = itemView.findViewById(R.id.cardViewAlumnos);
            imgAceptar = itemView.findViewById(R.id.imgAceptar);
            imgRechazar = itemView.findViewById(R.id.imgRechazar);
        }
    }
}
