package es.iespuertodelacruz.alejandrosamuel.studycircle.data.rest.dto;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UsuarioLoginDTO {

    @SerializedName("clave")
    @Expose
    private String clave;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("username")
    @Expose
    private String username;

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

}