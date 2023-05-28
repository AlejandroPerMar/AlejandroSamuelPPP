package es.iespuertodelacruz.alejandrosamuel.studycircle.ui.home.start;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.media.Image;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
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

        List<Alumno> alumnoList = new ArrayList<>();
        alumnoList.add(new Alumno("Juan", "Pérez"));
        alumnoList.add(new Alumno("María", "Gómez"));
        alumnoList.add(new Alumno("Pedro", "López"));
        alumnoList.add(new Alumno("Ana", "Sánchez"));

        cursos.add(new Curso("Curso 1", "Matemáticas", "Tutor 1", actividadesCurso1,alumnoList));
        cursos.add(new Curso("Curso 2", "Historia", "Tutor 2", actividadesCurso2,new ArrayList<>()));
        cursos.add(new Curso("Curso 3", "Ciencias", "Tutor 3", actividadesCurso3,alumnoList));
        cursos.add(new Curso("Curso 4", "Matemáticas", "Tutor 4", actividadesCurso1,new ArrayList<>()));
        cursos.add(new Curso("Curso 5", "Historia", "Tutor 5", actividadesCurso2,alumnoList));
        cursos.add(new Curso("Curso 6", "Ciencias", "Tutor 6", actividadesCurso3,alumnoList));

        cursoAdapter = new CursoAdapter(getActivity(), cursos);
        cursoAdapter.setOnItemClickListener(this);
        recyclerView.setAdapter(cursoAdapter);

        ImageButton addButton = view.findViewById(R.id.button);
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
        List<Alumno> alumnos = curso.getAlumnos();

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        View dialogView = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_curso, null);
        builder.setView(dialogView);

        TextView nombreTextView = dialogView.findViewById(R.id.nombreTextView);
        TextView materiaTextView = dialogView.findViewById(R.id.materiaTextView);
        TextView tutorTextView = dialogView.findViewById(R.id.tutorTextView);
        LinearLayout actividadesLayout = dialogView.findViewById(R.id.actividadesLayout);
        LinearLayout alumnosLayout = dialogView.findViewById(R.id.alumnosLayout);


        ImageButton editNameButton = (ImageButton) dialogView.findViewById(R.id.editCurso);
        ImageButton addAlumnoButton = (ImageButton) dialogView.findViewById(R.id.addAlumnoButton);
        ImageButton addActividadButton = (ImageButton) dialogView.findViewById(R.id.addActividadButton);



        nombreTextView.setText(nombre);
        materiaTextView.setText(materia);
        tutorTextView.setText(tutor);

        editNameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("Cambiar nombre del curso");

                View dialogView = LayoutInflater.from(getActivity()).inflate(R.layout.dialog_edit_name, null);
                builder.setView(dialogView);
                EditText nameEditText = dialogView.findViewById(R.id.nameEditText);
                nameEditText.setText(curso.getNombre());

                builder.setPositiveButton("Guardar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String nuevoNombre = nameEditText.getText().toString().trim();
                        curso.setNombre(nuevoNombre);
                        //cursoAdapter.notifyItemChanged(position);
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
        });
        addAlumnoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AgregarAlumnoDialog dialogFragment = new AgregarAlumnoDialog();

                dialogFragment.setOnAlumnoSelected(new AgregarAlumnoDialog.OnAlumnoSelectedListener() {
                    @Override
                    public void onAlumnoSelected(Alumno alumno) {
                        curso.getAlumnos().add(alumno);
                    }
                });

                dialogFragment.show(getChildFragmentManager(), "AgregarAlumnoDialogFragment");
            }
        });
        addActividadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                builder.setTitle("Agregar Actividad");

                // Inflar el diseño del diálogo
                LayoutInflater inflater = requireActivity().getLayoutInflater();
                View dialogView = inflater.inflate(R.layout.dialog_add_actividad, null);
                builder.setView(dialogView);

                // Obtener referencias a los componentes del diálogo
                EditText tituloEditText = dialogView.findViewById(R.id.txtTitulo);
                EditText descripcionEditText = dialogView.findViewById(R.id.txtDescripcion);
                DatePicker datePicker = dialogView.findViewById(R.id.datePicker);

                builder.setPositiveButton("Agregar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String titulo = tituloEditText.getText().toString();
                        String descripcion = descripcionEditText.getText().toString();
                        int dia = datePicker.getDayOfMonth();
                        int mes = datePicker.getMonth();
                        int anio = datePicker.getYear();

                        dialog.dismiss();
                    }
                });

                builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

                builder.create().show();
            }
        });

        for (String actividad : actividades) {
            LinearLayout container = new LinearLayout(getActivity());
            container.setOrientation(LinearLayout.HORIZONTAL);

            TextView actividadTextView = new TextView(getActivity());
            actividadTextView.setText(actividad);
            actividadTextView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showActividadDialog(actividad);
                }
            });

            ImageButton button = new ImageButton(getActivity());
            button.setLayoutParams(new LinearLayout.LayoutParams(60, 60));
            button.setPadding(16, 16, 16, 16);
            button.setImageResource(R.drawable.baseline_delete_24);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                    builder.setTitle("Borrar "+actividad);
                    builder.setMessage("¿Estás seguro de que deseas eliminar esta actividad?");
                    builder.setPositiveButton("Eliminar", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            container.removeView(actividadTextView);
                            container.removeView(button);
                        }
                    });
                    builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                        }
                    });
                    builder.create().show();
                }
            });

            container.addView(actividadTextView);
            container.addView(button);

            actividadesLayout.addView(container);

        }

        for (Alumno alumno : alumnos) {
            LinearLayout container = new LinearLayout(getActivity());
            container.setOrientation(LinearLayout.HORIZONTAL);

            TextView alumnoTextView = new TextView(getActivity());
            String nombreCompleto = alumno.getNombre() + " " + alumno.getApellido();
            alumnoTextView.setText(nombreCompleto);
            alumnoTextView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showAlumnoDialog(alumno);
                }
            });

            ImageButton button = new ImageButton(getActivity());
            button.setLayoutParams(new LinearLayout.LayoutParams(60, 60));
            button.setPadding(16, 16, 16, 16);
            button.setImageResource(R.drawable.baseline_delete_24);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                    builder.setTitle("Borrar "+ alumno.getNombre()+alumno.getApellido());
                    builder.setMessage("¿Estás seguro de que deseas eliminar este alumno?");
                    builder.setPositiveButton("Eliminar", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            container.removeView(alumnoTextView);
                            container.removeView(button);
                        }
                    });
                    builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                        }
                    });
                    builder.create().show();
                }
            });

            container.addView(alumnoTextView);
            container.addView(button);
            alumnosLayout.addView(container);
        }

        builder.setPositiveButton("Cerrar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void showActividadDialog(String actividad) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("Detalles de la actividad");
        builder.setMessage(actividad);
        builder.setPositiveButton("Cerrar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void showAlumnoDialog(Alumno alumno) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        String nombreCompleto = alumno.getNombre()+" "+alumno.getApellido();
        builder.setTitle(nombreCompleto);
        builder.setMessage("Detalles del alumno");
        builder.setPositiveButton("Cerrar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}
