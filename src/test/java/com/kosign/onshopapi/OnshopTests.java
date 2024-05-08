package com.kosign.onshopapi;

import com.kosign.onshopapi.component.security.JwtTokenUtil;
import com.kosign.onshopapi.component.security.UserAuthenticationProvider;
import com.kosign.onshopapi.enums.AuthProvider;
import com.kosign.onshopapi.payload.auth.SignUpRequest;
import com.kosign.onshopapi.service.auth.AuthService;
import com.kosign.onshopapi.utils.PasswordUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest
class OnshopTests {

    @Autowired
    private AuthService authService;
    private PasswordEncoder passwordEncoder;
    private UserAuthenticationProvider userAuthenticationProvider;
    public JwtTokenUtil jwtTokenUtil;

    @Test
    void contextLoads() throws Throwable {
        System.err.println("PWD : " + PasswordUtils.encrypt("Cc12345"));
        SignUpRequest payload = new SignUpRequest("Darith","cFdaQc5J+jgIZ64L0mCHdg==", "Dara@gmail.com", "PP", "0123456789", AuthProvider.ADMIN);
        authService.signup(payload);
    }

}
