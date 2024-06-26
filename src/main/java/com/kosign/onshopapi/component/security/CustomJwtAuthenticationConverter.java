package com.kosign.onshopapi.component.security;


import com.kosign.onshopapi.domain.user.UserRepository;
import com.kosign.onshopapi.payload.auth.SecurityUser;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtClaimNames;
import org.springframework.stereotype.Component;

@Component
public class CustomJwtAuthenticationConverter implements Converter<Jwt, AbstractAuthenticationToken> {
    private final UserRepository userRepository;

    public CustomJwtAuthenticationConverter(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public AbstractAuthenticationToken convert(Jwt jwt) {
        var user = userRepository.findByUserName(jwt.getSubject())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        // Get the issuer of the token to determine the authentication provider (USER, PARTNER)
        var issuer = jwt.getClaimAsString(JwtClaimNames.ISS);

        return new MyUserJwtAuthenticationToken<>(jwt, new SecurityUser(user));
    }

}
