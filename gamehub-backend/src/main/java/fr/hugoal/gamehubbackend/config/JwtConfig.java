package fr.hugoal.gamehubbackend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.security.KeyPair;
import java.security.KeyPairGenerator;

@Configuration
public class JwtConfig {

    @Bean
    public KeyPair keyPair() throws Exception {

        KeyPairGenerator generator =
                KeyPairGenerator.getInstance("RSA");

        generator.initialize(2048);

        return generator.generateKeyPair();
    }
}