package com.kosign.onshopapi.service.auth;

import com.kosign.onshopapi.common.api.StatusCode;
import com.kosign.onshopapi.component.security.JwtTokenUtil;
import com.kosign.onshopapi.component.security.UserAuthenticationProvider;
import com.kosign.onshopapi.helper.AuthHelper;
import com.kosign.onshopapi.payload.auth.*;
import com.kosign.onshopapi.domain.user.User;
import com.kosign.onshopapi.domain.user.UserRepository;
import com.kosign.onshopapi.enums.StatusActive;
import com.kosign.onshopapi.exception.BusinessException;
import com.kosign.onshopapi.utils.BaseSpecification;
import com.kosign.onshopapi.utils.PasswordUtils;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.stream.Collectors;

import jakarta.persistence.criteria.Predicate;


@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService{

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserAuthenticationProvider userAuthenticationProvider;
    public final JwtTokenUtil jwtTokenUtil;

    @Override
    public void signup(SignUpRequest payload) throws Throwable{

        var userExist = userRepository.findByUserName(payload.username()).orElse(new User());
        if (StringUtils.isNotEmpty(userExist.getUsername())) {
            throw new BusinessException(StatusCode.USER_NOT_FOUND);
        }

        String rawPwd;
        try {
            rawPwd = passwordEncoder.encode(PasswordUtils.decrypt(payload.password()));
        } catch (Exception e) {
            throw new BusinessException(StatusCode.PASSWORD_MUST_BE_ENCRYPTED);
        }

        var user = User.builder()
                .username(payload.username())
                .password(rawPwd)
                .address(payload.address())
                .email(payload.email())
                .phoneNumber(payload.phoneNumber())
                .role(payload.role())
                .status(StatusActive.ACTIVATE.getValue())
                .build();

        userRepository.save(user);
    }

    @Override
    public Object login(AuthRequest payload) throws Throwable {
//         Perform authentication
        Authentication authenticate = userAuthenticationProvider.authenticate(payload.username(), payload.password());

        SecurityUser securityUser = (SecurityUser) authenticate.getPrincipal();
        // Set the authenticated user in the SecurityContext
        SecurityContextHolder.getContext().setAuthentication(authenticate);
        // Generate JWT token
        String token = jwtTokenUtil.doGenerateToken(securityUser);
        // Return the token in the response
        return new AuthResponse(token, "Bearer", jwtTokenUtil.getExpireIn());
    }

    @Override
    public void update(String id, UpdateUserRequest payload) throws Throwable {

        var user = userRepository.findById(AuthHelper.getUserId()).orElseThrow(()-> new BusinessException(StatusCode.PASSWORD_MUST_BE_ENCRYPTED));

        user.setAddress(payload.address());
        user.setEmail(payload.email());
        user.setPassword(passwordEncoder.encode(PasswordUtils.decrypt(payload.password())));
        user.setUsername(payload.username());
        user.setPhoneNumber(payload.phoneNumber());

        userRepository.save(user);
    }

    @Override
    public Object getUsers(UsersCriteria criteria, Pageable pageable) throws Throwable {

        Page<User> userPage = userRepository.findAll((root, query, criteriaBuilder) -> {
            ArrayList<Predicate> predicates = new ArrayList<>();

            if (criteria != null) {
                if (StringUtils.isNotBlank(criteria.getSearchValue())) {
                    predicates.add(criteriaBuilder.or(
                            criteriaBuilder.like(root.get("username"), BaseSpecification.containUpperCase(criteria.getSearchValue())),
                            criteriaBuilder.like(root.get("username"), BaseSpecification.containLowerCase(criteria.getSearchValue()))
                    ));
                }
            }
            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        }, pageable);

        var data = userPage.stream().map(user ->
                UsersResponse.builder()
                        .address(user.getAddress())
                        .email(user.getEmail())
                        .phoneNumber(user.getPhoneNumber())
                        .username(user.getUsername())
                        .build()
                ).collect(Collectors.toList());

        return UsersMainResponse.builder()
                .usersResponses(data)
                .page(userPage)
                .build();
    }

    @Deprecated
    @Override
    public void delete(DeleteUserRequest payload) throws Throwable {
        payload.userId().forEach(userId -> {
            var user = userRepository.findUserId(userId).orElseThrow(() -> new BusinessException(StatusCode.USER_NOT_FOUND));

            userRepository.delete(user);
        });
    }

}
