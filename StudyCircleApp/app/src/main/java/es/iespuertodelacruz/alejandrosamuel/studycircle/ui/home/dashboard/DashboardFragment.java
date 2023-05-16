package es.iespuertodelacruz.alejandrosamuel.studycircle.ui.home.dashboard;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import es.iespuertodelacruz.alejandrosamuel.studycircle.R;
import es.iespuertodelacruz.alejandrosamuel.studycircle.databinding.FragmentDashboardBinding;

public class DashboardFragment extends Fragment {

    private RecyclerView recyclerViewAlerts;
    private AlertAdapter alertAdapter;
    private List<Alert> alertList;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dashboard, container, false);
        recyclerViewAlerts = view.findViewById(R.id.recyclerViewAlerts);
        recyclerViewAlerts.setLayoutManager(new LinearLayoutManager(getActivity()));
        alertList = new ArrayList<>();
        alertAdapter = new AlertAdapter(alertList);
        recyclerViewAlerts.setAdapter(alertAdapter);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        populateDummyData(); // Coloca datos de prueba en la lista de alertas
    }

    private void populateDummyData() {
        // Agrega alertas de ejemplo a la lista
        alertList.add(new Alert("Alerta 1", "9:00 AM", "Curso A"));
        alertList.add(new Alert("Alerta 2", "10:30 AM", "Curso B"));
        alertList.add(new Alert("Alerta 3", "2:00 PM", "Curso C"));
        alertAdapter.notifyDataSetChanged(); // Notifica al adaptador que se han agregado nuevos elementos
    }

    private class AlertAdapter extends RecyclerView.Adapter<AlertAdapter.AlertViewHolder> {

        private List<Alert> alertList;

        public AlertAdapter(List<Alert> alertList) {
            this.alertList = alertList;
        }

        @NonNull
        @Override
        public AlertViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_alert, parent, false);
            return new AlertViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull AlertViewHolder holder, int position) {
            Alert alert = alertList.get(position);
            holder.textTitle.setText(alert.getTitle());
            holder.textTime.setText(alert.getTime());
            holder.textCourse.setText(alert.getCourse());
            holder.btnDelete.setOnClickListener(v -> {
                // Lógica para borrar una alerta
                showConfirmationDialog(position);
            });
        }

        @Override
        public int getItemCount() {
            return alertList.size();
        }

        private void showConfirmationDialog(int position) {
            AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
            builder.setTitle("Confirmación")
                    .setMessage("¿Estás seguro de que deseas borrar esta alerta?")
                    .setPositiveButton("Borrar", (dialog, which) -> {
                        // Lógica para borrar la alerta
                        deleteAlert(position);
                    })
                    .setNegativeButton("Cancelar", (dialog, which) -> {
                        // No se realiza ninguna acción al cancelar
                    })
                    .show();
        }

        private void deleteAlert(int position) {
            alertList.remove(position);
            alertAdapter.notifyItemRemoved(position);
            Toast.makeText(getActivity(), "Alerta borrada", Toast.LENGTH_SHORT).show();
        }

        private class AlertViewHolder extends RecyclerView.ViewHolder {
            TextView textTitle;
            TextView textTime;
            TextView textCourse;
            Button btnDelete;

            public AlertViewHolder(@NonNull View itemView) {
                super(itemView);
                textTitle = itemView.findViewById(R.id.textTitle);
                textTime = itemView.findViewById(R.id.textTime);
                textCourse = itemView.findViewById(R.id.textCourse);
                btnDelete = itemView.findViewById(R.id.btnDelete);
            }
        }
    }

    private class Alert {
        private String title;
        private String time;
        private String course;

        public Alert(String title, String time, String course) {
            this.title = title;
            this.time = time;
            this.course = course;
        }

        public String getTitle() {
            return title;
        }

        public String getTime() {
            return time;
        }
        public String getCourse() {
            return course;
        }
    }
}