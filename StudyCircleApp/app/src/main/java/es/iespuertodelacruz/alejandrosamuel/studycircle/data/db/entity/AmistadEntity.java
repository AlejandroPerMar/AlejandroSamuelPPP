package es.iespuertodelacruz.alejandrosamuel.studycircle.data.db.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import java.math.BigInteger;

@Entity(tableName = AmistadEntity.TABLE_NAME,
        foreignKeys = {
                @ForeignKey(entity = UsuarioEntity.class,
                        parentColumns = UsuarioEntity.ID,
                        childColumns = AmistadEntity.ID_USUARIO1
                ),
                @ForeignKey(entity = UsuarioEntity.class,
                        parentColumns = UsuarioEntity.ID,
                        childColumns = AmistadEntity.ID_USUARIO2
                )
        }
)
public class AmistadEntity {

    public static final String TABLE_NAME = "amistades";
    public static final String ID = "id";
    public static final String ID_USUARIO1 = "id_usuario1";
    public static final String ID_USUARIO2 = "id_usuario2";

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String estado;
    @ColumnInfo(name = ID_USUARIO1, index = true)
    private Integer idUsuario1;
    @ColumnInfo(name = ID_USUARIO2, index = true)
    private Integer idUsuario2;

    public AmistadEntity() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Integer getIdUsuario1() {
        return idUsuario1;
    }

    public void setIdUsuario1(Integer idUsuario1) {
        this.idUsuario1 = idUsuario1;
    }

    public Integer getIdUsuario2() {
        return idUsuario2;
    }

    public void setIdUsuario2(Integer idUsuario2) {
        this.idUsuario2 = idUsuario2;
    }
}
