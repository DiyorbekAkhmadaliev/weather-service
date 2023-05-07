package weather.app.weatherservice.security;

import com.google.gson.Gson;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;
import weather.app.weatherservice.model.User;

import java.sql.Date;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Collection;

@Service
@RequiredArgsConstructor
public class JwtService {

    @Value("${security.secret.key}")
    private String secretKey;
    private final Gson gson;

    public String generateToken(String user, Collection<? extends GrantedAuthority> roles){
        return Jwts.builder()
                .setSubject(user)
                .claim("auth", roles)
                .setIssuedAt(Date.from(Instant.now()))
                .setExpiration(Date.from(Instant.now().plus(1, ChronoUnit.DAYS)))
                .signWith(Keys.hmacShaKeyFor(secretKey.getBytes()))
                .compact();
    }

    public String username(String token){
        String json = Jwts.parserBuilder()
                .setSigningKey(Keys.hmacShaKeyFor(secretKey.getBytes()))
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
        return gson.fromJson(json, User.class).getUsername();
    }

    public <T> T getClaim(String token, String claimName, Class<T> type){
        return Jwts.parserBuilder()
                .setSigningKey(Keys.hmacShaKeyFor(secretKey.getBytes()))
                .build()
                .parseClaimsJws(token)
                .getBody()
                .get(claimName, type);
    }

    public boolean validateToken(String token){
        return Jwts.parserBuilder()
                .setSigningKey(Keys.hmacShaKeyFor(secretKey.getBytes()))
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getExpiration().after(Date.from(Instant.now()));
    }
}
