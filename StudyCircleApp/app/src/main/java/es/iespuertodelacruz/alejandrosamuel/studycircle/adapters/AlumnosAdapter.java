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

import java.util.List;

import es.iespuertodelacruz.alejandrosamuel.studycircle.R;
import es.iespuertodelacruz.alejandrosamuel.studycircle.data.rest.dto.AlumnoDTO;
import es.iespuertodelacruz.alejandrosamuel.studycircle.data.rest.dto.CursoDTO;
import es.iespuertodelacruz.alejandrosamuel.studycircle.viewmodel.MainActivityViewModel;

public class AlumnosAdapter extends RecyclerView.Adapter<AlumnosAdapter.ViewHolder> {

    private List<AlumnoDTO> alumnosDTO;
    private MainActivityViewModel mainActivityViewModel;
    private ViewGroup viewGroup;
    private LifecycleOwner lifecycleOwner;

    public AlumnosAdapter(List<AlumnoDTO> alumnosDTO,
                           MainActivityViewModel mainActivityViewModel,
                           ViewGroup viewGroup,
                           LifecycleOwner viewLifecycleOwner) {
        this.alumnosDTO = alumnosDTO;
        this.mainActivityViewModel = mainActivityViewModel;
        this.viewGroup = viewGroup;
        this.lifecycleOwner = viewLifecycleOwner;
    }

    @NonNull
    @Override
    public AlumnosAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_alumno, parent, false);
        return new AlumnosAdapter.ViewHolder(view);
    }

    public void actualizarAnuncios(List<AlumnoDTO> nuevosAlumnos) {
        this.alumnosDTO = nuevosAlumnos;
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(@NonNull AlumnosAdapter.ViewHolder holder, int position) {
        AlumnoDTO objeto = alumnosDTO.get(position);
        holder.txtAlumno.setText(objeto.getUsuario().getUsername());
        holder.btnRemoveAlumno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext(), R.style.CustomAlertDialogStyle);
                builder.setTitle("Eliminar Alumno");
                builder.setMessage("¿Desea sacar al alumno del curso?");
                builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        LiveData<Object> objectLiveData = mainActivityViewModel.
                                removeAlumnoFromCurso(mainActivityViewModel.getSelectedCursoDTO().getId(), objeto.getId(), mainActivityViewModel.recuperarTokenSharedPreferences(view.getContext()));
                        objectLiveData.observe(lifecycleOwner, new Observer<Object>() {
                            @Override
                            public void onChanged(Object o) {
                                if(o instanceof CursoDTO) {
                                    Toast.makeText(viewGroup.getContext(), "Alumno eliminado correctamente", Toast.LENGTH_LONG).show();
                                    Navigation.findNavController(viewGroup).navigate(R.id.action_refresh_cursoFragment);
                                }else {
                                    Toast.makeText(viewGroup.getContext(), "No se ha podido eliminar al alumno", Toast.LENGTH_LONG).show();
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

    @Override
    public int getItemCount() {
        return alumnosDTO != null ? alumnosDTO.size() : 0;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtAlumno;
        CardView cardView;
        ImageView btnRemoveAlumno;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtAlumno = itemView.findViewById(R.id.txtAlumno);
            cardView = itemView.findViewById(R.id.cardViewAlumnos);
            btnRemoveAlumno = itemView.findViewById(R.id.btnRemoveAlumno);
        }
    }
}