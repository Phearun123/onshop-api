package com.kosign.onshopapi.payload.auth;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.kosign.onshopapi.enums.AuthProvider;
import jakarta.validation.constraints.NotBlank;

public record UpdateUserRequest (
        @JsonProperty("usr_nm")
        @NotBlank(message = "username can't be null")
        String username,
        @JsonProperty("pwd")
        @NotBlank(message = "password can't be null")
        String password,
        @JsonProperty("add")
        @NotBlank(message = "address can't be null")
        String address,
        @JsonProperty("phone_num")
        @NotBlank(message = "phone number can't be null")
        String phoneNumber,
        @JsonProperty("eml")
        @NotBlank(message = "email can't be null")
        String email

) {
}
