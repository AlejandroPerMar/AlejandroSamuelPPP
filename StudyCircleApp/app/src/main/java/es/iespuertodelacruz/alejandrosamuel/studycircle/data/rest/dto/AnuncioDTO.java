package es.iespuertodelacruz.alejandrosamuel.studycircle.data.rest.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AnuncioDTO {

    @SerializedName("id")
    @Expose
    private Integer id;

    @SerializedName("descripcion")
    @Expose
    private String descripcion;

    @SerializedName("motivo")
    @Expose
    private String motivo;

    @SerializedName("titulo")
    @Expose
    private String titulo;

    @SerializedName("materia")
    @Expose
    private MateriaDTO materia;

    @SerializedName("usuario")
    @Expose
    private UsuarioDTO usuario;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public MateriaDTO getMateria() {
        return materia;
    }

    public void setMateria(MateriaDTO materia) {
        this.materia = materia;
    }

    public UsuarioDTO getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioDTO usuario) {
        this.usuario = usuario;
    }

    @Override
    public String toString() {
        return "AnuncioDTO{" +
                "id=" + id +
                ", descripcion='" + descripcion + '\'' +
                ", motivo='" + motivo + '\'' +
                ", titulo='" + titulo + '\'' +
                ", materia=" + materia +
                ", usuario=" + usuario +
                '}';
    }
}
