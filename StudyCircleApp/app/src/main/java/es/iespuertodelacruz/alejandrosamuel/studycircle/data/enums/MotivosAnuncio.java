package es.iespuertodelacruz.alejandrosamuel.studycircle.data.enums;

public enum MotivosAnuncio {

    SEARCHING_TUTOR("SEARCHING_TUTOR", "En búsqueda de un tutor"),
    SEARCHING_STUDENTS("SEARCHING_STUDENTS", "En búsqueda de alumnos para tutorizar");

    private final String name;
    private final String descripcion;

    MotivosAnuncio(String name, String descripcion) {
        this.name = name;
        this.descripcion = descripcion;
    }

    public String getName() {
        return name;
    }

    public String getDescripcion() {
        return descripcion;
    }
}
