package es.iespuertodelacruz.alejandrosamuel.studycircle.ui.home.notifications;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;

import es.iespuertodelacruz.alejandrosamuel.studycircle.R;
import es.iespuertodelacruz.alejandrosamuel.studycircle.databinding.FragmentNotificationsBinding;


public class NotificationsFragment extends Fragment {

    private FragmentNotificationsBinding binding;

    private Date selectedDate;
    private Button fechaButton;
    private RecyclerView recyclerView;

    private List<Evento> eventos;

    private EventAdapter eventAdapter;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentNotificationsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        CalendarView calendarView = binding.calendarView;
        RecyclerView recyclerView = binding.recyclerView;

        eventos = new ArrayList<>();
        eventAdapter = new EventAdapter(requireContext(), eventos);

        eventos.add(new Evento("Evento 1", "Descripción del evento 1", new Date()));
        eventos.add(new Evento("Evento 2", "Descripción del evento 2", new Date(new Date().getTime() + 86400000)));
        eventos.add(new Evento("Evento 3", "Descripción del evento 3", new Date(new Date().getTime() + 172800000)));

        setupCalendar(calendarView, recyclerView, eventos);

        Button addButton = binding.btnAddEvento;
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAddEventoDialog();
            }
        });

        return root;
    }

    private void showAddEventoDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.dialog_add_evento, null);
        builder.setView(dialogView);

        EditText editTextTitulo = dialogView.findViewById(R.id.editTextTitulo);
        EditText editTextDescripcion = dialogView.findViewById(R.id.editTextDescripcion);
        Button fechaButton = dialogView.findViewById(R.id.buttonFecha);

        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH);

        fechaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(requireContext(),
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                                // Obtener la fecha seleccionada
                                Calendar selectedDate = Calendar.getInstance();
                                selectedDate.set(year, month, day);
                                selectedDate.set(Calendar.HOUR_OF_DAY, 0);
                                selectedDate.set(Calendar.MINUTE, 0);
                                selectedDate.set(Calendar.SECOND, 0);
                                selectedDate.set(Calendar.MILLISECOND, 0);

                                // Actualizar el texto del botón con la fecha seleccionada
                                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
                                String formattedDate = sdf.format(selectedDate.getTime());
                                fechaButton.setText(formattedDate);
                            }
                        }, year, month, dayOfMonth);
                datePickerDialog.show();
            }
        });

        builder.setPositiveButton("Agregar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                String titulo = editTextTitulo.getText().toString();
                String descripcion = editTextDescripcion.getText().toString();
                Date fecha = null;

                String fechaString = fechaButton.getText().toString();
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
                try {
                    fecha = sdf.parse(fechaString);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                Evento evento = new Evento(titulo, descripcion, fecha);
                eventos.add(evento);
                eventAdapter.notifyDataSetChanged();
            }
        });

        builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });

        AlertDialog dialog = builder.create();
        dialog.show();
    }

    private void showDatePicker() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(requireContext(), new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                Calendar selectedCalendar = Calendar.getInstance();
                selectedCalendar.set(year, month, day);
                selectedCalendar.set(Calendar.HOUR_OF_DAY, 0);
                selectedCalendar.set(Calendar.MINUTE, 0);
                selectedCalendar.set(Calendar.SECOND, 0);
                selectedCalendar.set(Calendar.MILLISECOND, 0);

                selectedDate = selectedCalendar.getTime();

                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());
                fechaButton.setText(dateFormat.format(selectedDate));
            }
        }, year, month, day);

        datePickerDialog.show();
    }

    private void agregarEvento(Evento evento) {
        List<Evento> eventos = ((EventAdapter) recyclerView.getAdapter()).getEvents();
        eventos.add(evento);
        recyclerView.getAdapter().notifyDataSetChanged();
    }
    public void setupCalendar(CalendarView calendarView, RecyclerView recyclerView, List<Evento> events) {
        Calendar initialDate = Calendar.getInstance();
        int year = initialDate.get(Calendar.YEAR);
        int month = initialDate.get(Calendar.MONTH);
        int day = initialDate.get(Calendar.DAY_OF_MONTH);

        initialDate.set(year, month, day);
        calendarView.setDate(initialDate.getTimeInMillis());

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);

        EventosOnDateChangeListener eventsOnDateChangeListener = new EventosOnDateChangeListener(recyclerView, events);
        recyclerView.setAdapter(new EventAdapter(recyclerView.getContext(), events));
        calendarView.setOnDateChangeListener(eventsOnDateChangeListener);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}

class EventAdapter extends RecyclerView.Adapter<EventAdapter.ViewHolder> {
    private List<Evento> events;
    private Context context;

    public EventAdapter(Context context, List<Evento> events) {
        this.context = context;
        this.events = events;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_evento, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Evento event = events.get(position);
        holder.eventTitle.setText(event.getTitulo());
        holder.eventDescription.setText(event.getDescripcion());
        holder.eventDate.setText(event.getFecha().toString());
    }

    @Override
    public int getItemCount() {
        return events.size();
    }

    public List<Evento> getEvents(){return events;}

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView eventTitle;
        TextView eventDate;
        TextView eventDescription;

        public ViewHolder(View itemView) {
            super(itemView);
            eventTitle = itemView.findViewById(R.id.tituloTextView);
            eventDescription = itemView.findViewById(R.id.descripcionTextView);
            eventDate = itemView.findViewById(R.id.fechaTextView);
        }
    }
}

class EventosOnDateChangeListener implements CalendarView.OnDateChangeListener {
    private RecyclerView recyclerView;
    private List<Evento> events;

    public EventosOnDateChangeListener(RecyclerView recyclerView, List<Evento> events) {
        this.recyclerView = recyclerView;
        this.events = events;
    }

    @Override
    public void onSelectedDayChange(@NonNull CalendarView view, int
            year, int month, int dayOfMonth) {
        Calendar selectedDate = Calendar.getInstance();
        selectedDate.set(year, month, dayOfMonth);
        selectedDate.set(Calendar.HOUR_OF_DAY, 0);
        selectedDate.set(Calendar.MINUTE, 0);
        selectedDate.set(Calendar.SECOND, 0);
        selectedDate.set(Calendar.MILLISECOND, 0);

        List<Evento> filteredEvents = new ArrayList<>();
        for (Evento event : events) {
            Calendar eventDate = Calendar.getInstance();
            eventDate.setTime(event.getFecha());
            if (eventDate.get(Calendar.DAY_OF_YEAR) == selectedDate.get(Calendar.DAY_OF_YEAR)) {
                filteredEvents.add(event);
            }
        }

        recyclerView.setAdapter(new EventAdapter(recyclerView.getContext(), filteredEvents));
    }
}


