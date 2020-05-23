package se.iuh.e2portal.config.jwt;

import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
@Slf4j
public class JwtTokenProvider {
	
    @Autowired
    private JwtProperties jwtProperties;

    public String generateToken(String id){
        Date now = new Date();
        Date duration = new Date(now.getTime()+jwtProperties.getDuration());
        return Jwts.builder()
                .setSubject(id)
                .setIssuedAt(now).setExpiration(duration)
                .signWith(SignatureAlgorithm.HS512,jwtProperties.getSecretCode()).compact();
    }
    
    public boolean validateToken(String token) {
        try {
            // System.out.println("Validate : " + token);
            Jwts.parser().setSigningKey(jwtProperties.getSecretCode()).parseClaimsJws(token);
            return true;
        } catch (MalformedJwtException ex) {
            log.error("Invalid JWT token");
        } catch (ExpiredJwtException ex) {
            log.error("Expired JWT token");
        } catch (UnsupportedJwtException ex) {
            log.error("Unsupported JWT token");
        } catch (IllegalArgumentException ex) {
            log.error("JWT claims string is empty.");
        }
        return false;
    }
    
    public String getUserIdFormJWT(String token) {
        Claims claims = Jwts.parser().setSigningKey(jwtProperties.getSecretCode()).parseClaimsJws(token).getBody();
        return claims.getSubject();
    }
}
