package es.iespuertodelacruz.alejandrosamuel.studycircle.ui.home.notifications;

import java.util.Date;

public class Evento {
    private String titulo;
    private String descripcion;
    private Date fecha;

    public Evento(String titulo, String descripcion, Date fecha) {
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.fecha = fecha;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public Date getFecha() {
        return fecha;
    }
}
