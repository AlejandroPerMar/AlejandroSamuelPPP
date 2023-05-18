package es.iespuertodelacruz.alejandrosamuel.studycircle.data.enums;

public enum RespuestasRegister {
    INVALID_NAME ("INVALID_NAME", "Nombre no válido"),
    NOT_AVAILABLE_USERNAME ("NOT_AVAILABLE_USERNAME", "Nombre de usuario no disponible"),
    INVALID_EMAIL ("INVALID_EMAIL", "Correo electrónico no válido"),
    NOT_MINIMUN_REQUIREMENTS_PASSWORD ("NOT_MINIMUN_REQUIREMENTS_PASSWORD", "La contraseña no cumple los requerimientos básicos"),
    NOT_AVAILABLE_EMAIL("NOT_AVAILABLE_EMAIL", "Ya existe una cuenta registrada con esta dirección");
    private final String name;
    private final String descripcion;

    RespuestasRegister(String name, String descripcion) {
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
