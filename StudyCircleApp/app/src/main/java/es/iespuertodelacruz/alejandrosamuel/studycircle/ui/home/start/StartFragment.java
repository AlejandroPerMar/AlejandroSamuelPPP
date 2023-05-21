package es.iespuertodelacruz.alejandrosamuel.studycircle.ui.home.start;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import es.iespuertodelacruz.alejandrosamuel.studycircle.R;
import es.iespuertodelacruz.alejandrosamuel.studycircle.databinding.FragmentStartBinding;
import es.iespuertodelacruz.alejandrosamuel.studycircle.ui.home.start.alumno.AgregarAlumnoDialog;
import es.iespuertodelacruz.alejandrosamuel.studycircle.ui.home.start.alumno.Alumno;
import es.iespuertodelacruz.alejandrosamuel.studycircle.ui.home.start.curso.Curso;
import es.iespuertodelacruz.alejandrosamuel.studycircle.ui.home.start.curso.CursoAdapter;

public class StartFragment extends Fragment implements CursoAdapter.OnItemClickListener{

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

        cursos.add(new Curso("Curso 1", "Matemáticas", "Tutor 1", actividadesCurso1,new ArrayList<>()));
        cursos.add(new Curso("Curso 2", "Historia", "Tutor 2", actividadesCurso2,new ArrayList<>()));
        cursos.add(new Curso("Curso 3", "Ciencias", "Tutor 3", actividadesCurso3,new ArrayList<>()));
        cursos.add(new Curso("Curso 4", "Matemáticas", "Tutor 4", actividadesCurso1,new ArrayList<>()));
        cursos.add(new Curso("Curso 5", "Historia", "Tutor 5", actividadesCurso2,new ArrayList<>()));
        cursos.add(new Curso("Curso 6", "Ciencias", "Tutor 6", actividadesCurso3,new ArrayList<>()));

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

        List<Alumno> listaAlumnos = new ArrayList<Alumno>();
        addAlumnoButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AgregarAlumnoDialog dialogFragment = new AgregarAlumnoDialog();

                    dialogFragment.setOnAlumnoSelected(new AgregarAlumnoDialog.OnAlumnoSelectedListener() {
                        @Override
                        public void onAlumnoSelected(Alumno alumno) {
                            listaAlumnos.add(alumno);
                        }
                    });

                    dialogFragment.show(getChildFragmentManager(), "AgregarAlumnoDialogFragment");
                }

        });

        /*
            @Override
            public void onClick(View v) {
                showAgregarAlumnoDialog(listaAlumnos, alumnosLayout);
            }
            */

        builder.setPositiveButton("Crear", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String nombreCurso = nombreEditText.getText().toString().trim();
                String materiaSeleccionada = materiaSpinner.getSelectedItem().toString();
                Curso nuevoCurso = new Curso(nombreCurso, materiaSeleccionada, "Tutor", new ArrayList<>(),listaAlumnos) {
                };

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
/*
    private void showAgregarAlumnoDialog(List<Alumno> listaAlumnos, LinearLayout alumnosLayout) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Agregar Alumno");

        View dialogView = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_agregar_alumno, null);
        builder.setView(dialogView);

        EditText searchEditText = dialogView.findViewById(R.id./);
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

*/
    private List<String> obtenerListaMaterias() {
        List<String> materias = new ArrayList<>();
        materias.add("Matemáticas");
        materias.add("Historia");
        materias.add("Ciencias");
        materias.add("Lengua");
        materias.add("Física");
        materias.add("Química");
        return materias;
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
