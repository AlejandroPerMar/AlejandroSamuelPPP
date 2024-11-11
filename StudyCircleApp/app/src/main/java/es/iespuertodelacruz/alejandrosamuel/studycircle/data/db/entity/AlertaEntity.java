package es.iespuertodelacruz.alejandrosamuel.studycircle.data.db.entity;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = AlertaEntity.TABLE_NAME,
        foreignKeys = @ForeignKey(entity = UsuarioEntity.class,
                parentColumns = UsuarioEntity.ID,
                childColumns = AlertaEntity.ID_USUARIO
        )
)
public class AlertaEntity {
    public static final String TABLE_NAME = "alertas";
    public static final String ID = "id";
    public static final String ID_USUARIO = "id_usuario";

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String mensaje;
    private String estado;
    private String tipo;
    @ColumnInfo(name = ID_USUARIO, index = true)
    private Integer idUsuario;

    public AlertaEntity() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }
}
