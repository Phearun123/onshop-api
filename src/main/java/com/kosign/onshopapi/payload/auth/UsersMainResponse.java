package com.kosign.onshopapi.payload.auth;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.kosign.onshopapi.common.Pagination;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@JsonInclude
public class UsersMainResponse {

    @JsonProperty("data")
    private List<UsersResponse> usersResponses;

    private Pagination pagination;

    @Builder
    public UsersMainResponse(List<UsersResponse> usersResponses, Page<?> page) {
        this.usersResponses = usersResponses;
        this.pagination = new Pagination(page);
    }
}
