package es.iespuertodelacruz.alejandrosamuel.studycircle.data.enums;

public enum RespuestasUsuario {

    USER_OR_NOMBRE_COMPLETO_NOT_VALID("USER_OR_NOMBRE_COMPLETO_NOT_VALID", ""),
    USER_OR_USERNAME_NOT_VALID("USER_OR_USERNAME_NOT_VALID", "");

    private final String name;
    private final String descripcion;

    RespuestasUsuario(String name, String descripcion) {
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
