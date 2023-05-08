package es.iespuertodelacruz.alejandrosamuel.studycircle.data.rest.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CursoDTO {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("titulo")
    @Expose
    private String titulo;
    @SerializedName("materiaTutor")
    @Expose
    private MateriaTutorDTO materiaTutor;
    @SerializedName("actividades")
    @Expose
    private List<ActividadDTO> actividades;
    @SerializedName("alumnos")
    @Expose
    private List<AlumnoDTO> alumnos;

    public CursoDTO() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<AlumnoDTO> getAlumnos() {
        return alumnos;
    }

    public void setAlumnos(List<AlumnoDTO> alumnos) {
        this.alumnos = alumnos;
    }

    public List<ActividadDTO> getActividades() {
        return actividades;
    }

    public void setActividades(List<ActividadDTO> actividades) {
        this.actividades = actividades;
    }

    public MateriaTutorDTO getMateriaTutor() {
        return materiaTutor;
    }

    public void setMateriaTutor(MateriaTutorDTO materiaTutor) {
        this.materiaTutor = materiaTutor;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
}
