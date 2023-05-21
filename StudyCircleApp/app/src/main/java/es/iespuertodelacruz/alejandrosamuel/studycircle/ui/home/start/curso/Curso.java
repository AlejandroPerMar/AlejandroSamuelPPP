package es.iespuertodelacruz.alejandrosamuel.studycircle.ui.home.start.curso;

import java.util.List;

import es.iespuertodelacruz.alejandrosamuel.studycircle.ui.home.start.alumno.Alumno;

public class Curso {
    private String nombre;
    private String materia;
    private String tutor;
    private List<String> actividades;

    public Curso(String nombre, String materia, String tutor, List<String> actividades, List<Alumno> listaAlumnos) {
        this.nombre = nombre;
        this.materia = materia;
        this.tutor = tutor;
        this.actividades = actividades;
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
}
