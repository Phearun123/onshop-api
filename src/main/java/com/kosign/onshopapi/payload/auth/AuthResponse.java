package com.kosign.onshopapi.payload.auth;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public record AuthResponse(
        String accessToken,
        String tokenType,
        Long expiresIn

) {
}
