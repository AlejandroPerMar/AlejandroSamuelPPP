package es.iespuertodelacruz.alejandrosamuel.studycircle.data.enums;

public enum RespuestasCursos {
    COURSE_DTO_NOT_VALID("COURSE_DTO_NOT_VALID", "Formato de curso no válido"),
    STUDENT_PROFILE_NOT_CREATED("STUDENT_PROFILE_NOT_CREATED", "No existe perfil de alumno para este usuario"),
    TUTOR_PROFILE_NOT_CREATED("TUTOR_PROFILE_NOT_CREATED", "No existe perfil de tutor para este usuario"),
    INVALID_PARAMETERS("INVALID_PARAMETERS", "Parámetros inválidos"),
    NON_EXISTING_COURSE_OR_STUDENT("NON_EXISTING_COURSE_OR_STUDENT", "Curso o alumno no existente"),
    NON_AUTHENTICATED_OWNER("NON_AUTHENTICATED_OWNER", "El curso no pertenece al usuario"),
    NON_EXISTING_COURSE("NON_EXISTING_COURSE", "Curso no existente"),
    STUDENT_OR_COURSE_NOT_FOUND("STUDENT_OR_COURSE_NOT_FOUND", "Alumno o curso no encontrados"),
    STUDENT_INVITED("STUDENT_INVITED", "Se ha invitado con éxito al alumno"),
    STUDENT_NOT_FOUND_IN_CONTACTS("STUDENT_NOT_FOUND_IN_CONTACTS", "Alumno no se encuentra en la lista de contactos del usuario"),
    ALERT_NOT_FOUND("ALERT_NOT_FOUND", "No se ha encontrado la alerta"),
    STUDENT_OR_INVITATION_NOT_FOUND("STUDENT_OR_INVITATION_NOT_FOUND", ""),
    INVITATION_REMOVED("INVITATION_REMOVED", ""),
    COURSE_REMOVED("INVITATION_REMOVED", ""),
    USER_ALREADY_IN_COURSE_OR_INVITED("USER_ALREADY_IN_COURSE_OR_INVITED", "");


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
