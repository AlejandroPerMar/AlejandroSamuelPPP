package es.iespuertodelacruz.alejandrosamuel.studycircle.data.rest.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.math.BigInteger;

public class AlertaActividadDTO {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("fechaCreacion")
    @Expose
    private BigInteger fechaCreacion;
    @SerializedName("estado")
    @Expose
    private String estado;
    @SerializedName("actividad")
    @Expose
    private ActividadDTO actividad;
    @SerializedName("usuario")
    @Expose
    private UsuarioDTO usuario;

    public AlertaActividadDTO() {
    }

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

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public UsuarioDTO getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioDTO usuario) {
        this.usuario = usuario;
    }

    public ActividadDTO getActividad() {
        return actividad;
    }

    public void setActividad(ActividadDTO actividad) {
        this.actividad = actividad;
    }

}
