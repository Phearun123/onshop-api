package com.kosign.onshopapi.controller.auth;

import com.kosign.onshopapi.controller.OnShopRestController;
import com.kosign.onshopapi.payload.MultiSortBuilder;
import com.kosign.onshopapi.payload.auth.SignUpRequest;
import com.kosign.onshopapi.payload.auth.UpdateUserRequest;
import com.kosign.onshopapi.payload.auth.UsersCriteria;
import com.kosign.onshopapi.service.auth.AuthRequest;
import com.kosign.onshopapi.service.auth.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.*;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;

@RestController
@RequestMapping("/api/os/v1/auth")
@RequiredArgsConstructor
public class AuthController extends OnShopRestController {

    private final AuthService authService;

    @PostMapping("/signup")
    public Object signup(@RequestBody @Valid SignUpRequest payload) throws Throwable {
        authService.signup(payload);
        return ok();
    }

    @PostMapping("/login")
    public Object login(@RequestBody @Valid AuthRequest payload) throws Throwable {
        return ok(authService.login(payload));
    }

    @PutMapping("/{id}")
    public Object update(@PathVariable String id, @RequestBody @Valid UpdateUserRequest payload) throws Throwable {
        authService.update(id, payload);
        return ok();
    }

    @GetMapping("")
    public Object getUser(
            @RequestParam(value = "search_value", required = false) String searchValue,
            @RequestParam(value = "page_size", required = false, defaultValue = "10") Integer pageSize,
            @RequestParam(value = "page_number", required = false, defaultValue = "0") Integer pageNumber,
            @RequestParam(value = "sort_columns", required = false, defaultValue = "username:desc") String sortColumns
    ) throws Throwable {
        List<Sort.Order> sortBuilder = new MultiSortBuilder().with(sortColumns).build();
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(sortBuilder));

        var criteria = UsersCriteria.builder()
                .pageNumber(pageNumber)
                .pageSize(pageSize)
                .searchValue(searchValue)
                .build();

        return ok(authService.getUsers(criteria, pageable));
    }


}
