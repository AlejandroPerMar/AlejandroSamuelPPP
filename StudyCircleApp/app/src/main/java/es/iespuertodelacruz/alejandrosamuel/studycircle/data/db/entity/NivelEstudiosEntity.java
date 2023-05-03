package es.iespuertodelacruz.alejandrosamuel.studycircle.data.db.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;


@Entity(tableName = NivelEstudiosEntity.TABLE_NAME)
public class NivelEstudiosEntity {
    public static final String TABLE_NAME = "nivelEstudios";
    public static final String ID = "id";

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String nombre;

    public NivelEstudiosEntity() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
