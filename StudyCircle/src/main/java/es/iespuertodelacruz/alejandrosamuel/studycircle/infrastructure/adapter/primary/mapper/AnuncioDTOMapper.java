package es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.primary.mapper;

import es.iespuertodelacruz.alejandrosamuel.studycircle.domain.model.Anuncio;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.primary.dto.AnuncioDTO;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Objects;

public class AnuncioDTOMapper {

    @Autowired
    private MateriaDTOMapper materiaMapper;

    @Autowired
    private UsuarioDTOMapper usuarioMapper;

    @Autowired
    private DTOJustIdMapper justIdMapper;

    public Anuncio toDomain(AnuncioDTO in) {
        if(Objects.isNull(in)) return null;

        Anuncio anuncio = new Anuncio();
        anuncio.setId(in.getId());
        anuncio.setDescripcion(in.getDescripcion());
        anuncio.setMotivo(in.getMotivo());
        anuncio.setMateria(justIdMapper.toDomain(in.getMateria()));
        anuncio.setUsuario(justIdMapper.toDomain(in.getUsuario()));
        anuncio.setTitulo(in.getTitulo());
        return anuncio;
    }

    public AnuncioDTO toDTO(Anuncio in) {
        if(Objects.isNull(in)) return null;

        AnuncioDTO anuncio = new AnuncioDTO();
        anuncio.setDescripcion(in.getDescripcion());
        anuncio.setMotivo(in.getMotivo());
        anuncio.setMateria(materiaMapper.toDTO(in.getMateria()));
        anuncio.setUsuario(usuarioMapper.toDTO(in.getUsuario()));
        anuncio.setTitulo(in.getTitulo());
        return anuncio;
    }
}
