package es.iespuertodelacruz.alejandrosamuel.studycircle.infrastructure.security;

import java.security.Key;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import org.springframework.stereotype.Service;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

class KeyGenerator {
	private static KeyGenerator keyGenerator;
	private Key key;
	private KeyGenerator() {
		key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
	}
	public static KeyGenerator getInstance() {
		if(keyGenerator == null)
			keyGenerator = new KeyGenerator();
		return keyGenerator;
	}
	
	public Key getKey() { return key; }
}


@Service
public class JwtService {
	private static final Logger LOGGER = Logger.getLogger(JwtService.class.getName());
	private static final long DURACION_TOKEN = 24 * 60 * 60 * 1000;
	public JwtService() {
	}

	public List<String> extractRoles(String token) {
		Claims claims = extractAllClaims(token);
		List<String> roles = (List<String>) claims.get("authorities");

		LOGGER.info("LOS ROLES DEL USUARIO SON: " + roles.get(0));
		return roles;
	}	
	
	public String extractUsername(String token) {
		Claims claims = extractAllClaims(token);
		String subject = claims.getSubject();
		return subject;
	}

    public String generateToken(String username, List<String> roles) {
    	KeyGenerator keygenerator = KeyGenerator.getInstance();
    	
        String token = Jwts
                .builder()
                .claim("authorities", roles)
                .setSubject(username)
                .setExpiration(getExpirationDate())
                .signWith(keygenerator.getKey())
                .compact();

        return token;
    }

    public Claims getClaims(String token) throws ExpiredJwtException, MalformedJwtException {
    	KeyGenerator keygenerator = KeyGenerator.getInstance();
        return Jwts.parserBuilder()
                .setSigningKey(keygenerator.getKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
                
                
    }

    private Date getExpirationDate() {
        return new Date(System.currentTimeMillis() + DURACION_TOKEN);

    }

	private Claims extractAllClaims(String token) {
		return Jwts
				.parserBuilder()
				.setSigningKey(KeyGenerator.getInstance().getKey())
				.build()
				.parseClaimsJws(token)
				.getBody();
	}
}