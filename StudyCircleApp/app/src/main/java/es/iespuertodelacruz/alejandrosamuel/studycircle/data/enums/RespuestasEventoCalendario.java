package es.iespuertodelacruz.alejandrosamuel.studycircle.data.enums;

public enum RespuestasEventoCalendario {
    INVALID_USER_PROFILE("INVALID_USER_PROFILE", "Perfil de usuario inválido"),
    CALENDAR_EVENT_DTO_NOT_VALID("CALENDAR_EVENT_DTO_NOT_VALID", "EventoCalendarioDTO no válido");

    private final String name;
    private final String descripcion;


    RespuestasEventoCalendario(String name, String descripcion) {
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
        return "RespuestasEventoCalendario{" +
                "name='" + name + '\'' +
                ", descripcion='" + descripcion + '\'' +
                '}';
    }

}
