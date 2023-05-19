package es.iespuertodelacruz.alejandrosamuel.studycircle.data.enums;

public enum RespuestasAuthToken {
    INVALID_USERNAME_OR_PASSWORD("INVALID_USERNAME_OR_PASSWORD", "Nombre de usuario o contraseña incorrectos");

    private final String name;
    private final String descripcion;


    RespuestasAuthToken(String name, String descripcion) {
        this.name = name;
        this.descripcion = descripcion;
    }

    public String getName() {
        return name;
    }

    public String getDescripcion() {
        return descripcion;
    }

    @Override
    public String toString() {
        return "RespuestasAuthToken{" +
                "name='" + name + '\'' +
                ", descripcion='" + descripcion + '\'' +
                '}';
    }
}
