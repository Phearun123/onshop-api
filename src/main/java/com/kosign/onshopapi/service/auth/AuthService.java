package com.kosign.onshopapi.service.auth;

import com.kosign.onshopapi.payload.auth.AuthRequest;

public interface AuthService {
    void signup(AuthRequest payload) throws Throwable;
}
