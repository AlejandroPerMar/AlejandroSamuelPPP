package es.iespuertodelacruz.alejandrosamuel.studycircle.domain.port.primary;

public interface IHTMLBuilder {
    String buildEmail(String name, String link);

    String buildConfirmationPage();
}
