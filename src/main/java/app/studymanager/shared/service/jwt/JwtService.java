package app.studymanager.shared.service.jwt;

import app.studymanager.modules.user.User;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JwtService {
    private final Algorithm secret;

    public JwtService(JwtProperties jwtProperties) {
        final String secret = jwtProperties.getSecret();
        this.secret = Algorithm.HMAC256(secret);
    }

    public String generate(User user) {
        Date issuedAt = new Date();
        Date expiresAt = DateUtils.addDays(issuedAt, 1);

        return JWT.create()
                .withSubject(user.getEmail())
                .withIssuedAt(issuedAt)
                .withExpiresAt(expiresAt)
                .sign(secret);
    }

    public String getSubject(String token) {
        return JWT.require(secret)
                .build()
                .verify(token)
                .getSubject();
    }
}
