package es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.service;

import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.model.Amistad;
import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.port.secondary.IAmistadRepository;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.entity.AlertaEntity;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.entity.AmistadEntity;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.mapper.AmistadEntityMapper;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.repository.AmistadEntityJPARepository;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.enums.EstadosAlerta;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.enums.TiposAlertas;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class AmistadEntityService implements IAmistadRepository {

    @Autowired
    private AmistadEntityJPARepository repository;

    @Autowired
    private AmistadEntityMapper mapper;

    @Override
    public Amistad create(Amistad amistad) {
        AmistadEntity amistadEntity = mapper.toEntityPost(amistad);
        amistadEntity = repository.save(amistadEntity);
        AlertaEntity alertaEntity = new AlertaEntity();
        alertaEntity.setTipo(TiposAlertas.FRIENSHIP_REQUEST.name());
        alertaEntity.setUsuario(amistadEntity.getUsuario1());
        alertaEntity.setEstado(EstadosAlerta.NEW_ALERT.name());
        return mapper.toDomain(amistadEntity);
    }

    @Override
    public Amistad accept(Amistad amistad) {
        return null;
    }

    @Override
    public Amistad findById(Integer id) {
        return mapper.toDomain(Objects.requireNonNull(repository.findById(id).orElse(null)));
    }

    @Override
    public Amistad remove(Amistad amistad) {
        return null;
    }

    @Override
    public Amistad findByUsuarioId(Integer idUsuario) {
        return null;
    }

    @Override
    public Amistad findAmistadByIds(Integer id1, Integer id2) {
        return mapper.toDomain(Objects.requireNonNull(repository.findAmistadByIds(id1, id2).orElse(null)));
    }

}
