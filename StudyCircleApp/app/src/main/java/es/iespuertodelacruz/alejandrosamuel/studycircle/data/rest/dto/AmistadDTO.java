package es.iespuertodelacruz.alejandrosamuel.studycircle.data.rest.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AmistadDTO {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("usuario1")
    @Expose
    private UsuarioDTO usuario1;
    @SerializedName(("estado"))
    @Expose
    private String estado;
    @SerializedName("usuario2")
    @Expose
    private UsuarioDTO usuario2;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public UsuarioDTO getUsuario1() {
        return usuario1;
    }

    public void setUsuario1(UsuarioDTO usuario1) {
        this.usuario1 = usuario1;
    }

    public UsuarioDTO getUsuario2() {
        return usuario2;
    }

    public void setUsuario2(UsuarioDTO usuario2) {
        this.usuario2 = usuario2;
    }

    @Override
    public String toString() {
        return "AmistadDTO{" +
                "id=" + id +
                ", usuario1=" + usuario1 +
                ", usuario2=" + usuario2 +
                '}';
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
