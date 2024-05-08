package com.kosign.onshopapi.controller.auth;

import com.kosign.onshopapi.controller.OnShopRestController;
import com.kosign.onshopapi.payload.auth.SignUpRequest;
import com.kosign.onshopapi.service.auth.AuthRequest;
import com.kosign.onshopapi.service.auth.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/os/v1/auth")
@RequiredArgsConstructor
public class AuthController extends OnShopRestController {

    private final AuthService authService;

    @PostMapping("/signup")
    public Object signup(@RequestBody @Valid SignUpRequest payload) throws Throwable {
        authService.signup(payload);
        return ok();
    }

    @PostMapping("/login")
    public Object login(@RequestBody @Valid AuthRequest payload) throws Throwable {
        return ok(authService.login(payload));
    }

    @GetMapping("/get")
    public Object get() {
        return "hello";
    }


}
