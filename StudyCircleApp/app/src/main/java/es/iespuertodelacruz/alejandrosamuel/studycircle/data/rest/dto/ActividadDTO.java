package es.iespuertodelacruz.alejandrosamuel.studycircle.data.rest.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.math.BigInteger;

public class ActividadDTO {
    @SerializedName("id")
    @Expose
    private Integer id;

    @SerializedName("fechaCreacion")
    @Expose
    private BigInteger fechaCreacion;

    @SerializedName("descripcion")
    @Expose
    private String descripcion;

    @SerializedName("nombre")
    @Expose
    private String nombre;

    @SerializedName("fechaActividad")
    @Expose
    private BigInteger fechaActividad;

    @SerializedName("curso")
    @Expose
    private CursoDTO curso;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BigInteger getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(BigInteger fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
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

    public BigInteger getFechaActividad() {
        return fechaActividad;
    }

    public void setFechaActividad(BigInteger fechaActividad) {
        this.fechaActividad = fechaActividad;
    }

    public CursoDTO getCurso() {
        return curso;
    }

    public void setCurso(CursoDTO curso) {
        this.curso = curso;
    }

    @Override
    public String toString() {
        return "ActividadDTO{" +
                "id=" + id +
                ", fechaCreacion=" + fechaCreacion +
                ", descripcion='" + descripcion + '\'' +
                ", nombre='" + nombre + '\'' +
                ", fechaActividad=" + fechaActividad +
                ", curso=" + curso +
                '}';
    }
}
