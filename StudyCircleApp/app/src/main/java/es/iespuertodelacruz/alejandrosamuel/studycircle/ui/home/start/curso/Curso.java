package es.iespuertodelacruz.alejandrosamuel.studycircle.ui.home.start.curso;

import android.database.CursorIndexOutOfBoundsException;

import java.util.List;

import es.iespuertodelacruz.alejandrosamuel.studycircle.ui.home.start.alumno.Alumno;

public class Curso {
    private String nombre;
    private String materia;
    private String tutor;
    private List<String> actividades;

    private List<Alumno> alumnos;

    public Curso (){ }

    public Curso(String nombre, String materia, String tutor, List<String> actividades, List<Alumno> listaAlumnos) {
        this.nombre = nombre;
        this.materia = materia;
        this.tutor = tutor;
        this.actividades = actividades;
        this.alumnos = listaAlumnos;
    }


    public String getNombre() {
        return nombre;
    }


    public String getMateria() {
        return materia;
    }

    public String getTutor() {
        return tutor;
    }

    public List<String> getActividades() {
        return actividades;
    }

    public List<Alumno> getAlumnos() { return alumnos;}

    public void setNombre(String nombre) { this.nombre = nombre;  }

    public void setMateria(String materia) { this.materia = materia;  }

    public void setTutor(String tutor) { this.tutor = tutor; }

    public void setActividades(List<String> actividades) { this.actividades = actividades; }

    public void setAlumnos(List<Alumno> alumnos) { this.alumnos = alumnos; }
}
