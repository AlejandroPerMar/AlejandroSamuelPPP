package es.iespuertodelacruz.alejandrosamuel.studycircle.decorators;

import android.graphics.Color;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.DayViewDecorator;
import com.prolificinteractive.materialcalendarview.DayViewFacade;
import com.prolificinteractive.materialcalendarview.spans.DotSpan;

import org.threeten.bp.LocalDate;


public class EventDecorator implements DayViewDecorator {

    private CalendarDay fecha;

    public EventDecorator(LocalDate fecha) {
        this.fecha = CalendarDay.from(fecha);
    }

    @Override
    public boolean shouldDecorate(CalendarDay day) {
        return day.equals(this.fecha);
    }

    @Override
    public void decorate(DayViewFacade view) {
        view.addSpan(new DotSpan(5, Color.RED)); // Añade un punto rojo para los días con eventos
    }
}
