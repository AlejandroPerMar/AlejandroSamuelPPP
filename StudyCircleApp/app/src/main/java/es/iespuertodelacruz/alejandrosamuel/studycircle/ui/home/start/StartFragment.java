package es.iespuertodelacruz.alejandrosamuel.studycircle.ui.home.start;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import es.iespuertodelacruz.alejandrosamuel.studycircle.R;
import es.iespuertodelacruz.alejandrosamuel.studycircle.databinding.FragmentStartBinding;
import es.iespuertodelacruz.alejandrosamuel.studycircle.ui.home.start.alumno.Alumno;
import es.iespuertodelacruz.alejandrosamuel.studycircle.ui.home.start.alumno.AlumnoAdapter;
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

        List<Alumno> listaAlumnos = new ArrayList<>();
        listaAlumnos.add(new Alumno("Juan", "Pérez"));
        listaAlumnos.add(new Alumno("María", "Gómez"));
        listaAlumnos.add(new Alumno("Pedro", "López"));
        listaAlumnos.add(new Alumno("Ana", "Sánchez"));
        addAlumnoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAgregarAlumnoDialog(listaAlumnos, alumnosLayout);
            }
        });

        builder.setPositiveButton("Crear", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String nombreCurso = nombreEditText.getText().toString().trim();
                String materiaSeleccionada = materiaSpinner.getSelectedItem().toString();
                Curso nuevoCurso = new Curso(nombreCurso, materiaSeleccionada, nombreCurso, listaAlumnos);

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

    private void showAgregarAlumnoDialog(List<Alumno> listaAlumnos, LinearLayout alumnosLayout) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Agregar Alumno");

        View dialogView = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_agregar_alumno, null);
        builder.setView(dialogView);

        EditText searchEditText = dialogView.findViewById(R.id.searchEditText);
        RecyclerView alumnosRecyclerView = dialogView.findViewById(R.id.alumnosRecyclerView);

        // Configurar RecyclerView y adaptador
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        alumnosRecyclerView.setLayoutManager(layoutManager);
        AlumnoAdapter alumnoAdapter = new AlumnoAdapter(getActivity(), new ArrayList<>());
        alumnosRecyclerView.setAdapter(alumnoAdapter);

        // Realizar búsqueda de alumnos y actualizar el adaptador con los resultados
        searchEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String searchText = s.toString().trim();
                List<Alumno> resultados = buscarAlumnos(searchText);
                alumnoAdapter.setAlumnos(resultados);
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        builder.setPositiveButton("Agregar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // Agregar alumnos seleccionados a la lista
                List<Alumno> alumnosSeleccionados = alumnoAdapter.getAlumnosSeleccionados();
                for (Alumno alumno : alumnosSeleccionados) {
                    listaAlumnos.add(alumno);
                    TextView alumnoTextView = new TextView(getActivity());
                    alumnoTextView.setText(alumno.getNombre());
                    alumnosLayout.addView(alumnoTextView);
                }
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

    private List<Alumno> buscarAlumnos(String searchText) {
        // Implementa la lógica para buscar alumnos en función del texto de búsqueda
        // y devuelve una lista de resultados
        // Aquí puedes realizar una búsqueda en una base de datos o en una lista de alumnos preexistente
        // Por ahora, simplemente devolvemos una lista vacía
        return new ArrayList<>();
    }

    private List<String> obtenerListaMaterias() {
        List<String> materias = new ArrayList<>();
        materias.add("Matemáticas");
        materias.add("Historia");
        materias.add("Ciencias");
        return materias;
    }

    @Override
    public void onItemClick(int position) {
        // Acciones a realizar cuando se hace clic en un curso
        Curso cursoSeleccionado = cursos.get(position);
        Toast.makeText(getActivity(), "Curso seleccionado: " + cursoSeleccionado.getNombre(), Toast.LENGTH_SHORT).show();
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