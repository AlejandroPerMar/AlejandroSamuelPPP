package es.iespuertodelacruz.alejandrosamuel.studycircle.data.db.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;


@Entity(tableName = AlumnoEntity.TABLE_NAME,
        foreignKeys = {
                @ForeignKey(entity = NivelEstudiosEntity.class,
                        parentColumns = NivelEstudiosEntity.ID,
                        childColumns = AlumnoEntity.ID_NIVEL_ESTUDIOS
                ),
                @ForeignKey(entity = UsuarioEntity.class,
                        parentColumns = UsuarioEntity.ID,
                        childColumns = AlumnoEntity.ID_USUARIO
                )
        }
)
public class AlumnoEntity {
    public static final String TABLE_NAME = "alumnos";
    public static final String ID = "id";
    public static final String ID_USUARIO = "id_usuario";
    public static final String ID_NIVEL_ESTUDIOS = "id_nivel_estudios";

    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = ID_NIVEL_ESTUDIOS, index = true)
    private Integer idNivelEstudios;
    @ColumnInfo(name = ID_USUARIO, index = true)
    private Integer idUsuario;

    public AlumnoEntity() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getIdNivelEstudios() {
        return idNivelEstudios;
    }

    public void setIdNivelEstudios(Integer idNivelEstudios) {
        this.idNivelEstudios = idNivelEstudios;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }
}
