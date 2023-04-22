package es.iespuertodelacruz.alejandrosamuel.studycircle.data.db.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;


@Entity(tableName = MateriaTutorEntity.TABLE_NAME,
        foreignKeys = {
            @ForeignKey(entity = MateriaEntity.class,
                parentColumns = MateriaEntity.ID,
                childColumns = MateriaTutorEntity.ID_MATERIA
            ),
            @ForeignKey(entity = TutorEntity.class,
                parentColumns = TutorEntity.ID,
                childColumns = MateriaTutorEntity.ID_TUTOR
        )}
)
public class MateriaTutorEntity {
    public static final String TABLE_NAME = "materia_tutor";
    public static final String ID = "id";
    public static final String ID_MATERIA = "id_materia";
    public static final String ID_TUTOR = "id_tutor";

    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = ID_MATERIA, index = true)
    private Integer idMateria;
    @ColumnInfo(name = ID_TUTOR, index = true)
    private Integer idTutor;

    public MateriaTutorEntity() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getIdMateria() {
        return idMateria;
    }

    public void setIdMateria(Integer idMateria) {
        this.idMateria = idMateria;
    }

    public Integer getIdTutor() {
        return idTutor;
    }

    public void setIdTutor(Integer idTutor) {
        this.idTutor = idTutor;
    }
}
