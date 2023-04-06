package es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.service;

import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.entity.TokenConfirmacionEntity;
import es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.adapter.secondary.repository.TokenConfirmacionEntityJPARepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.Date;
import java.util.Optional;

@Service
public class TokenConfirmacionEntityService {
    @Autowired
    private TokenConfirmacionEntityJPARepository tokenRepository;

    public TokenConfirmacionEntity saveTokenConfirmacion(TokenConfirmacionEntity token) {
        return tokenRepository.save(token);
    }
    public Optional<TokenConfirmacionEntity> getToken(String token) {
        return tokenRepository.findByToken(token);
    }

    public Integer setConfirmado(String token) {
        return tokenRepository.updateConfirmado(token, new BigInteger(new Date().getTime() + ""));
    }
}
