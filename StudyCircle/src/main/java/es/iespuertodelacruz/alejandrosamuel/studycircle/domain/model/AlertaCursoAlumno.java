package es.iespuertodelacruz.alejandrosamuel.studycircle.domain.model;


import javax.persistence.*;
import java.math.BigInteger;

public class AlertaCursoAlumno {
    private Integer id;
    private BigInteger fechaCreacion;
    private String estado;
    private Curso curso;
    private Usuario usuario;

    public AlertaCursoAlumno() {
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BigInteger getFechaCreacion() {
        return this.fechaCreacion;
    }

    public void setFechaCreacion(BigInteger fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public String getEstado() {
        return this.estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Usuario getUsuario() {
        return this.usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }
}
