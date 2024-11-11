package es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.enums;

public enum PerfilUsuario {

    TUTOR_PROFILE("TUTOR_PROFILE", "Perfil de usuario tutor"),
    STUDENT_PROFILE("STUDENT_PROFILE", "Perfil de usuario alumno");

    private final String perfilUsuario;
    private final String descripcion;

    PerfilUsuario(String perfilUsuario, String descripcion) {
        this.perfilUsuario = perfilUsuario;
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getPerfilUsuario() {
        return perfilUsuario;
    }
}
