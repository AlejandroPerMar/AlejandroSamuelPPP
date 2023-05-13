package es.iespuertodelacruz.alejandrosamuel.studycircle.data.enums;

public enum RespuestasAmistad {
    ALREADY_EXISTING_FRIENDSHIP("ALREADY_EXISTING_FRIENDSHIP", "Amistad ya existente"),
    FORBIDDEN_FRIENDSHIP_FOR_USER("FORBIDDEN_FRIENDSHIP_FOR_USER", "Amistad prohibida para el usuario"),
    NON_EXISTING_USER1_OR_USER2("NON_EXISTING_USER1_OR_USER2", "Usuario1 y/o Usuario2 no existentes"),
    INVALID_FRIENDSHIP_FORMAT("INVALID_FRIENDSHIP_FORMAT", "Formato inválido de amistad"),
    REMOVED_FRIENDSHIP("REMOVED_FRIENDSHIP", "Amistad eliminada"),
    ALREADY_ACCEPTED_FRIENDSHIP("ALREADY_ACCEPTED_FRIENDSHIP", "Amistad ya aceptada");

    private final String name;
    private final String descripcion;

    RespuestasAmistad(String name, String descripcion) {
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
