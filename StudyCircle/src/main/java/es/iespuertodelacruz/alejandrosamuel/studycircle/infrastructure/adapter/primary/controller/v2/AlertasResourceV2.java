package es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.primary.controller.v2;

import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.primary.dto.AlertaDTO;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.security.UserDetailsLogin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.model.Alerta;
import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.port.primary.IAlertaService;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.primary.mapper.AlertaDTOMapper;
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
    private AlertaDTOMapper mapper;

    @GetMapping
    public ResponseEntity<?> findByUsuario() {
        List<Alerta> alertasUsuario = service.findByUsername(getUsernameUsuario());
        List<AlertaDTO> alertasUsuarioDTO = alertasUsuario.stream().map(mapper::toDTO).toList();
        return ResponseEntity.ok(alertasUsuarioDTO);
    }

    private String getUsernameUsuario() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return ((UserDetailsLogin) principal).getUsername();
    }
}