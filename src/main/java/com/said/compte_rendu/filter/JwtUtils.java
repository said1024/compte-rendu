package com.said.compte_rendu.filter;
import org.springframework.beans.factory.annotation.Value;
import com.said.compte_rendu.model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.crypto.spec.SecretKeySpec;
import java.time.Instant;
import java.util.Base64;
import java.util.Date;
import java.time.temporal.ChronoUnit;


@Slf4j
@Component
public class JwtUtils {

    public static final String SECRET = "TcwbewNUck4rUkw23JUUSSAe7ryrCJXwCfvLZS84+Vg4Or1WWYDY6kM430aJOvU1KAQQKSs2xFd9ImCE+hocyA==";

    public String createToken(User user) {
        var now = Instant.now();
        var hmacKey = new SecretKeySpec(Base64.getDecoder().decode(SECRET), SignatureAlgorithm.HS256.getJcaName());
        return Jwts.builder().setClaims(user.toMap()).setIssuedAt(Date.from(now))
                .setExpiration(Date.from(now.plus(7 * 30, ChronoUnit.DAYS))).signWith(hmacKey).compact();
    }

    public User parse(String token) {

        final Jwt jwt = Jwts.parserBuilder().setSigningKey(SECRET).build().parse(token);
        var claims = (Claims) jwt.getBody();

        return User.builder()
                .id(Long.parseLong (claims.get("id").toString()))
                .firstName((String) claims.get("firstName"))
                .lastName((String) claims.get("lastName"))

                .build();
    }
}
