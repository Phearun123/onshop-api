package com.kosign.onshopapi.service.auth;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;

public record AuthRequest(
        @JsonProperty("usr_nm")
        @NotBlank
        String username,
        @JsonProperty("pwd")
        @NotBlank
        String password
) {
}
