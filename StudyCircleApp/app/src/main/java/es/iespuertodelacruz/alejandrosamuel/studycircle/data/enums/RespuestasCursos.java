package es.iespuertodelacruz.alejandrosamuel.studycircle.data.enums;

public enum RespuestasCursos {
    COURSE_DTO_NOT_VALID("COURSE_DTO_NOT_VALID", "Formato de curso no válido"),
    STUDENT_PROFILE_NOT_CREATED("STUDENT_PROFILE_NOT_CREATED", "No existe perfil de alumno para este usuario"),
    TUTOR_PROFILE_NOT_CREATED("TUTOR_PROFILE_NOT_CREATED", "No existe perfil de tutor para este usuario"),
    INVALID_PARAMETERS("INVALID_PARAMETERS", "Parámetros inválidos"),
    NON_EXISTING_COURSE_OR_STUDENT("NON_EXISTING_COURSE_OR_STUDENT", "Curso o alumno no existente"),
    NON_AUTHENTICATED_OWNER("NON_AUTHENTICATED_OWNER", "El curso no pertenece al usuario");

    private final String name;
    private final String descripcion;

    RespuestasCursos(String name, String descripcion) {
        this.name = name;
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getName() {
        return name;
    }
}
