package es.iespuertodelacruz.alejandrosamuel.studycircle.data.rest.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.math.BigInteger;

public class ActividadDTO {
    @SerializedName("id")
    @Expose
    private Integer id;

    @SerializedName("fechaCreacion")
    @Expose
    private BigInteger fechaCreacion;

    @SerializedName("descripcion")
    @Expose
    private String descripcion;

    @SerializedName("nombre")
    @Expose
    private String nombre;

    @SerializedName("fechaActividad")
    @Expose
    private BigInteger fechaActividad;

    @SerializedName("curso")
    @Expose
    private CursoDTO curso;
}
