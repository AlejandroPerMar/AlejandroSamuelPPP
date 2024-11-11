package es.iespuertodelacruz.alejandrosamuel.studycircle.data.rest.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class AlumnoDTO {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("materias")
    @Expose
    private List<MateriaDTO> materias;
    @SerializedName("nivelEstudios")
    @Expose
    private NivelEstudiosDTO nivelEstudios;
    @SerializedName("usuario")
    @Expose
    private UsuarioDTO usuario;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<MateriaDTO> getMaterias() {
        return materias;
    }

    public void setMaterias(List<MateriaDTO> materias) {
        this.materias = materias;
    }

    public NivelEstudiosDTO getNivelEstudios() {
        return nivelEstudios;
    }

    public void setNivelEstudios(NivelEstudiosDTO nivelEstudios) {
        this.nivelEstudios = nivelEstudios;
    }

    public UsuarioDTO getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioDTO usuario) {
        this.usuario = usuario;
    }

    @Override
    public String toString() {
        return "AlumnoDTO{" +
                "id=" + id +
                ", materias=" + materias +
                ", nivelEstudios=" + nivelEstudios +
                ", usuario=" + usuario +
                '}';
    }
}
