package es.iespuertodelacruz.alejandrosamuel.studycircle.data.enums;

public enum RespuestasActividad {

    ACTIVITY_NOT_FOUND("ACTIVITY_NOT_FOUND", "Actividad no encontrada"),
    ACTIVITY_REMOVED("ACTIVITY_REMOVED", "Actividad eliminada correctamente"),
    ACTIVITY_NOT_REMOVED("ACTIVITY_NOT_REMOVED", "Actividad no eliminada"),
    ACTIVITY_NOT_UPDATED("ACTIVITY_NOT_UPDATED", "Actualización no realizada"),
    ACTIVITY_FORBIDDEN("ACTIVITY_FORBIDDEN", "Actividad no disponible"),
    COURSE_ACTIVITY_NOT_VALID("COURSE_ACTIVITY_NOT_VALID", "Curso indicado no válido"),
    TUTOR_PROFILE_NOT_CREATED("TUTOR_PROFILE_NOT_CREATED", "Perfil de tutor no creado para el usuario"),
    INVALID_ACTIVITY_FORMAT("INVALID_ACTIVITY_FORMAT", "Valores de actividad no válido"),
    STUDENT_PROFILE_NOT_CREATED("STUDENT_PROFILE_NOT_CREATED", "Perfil de estudiante no creado para el usuario");

    private final String name;
    private final String descripcion;

    RespuestasActividad(String name, String descripcion) {
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
