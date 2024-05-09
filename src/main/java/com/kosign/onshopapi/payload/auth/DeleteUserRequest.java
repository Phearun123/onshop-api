package com.kosign.onshopapi.payload.auth;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import java.util.ArrayList;

public record DeleteUserRequest (

        @NotNull
        @Valid
        @JsonProperty("user_id")
        ArrayList<Long> userId

) {
}
