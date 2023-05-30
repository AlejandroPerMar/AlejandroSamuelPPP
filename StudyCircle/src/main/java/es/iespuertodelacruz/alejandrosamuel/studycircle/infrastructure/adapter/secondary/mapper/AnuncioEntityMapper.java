package es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.mapper;

import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.model.Anuncio;
import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.model.Materia;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.entity.AnuncioEntity;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.entity.MateriaEntity;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigInteger;
import java.util.Date;
import java.util.Objects;

public class AnuncioEntityMapper {

    @Autowired
    private UsuarioEntityMapper usuarioMapper;
    @Autowired
    private MateriaEntityMapper materiaMapper;

    @Autowired
    private EntityJustIdMapper justIdMapper;

    public Anuncio toDomain(AnuncioEntity in) {
        if(Objects.isNull(in)) return null;

        Anuncio anuncio = new Anuncio();
        anuncio.setId(in.getId());
        anuncio.setDescripcion(in.getDescripcion());
        anuncio.setMotivo(in.getMotivo());
        anuncio.setMateria(materiaMapper.toDomain(in.getMateria()));
        anuncio.setUsuario(usuarioMapper.toDomain(in.getUsuario()));
        anuncio.setTitulo(in.getTitulo());
        return anuncio;
    }

    public Anuncio toDomainPost(AnuncioEntity in) {
        if(Objects.isNull(in)) return null;

        Anuncio anuncio = new Anuncio();
        anuncio.setId(in.getId());
        anuncio.setDescripcion(in.getDescripcion());
        anuncio.setMotivo(in.getMotivo());
        anuncio.setMateria(justIdMapper.toDomain(in.getMateria()));
        anuncio.setUsuario(usuarioMapper.toDomain(in.getUsuario()));
        anuncio.setTitulo(in.getTitulo());
        return anuncio;
    }

    public AnuncioEntity toEntity(Anuncio in) {
        if(Objects.isNull(in)) return null;

        AnuncioEntity anuncio = new AnuncioEntity();
        anuncio.setFechaCreacion(new BigInteger(String.valueOf(new Date().getTime())));
        anuncio.setDescripcion(in.getDescripcion());
        anuncio.setMotivo(in.getMotivo());
        anuncio.setMateria(justIdMapper.toEntity(in.getMateria()));
        anuncio.setUsuario(justIdMapper.toEntity(in.getUsuario()));
        anuncio.setTitulo(in.getTitulo());
        return anuncio;
    }
}
