package mr.vadel.chatwhatsapp.security;

import jakarta.validation.constraints.NotNull;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;

import java.util.stream.Stream;

public class KeycloakJwtAuthenticationConverter implements Converter<Jwt, AbstractAuthenticationToken> {
    @Override
    public AbstractAuthenticationToken convert(@NotNull Jwt source) {
        return new JwtAuthenticationToken(source ,
                Stream.concat(new JwtGrantedAuthoritiesConverter().convert(Jwt.withTokenValue().build()))
                );
    }
}
