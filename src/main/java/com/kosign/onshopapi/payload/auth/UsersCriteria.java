package com.kosign.onshopapi.payload.auth;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UsersCriteria {
    private String searchValue;
    private Integer pageNumber;
    private Integer pageSize;

    @Builder
    public UsersCriteria(String searchValue, Integer pageNumber, Integer pageSize) {
        this.searchValue = searchValue;
        this.pageNumber = pageNumber;
        this.pageSize = pageSize;
    }
}
