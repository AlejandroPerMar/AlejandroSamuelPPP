package es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.entity;

import javax.persistence.*;
import java.math.BigInteger;

@Entity
@Table(name="course_student_alerts")
@NamedQuery(name="AlertaCursoAlumnoEntity.findAll", query="SELECT a FROM AlertaCursoAlumnoEntity a")
public class AlertaCursoAlumnoEntity {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;

    @Column(name="created_at")
    private BigInteger fechaCreacion;

    @Column(name="status")
    private String estado;

    //uni-directional many-to-one association to CursoEntity
    @ManyToOne
    @JoinColumn(name="id_course")
    private CursoEntity curso;

    //uni-directional many-to-one association to UsuarioEntity
    @ManyToOne
    @JoinColumn(name="id_user")
    private UsuarioEntity usuario;

    public AlertaCursoAlumnoEntity() {
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

    public UsuarioEntity getUsuario() {
        return this.usuario;
    }

    public void setUsuario(UsuarioEntity usuario) {
        this.usuario = usuario;
    }

    public CursoEntity getCurso() {
        return curso;
    }

    public void setCurso(CursoEntity curso) {
        this.curso = curso;
    }
}
