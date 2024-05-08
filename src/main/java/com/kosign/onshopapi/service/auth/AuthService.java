package com.kosign.onshopapi.service.auth;

import com.kosign.onshopapi.payload.auth.SignUpRequest;
import com.kosign.onshopapi.payload.auth.UpdateUserRequest;
import com.kosign.onshopapi.payload.auth.UsersCriteria;
import org.springframework.data.domain.Pageable;

public interface AuthService {
    void signup(SignUpRequest payload) throws Throwable;
    Object login(AuthRequest payload) throws Throwable;
    void update(String id, UpdateUserRequest payload) throws Throwable;
    Object getUsers(UsersCriteria criteria, Pageable pageable) throws Throwable;
}
