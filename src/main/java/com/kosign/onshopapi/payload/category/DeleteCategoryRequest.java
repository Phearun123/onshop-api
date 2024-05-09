package com.kosign.onshopapi.payload.category;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

import java.util.ArrayList;

public record DeleteCategoryRequest (
        @JsonProperty("category_id")
        @NotNull
        @Valid
        ArrayList<Long> categoryId

) {
}
