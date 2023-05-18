package es.iespuertodelacruz.alejandrosamuel.studycircle.ui.anuncios.anuncio;

public class Anuncio {
        private String titulo;
        private String descripcion;
        private String autor;

        public Anuncio(String titulo, String descripcion, String autor) {
            this.titulo = titulo;
            this.descripcion = descripcion;
            this.autor = autor;
        }

        public String getTitulo() {
            return titulo;
        }

        public String getDescripcion() {
            return descripcion;
        }

        public String getAutor() {
            return autor;
        }
}


