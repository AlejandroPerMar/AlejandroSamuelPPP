package es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.primary.controller.v3;

import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.model.NivelEstudios;
import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.port.primary.INivelEstudiosService;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.primary.dto.NivelEstudiosDTO;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.primary.mapper.NivelEstudiosDTOMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/api/v3/estudios")
public class NivelesEstudiosResourceV3 {

    @Autowired
    private INivelEstudiosService nivelEstudiosService;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody NivelEstudiosDTO request) {
        NivelEstudiosDTOMapper mapper = new NivelEstudiosDTOMapper();

        NivelEstudios nivelEstudios = nivelEstudiosService.create(mapper.toDomain(request));

        return ResponseEntity.status(HttpStatus.OK).body(mapper.toDTO(nivelEstudios));
    }
}
