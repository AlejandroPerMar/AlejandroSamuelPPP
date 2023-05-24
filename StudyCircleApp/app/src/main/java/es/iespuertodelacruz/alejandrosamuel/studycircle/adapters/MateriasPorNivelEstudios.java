package es.iespuertodelacruz.alejandrosamuel.studycircle.adapters;

import java.util.List;

import es.iespuertodelacruz.alejandrosamuel.studycircle.data.rest.dto.MateriaDTO;

public class MateriasPorNivelEstudios {
    private String nombreNivelEstudios;
    private List<MateriaDTO> materiasSeleccionadas;

    public MateriasPorNivelEstudios(String nombreNivelEstudios, List<MateriaDTO> materiasSeleccionadas) {
        this.nombreNivelEstudios = nombreNivelEstudios;
        this.materiasSeleccionadas = materiasSeleccionadas;
    }

    public MateriasPorNivelEstudios() {}


    public List<MateriaDTO> getMateriasSeleccionadas() {
        return materiasSeleccionadas;
    }

    public void setMateriasSeleccionadas(List<MateriaDTO> materiasSeleccionadas) {
        this.materiasSeleccionadas = materiasSeleccionadas;
    }

    public String getNombreNivelEstudios() {
        return nombreNivelEstudios;
    }

    public void setNombreNivelEstudios(String nombreNivelEstudios) {
        this.nombreNivelEstudios = nombreNivelEstudios;
    }
}
