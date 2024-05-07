package com.kosign.onshopapi.controller.auth;

import com.kosign.onshopapi.controller.OnShopRestController;
import com.kosign.onshopapi.payload.auth.AuthRequest;
import com.kosign.onshopapi.service.auth.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/os/v1/auth")
@RequiredArgsConstructor
public class AuthController extends OnShopRestController {

    private final AuthService authService;

    @PostMapping("/signup")
    public Object signup(@RequestBody @Valid AuthRequest payload) throws Throwable {
        authService.signup(payload);
        return ok();
    }


}
