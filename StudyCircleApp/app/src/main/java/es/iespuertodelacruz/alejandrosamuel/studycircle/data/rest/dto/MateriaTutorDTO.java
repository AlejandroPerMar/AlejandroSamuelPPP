package es.iespuertodelacruz.alejandrosamuel.studycircle.data.rest.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class MateriaTutorDTO {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("materia")
    @Expose
    private MateriaDTO materia;
    @SerializedName("tutor")
    @Expose
    private TutorDTO tutor;
    @SerializedName("cursosTutor")
    @Expose
    private List<CursoDTO> cursosTutor;

    public MateriaTutorDTO() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public MateriaDTO getMateria() {
        return materia;
    }

    public void setMateria(MateriaDTO materia) {
        this.materia = materia;
    }

    public TutorDTO getTutor() {
        return tutor;
    }

    public void setTutor(TutorDTO tutor) {
        this.tutor = tutor;
    }

    public List<CursoDTO> getCursosTutor() {
        return cursosTutor;
    }

    public void setCursosTutor(List<CursoDTO> cursosTutor) {
        this.cursosTutor = cursosTutor;
    }
}
