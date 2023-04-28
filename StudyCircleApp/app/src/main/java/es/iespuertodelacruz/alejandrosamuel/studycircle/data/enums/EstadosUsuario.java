package es.iespuertodelacruz.alejandrosamuel.studycircle.data.enums;

public enum EstadosUsuario {
    STATUS_ACTIVE ("STATUS_ACTIVE", "Estado activo"),
    STATUS_INACTIVE ("STATUS_INACTIVE", "Estado inactivo"),
    STATUS_BAN ("STATUS_BAN", "Estado baneado"),
    STATUS_PENDING_VERIFICATION ("STATUS_PENDING_VERIFICATION", "Estado pendiente de verificación");

    private final String name;
    private final String descripcion;

    EstadosUsuario(String name, String descripcion) {
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
