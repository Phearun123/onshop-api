package com.kosign.onshopapi.service.auth;

import com.kosign.onshopapi.payload.auth.SignUpRequest;

public interface AuthService {
    void signup(SignUpRequest payload) throws Throwable;
    Object login(AuthRequest payload) throws Throwable;
}
