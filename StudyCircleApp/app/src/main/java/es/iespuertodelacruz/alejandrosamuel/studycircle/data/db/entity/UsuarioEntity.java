package es.iespuertodelacruz.alejandrosamuel.studycircle.data.db.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = UsuarioEntity.TABLE_NAME)
public class UsuarioEntity {
    public static final String TABLE_NAME = "usuarios";
    public static final String ID = "id";

    @PrimaryKey(autoGenerate = true)
    public Integer id;
    private String email;
    private String nombreCompleto;
    private String username;

    public UsuarioEntity() {
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
}
