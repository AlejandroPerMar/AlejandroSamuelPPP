package es.iespuertodelacruz.alejandrosamuel.studycircle.data.db.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = ActividadCursoEntity.TABLE_NAME,
        foreignKeys = {
                @ForeignKey(entity = ActividadEntity.class,
                        parentColumns = ActividadEntity.ID,
                        childColumns = ActividadCursoEntity.ID_ACTIVIDAD
                ),
                @ForeignKey(entity = CursoEntity.class,
                        parentColumns = CursoEntity.ID,
                        childColumns = ActividadCursoEntity.ID_CURSO
                )
        }
)
public class ActividadCursoEntity {
    public static final String TABLE_NAME = "actividad_curso";
    public static final String ID = "id";
    public static final String ID_ACTIVIDAD = "id_actividad";
    public static final String ID_CURSO = "id_curso";

    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = ID_ACTIVIDAD, index = true)
    private Integer idActividad;
    @ColumnInfo(name = ID_CURSO, index = true)
    private Integer idCurso;

    public ActividadCursoEntity() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getIdActividad() {
        return idActividad;
    }

    public void setIdActividad(Integer idActividad) {
        this.idActividad = idActividad;
    }

    public Integer getIdCurso() {
        return idCurso;
    }

    public void setIdCurso(Integer idCurso) {
        this.idCurso = idCurso;
    }
}
