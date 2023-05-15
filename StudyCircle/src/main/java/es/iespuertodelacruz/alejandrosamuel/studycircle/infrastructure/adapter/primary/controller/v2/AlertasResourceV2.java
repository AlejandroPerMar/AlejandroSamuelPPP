package es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.primary.controller.v2;

import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.model.AlertaAmistad;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.primary.dto.AlertaActividadDTO;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.primary.dto.AlertaAmistadDTO;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.primary.mapper.AlertaAmistadDTOMapper;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.security.UserDetailsLogin;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.model.AlertaActividad;
import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.port.primary.IAlertaService;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.primary.mapper.AlertaActividadDTOMapper;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.config.SwaggerConfig;
import io.swagger.annotations.Api;

import java.util.List;

@Api(tags = {SwaggerConfig.ALERTA_V2_TAG})
@RestController
@CrossOrigin
@RequestMapping("/api/v2/alertas")
public class AlertasResourceV2 {

    @Autowired
    private IAlertaService service;

    @Autowired
    private AlertaActividadDTOMapper actividadMapper;

    @Autowired
    private AlertaAmistadDTOMapper amistadMapper;

    @GetMapping("/actividades")
    @ApiOperation(
            value= "Encontrar las alertas de actividades del usuario autenticado",
            notes= """
                    Posibles respuestas:\s
                    • List<AlertaActividadDTO>. Devuelve la lista de alertas correspondiente al usuario, aunque esté vacía
                    """
    )
    public ResponseEntity<?> findAlertasActividadByUsuario() {
        List<AlertaActividad> alertasUsuario = service.findAlertasActividadByUsername(getUsernameUsuario());
        List<AlertaActividadDTO> alertasUsuarioDTO = alertasUsuario.stream().map(actividadMapper::toDTO).toList();
        return ResponseEntity.ok(alertasUsuarioDTO);
    }

    @GetMapping("/amistades")
    @ApiOperation(
            value= "Encontrar las alertas de amistades del usuario autenticado",
            notes= """
                    Posibles respuestas:\s
                    • List<AlertaAmistadDTO>. Devuelve la lista de alertas correspondiente al usuario, aunque esté vacía
                    """
    )
    public ResponseEntity<?> findAlertasAmistadByUsuario() {
        List<AlertaAmistad> alertasUsuario = service.findAlertasAmistadByUsername(getUsernameUsuario());
        List<AlertaAmistadDTO> alertasUsuarioDTO = alertasUsuario.stream().map(amistadMapper::toDTO).toList();
        return ResponseEntity.ok(alertasUsuarioDTO);
    }

    private String getUsernameUsuario() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return ((UserDetailsLogin) principal).getUsername();
    }
}