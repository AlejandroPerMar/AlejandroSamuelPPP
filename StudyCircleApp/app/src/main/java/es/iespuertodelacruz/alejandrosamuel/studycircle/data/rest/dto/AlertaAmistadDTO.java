package es.iespuertodelacruz.alejandrosamuel.studycircle.data.rest.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.math.BigInteger;

public class AlertaAmistadDTO {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("fechaCreacion")
    @Expose
    private BigInteger fechaCreacion;
    @SerializedName("estado")
    @Expose
    private String estado;
    @SerializedName("amistad")
    @Expose
    private AmistadDTO amistad;
    @SerializedName("usuario")
    @Expose
    private UsuarioDTO usuario;

    public AlertaAmistadDTO() {
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

    public AmistadDTO getAmistad() {
        return amistad;
    }

    public void setAmistad(AmistadDTO amistad) {
        this.amistad = amistad;
    }

}
