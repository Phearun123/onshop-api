package com.kosign.onshopapi.payload.auth;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.kosign.onshopapi.enums.AuthProvider;
import jakarta.validation.constraints.NotBlank;

public record SignUpRequest(
        @JsonProperty("usr_nm")
        @NotBlank
        String username,
        @JsonProperty("pwd")
        @NotBlank
        String password,
        @JsonProperty("eml")
        @NotBlank
        String email,
        @JsonProperty("add")
        @NotBlank
        String address,
        @JsonProperty("phone_num")
        @NotBlank
        String phoneNumber,
        @JsonProperty("role")
        AuthProvider role

) {
}
