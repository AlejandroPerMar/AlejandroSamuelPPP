package es.iespuertodelacruz.alejandrosamuel.studycircle.data.rest.dto;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Date;

import es.iespuertodelacruz.alejandrosamuel.studycircle.data.db.entity.ActividadEntity;
import es.iespuertodelacruz.alejandrosamuel.studycircle.data.db.entity.UsuarioEntity;

public class EventoCalendarioDTO {

    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("id")
    @Expose
    private String descripcion;
    @SerializedName("id")
    @Expose
    private String nombre;
    @SerializedName("id")
    @Expose
    private Date fechaEvento;
    @SerializedName("id")
    @Expose
    private Integer idActividad;
    @SerializedName("id")
    @Expose
    private Integer idUsuario;

    public EventoCalendarioDTO() {
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

    public Date getFechaEvento() {
        return fechaEvento;
    }

    public void setFechaEvento(Date fechaEvento) {
        this.fechaEvento = fechaEvento;
    }

    public Integer getIdActividad() {
        return idActividad;
    }

    public void setIdActividad(Integer idActividad) {
        this.idActividad = idActividad;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }
}
