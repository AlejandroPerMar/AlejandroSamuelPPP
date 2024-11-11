package es.iespuertodelacruz.alejandrosamuel.studycircle.data.rest.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.math.BigInteger;
import java.util.List;

public class TutorDTO {

    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("materiasTutor")
    @Expose
    private List<MateriaTutorDTO> materiasTutor;
    @SerializedName("usuario")
    @Expose
    private UsuarioDTO usuario;

    public TutorDTO() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<MateriaTutorDTO> getMateriasTutor() {
        return materiasTutor;
    }

    public void setMateriasTutor(List<MateriaTutorDTO> materiasTutor) {
        this.materiasTutor = materiasTutor;
    }

    public UsuarioDTO getUsuario() {
        return usuario;
    }

    public void setUsuario(UsuarioDTO usuario) {
        this.usuario = usuario;
    }
}
