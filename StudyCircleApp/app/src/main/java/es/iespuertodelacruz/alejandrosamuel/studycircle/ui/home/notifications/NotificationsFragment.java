package es.iespuertodelacruz.alejandrosamuel.studycircle.ui.home.notifications;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CalendarView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;



import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

import es.iespuertodelacruz.alejandrosamuel.studycircle.R;
import es.iespuertodelacruz.alejandrosamuel.studycircle.databinding.FragmentNotificationsBinding;


public class NotificationsFragment extends Fragment {

    private FragmentNotificationsBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentNotificationsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        CalendarView calendarView = binding.calendarView;
        RecyclerView recyclerView = binding.recyclerView;

        List<Evento> events = new ArrayList<>();
        events.add(new Evento("Evento 1", "Descripción del evento 1", new Date()));
        events.add(new Evento("Evento 2", "Descripción del evento 2", new Date(new Date().getTime() + 86400000)));
        events.add(new Evento("Evento 3", "Descripción del evento 3", new Date(new Date().getTime() + 172800000)));

        setupCalendar(calendarView, recyclerView, events);

        return root;
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


