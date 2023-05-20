package es.iespuertodelacruz.alejandrosamuel.studycircle.ui.home.start;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import es.iespuertodelacruz.alejandrosamuel.studycircle.R;
import es.iespuertodelacruz.alejandrosamuel.studycircle.databinding.FragmentStartBinding;
import es.iespuertodelacruz.alejandrosamuel.studycircle.ui.home.start.curso.Curso;

public class StartFragment extends Fragment implements CursoAdapter.OnItemClickListener {

    private RecyclerView recyclerView;
    private CursoAdapter cursoAdapter;
    private List<Curso> cursos;
    private FragmentStartBinding binding;

    public static StartFragment newInstance() {
        return new StartFragment();
    }

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_start, container, false);

        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        cursos = new ArrayList<>();

        List<String> actividadesCurso1 = Arrays.asList("Actividad 1", "Actividad 2", "Actividad 3");
        List<String> actividadesCurso2 = Arrays.asList("Actividad 4", "Actividad 5", "Actividad 6");
        List<String> actividadesCurso3 = Arrays.asList("Actividad 7", "Actividad 8", "Actividad 9");

        cursos.add(new Curso("Curso 1", "Matemáticas", "Tutor 1", actividadesCurso1));
        cursos.add(new Curso("Curso 2", "Historia", "Tutor 2", actividadesCurso2));
        cursos.add(new Curso("Curso 3", "Ciencias", "Tutor 3", actividadesCurso3));

        cursos.add(new Curso("Curso 4", "Matemáticas", "Tutor 4", actividadesCurso1));
        cursos.add(new Curso("Curso 5", "Historia", "Tutor 5", actividadesCurso2));
        cursos.add(new Curso("Curso 6", "Ciencias", "Tutor 6", actividadesCurso3));

        cursoAdapter = new CursoAdapter(getActivity(), cursos);
        cursoAdapter.setOnItemClickListener(this);
        recyclerView.setAdapter(cursoAdapter);


        Button addButton = view.findViewById(R.id.button);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCreateCursoDialog();
            }
        });

        return view;
    }



    private void showCreateCursoDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Crear nuevo curso");

        View dialogView = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_create_curso, null);
        builder.setView(dialogView);

        EditText nombreEditText = dialogView.findViewById(R.id.nombreEditText);
        Spinner materiaSpinner = dialogView.findViewById(R.id.materiaSpinner);
        LinearLayout alumnosLayout = dialogView.findViewById(R.id.alumnosLayout);
        Button addAlumnoButton = dialogView.findViewById(R.id.addAlumnoButton);

        ArrayAdapter<String> materiaAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, obtenerListaMaterias());
        materiaAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        materiaSpinner.setAdapter(materiaAdapter);

        List<String> listaAlumnos = new ArrayList<>();

        addAlumnoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Mostrar un diálogo para ingresar el nombre del alumno
                AlertDialog.Builder alumnoDialogBuilder = new AlertDialog.Builder(getActivity());
                alumnoDialogBuilder.setTitle("Agregar Alumno");

                final EditText alumnoEditText = new EditText(getActivity());
                alumnoDialogBuilder.setView(alumnoEditText);

                alumnoDialogBuilder.setPositiveButton("Agregar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String nombreAlumno = alumnoEditText.getText().toString().trim();

                        if (!TextUtils.isEmpty(nombreAlumno)) {
                            listaAlumnos.add(nombreAlumno);
                            TextView alumnoTextView = new TextView(getActivity());
                            alumnoTextView.setText(nombreAlumno);
                            alumnosLayout.addView(alumnoTextView);
                        }
                    }
                });

                alumnoDialogBuilder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

                AlertDialog alumnoDialog = alumnoDialogBuilder.create();
                alumnoDialog.show();
            }
        });

        builder.setPositiveButton("Crear", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String nombreCurso = nombreEditText.getText().toString().trim();
                String materiaSeleccionada = materiaSpinner.getSelectedItem().toString();
                Curso nuevoCurso = new Curso(nombreCurso, materiaSeleccionada,nombreCurso,listaAlumnos);

                dialog.dismiss();
            }
        });

        builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    // Lógica para obtener la lista de materias disponibles
    private List<String> obtenerListaMaterias() {
        List<String> listaMaterias = new ArrayList<>();
        listaMaterias.add("Matemáticas");
        listaMaterias.add("Historia");
        listaMaterias.add("Ciencias");
        return listaMaterias;
    }

    @Override
    public void onItemClick(int position) {
        Curso curso = cursos.get(position);
        showCursoDialog(curso);
    }

    private void showCursoDialog(Curso curso) {
        String nombre = curso.getNombre();
        String materia = curso.getMateria();
        String tutor = curso.getTutor();
        List<String> actividades = curso.getActividades();

        CursoDialogFragment dialogFragment = CursoDialogFragment.newInstance(nombre, materia, tutor, actividades);
        dialogFragment.show(requireActivity().getSupportFragmentManager(), "curso_dialog");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}


class CursoAdapter extends RecyclerView.Adapter<CursoAdapter.CursoViewHolder> {

    private Context context;
    private List<Curso> cursos;
    private OnItemClickListener onItemClickListener;

    public CursoAdapter(Context context, List<Curso> cursos) {
        this.context = context;
        this.cursos = cursos;
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.onItemClickListener = listener;
    }

    @NonNull
    @Override
    public CursoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.curso_card, parent, false);
        return new CursoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CursoViewHolder holder, int position) {
        Curso curso = cursos.get(position);
        holder.nombreTextView.setText(curso.getNombre());
        holder.materiaTextView.setText(curso.getMateria());
        holder.tutorTextView.setText(curso.getTutor());
    }

    @Override
    public int getItemCount() {
        return cursos.size();
    }

    class CursoViewHolder extends RecyclerView.ViewHolder {

        TextView nombreTextView;
        TextView materiaTextView;
        TextView tutorTextView;

        CursoViewHolder(@NonNull View itemView) {
            super(itemView);
            nombreTextView = itemView.findViewById(R.id.nombreTextView);
            materiaTextView = itemView.findViewById(R.id.materiaTextView);
            tutorTextView = itemView.findViewById(R.id.tutorTextView);

            itemView.setOnClickListener(v -> {
                if (onItemClickListener != null) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        onItemClickListener.onItemClick(position);
                    }
                }
            });
        }
    }
}