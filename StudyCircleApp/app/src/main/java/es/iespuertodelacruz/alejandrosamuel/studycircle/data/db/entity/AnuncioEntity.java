package es.iespuertodelacruz.alejandrosamuel.studycircle.data.db.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = AnuncioEntity.TABLE_NAME,
        foreignKeys = {
                @ForeignKey(entity = MateriaEntity.class,
                        parentColumns = MateriaEntity.ID,
                        childColumns = AnuncioEntity.ID_MATERIA
                ),
                @ForeignKey(entity = UsuarioEntity.class,
                        parentColumns = UsuarioEntity.ID,
                        childColumns = AnuncioEntity.ID_USUARIO
                )
        }
)
public class AnuncioEntity {

    public static final String TABLE_NAME = "anuncios";
    public static final String ID = "id";
    public static final String ID_USUARIO = "id_usuario";
    public static final String ID_MATERIA = "id_materia";

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String descripcion;
    private String motivo;
    private String titulo;
    @ColumnInfo(name = ID_MATERIA, index = true)
    private Integer idMateria;
    @ColumnInfo(name = ID_USUARIO, index = true)
    private Integer idUsuario;

    public AnuncioEntity() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Integer getIdMateria() {
        return idMateria;
    }

    public void setIdMateria(Integer idMateria) {
        this.idMateria = idMateria;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }
}
