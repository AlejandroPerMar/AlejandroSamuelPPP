package es.iespuertodelacruz.alejandrosamuel.studycircle.data.enums;

public enum RespuestasAnuncio {
    NON_EXISTING_ANNOUNCEMENT("NON_EXISTING_ANNOUNCEMENT", ""),
    NON_EXISTING_ANNOUNCEMENTS("NON_EXISTING_ANNOUNCEMENTS", ""),
    INVALID_DTO_ANNOUNCEMENT("INVALID_DTO_ANNOUNCEMENT", "");

    private final String name;
    private final String descripcion;

    RespuestasAnuncio(String name, String descripcion) {
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
