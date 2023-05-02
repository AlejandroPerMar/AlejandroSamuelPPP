package es.iespuertodelacruz.alejandrosamuel.studycircle.data.enums;

public enum RespuestasProfileConf {
    STUDENT_PROFILE_NOT_FOUND("STUDENT_PROFILE_NOT_FOUND", "Perfil de alumno no encontrado"),
    STUDENT_PROFILE_ALREADY_CREATED("STUDENT_PROFILE_ALREADY_CREATED", "Perfil de alumno ya existente"),
    STUDENT_PROFILE_NOT_CREATED("STUDENT_PROFILE_NOT_CREATED", "Perfil de alumno no creado"),
    INVALID_STUDENT_FORMAT("INVALID_STUDENT_FORMAT", "Formato inválido de perfil de alumno"),
    STUDENT_PROFILE_NOT_UPDATED("STUDENT_PROFILE_NOT_UPDATED", "Perfil de alumno no actualizado"),

    TUTOR_PROFILE_ALREADY_CREATED("TUTOR_PROFILE_ALREADY_CREATED", "Perfil de tutor ya existente"),
    TUTOR_PROFILE_NOT_FOUND("TUTOR_PROFILE_NOT_FOUND", "Perfil de tutor no encontrado"),
    TUTOR_PROFILE_NOT_CREATED("TUTOR_PROFILE_NOT_CREATED", "Perfil de tutor no creado"),
    INVALID_TUTOR_FORMAT("INVALID_TUTOR_FORMAT", "Formato inválido de perfil de tutor"),
    TUTOR_PROFILE_NOT_UPDATED("TUTOR_PROFILE_NOT_UPDATED", "Perfil de tutor no actualizado");

    private final String name;
    private final String descripcion;

    RespuestasProfileConf(String name, String descripcion) {
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
