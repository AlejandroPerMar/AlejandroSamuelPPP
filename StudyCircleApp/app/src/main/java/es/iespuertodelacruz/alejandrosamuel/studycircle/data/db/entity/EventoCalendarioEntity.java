package es.iespuertodelacruz.alejandrosamuel.studycircle.data.db.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import java.util.Date;

@Entity(tableName = EventoCalendarioEntity.TABLE_NAME,
        foreignKeys = {
                @ForeignKey(entity = ActividadEntity.class,
                        parentColumns = ActividadEntity.ID,
                        childColumns = EventoCalendarioEntity.ID_ACTIVIDAD
                ),
                @ForeignKey(entity = UsuarioEntity.class,
                        parentColumns = UsuarioEntity.ID,
                        childColumns = EventoCalendarioEntity.ID_USUARIO
                )
        }
)
public class EventoCalendarioEntity {

    public static final String TABLE_NAME = "eventos_calendario";
    public static final String ID = "id";
    public static final String ID_ACTIVIDAD = "id_actividad";
    public static final String ID_USUARIO = "id_usuario";

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String descripcion;
    private String nombre;
    private Date fechaEvento;
    @ColumnInfo(name = ID_ACTIVIDAD, index = true)
    private Integer idActividad;
    @ColumnInfo(name = ID_USUARIO, index = true)
    private Integer idUsuario;

    public EventoCalendarioEntity() {
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

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Date getFechaEvento() {
        return fechaEvento;
    }

    public void setFechaEvento(Date fechaEvento) {
        this.fechaEvento = fechaEvento;
    }

    public Integer getIdActividad() {
        return idActividad;
    }

    public void setIdActividad(Integer idActividad) {
        this.idActividad = idActividad;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }
}
