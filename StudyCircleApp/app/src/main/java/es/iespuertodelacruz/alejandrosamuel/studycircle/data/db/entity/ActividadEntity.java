package es.iespuertodelacruz.alejandrosamuel.studycircle.data.db.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity(tableName = ActividadEntity.TABLE_NAME)
public class ActividadEntity {

    public static final String TABLE_NAME = "actividades";
    public static final String ID = "id";

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String descripcion;
    private String nombre;
    private String estado;
    private Date fechaActividad;

    public ActividadEntity() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Date getFechaActividad() {
        return fechaActividad;
    }

    public void setFechaActividad(Date fechaActividad) {
        this.fechaActividad = fechaActividad;
    }
}
