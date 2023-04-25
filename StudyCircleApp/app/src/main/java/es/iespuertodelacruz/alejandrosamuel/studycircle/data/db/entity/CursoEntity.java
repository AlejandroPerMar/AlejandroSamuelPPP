package es.iespuertodelacruz.alejandrosamuel.studycircle.data.db.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = CursoEntity.TABLE_NAME,
        foreignKeys = @ForeignKey(entity = MateriaTutorEntity.class,
                parentColumns = MateriaTutorEntity.ID,
                childColumns = CursoEntity.ID_MATERIA_TUTOR
        )
)
public class CursoEntity {
    public static final String TABLE_NAME = "cursos";
    public static final String ID = "id";
    public static final String ID_MATERIA_TUTOR = "id_materia_tutor";

    @PrimaryKey(autoGenerate = true)
    private int id;
    private Double precioHora;
    private String titulo;
    @ColumnInfo(name = ID_MATERIA_TUTOR, index = true)
    private Integer idMateriaTutor;

    public CursoEntity() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Double getPrecioHora() {
        return precioHora;
    }

    public void setPrecioHora(Double precioHora) {
        this.precioHora = precioHora;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Integer getIdMateriaTutor() {
        return idMateriaTutor;
    }

    public void setIdMateriaTutor(Integer idMateriaTutor) {
        this.idMateriaTutor = idMateriaTutor;
    }
}
