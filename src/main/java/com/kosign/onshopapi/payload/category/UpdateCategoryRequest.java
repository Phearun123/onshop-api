package com.kosign.onshopapi.payload.category;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;

public record UpdateCategoryRequest(

        @JsonProperty("category_nm")
        @NotBlank
        String categoryName

) {
}
