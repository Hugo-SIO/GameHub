package fr.hugoal.gamehubbackend.services;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.crypto.RSASSASigner;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import fr.hugoal.gamehubbackend.models.User;
import org.springframework.stereotype.Service;

import java.security.KeyPair;
import java.security.interfaces.RSAPrivateKey;
import java.time.Instant;
import java.util.Date;

@Service
public class JwtService {

    private final RSAPrivateKey privateKey;

    public JwtService(KeyPair keyPair) {
        this.privateKey = (RSAPrivateKey) keyPair.getPrivate();
    }

    public String generateToken(User user) {

        Instant now = Instant.now();

        JWTClaimsSet claims = new JWTClaimsSet.Builder()
                .subject(user.getUsername())
                .claim("role", user.getRole())
                .issueTime(Date.from(now))
                .expirationTime(Date.from(now.plusSeconds(3600)))
                .issuer("gamehub-backend")
                .build();

        SignedJWT signedJWT = new SignedJWT(
                new JWSHeader(JWSAlgorithm.RS256),
                claims
        );

        try {
            signedJWT.sign(new RSASSASigner(privateKey));
        } catch (JOSEException e) {
            throw new RuntimeException("Unable to generate JWT", e);
        }

        return signedJWT.serialize();
    }
}