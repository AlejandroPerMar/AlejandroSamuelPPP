package es.iespuertodelacruz.alejandrosamuel.studycircle.ui.home.start.alumno;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import androidx.appcompat.widget.SearchView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import es.iespuertodelacruz.alejandrosamuel.studycircle.R;


public class AgregarAlumnoDialog extends DialogFragment {

    private List<Alumno> alumnoList;
    private List<Alumno> filteredList;
    private AlumnoAdapter alumnoAdapter;
    private OnAlumnoSelectedListener listener;

    public void setOnAlumnoSelected(OnAlumnoSelectedListener listener) {
        this.listener = listener;
    }
    public interface OnAlumnoSelectedListener {
        void onAlumnoSelected(Alumno alumno);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NORMAL, R.style.AppTheme_Dialog);
        alumnoList = new ArrayList<>();
        filteredList = new ArrayList<>();
        alumnoList.add(new Alumno("Juan", "Pérez"));
        alumnoList.add(new Alumno("María", "Gómez"));
        alumnoList.add(new Alumno("Pedro", "López"));
        alumnoList.add(new Alumno("Ana", "Sánchez"));
        filteredList.addAll(alumnoList);
        alumnoAdapter = new AlumnoAdapter();
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(requireActivity());
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_agregar_alumno, null);
        builder.setView(view)
                .setTitle("Agregar Alumno")
                .setNegativeButton("Cancelar", null)
                .setPositiveButton("Agregar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        int position = alumnoAdapter.getSelectedPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            Alumno alumno = filteredList.get(position);
                            listener.onAlumnoSelected(alumno);
                        }
                    }
                });

        RecyclerView recyclerView = view.findViewById(R.id.alumnosRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        recyclerView.setAdapter(alumnoAdapter);

        SearchView searchView = view.findViewById(R.id.searchAlumno);
        searchView.setQueryHint(getString(R.string.search_hint));

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                alumnoAdapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                alumnoAdapter.getFilter().filter(newText);
                return false;
            }
        });

        return builder.create();
    }

    private class AlumnoAdapter extends RecyclerView.Adapter<AlumnoViewHolder> implements Filterable {

        private int selectedPosition = RecyclerView.NO_POSITION;

        @NonNull
        @Override
        public AlumnoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_alumno, parent, false);
            return new AlumnoViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(@NonNull AlumnoViewHolder holder, int position) {
            Alumno alumno = filteredList.get(position);
            holder.nombreTextView.setText(alumno.getNombre());
            holder.apellidoTextView.setText(alumno.getApellido());

            holder.itemView.setActivated(position == selectedPosition);
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    notifyItemChanged(selectedPosition);
                    selectedPosition = holder.getAdapterPosition();
                    notifyItemChanged(selectedPosition);
                }
            });
        }

        @Override
        public int getItemCount() {
            return filteredList.size();
        }

        @Override
        public Filter getFilter() {
            return new Filter() {
                @Override
                protected FilterResults performFiltering(CharSequence constraint) {
                    FilterResults results = new FilterResults();

                    if (constraint == null || constraint.length() == 0) {
                        results.values = alumnoList;
                        results.count = alumnoList.size();
                    } else {
                        List<Alumno> filteredAlumnos = new ArrayList<>();
                        for (Alumno alumno : alumnoList) {
                            if (alumno.getNombre().toLowerCase().contains(constraint.toString().toLowerCase())) {
                                filteredAlumnos.add(alumno);
                            }
                        }
                        results.values = filteredAlumnos;
                        results.count = filteredAlumnos.size();
                    }

                    return results;
                }

                @Override
                protected void publishResults(CharSequence constraint, FilterResults results) {
                    filteredList = (List<Alumno>) results.values;
                    notifyDataSetChanged();
                }
            };
        }

        public int getSelectedPosition() {
            return selectedPosition;
        }
    }

    private static class AlumnoViewHolder extends RecyclerView.ViewHolder {

        TextView nombreTextView;
        TextView apellidoTextView;

        public AlumnoViewHolder(@NonNull View itemView) {
            super(itemView);
            nombreTextView = itemView.findViewById(R.id.nombre_text_view);
            apellidoTextView = itemView.findViewById(R.id.apellido_text_view);
        }
    }


    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
    }
}