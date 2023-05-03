package es.iespuertodelacruz.alejandrosamuel.studycircle.data.db.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import java.math.BigInteger;

@Entity(tableName = TutorEntity.TABLE_NAME,
        foreignKeys = @ForeignKey(entity = UsuarioEntity.class,
                parentColumns = UsuarioEntity.ID,
                childColumns = TutorEntity.ID_USUARIO
        )
)
public class TutorEntity {
    public static final String TABLE_NAME = "tutores";
    public static final String ID = "id";
    public static final String ID_USUARIO = "id_usuario";

    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = ID_USUARIO, index = true)
    private Integer idUsuario;

    public TutorEntity() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }
}
