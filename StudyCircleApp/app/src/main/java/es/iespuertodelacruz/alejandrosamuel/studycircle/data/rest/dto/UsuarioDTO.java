package es.iespuertodelacruz.alejandrosamuel.studycircle.data.rest.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class UsuarioDTO {
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("nombreCompleto")
    @Expose
    private String nombreCompleto;
    @SerializedName("username")
    @Expose
    private String username;

    public UsuarioDTO() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "UsuarioDTO{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", nombreCompleto='" + nombreCompleto + '\'' +
                ", username='" + username + '\'' +
                '}';
    }
}
