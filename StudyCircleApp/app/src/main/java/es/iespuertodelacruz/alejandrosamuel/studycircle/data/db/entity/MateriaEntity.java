package es.iespuertodelacruz.alejandrosamuel.studycircle.data.db.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = MateriaEntity.TABLE_NAME,
        foreignKeys = @ForeignKey(entity = NivelEstudiosEntity.class,
                parentColumns = NivelEstudiosEntity.ID,
                childColumns = MateriaEntity.ID_NIVEL_ESTUDIOS
        )
)
public class MateriaEntity {
    public static final String TABLE_NAME = "materias";
    public static final String ID = "id";
    public static final String ID_NIVEL_ESTUDIOS = "id_nivel_estudios";

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String nombre;
    @ColumnInfo(name = ID_NIVEL_ESTUDIOS, index = true)
    private Integer idNivelEstudios;

    public MateriaEntity() {
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

    public Integer getIdNivelEstudios() {
        return idNivelEstudios;
    }

    public void setIdNivelEstudios(Integer idNivelEstudios) {
        this.idNivelEstudios = idNivelEstudios;
    }
}
