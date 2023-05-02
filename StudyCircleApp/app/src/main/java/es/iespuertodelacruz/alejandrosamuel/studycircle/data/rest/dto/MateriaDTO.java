package es.iespuertodelacruz.alejandrosamuel.studycircle.data.rest.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MateriaDTO {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("nivelEstudios")
    @Expose
    private NivelEstudiosDTO nivelEstudios;
    @SerializedName("nombre")
    @Expose
    private String nombre;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public NivelEstudiosDTO getNivelEstudios() {
        return nivelEstudios;
    }

    public void setNivelEstudios(NivelEstudiosDTO nivelEstudios) {
        this.nivelEstudios = nivelEstudios;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
