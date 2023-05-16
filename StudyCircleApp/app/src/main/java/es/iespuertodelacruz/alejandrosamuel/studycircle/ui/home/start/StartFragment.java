package es.iespuertodelacruz.alejandrosamuel.studycircle.ui.home.start;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import es.iespuertodelacruz.alejandrosamuel.studycircle.R;
import es.iespuertodelacruz.alejandrosamuel.studycircle.databinding.FragmentStartBinding;

public class StartFragment extends Fragment {

    private RecyclerView recyclerView;
    private CursoAdapter cursoAdapter;
    private List<Curso> cursos;
    private FragmentStartBinding binding;

    public static StartFragment newInstance() {
        return new StartFragment();
    }


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
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


        cursoAdapter = new CursoAdapter(getActivity(), cursos);
        recyclerView.setAdapter(cursoAdapter);

        return view;
    }

    class CursoAdapter extends RecyclerView.Adapter<CursoAdapter.CursoViewHolder> {

        private Context context;
        private List<Curso> cursos;

        public CursoAdapter(Context context, List<Curso> cursos) {
            this.context = context;
            this.cursos = cursos;
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
            }
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}