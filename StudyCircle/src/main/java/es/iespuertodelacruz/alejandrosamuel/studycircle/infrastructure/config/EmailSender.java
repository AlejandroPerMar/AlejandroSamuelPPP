package es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.config;

public interface EmailSender {
    void enviar(String nombre, String email);
}
