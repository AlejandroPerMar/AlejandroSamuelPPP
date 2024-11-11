package es.iespuertodelacruz.alejandrosamuel.studycircle.data.rest.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.math.BigInteger;

public class AlertaCursoAlumnoDTO {

    @SerializedName("id")
    @Expose
    private Integer id;

    @SerializedName("estado")
    @Expose
    private String estado;

    @SerializedName("curso")
    @Expose
    private CursoDTO curso;

    @SerializedName("usuario")
    @Expose
    private UsuarioDTO usuario;

    public AlertaCursoAlumnoDTO() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public CursoDTO getCurso() {
        return curso;
    }

    public void setCurso(CursoDTO curso) {
        this.curso = curso;
    }

    public UsuarioDTO getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioDTO usuario) {
        this.usuario = usuario;
    }
}
